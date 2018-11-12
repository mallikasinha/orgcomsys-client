/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.jfoenix.controls.JFXTextField;
import com.orgcomsys.model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
public class UserDetailController implements Initializable {

    @FXML
    private AnchorPane userdetail;
    
    @FXML
    private JFXTextField fullName;

    @FXML
    private JFXTextField mobileNo;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField emailAddress;

    @FXML
    private JFXTextField password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    void onCompany(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
        AnchorPane signup = loader.load();
        
        SignupController signupController = loader.getController();
        User user = new User(
            fullName.getText(),
            Long.parseLong(mobileNo.getText()),
            address.getText(),
            username.getText(),
            emailAddress.getText(),
            password.getText()
        );
        signupController.setUser(user);
        
        userdetail.getChildren().setAll(signup);
    }
    
}
