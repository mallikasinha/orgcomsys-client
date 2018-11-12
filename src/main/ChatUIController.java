/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.orgcomsys.impl.AuthenticationImpl;
import com.orgcomsys.model.Response;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.orgcomsys.utility.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Mallika
 */
public class ChatUIController implements Initializable {

    @FXML
    private ImageView searchImage;
     
    @FXML
    private ListView<String> listOfUser;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PreparedStatement statement = null;
        ObservableList<String> data;
        data = FXCollections.observableArrayList();
        listOfUser.setItems(data);
        String sql = "select full_name from User ";
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);    
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    data.add(resultSet.getString(1));
                    System.out.println(resultSet.getString(1));
                }}
        } catch (SQLException ex) {
            Logger.getLogger(AuthenticationImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    };

       

       
    
}
