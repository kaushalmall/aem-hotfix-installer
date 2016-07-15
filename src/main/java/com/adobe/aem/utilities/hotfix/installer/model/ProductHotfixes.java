package com.adobe.aem.utilities.hotfix.installer.model;

/**
 *
 * @author brobert
 */
public class ProductHotfixes implements Comparable<ProductHotfixes> {

    private String productVersion;
    private String pageHref;

    @Override
    public int compareTo(ProductHotfixes other) {
        return getProductVersion().toUpperCase().compareTo(other.getProductVersion().toUpperCase());
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
    
}
