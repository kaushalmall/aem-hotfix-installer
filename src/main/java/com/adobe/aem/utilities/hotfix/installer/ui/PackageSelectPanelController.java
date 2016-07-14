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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableView;

/**
 * FXML Controller class
 *
 * @author brobert
 */
public class PackageSelectPanelController implements Initializable {

    @FXML
    private ComboBox<?> recommendedPackages;
    @FXML
    private TextField searchTextField;
    @FXML
    private TreeTableView<?> searchResultsTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void searchAction(ActionEvent event) {
    }

    @FXML
    private void addSelectedAction(ActionEvent event) {
    }
    
}
