/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.EmailSessionBean;
import ejb.MemberFacade;
import ejb.UserFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import model.User;

/**
 *
 * @author rmsor_000
 */
@ManagedBean
@SessionScoped
public class SignupController implements Serializable {

    @Inject
    private EmailSessionBean emailSessionBean;
    @Inject
    private MemberFacade memberFacade;
    @Inject
    private UserFacade userFacade;
    private User user;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String conPassword;

    private String phoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String age;
    private String gender;
    private String profilePic;

    private String extension;

    //for current user id
    private Long id;

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Creates a new instance of SignupController
     */
    public SignupController() {
    }
//    @PostConstruct
//    public void showPostConstructMessage(){
//        FacesMessage facesMessage;
//        facesMessage = new FacesMessage("RegControl EJB Postconstruct Complete");
//        facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
//        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//    }

    public String saveRegInfo() {
        user = userFacade.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setUserName(email);
            user.setProfilePic("test.jpg");
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(emailSessionBean.encryptToSha256(password));
            user.setGender("Male");
            user.setRole("MEMBER");
            user.setRegisteredDate(new Date());
            userFacade.create(user);
            FacesMessage facesMessage = new FacesMessage("Registration Done Successfully");
            facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

            return "login?faces-redirect=true";

        } else {
            //this user already exists so they can not register again
            FacesMessage facesMessage = new FacesMessage("Registration Failed - user already exists");
            facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            System.out.println("Registration failed - user exists: " + user.toString());
        }
        return "signup";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConPassword() {
        return conPassword;
    }

    public void setConPassword(String conPassword) {
        this.conPassword = conPassword;
    }

    public MemberFacade getMemberFacade() {
        return memberFacade;
    }

    public void setMemberFacade(MemberFacade memberFacade) {
        this.memberFacade = memberFacade;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    //to go edit profile page
    public String editProfile() {

        HttpServletRequest hsr = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String userName = ec.getRemoteUser();

        user = userFacade.findByEmail(userName);

        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setEmail(user.getEmail());
        setAddressLine1(user.getAddressLine1());
        setAddressLine2(user.getAddressLine2());
        setAge(user.getAge());
        setGender(user.getGender());
        setPhoneNumber(user.getPhoneNumber());
        setProfilePic(user.getProfilePic());
        setId(user.getId());
        return "editprofile?faces-redirect=true";

    }

    //to update user profile
    public String updateUserRegInfo() throws IOException {

        String st = "success";
        if (getFile1() != null) {
            st = upload();
        }
        if (st.equalsIgnoreCase("success")) {

            String fileName = "";

            if (getFile1() != null) {
                fileName = File.separator + "faces" + File.separator + "resources"
                        + File.separator + "profile_pic" + File.separator + id + "." + extension;
            }

            userFacade.updateProfile(id, email, firstName, lastName,
                    emailSessionBean.encryptToSha256(password), phoneNumber, addressLine1, addressLine2,
                    gender, age, fileName);

            FacesMessage facesMessage = new FacesMessage("Updated User Profile Successfully");
            facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

            return "dashboard?faces-redirect=true";

        } else {
            return null;
        }

    }

    private Part file1;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public String upload() throws IOException {

        InputStream inputStream = null;
        OutputStream outputStream = null;
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context
                .getExternalContext().getContext();
        String path = servletContext.getRealPath("");
        boolean file1Success = false;

        String fileName = "";

        if (file1.getSize() > 0) {
            fileName = getFileNameFromPart(file1);
            /**
             * destination where the file will be uploaded
             */
            File outputFile = new File(path + File.separator + "resources"
                    + File.separator + "profile_pic" + File.separator + fileName);
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

        File oldfile = new File(path + File.separator + "resources"
                + File.separator + "profile_pic" + File.separator + fileName);

        extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        File newfile = new File(path + File.separator + "resources"
                + File.separator + "profile_pic" + File.separator + id + "." + extension);

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
}
