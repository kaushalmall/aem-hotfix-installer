/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adobe.aem.utilities.hotfix.installer.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableView;

/**
 * FXML Controller class
 *
 * @author brobert
 */
public class InstallPanelController implements Initializable {

    @FXML
    private TreeTableView<?> selectedPackagesTable;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void removeSelectedPackages(ActionEvent event) {
    }

    @FXML
    private void installPackagesAction(ActionEvent event) {
    }

}
