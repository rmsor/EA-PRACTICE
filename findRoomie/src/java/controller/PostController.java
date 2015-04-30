/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.PostFacade;
import ejb.UserFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Comment;
import model.Post;
import model.User;

/**
 *
 * @author Ashok Subedi
 */
@ManagedBean
@RequestScoped
public class PostController extends BaseController {

    @EJB
    private PostFacade postFacade;
    @EJB
    private UserFacade userFacade;
    private List<Post> allPosts;
    private Post myPost;
    private Comment comment;

    private String extension;

    String fileName = "";

    /**
     * Creates a new instance of PostController
     */
    public PostController() {

        System.out.println("inside the post controller constructor");
        this.myPost = new Post();
    }

    public Post getPost() {
        return myPost;
    }

    public void setPost(Post p) {
        this.myPost = p;
    }

    public String postThisPost() throws IOException {

        String st = upload();

        String email = getEc().getRemoteUser();
        User user = userFacade.findByEmail(email);

        String fileName = File.separator + "faces" + File.separator + "resources"
                + File.separator + "room_pics" + File.separator + user.getId() + myPost.getImages();

        myPost.setImages(fileName);
        myPost.setPostedBy(user);
        myPost.setPostStatus("open");
        this.postFacade.create(myPost);
        System.out.println("okhere");
//        return "listmyrooms?faces-redirect=true";
        return "dashboard?faces-redirect=true";

    }

    public String makePost() {
        return "addNewPost";
    }

    public String showPosts() {
        /**
         * We could do List<Post> allPosts = posts.findAll();
         *
         * and then simply
         *
         * return "showPosts";
         *
         * In showPosts.xhtml, we can easily have allPosts available through the
         * postController bean BUT this will cause the servlet to only forward
         * the request not redirect.
         *
         * And if we redirect, the bean will not be available. We will need
         * flash scope to survive a redirect.
         *
         * More: maxkatz.org/2010/07/27/learning-jsf2-using-flash-scope/
         */
        HttpSession session = (HttpSession) getEc().getSession(false);

        allPosts = postFacade.findAllByUser((User) session.getAttribute("userObj"));
        getFlash().put("posts", allPosts);

        return "showPosts?faces-redirect=true";
    }

    public String roomDetails() {

        return "roomDetails?faces-redirect=true";
    }

    public Comment getComment() {
        return comment;
    }

    public void preRenderView(String postId) {
        System.out.println("postId: " + postId);

        Post post = postFacade.find(Long.parseLong(postId));

        System.out.println("post.title: " + post.getTitle());

        getFlash().put("post", post);
        setPost(post);

    }

    public String submitComment() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, this.comment.getDescription());

        System.out.println("inside the submit commment");

        return "dashboard.xhtml?faces-redirect=true";
    }

    //to update user profile
    public String updatePostInfo() throws IOException {

        String st = "success";
        if (getFile1() != null) {
            st = upload();
        }
        if (st.equalsIgnoreCase("success")) {

            String email = getEc().getRemoteUser();
            User user = userFacade.findByEmail(email);

            String fileName = "";

            if (getFile1() != null) {

                fileName = File.separator + "faces" + File.separator + "resources"
                        + File.separator + "room_pics" + File.separator + user.getId() + myPost.getImages();
            }

            postFacade.updatePost(myPost.getId(), myPost.getTitle(),
                    myPost.getTotalRooms(),
                    myPost.getCurrentHolders(),
                    myPost.getAddressStreet(), myPost.getAddressCity(),
                    myPost.getAddressState(),
                    myPost.getRoomDescription(),
                    myPost.getExpectedRoomieNumber(), myPost.getPricePerMonth(),
                    myPost.getRequiredGender(), myPost.getRequiredCountry(),
                    myPost.getMinimumAge(),
                    myPost.getMaximumAge(), myPost.getRommieQualities(),
                    fileName, myPost.getPostStatus());

            FacesMessage facesMessage = new FacesMessage("Updated Post Successfully");
            facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

            return "dashboard?faces-redirect=true";

//            System.out.println(myPost.getId()+"\t"+ myPost.getTitle()+"\t"+ 
//                    myPost.getTotalRooms()+"\t"+
//                    myPost.getCurrentHolders()+"\t"+
//                    myPost.getAddressStreet()+"\t"+ myPost.getAddressCity()+"\t"+
//                    myPost.getAddressState()+"\t"+
//                    myPost.getRoomDescription()+"\t"+
//                    myPost.getExpectedRoomieNumber()+"\t"+ myPost.getPricePerMonth()+"\t"+
//                    myPost.getRequiredGender()+"\t"+ myPost.getRequiredCountry()+"\t"+
//                    myPost.getMinimumAge()+"\t"+
//                    myPost.getMaximumAge()+"\t"+ myPost.getRommieQualities()+"\t"+
//                    myPost.getImages());
        } else {
            return null;
        }

    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String upload() throws IOException {

        InputStream inputStream = null;
        OutputStream outputStream = null;
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context
                .getExternalContext().getContext();
        String path = servletContext.getRealPath("");
        boolean file1Success = false;

        Part file1 = getFile1();

        if (file1.getSize() > 0) {
            fileName = getFileNameFromPart(file1);
            /**
             * destination where the file will be uploaded
             */
            File outputFile = new File(path + File.separator + "resources"
                    + File.separator + "room_pics" + File.separator + fileName);
            inputStream = file1.getInputStream();
            outputStream = new FileOutputStream(outputFile);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = 0;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            file1Success = true;
        }

        if (file1Success) {
            System.out.println("File uploaded to : " + path);
            /**
             * set the success message when the file upload is successful
             */
            setMessage("File successfully uploaded to " + path);
        } else {
            /**
             * set the error message when error occurs during the file upload
             */
            setMessage("Error, select atleast one file!");
        }

        myPost.setImages(fileName);

        String email = getEc().getRemoteUser();
        User user = userFacade.findByEmail(email);

        File oldfile = new File(path + File.separator + "resources"
                + File.separator + "room_pics" + File.separator + fileName);

        extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        File newfile = new File(path + File.separator + "resources"
                + File.separator + "room_pics" + File.separator + user.getId() + fileName);

        if (oldfile.renameTo(newfile)) {
            System.out.println("Rename succesful");
            return "success";

        } else {
            System.out.println("Rename failed");

            return "fail";
        }

    }

    public static String getFileNameFromPart(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                String fileName = content.substring(content.indexOf('=') + 1)
                        .trim().replace("\"", "");
                return fileName;
            }
        }
        return null;
    }

    public static final int BUFFER_SIZE = 3000000;

    public void validateFile(FacesContext con, UIComponent comp, Object value) {
        Part p = (Part) value;

        if (p != null) {
            List<FacesMessage> list = new ArrayList<>();
            if (p.getSize() == 0) {
                list.add(new FacesMessage("File Size too small"));
            }
            if (p.getSize() > BUFFER_SIZE) {
                list.add(new FacesMessage("File Size too Big"));
            }

            if (!("image/png".equals(p.getContentType()) || "image/jpeg".equals(p.getContentType()))) {
                list.add(new FacesMessage("not an image file"));
            }

            if (!list.isEmpty()) {
                throw new ValidatorException(list);
            }

        }
    }

    private Part file1;

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public List<Comment> getAllComments() {
        return this.myPost.getComments();
    }

}
