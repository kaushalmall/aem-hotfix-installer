package com.adobe.aem.utilities.hotfix.installer.model;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author brobert
 */
public class ProductVersion implements Comparable<ProductVersion> {

    private String productVersion;
    private String pageHref;
    private Set<Hotfix> hotfixes = new TreeSet<Hotfix>();

    @Override
    public int compareTo(ProductVersion other) {
        return getProductVersion().toUpperCase().compareTo(other.getProductVersion().toUpperCase());
    }

    public String getPageHref() {
        return pageHref;
    }

    public void setPageHref(String href) {
        pageHref = href;
    }

    public String getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(String ver) {
        productVersion = ver;
    }
    
    public Set<Hotfix> getHotfixes() {
        return hotfixes;
    }
}
