package beans;

import boundaries.OrderInfoFacade;
import boundaries.OrderLineFacade;
import boundaries.ProductFacade;
import entities.OrderInfo;
import entities.OrderLine;
import entities.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.enterprise.context.RequestScoped;
//import javax.ejb.Singleton;
//import javax.ejb.Stateful;
//import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

//@Stateless
@ManagedBean(name="autoCompleteView")
//@ViewScoped
@ViewScoped
public class AutoCompleteView implements Serializable {
    @EJB
    private OrderInfoFacade orderInfoFacade;
    @EJB
    private OrderLineFacade orderLineFacade;
    @EJB
    private ProductFacade productFacade;
    
    
    
    
    OrderInfo order = new OrderInfo();
    
    //private OrderLine orderLine = new OrderLine();
    private List<OrderLine> orderLineList;
    
    private String message = "";

    
    private double totalAmount;
    private static List<Product> dbProductList;
    private Product product = new Product();
    public List<Product> dataTableProductList = new ArrayList<Product>();

    
    
    
    public AutoCompleteView() {
        
    }

    public String getMessage() {
	        return message;
	    }
	 
	    public void setMessage(String message) {
	        this.message = message;
	    }

    
    
    
    public static void removeViewScopedBean(String beanName) 
    {
      FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(beanName);
    }
    
    public void deleteAction(OrderLine orderLine){        
        //totalAmount = totalAmount - (orderLine.getQuantity()*orderLine.getProduct().getSelling_price());
        order.removeOrderLine(orderLine);
        orderLineList.remove(orderLine);
        ajax();
        //System.out.println((String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("SessionEmployeeRole"));
    }

    public OrderInfo getOrder() {
        return order;
    }

    public void setOrder(OrderInfo order) {
        this.order = order;
    }
    
    @Remove
    public String payNow(){
        order.setCustomer(null);
        order.setTax(0);
        
        Iterator it = orderLineList.iterator();
        double total = 0;
        while(it.hasNext()){
            OrderLine ol = (OrderLine)it.next();            
            total += ol.getProduct().getSelling_price()*ol.getQuantity();
        }
        
        order.setTotalPrice(total);
        orderInfoFacade.create(order);
        it = orderLineList.iterator();
        while(it.hasNext()){
            OrderLine ol = (OrderLine)it.next();
            System.out.println("order: "+order);
            ol.setOrder(order);
            orderLineFacade.edit(ol);
            
        }
        
        //removeViewScopedBean("autoCompleteView");
        
       // return "payment";
        return "/faces/payment.xhtml?faces-redirect=true&includeViewParams=true";
        //return "/faces/foo.xhtml?faces-redirect=true&includeViewParams=true";
    }
    
    public void addProduct(){
        if(orderLineList == null)
            orderLineList = new ArrayList<OrderLine>();
        OrderLine ol = new OrderLine();                
        ol.setProduct(product);
        ol.setOrder(null);
        ol.setQuantity(0);
        ol.setSubtotal(ol.getQuantity()*(ol.getProduct().getSelling_price()));
        System.out.println("ol.getQuantity()*(ol.getProduct().getSelling_price()): "+ol.getQuantity()+"...."+(ol.getProduct().getSelling_price()));
        //orderLineFacade.create(ol);
        //System.out.println("............................######################$$$$$$$$$$$$$$");
        totalAmount = totalAmount + (ol.getQuantity()*ol.getProduct().getSelling_price());
        orderLineList.add(ol);
        order.addOrderLine(ol);   
        //System.out.println("totalAmount: "+totalAmount);
    }
    
    public void ajax(){
        Iterator it = orderLineList.iterator();
        totalAmount = 0;
        while(it.hasNext()){
            OrderLine ol = (OrderLine)it.next();
            totalAmount += ol.getQuantity()*ol.getProduct().getSelling_price();
            System.out.println(""+ol.getQuantity());
        }
    }
    

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double total) {
        this.totalAmount = total;
    }
    
    
    /*
    public OrderLine getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(OrderLine orderLine) {
        this.orderLine = orderLine;
    }
    */

    public List<OrderLine> getOrderLineList() {
        return orderLineList;
    }

    public void setOrderLineList(List<OrderLine> orderLineList) {
        this.orderLineList = orderLineList;
    }
    
    
    
    

    public List<Product> completeProduct(String query) {
        System.out.println("I am at completeProduct");
        List<Product> results = new ArrayList<Product>();
        dbProductList = productFacade.findAll();
        for (Product e : dbProductList) {
            if (e.getProduct_name().toLowerCase().startsWith(query)) {
                results.add(e);
            }
        }
        return results;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public static List<Product> getDbSource() {
        return dbProductList;
    }

    

    public static void setDbProductList(List<Product> dbProductList) {
        AutoCompleteView.dbProductList = dbProductList;
    }

    public List<Product> getDataTableProductList() {
        return dataTableProductList;
    }

    public void setDataTableProductList(List<Product> dataTableProductList) {
        this.dataTableProductList = dataTableProductList;
    }

    /*public void onItemSelect(SelectEvent event) {
        dataTableProductList.add(getProduct());
    }*/
    
/*
    @Override
    public String toString() {
    return "AutoCompleteView{" + "orderLineFacade=" + orderLineFacade + ", productFacade=" + productFacade + ", orderLine=" + orderLine + ", orderLineList=" + orderLineList + ", product=" + product + ", dataTableProductList=" + dataTableProductList + '}';
    }
     */

    @Override
    public String toString() {
        return "AutoCompleteView{" + "orderInfoFacade=" + orderInfoFacade + ", orderLineFacade=" + orderLineFacade + ", productFacade=" + productFacade + ", order=" + order + ", orderLineList=" + orderLineList + ", message=" + message + ", totalAmount=" + totalAmount + ", product=" + product + ", dataTableProductList=" + dataTableProductList + '}';
    }
    
    

}