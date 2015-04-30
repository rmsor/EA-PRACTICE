/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import model.Post;
import model.User;

/**
 *
 * @author Ashok Subedi
 */
@Stateless
public class PostFacade extends AbstractFacade<Post> {

    @PersistenceContext(unitName = "findRoomiePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostFacade() {
        super(Post.class);
    }

    public List<Post> findAllByUser(User userObj) {
        /**
         * We don't need to have userObj passed here.We can simply fetch it
         * again from the FacesContext object.
         */

        List<Post> posts = null;

        try {
            User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userObj");
            System.out.println("user.email: " + user.getEmail());

            String jpql = "SELECT p from Post p where p.postedBy = :user";
            Query query = getEntityManager().createQuery(jpql);
            query.setParameter("user", userObj);

            posts = query.getResultList();

        } catch (Exception e) {

            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }

        return posts;
    }

    public List<Post> search(String city, String state, String gender, Double price, Integer numberOfRooms) {
        List<Post> posts = new ArrayList<>();

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Post> cq = cb.createQuery(Post.class);
        Root<Post> post = cq.from(Post.class);

        cq.select(post);

        List<Predicate> predicates = new ArrayList<Predicate>();

        
        if (city != null) {
            predicates.add(cb.like(
                    cb.upper(post.get("addressCity")), "%" + city.toUpperCase() + "%")
            );
        }

        if (state != null) {
            predicates.add(cb.equal(
                    cb.upper(post.get("addressState")), "%" + state.toUpperCase() + "%"));
        }

        if (gender != null) {
            predicates.add(cb.equal(post.get("requiredGender"), gender));
        }

        if (price != null) {
            predicates.add(cb.equal(post.get("pricePerMonth"), price));
        }

        if (numberOfRooms != null) {
            predicates.add(cb.equal(post.get("totalRooms"), numberOfRooms));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Post> q = getEntityManager().createQuery(cq);

        return q.getResultList();
    }

    //update post
    public int updatePost(Long post_id, String title, int totalRooms, int currentHolders,
            String addressStreet, String addressCity, String addressState,
            String roomDescription,
            int expectedRoomieNumber, double pricePerMonth,
            String requiredGender, String requiredCountry, int minimumAge,
            int maximumAge, String rommieQualities, String images, String postStatus) {

        String jpql = "";

        if (images.length() > 1) {
            jpql = "UPDATE Post SET title= :title, "
                    + "totalRooms= :totalRooms , currentHolders= :currentHolders"
                    + " ,addressStreet = :addressStreet, "
                    + "addressCity=:addressCity, addressState=:addressState, "
                    + "roomDescription=:roomDescription, expectedRoomieNumber=:expectedRoomieNumber,"
                    + " pricePerMonth=:pricePerMonth,"
                    + "requiredGender=:requiredGender,"
                    + "requiredCountry=:requiredCountry,"
                    + "minimumAge=:minimumAge,"
                    + "maximumAge=:maximumAge,"
                    + "images=:images,"
                    + "rommieQualities=:rommieQualities,"
                    + "postStatus= :postStatus "
                    + " WHERE id = :post_id";

        } else {

            jpql = "UPDATE Post SET title= :title, "
                    + "totalRooms= :totalRooms , currentHolders= :currentHolders"
                    + " ,addressStreet = :addressStreet, "
                    + "addressCity=:addressCity, addressState=:addressState, "
                    + "roomDescription=:roomDescription, expectedRoomieNumber=:expectedRoomieNumber,"
                    + " pricePerMonth=:pricePerMonth,"
                    + "requiredGender=:requiredGender,"
                    + "requiredCountry=:requiredCountry,"
                    + "minimumAge=:minimumAge,"
                    + "maximumAge=:maximumAge,"
                    + "rommieQualities=:rommieQualities,"
                    + "postStatus= :postStatus "
                    + " WHERE id = :post_id";
        }

        Query query = em.createQuery(jpql, Post.class);

        query.setParameter("post_id", post_id);
        query.setParameter("title", title);
        query.setParameter("totalRooms", totalRooms);
        query.setParameter("currentHolders", currentHolders);
        query.setParameter("addressStreet", addressStreet);
        query.setParameter("addressCity", addressCity);
        query.setParameter("addressState", addressState);
        query.setParameter("roomDescription", roomDescription);
        query.setParameter("expectedRoomieNumber", expectedRoomieNumber);
        query.setParameter("pricePerMonth", pricePerMonth);
        query.setParameter("requiredGender", requiredGender);
        query.setParameter("requiredCountry", requiredCountry);
        query.setParameter("minimumAge", minimumAge);
        query.setParameter("maximumAge", maximumAge);
        query.setParameter("rommieQualities", rommieQualities);

        if (images.length() > 1) {
            query.setParameter("images", images);
        }

        query.setParameter("postStatus", postStatus);

        return query.executeUpdate();
    }
    
    

}
