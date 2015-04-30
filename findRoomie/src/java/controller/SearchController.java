package controller;

import commonEnums.States;
import ejb.PostFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Post;

/**
 *
 * @author xtrememe
 */
@ManagedBean
@RequestScoped
public class SearchController extends BaseController{
    @EJB
    private PostFacade postFacade;
    private String selectedState;
    private String selectedCity;
    private Double price;
    private Integer noOfRooms;
    private String gender;
    private List<Post> allPosts;

    
    /**
     * Creates a new instance of SearchController
     */
    public SearchController() {
        
    }
    
    
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(Integer noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getSelectedState() {
        return selectedState;
    }

    public void setSelectedState(String state) {
        this.selectedState = state;
    }

    public String getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(String city) {
        this.selectedCity = city;
    }
    
    
    public States[] getStates(){
        return States.values();
    }
    
    
    public List<Post> getAllPosts(){
        return allPosts;
    }
    
    public String listRooms(){
        //selectedCity = selectedCity.equals("") ? null : selectedCity;
       // gender = (gender != null && gender.equalsIgnoreCase("any")) ? null : gender;
        
        
        allPosts = postFacade.search(selectedCity, selectedState, gender, price, noOfRooms);
        
        getFlash().put("results", allPosts);
        
        
        return "listMyRooms?faces-redirect=true";
    }
    
    
    public String search(){
        System.out.println("selectedCity: " + selectedCity);
        System.out.println("selectedState: " + selectedState);
        selectedCity = "".equals(selectedCity) ? null : selectedCity;
        
        
        getFlash().put("results", postFacade.search(selectedCity, selectedState, null, null, null));
        
        return "searchResults?faces-redirect=true";
    }
    
    
//    public void preRenderView(String postId){
//        System.out.println("postId: " + postId);
//        
//        Post post = postFacade.find(Long.parseLong(postId));
//        
//        System.out.println("post.title: " + post.getTitle());
//        
//        getFlash().put("post", post);
//        //setPost(post);
//        
//    
//    }
    
    
    public String roomDetails(){
        
        return "roomDetails?faces-redirect=true";
    }
    
    
}
