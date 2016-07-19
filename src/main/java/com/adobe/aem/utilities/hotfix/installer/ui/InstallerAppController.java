/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adobe.aem.utilities.hotfix.installer.ui;

import com.adobe.aem.utilities.hotfix.installer.HotfixInstallerService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author brobert
 */
public class InstallerAppController extends HotfixInstallerService implements Initializable {

    @FXML
    ConnectPanelController connectController;
    
    @FXML
    PackageSelectPanelController packageSelectController;

    @FXML
    InstallPanelController installController;
       
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HotfixInstallerService.registerInstance(this);
    }    
    
}
