package com.adobe.aem.utilities.hotfix.installer.utility;

import com.adobe.aem.utilities.hotfix.installer.model.Hotfix;
import com.adobe.aem.utilities.hotfix.installer.model.ProductVersion;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author brobert
 */
public class HotfixExtractor {

    private final CloseableHttpClient client;
    public static final String HELP_SITE = "https://helpx.adobe.com";
    public static final String PACKAGE_SHARE_URL_BASE = "https://www.adobeaemcloud.com/content/marketplace";

    public HotfixExtractor() {
        client = ConnectionManager.getInstance().getAuthenticatedClient(null, null);
    }

    public Set<ProductVersion> scrapeProductVersions() {
        Set<ProductVersion> productHotfixes = new TreeSet<>();
        productHotfixes.addAll(
                getProductVersionPage().select("a").stream()
                .filter(this::hasValidProductVersion)
                .map(this::extractHotfixPage)
                .collect(Collectors.toSet())
        );

        return productHotfixes;
    }

    public Set<Hotfix> scrapeHotfixesByVersion(ProductVersion productVersion) {
        if (productVersion.getHotfixes().isEmpty()) {
            getHotfixListPage(productVersion)
                    .select("a").stream()
                    .filter(this::isHotfixLink)
                    .map(this::extractHotfix)
                    .collect(Collectors.toCollection(productVersion::getHotfixes));
        }
        return productVersion.getHotfixes();
    }

    public Document getProductVersionPage() {
        try {
            String uri = HELP_SITE + "/experience-manager/kb/index/hotfixes.html";
            CloseableHttpResponse response = client.execute(new HttpGet(uri));
            Document doc = Jsoup.parse(response.getEntity().getContent(), null, uri);
            response.close();
            return doc;
        } catch (IOException ex) {
            Logger.getLogger(HotfixExtractor.class.getName()).log(Level.SEVERE, null, ex);
            return new Document("");
        }
    }

    private boolean hasValidProductVersion(Element link) {
        String href = link.attr("href");
        if (!href.contains("available-hotfixes")) {
            return false;
        }
        return href.startsWith("/content/help/en/experience-manager/kb/");
    }

    private boolean isHotfixLink(Element link) {
        String href = link.attr("href");
        return href.startsWith(PACKAGE_SHARE_URL_BASE);
    }

    private ProductVersion extractHotfixPage(Element link) {
        String linkText = link.text();
        for (String remove : new String[]{"Adobe Experience Manager ", "AEM ", " hot fixes", " hotfixes", " recommended"}) {
            linkText = linkText.replace(remove, "");
        }
        linkText = linkText.replace("Service Pack ", "SP");
        ProductVersion hotfixes = new ProductVersion();
        hotfixes.setProductVersion(linkText);
        hotfixes.setPageHref(HELP_SITE + link.attr("href"));
        return hotfixes;
    }

    private Document getHotfixListPage(ProductVersion productVersion) {
        try {
            String uri = productVersion.getPageHref();
            CloseableHttpResponse response = client.execute(new HttpGet(uri));
            Document doc = Jsoup.parse(response.getEntity().getContent(), null, uri);
            response.close();
            return doc;
        } catch (IOException ex) {
            Logger.getLogger(HotfixExtractor.class.getName()).log(Level.SEVERE, null, ex);
            return new Document("");
        } 
    }

    private Hotfix extractHotfix(Element hotfixLink) {
        Hotfix hotfix = new Hotfix();
        hotfix.setDownloadHref(hotfixLink.attr("href"));
        hotfix.setName(hotfixLink.text());
        Elements row = hotfixLink.parents().select("tr").first().select("td");
        String dateStr = row.get(0).text();
        hotfix.setDate(parseDate(dateStr));
        if (row.size() >= 3) {
            String description = row.get(2).text();
            hotfix.setDescripton(description);
        }
        return hotfix;
    }

    Locale l = new Locale("en","us");
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d yyyy", l);
    String[] removeFromDate = new String[] {
        "([0-9])st, ", "([0-9])st ", "([0-9])nd, ", "([0-9])nd ", "([0-9])rd, ", "([0-9])rd ", "([0-9])th, " , "([0-9])th ", ", "
    };
    
    private Calendar parseDate(String str) {
        Calendar cal = Calendar.getInstance();
        for (String remove : removeFromDate) {
            str = str.replaceAll(remove, "$1 ");
        }
        try {
            cal.setTime(dateFormat.parse(str));
        } catch (ParseException ex) {
            Logger.getLogger(HotfixExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cal;
    }
}
