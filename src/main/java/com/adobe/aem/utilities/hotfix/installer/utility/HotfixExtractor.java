package com.adobe.aem.utilities.hotfix.installer.utility;

import com.adobe.aem.utilities.hotfix.installer.model.ProductHotfixes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 *
 * @author brobert
 */
public class HotfixExtractor {

    private final CloseableHttpClient client;

    public HotfixExtractor() {
        client = ConnectionManager.getInstance().getAuthenticatedClient(null, null);
    }

    public Set<ProductHotfixes> scrapeProductHotfixes() {
        Set<ProductHotfixes> productHotfixes = new TreeSet<>();
        productHotfixes.addAll(
                getRawHotfixPage()
                .filter(this::hasValidProductVersion)
                .map(this::extractHotfixPage)
                .collect(Collectors.toSet())
        );

        return productHotfixes;
    }

    public Stream<String> getRawHotfixPage() {
        try {
            CloseableHttpResponse response = client.execute(new HttpGet("https://helpx.adobe.com/experience-manager/kb/index/hotfixes.html"));
            return new BufferedReader(new InputStreamReader(response.getEntity().getContent())).lines();
        } catch (IOException ex) {
            Logger.getLogger(HotfixExtractor.class.getName()).log(Level.SEVERE, null, ex);
            return Stream.empty();
        }
    }

    private boolean hasValidProductVersion(String linkHtml) {
        if (!linkHtml.contains("available-hotfixes")) {
            return false;
        }
        String href = getHref(linkHtml);
        return href.startsWith("/content/help/en/experience-manager/kb/");
    }

    private ProductHotfixes extractHotfixPage(String linkHtml) {
        String linkText = getLinkText(linkHtml);
        linkText = linkText.replace("Adobe Experience Manager", "AEM");
        linkText = linkText.replace(" hot fixes", "");
        linkText = linkText.replace(" hotfixes", "");
        ProductHotfixes hotfixes = new ProductHotfixes();
        hotfixes.setProductVersion(linkText);
        hotfixes.setPageHref(getHref(linkHtml));
        return hotfixes;
    }

    private String getHref(String linkHtml) {
        int startIndex = linkHtml.indexOf("href=") + 6;
        int endIndex = linkHtml.indexOf('"', startIndex);
        return linkHtml.substring(startIndex, endIndex);
    }

    private String getLinkText(String linkHtml) {
        int startHref = linkHtml.indexOf("href=") + 6;
        int startIndex = linkHtml.indexOf("\">", startHref) + 2;
        int endIndex = linkHtml.indexOf('<', startIndex);
        return linkHtml.substring(startIndex, endIndex);
    }
}
