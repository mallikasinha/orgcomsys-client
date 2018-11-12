/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.orgcomsys.inter.Authentication;
import com.orgcomsys.model.Response;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mallika
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane login;
    
    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void signUp(ActionEvent event) throws IOException {
        AnchorPane userdetail = FXMLLoader.load(getClass().getResource("UserDetail.fxml"));
        login.getChildren().setAll(userdetail);
        
    }
    
    @FXML
    private void onSignIn(ActionEvent event) throws IOException  {
        try {
            // Getting the registry
            Registry registry = LocateRegistry.getRegistry("localhost", Registry.REGISTRY_PORT);
            
            // Looking up the registry for the remote object
            Authentication authentication = (Authentication) registry.lookup("Authentication");
            Response response = authentication.signIn(username.getText(), password.getText());
            System.out.println("Code: " + response.getCode() + " | Status: " + response.getStatus());
            AnchorPane chatUI = FXMLLoader.load(getClass().getResource("ChatUI.fxml"));
            login.getChildren().setAll(chatUI);
        } catch (Exception e) {
         System.err.println("Client exception: " + e.toString()); 
         e.printStackTrace();
        }
        
        
        
        
        
    }

    
}
