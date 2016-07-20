/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adobe.aem.utilities.hotfix.installer.ui;

import com.adobe.aem.utilities.hotfix.installer.HotfixInstallerService;
import com.adobe.aem.utilities.hotfix.installer.model.Hotfix;
import com.adobe.aem.utilities.hotfix.installer.model.ProductVersion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author brobert
 */
public class PackageSelectPanelController implements Initializable {

    @FXML
    private ComboBox<ProductVersion> recommendedPackages;
    @FXML
    private TextField searchTextField;
    @FXML
    private TableView<Hotfix> searchResultsTable;
    private HotfixInstallerService installer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        installer = HotfixInstallerService.getInstance();
        recommendedPackages.setItems(installer.getProductVersions());
        recommendedPackages.valueProperty().addListener((p, o, n) -> selectProductVersion(n));
        recommendedPackages.setConverter(new StringConverter<ProductVersion>() {
            @Override
            public String toString(ProductVersion object) {
                return object.getProductVersion();
            }

            @Override
            public ProductVersion fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        searchResultsTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        searchResultsTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        searchResultsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void searchAction(ActionEvent event) {
        if (searchTextField.getText() == null || "".equals(searchTextField.getText())) {
            searchResultsTable.getItems().clear();
        } else {
            searchResultsTable.getItems().setAll(
                installer.findHotfixesByText(searchTextField.getText())
            );
        }
    }

    @FXML
    private void addSelectedAction(ActionEvent event) {
        installer.getSelectedHotfixes().addAll(searchResultsTable.getSelectionModel().getSelectedItems());
    }

    private void selectProductVersion(ProductVersion version) {
        searchResultsTable.getItems().setAll(installer.findHotfixesByVersion(version));
    }

}
