/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.beans;

import com.ea.promed.entities.Appointment;
import com.ea.promed.entities.Client;
import com.ea.promed.entities.Client;
import com.ea.promed.entities.Patient;
import com.ea.promed.entities.User;
import com.ea.promed.facades.ClientFacade;
import com.ea.promed.facades.UserFacade;
import com.ea.promed.util.Email;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.mail.MessagingException;

/**
 *
 * @author kunda_000
 */
@ManagedBean
@RequestScoped
public class ClientBean extends AbstractBean {

    
    @EJB
    private ClientFacade clientFacade;
    
    @EJB
    private UserFacade userFacade;
    
    @EJB
    private Email emailBean;
    
    
    private Client client;

    
    
    public ClientBean() {
          
    }

    public Client getClient() {
        if(client == null)
        {
            client = new Client();
            client.setUser(new User());
        }
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    
    
    
    public String createClientByAdmin() throws MessagingException, UnsupportedEncodingException
    {
        
        if(client.getId() != null)
        {
            Client eClient = (Client) sessionMap.get("eClient");
            
            client.setUser(eClient.getUser());
            
            clientFacade.edit(client);
            
            sessionMap.put("message", "Client Info updated Successfully.");
            
        }else{
        
        
            String code = UUID.randomUUID().toString();

            String hash = Hashing.sha256().hashString(code, Charsets.UTF_8).toString();

            client.getUser().setVerification(hash);
            
            client.getUser().setPassword(hash);

            clientFacade.create(client);

            userFacade.activateUser(client.getUser(), 4);

            emailBean.setToemail(client.getUser().getEmail());
            emailBean.setSubject("Login Credentials : Pro Medical Services");
            emailBean.setMessagetext(client.getFirstName(),"Your user name is " +client.getUser().getUsername()+ ". You can create new password by clicking below link.",hash);

            emailBean.sendEMail();

            sessionMap.put("message", "Client Info added Successfully.");
            
        }
        
        
        return "clients?faces-redirect=true";
    }
    
    
    
    public void editClient(String clientid) throws IOException
    {
        Long did = Long.parseLong(clientid);
        client = clientFacade.find(did);
        
        if(client != null)
        {
            sessionMap.put("eClient", client);
        }
    }
    
    
    
    
    
    public String createClient() throws MessagingException, UnsupportedEncodingException
    {
        
        String hash = Hashing.sha256().hashString(client.getUser().getPassword(), Charsets.UTF_8).toString();
        
        client.getUser().setVerification(hash);
        client.getUser().setPassword(hash);
        clientFacade.create(client);
        
        emailBean.setToemail(client.getUser().getEmail());
        emailBean.setSubject("Email Verification from Pro Medical Services");
        emailBean.setMessagetext(client.getFirstName(),hash);
        
        emailBean.sendEMail();
        
        sessionMap.put("message", "Registration successfull. Please check your email to verify");
        
        return "dashboard/index?faces-redirect=true";
    }
    
    
    
    
    public List<Patient> loadPatientsFromClient()
    {
        Client cClient = (Client) sessionMap.get("cClient");
        return clientFacade.getMyPatients(cClient);
    }
    
    
    public List<Client> listAllClients()
    {
        return clientFacade.listAllClients();
    }
    
    public List<Appointment> listAppointments()
    {
        Client cClient = (Client) sessionMap.get("cClient");
        return clientFacade.listAppointments(cClient);
    }
    
    
}
