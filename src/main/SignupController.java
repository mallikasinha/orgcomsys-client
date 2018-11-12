/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.jfoenix.controls.JFXTextField;
import com.orgcomsys.inter.Authentication;
import com.orgcomsys.model.Company;
import com.orgcomsys.model.Response;
import com.orgcomsys.model.User;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Mallika
 */
public class SignupController implements Initializable {

    @FXML
    private JFXTextField companyName;
    @FXML
    private JFXTextField companyAddress;
    @FXML
    private JFXTextField phoneNo;
    @FXML
    private JFXTextField panNo;
    @FXML
    private JFXTextField mailingAddress;
    
    private User user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void setUser(User user){
        this.user = user;
    }

    @FXML
    private void onSignUp(ActionEvent event) {
        try {  
         // Getting the registry 
         Registry registry = LocateRegistry.getRegistry("localhost", Registry.REGISTRY_PORT); 
    
         // Looking up the registry for the remote object 
         Authentication authentication = (Authentication) registry.lookup("Authentication"); 
    
         Company company = new Company(
            companyName.getText(),
            companyAddress.getText(),
            Long.parseLong(phoneNo.getText()),
            Long.parseLong(panNo.getText()),
            mailingAddress.getText()
         );
         
         System.out.println(
            user.getFullName() + " | " + 
            user.getMobileNo() + " | " +
            user.getAddress() + " | " + 
            user.getUsername() + " | " +
            user.getEmailAddress() + " | " +
            user.getPassword() + " | " +
            company.getCompanyName() + " | " +
            company.getCompanyAddress() + " | " +
            company.getPhoneNo() + " | " +
            company.getPanNo() + " | " +
            company.getMailingAddress()
        );
         
         // Calling the remote method using the obtained object 
         Response response = authentication.signUp(user, company);
         System.out.println("Code: " + response.getCode() + " | Status: " + response.getStatus());
         // System.out.println("Remote method invoked"); 
      } catch (Exception e) {
         System.err.println("Client exception: " + e.toString()); 
         e.printStackTrace(); 
      }
    }
}
