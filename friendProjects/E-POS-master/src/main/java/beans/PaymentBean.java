/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import boundaries.PaymentFacade;
import boundaries.PaymentTypeFacade;
import entities.Payment;
import entities.PaymentType;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import javax.inject.Named;
import javax.xml.ws.WebServiceRef;

import webservice_am.CardvalidatorAm_Service;
import webservice_ms.CardvalidationMs_Service;
import webservicevisa.CardValidatorVisa_Service;

/**
 *
 * @author MDZahidul
 */
@Named("pmb")
@RequestScoped
public class PaymentBean implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/WebService/Cardvalidator_am.wsdl")
    private CardvalidatorAm_Service service_3;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/WebService/Cardvalidation_ms.wsdl")
    private CardvalidationMs_Service service_2;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/WebService/CardValidator_Visa.wsdl")
    private CardValidatorVisa_Service service_1;

   

    @EJB
    private PaymentTypeFacade paymentTypeFacade;

    @EJB

    private PaymentFacade paymentFacade;

    private Payment pm;
    private PaymentType pmt;
    private String card_type;
    private String service_message;
    private String card_number;
    private String card_holder;
    private String card_expiray;
    private String cvc;
    private double totatlAmount;
    private int order_id;
    AutoCompleteView autoComplete;
    private UIInput totalAmountUI;
    private UIInput orderIdUI;
    private String cardType;
    //FacesContext fc = FacesContext.getCurrentInstance();

    public PaymentBean() {

//        autoComplete = (AutoCompleteView) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("autoCompleteView");
//        Map<String, String> params
//                = fc.getExternalContext().getRequestParameterMap();
//        totatlAmount = Double.parseDouble(params.get("totalAmount"));
//        order_id = Integer.parseInt(params.get("id"));
//        System.out.println(totatlAmount);
//        System.out.println(order_id);
        pm = new Payment();
        pmt = new PaymentType();
    }

    public Payment getPm() {
        return pm;
    }

    public void setPm(Payment pm) {
        this.pm = pm;
    }

    public PaymentType getPmt() {
        return pmt;
    }

    public void setPmt(PaymentType pmt) {

        this.pmt = pmt;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("PaymentType", this.pmt);
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("PaymentType", this.card_type);

    }

    public String getService_message() {
        return service_message;
    }

    public void setService_message(String service_message) {
        this.service_message = service_message;
    }

    public String getCard_number() {
        return card_number;
    }

    public String getCard_holder() {
        return card_holder;
    }

    public void setCard_holder(String card_holder) {
        this.card_holder = card_holder;
    }

    public String getCard_expiray() {
        return card_expiray;
    }

    public void setCard_expiray(String card_expiray) {
        this.card_expiray = card_expiray;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public UIInput getOrderIdUI() {
        return orderIdUI;
    }

    public void setOrderIdUI(UIInput orderIdUI) {
        this.orderIdUI = orderIdUI;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("CardType", this.cardType);
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("card_number", this.card_number);
        System.out.println("setting card number is:");
        System.out.println(this.card_number);
    }

    public double getTotatlAmount() {
        return totatlAmount;
    }

    public void setTotatlAmount(double totatlAmount) {
        this.totatlAmount = totatlAmount;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public UIInput getTotalAmountUI() {
        return totalAmountUI;
    }

    public void setTotalAmountUI(UIInput totalAmountUI) {
        this.totalAmountUI = totalAmountUI;
    }

    public String makePayment() {
        String types = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("CardType");
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println(totalAmountUI.getId() + " - " + totalAmountUI.getValue());
        System.out.println(orderIdUI.getId() + " - " + orderIdUI.getValue());
        Date date = new Date();
//        String trans_date=dateFormat.format(date);
        //System.out.println(totatlAmount);
        pmt.setType(types);
        paymentTypeFacade.create(pmt);
        pm.setOrder_id(Integer.parseInt(orderIdUI.getValue().toString()));
        pm.setStatus("sucess");
        pm.setTransaction_date(date);
        pm.setPayment_type(pmt);
        pm.setAmount(Double.parseDouble(totalAmountUI.getValue().toString()));

        paymentFacade.create(pm);

//        autoComplete.dataTableProductList.clear();
////        autoComplete.setMessage(null);
////        autoComplete.setOrder(null);
//        autoComplete.orderLineList.clear();
//         autoComplete.setProduct(null);
//        autoComplete.setTotalAmount(0.0);
        return "order";
    }

    public void cardValidation() {
        String crnumber = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("card_number");
        String types = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("CardType");
        System.out.println(crnumber);
        System.out.println(types);
        System.out.println(pmt.getType());
        if (types.equals("visa")) {
            visavalidation();
        }

        if (types.equals("master")) {
            msvalidation();
        }
        if (types.equals("americanexpress")) {
            amvalidation();
        }

    }

    private String visavalidator(java.lang.String cardnumber) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        webservicevisa.CardValidatorVisa port = service_1.getCardValidatorVisaPort();
        return port.visavalidator(cardnumber);
    }

    private String mastercardvalidator(java.lang.String cardnumber) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        webservice_ms.CardvalidationMs port = service_2.getCardvalidationMsPort();
        return port.mastercardvalidator(cardnumber);
    }

    private String amvalidation(java.lang.String cardnumber) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        webservice_am.CardvalidatorAm port = service_3.getCardvalidatorAmPort();
        return port.amvalidation(cardnumber);
    }

    public void visavalidation() {
        String crnumber = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("card_number");
        if (visavalidator(crnumber).equals("success")) {
            service_message = "OK";
            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage("Successful", "Your message: " + service_message));
            context.addMessage(null, new FacesMessage("Second Message", "You can procedd to checkout"));

        } else {

//            FacesContext context = FacesContext.getCurrentInstance();
//            UIInput card_number_ui = (UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent("paymentForm:cardNumber");
//            System.out.print(card_number_ui.getId().);
//            card_number_ui.setValue(null);
            //card_number_ui.setLocalValueSet(false);
            service_message = "invalid info";
            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Card Information", "Please re enter the information"));

            card_holder = "";
            card_number = "";
            card_expiray = "";
            cvc = "";

        }
    }

    public void msvalidation() {
        String crnumber = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("card_number");
        if (mastercardvalidator(crnumber).equals("success")) {
            service_message = "OK";
            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage("Successful", "Your message: " + service_message));
            context.addMessage(null, new FacesMessage("Second Message", "You can procedd to checkout"));

        } else {

//            FacesContext context = FacesContext.getCurrentInstance();
//            UIInput card_number_ui = (UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent("paymentForm:cardNumber");
//            System.out.print(card_number_ui.getId().);
//            card_number_ui.setValue(null);
            //card_number_ui.setLocalValueSet(false);
            service_message = "invalid info";
            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Card Information", "Please re enter the information"));

            card_holder = "";
            card_number = "";
            card_expiray = "";
            cvc = "";

        }
    }

    public void amvalidation() {
        String crnumber = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("card_number");
        if (amvalidation(crnumber).equals("success")) {
            service_message = "OK";
            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage("Successful", "Your message: " + service_message));
            context.addMessage(null, new FacesMessage("Second Message", "You can procedd to checkout"));

        } else {

//            FacesContext context = FacesContext.getCurrentInstance();
//            UIInput card_number_ui = (UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent("paymentForm:cardNumber");
//            System.out.print(card_number_ui.getId().);
//            card_number_ui.setValue(null);
            //card_number_ui.setLocalValueSet(false);
            service_message = "invalid info";
            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Card Information", "Please re enter the information"));

            card_holder = "";
            card_number = "";
            card_expiray = "";
            cvc = "";

        }
    }

}
