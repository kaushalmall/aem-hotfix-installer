/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adobe.aem.utilities.hotfix.installer.ui;

import com.adobe.aem.utilities.hotfix.installer.model.Login;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author brobert
 */
public class ConnectPanelController implements Initializable {
    private final String PACKAGE_SHARE_HOST = "www.adobeaemcloud.com";
    private final String PACKAGE_SHARE_URI = "/content/packageshare.html";

    @FXML
    private Label aemConnectionVerificationLabel;
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
    @FXML
    private VBox connectPanel;

    AuthHandler aemHandler;
    AuthHandler pkgHandler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aemHandler = new AuthHandler(hostField.textProperty(), sslCheckbox.selectedProperty(), usernameField.textProperty(), passwordField.textProperty());
        Login aemLogin = aemHandler.getLogin();
        aemConnectionVerificationLabel.textProperty().bind(aemLogin.statusMessageProperty());
        aemLogin.loginConfirmedProperty().addListener((p, o, n) -> updateConnectionStyles());
        aemLogin.loginErrorProperty().addListener((p, o, n) -> updateConnectionStyles());
 
        pkgHandler = new AuthHandler(new ReadOnlyStringWrapper(PACKAGE_SHARE_HOST), new ReadOnlyBooleanWrapper(true), adobeIdField.textProperty(), adobePasswordField.textProperty());
        pkgHandler.setTestPage(PACKAGE_SHARE_URI);
        Login pkgLogin = pkgHandler.getLogin();
        pkgConnectionVerificationLabel.textProperty().bind(pkgLogin.statusMessageProperty());
        pkgLogin.loginConfirmedProperty().addListener((p, o, n) -> updateConnectionStyles());
        pkgLogin.loginErrorProperty().addListener((p, o, n) -> updateConnectionStyles());
    }

    private void updateConnectionStyles() {
        connectPanel.getStyleClass().clear();
        Login aemLogin = aemHandler.getLogin();
        String aemStyle
                = aemLogin.loginConfirmedProperty().get()
                        ? "connected-aem"
                        : aemLogin.loginErrorProperty().get()
                                ? "not-connected-aem"
                                : "connecting-aem";
        Login pkgLogin = pkgHandler.getLogin();
        String pkgStyle
                = pkgLogin.loginConfirmedProperty().get()
                        ? "connected-pkg"
                        : pkgLogin.loginErrorProperty().get()
                                ? "not-connected-pkg"
                                : "connecting-pkg";
        connectPanel.getStyleClass().addAll(aemStyle, pkgStyle);
    }
}
