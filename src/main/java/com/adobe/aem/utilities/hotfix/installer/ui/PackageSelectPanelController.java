/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adobe.aem.utilities.hotfix.installer.ui;

import com.adobe.aem.utilities.hotfix.installer.model.ProductHotfixes;
import com.adobe.aem.utilities.hotfix.installer.utility.HotfixExtractor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableView;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author brobert
 */
public class PackageSelectPanelController implements Initializable {

    @FXML
    private ComboBox<ProductHotfixes> recommendedPackages;
    @FXML
    private TextField searchTextField;
    @FXML
    private TreeTableView<?> searchResultsTable;
    private HotfixExtractor hotfixExtractor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hotfixExtractor = new HotfixExtractor();
        new Thread(this::extractVersions).start();
        recommendedPackages.valueProperty().addListener((p, o, n) -> selectedProduct(n));
        recommendedPackages.setConverter(new StringConverter<ProductHotfixes>() {
            @Override
            public String toString(ProductHotfixes object) {
                return object.getProductVersion();
            }

            @Override
            public ProductHotfixes fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    @FXML
    private void searchAction(ActionEvent event) {
    }

    @FXML
    private void addSelectedAction(ActionEvent event) {
    }

    private void extractVersions() {
        recommendedPackages.getItems().addAll(
                hotfixExtractor.scrapeProductHotfixes()
        );
    }

    private void selectedProduct(ProductHotfixes n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
