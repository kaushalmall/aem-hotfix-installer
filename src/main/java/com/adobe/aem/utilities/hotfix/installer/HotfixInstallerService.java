package com.adobe.aem.utilities.hotfix.installer;

import com.adobe.aem.utilities.hotfix.installer.model.Hotfix;
import com.adobe.aem.utilities.hotfix.installer.model.ProductVersion;
import com.adobe.aem.utilities.hotfix.installer.utility.HotfixExtractor;
import java.util.Collection;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author brobert
 */
public class HotfixInstallerService {

    private static HotfixInstallerService instance;
    public static HotfixInstallerService getInstance() {
        if (instance == null) {
            instance = new HotfixInstallerService();
        }
        return instance;
    }
    
    private HotfixExtractor hotfixExtractor = new HotfixExtractor();
    private ObservableList<ProductVersion> productVersions = null;
    private ObservableList<Hotfix> selectedHotfixes = FXCollections.observableArrayList();

    public ObservableList<ProductVersion> getProductVersions() {
        if (productVersions == null) {
            productVersions = FXCollections.observableArrayList(hotfixExtractor.scrapeProductVersions());
        }
        return productVersions;
    }

    public ObservableList<Hotfix> getSelectedHotfixes() {
        return selectedHotfixes;        
    }

    public Collection<Hotfix> findHotfixesByText(String text) {
        return Collections.EMPTY_LIST;
    }

    public Collection<Hotfix> findHotfixesByVersion(ProductVersion version) {
        return hotfixExtractor.scrapeHotfixesByVersion(version);
    }    
}
