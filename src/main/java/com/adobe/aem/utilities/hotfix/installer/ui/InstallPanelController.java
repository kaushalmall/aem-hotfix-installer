/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adobe.aem.utilities.hotfix.installer.ui;

import com.adobe.aem.utilities.hotfix.installer.HotfixInstallerService;
import com.adobe.aem.utilities.hotfix.installer.model.Hotfix;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author brobert
 */
public class InstallPanelController implements Initializable {
    HotfixInstallerService installer = HotfixInstallerService.getInstance();
    
    @FXML
    private TableView<Hotfix> selectedPackagesTable;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedPackagesTable.setItems(installer.getSelectedHotfixes());
        selectedPackagesTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        selectedPackagesTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));    
    }

    @FXML
    private void removeSelectedPackages(ActionEvent event) {
        installer.getSelectedHotfixes().removeAll(selectedPackagesTable.getSelectionModel().getSelectedItems());
    }

    @FXML
    private void installPackagesAction(ActionEvent event) {
    }

}
