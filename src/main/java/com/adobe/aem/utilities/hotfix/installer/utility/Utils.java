package com.adobe.aem.utilities.hotfix.installer.utility;

import com.adobe.aem.utilities.hotfix.installer.jaxb.packages.object.Crx;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by kmall.
 */
public class Utils {

    private static Logger log = LogManager.getLogger(Utils.class.getName());

    public static HttpClient setupClient( Credentials credentials ){

        HttpClient httpClient = new HttpClient();
        httpClient.getParams().setAuthenticationPreemptive(true);
        httpClient.getState().setCredentials(AuthScope.ANY, credentials);

        return  httpClient;

    }

    public static Part[] setupParts( String filePath, String installPackages ) throws FileNotFoundException {

        log.info("Setting up for package: " + filePath);

        File packageFile = new File( filePath );

        Part[] parts = {
                new StringPart(Constants.NAME, packageFile.getName() ),
                new StringPart(Constants.FORCE, Constants.TRUE),
                new StringPart(Constants.INSTALL, installPackages),
                new FilePart( Constants.FILE, packageFile )
        };

        return parts;
    }

    public static List<Crx.Response.Data.Packages.Package> getCurrentHFList(HttpClient httpClient, HttpHost httpHost) throws IOException, JAXBException {
        List<Crx.Response.Data.Packages.Package> currentPackagesList = null;

        GetMethod getMethod = new GetMethod( httpHost.toString() + Constants.CRX_PACKMGR_LS );
        int statusCode = httpClient.executeMethod( getMethod );

        if (statusCode != HttpStatus.SC_OK) {
            log.error("Method failed: {}", getMethod.getStatusLine());
        }

        InputStream inputStream = getMethod.getResponseBodyAsStream();

        JAXBContext jaxbContext = JAXBContext.newInstance( Crx.class );
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        JAXBElement<Crx> crxJAXBElement = jaxbUnmarshaller.unmarshal(new StreamSource(inputStream), Crx.class);
        Crx lsResponseObject = crxJAXBElement.getValue();

        Crx.Response.Data data = lsResponseObject.getResponse().getData();

        if( data != null ){
            Crx.Response.Data.Packages dataPackages = data.getPackages();
            currentPackagesList = dataPackages.getPackage();
        }

        return currentPackagesList;
    }

    public static void checkCurrentInstalledHotfixes(List<Crx.Response.Data.Packages.Package> currentPackagesList, List<String> hotfixes) {

        for( String hotfix : hotfixes ){

            if( isPackageInstalled( hotfix, currentPackagesList ) ){
                System.out.println( "Package " + hotfix + " is installed on the target instance" );
            } else {
                System.err.println( "Package " + hotfix + " is NOT installed on the target instance" );;
            }
        }
    }

    public static boolean isPackageInstalled ( String packageName, List<Crx.Response.Data.Packages.Package> currentPackagesList ){

        boolean isPackageInstalled = false;
        for( Crx.Response.Data.Packages.Package aemPackage : currentPackagesList ){
            String downloadName = aemPackage.getDownloadName();

            if( packageName.equalsIgnoreCase( downloadName ) ){
                String lastUnpackedDate = aemPackage.getLastUnpacked();

                if( StringUtils.isNotEmpty( lastUnpackedDate ) ){
                    isPackageInstalled = true;
                    break;
                }
            }
        }
        return isPackageInstalled;
    }
}
