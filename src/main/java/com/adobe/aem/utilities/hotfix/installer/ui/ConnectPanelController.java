/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adobe.aem.utilities.hotfix.installer.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author brobert
 */
public class ConnectPanelController implements Initializable {

    @FXML
    private Label aemConnectionVerificationLabel;
    @FXML
    private Label connectionVerificationLabel;
    @FXML
    private Label pkgConnectionVerificationLabel;
    @FXML
    private TextField hostField;
    @FXML
    private CheckBox sslCheckbox;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField adobeIdField;
    @FXML
    private PasswordField adobePasswordField;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
