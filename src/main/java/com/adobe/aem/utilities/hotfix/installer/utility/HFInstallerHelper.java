package com.adobe.aem.utilities.hotfix.installer.utility;

import com.adobe.aem.utilities.hotfix.installer.HotfixInstaller;
import com.adobe.aem.utilities.hotfix.installer.installstatus.object.InstallStatus;
import com.adobe.aem.utilities.hotfix.installer.installstatus.object.Status;
import com.adobe.aem.utilities.hotfix.installer.jaxb.packages.object.Crx;
import com.google.gson.Gson;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.io.IOUtils;
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
import java.util.Scanner;

/**
 * Created by kmall.
 */
public class HFInstallerHelper {

    private static Logger log = LogManager.getLogger(HFInstallerHelper.class.getName());

    List<Crx.Response.Data.Packages.Package> currentPackages = null;
    HttpClient httpClient = null;

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpHost getHttpHost() {
        return httpHost;
    }

    public void setHttpHost(HttpHost httpHost) {
        this.httpHost = httpHost;
    }

    public Credentials getAemCredentials() {
        return aemCredentials;
    }

    public void setAemCredentials(Credentials aemCredentials) {
        this.aemCredentials = aemCredentials;
    }

    HttpHost httpHost = null;
    Credentials aemCredentials = null;

    String basePath = null;

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public HFInstallerHelper(HttpHost httpHost, Credentials credentials ){
        this.httpHost = httpHost;
        this.aemCredentials = credentials;

        setupClient();
    }

    public void setupClient(){

        if( this.httpClient == null ){
            HttpClient httpClient = new HttpClient();
            httpClient.getParams().setAuthenticationPreemptive(true);
            httpClient.getState().setCredentials(AuthScope.ANY, this.aemCredentials);

            this.httpClient = httpClient;
        }
    }

    public Part[] setupParts( String filePath, String installPackages ) throws FileNotFoundException {

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

    public List<Crx.Response.Data.Packages.Package> getCurrentPackagesList() throws IOException, JAXBException {
        List<Crx.Response.Data.Packages.Package> currentPackagesList = null;

        if( this.currentPackages == null ){
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

            if( lsResponseObject != null ){
                Crx.Response.Data data = lsResponseObject.getResponse().getData();

                if( data != null ){
                    Crx.Response.Data.Packages dataPackages = data.getPackages();
                    currentPackagesList = dataPackages.getPackage();
                    this.currentPackages = currentPackagesList;
                }
            }
        }

        return this.currentPackages;
    }

    public void checkCurrentInstalledHotfixes(List<String> hotfixes) {

        if( this.currentPackages != null ){
            for( String hotfix : hotfixes ){

                if( isPackageInstalled( hotfix ) ){
                    System.out.println( "Package " + hotfix + " is installed on the target instance" );
                } else {
                    System.err.println( "Package " + hotfix + " is NOT installed on the target instance" );;
                }
            }
        }
    }

    public boolean isPackageInstalled ( String packageName ){

        boolean isPackageInstalled = false;

        if( this.currentPackages != null ){
            for( Crx.Response.Data.Packages.Package aemPackage : this.currentPackages ){
                String downloadName = aemPackage.getDownloadName();

                if( packageName.equalsIgnoreCase( downloadName ) ){
                    String lastUnpackedDate = aemPackage.getLastUnpacked();

                    if( StringUtils.isNotEmpty( lastUnpackedDate ) ){
                        isPackageInstalled = true;
                        break;
                    }
                }
            }
        }

        return isPackageInstalled;
    }

    public boolean promptKeyInput(String hfName, boolean isSilent ) throws IOException {
        boolean userFlag = false;

        if( isSilent ){
            return true;
        }

        StringBuffer userMessage = new StringBuffer();

        if( isPackageInstalled( hfName ) ){
            System.err.println( "\n Looks like " + hfName + " is already installed on the target AEM instance" );
        }

        userMessage.append( "\n Do you want to install " + hfName + "?" );
        userMessage.append( "\n Please check the logs, make sure the previous HF installed correctly. " );
        userMessage.append( Constants.CRX_PACKMGR_INSTALL_STATUS_JSP + " page says " + isSafeToInstallPackage() );
        userMessage.append( "\n Press \"Y\" to install or press \"N\" to skip." );

        System.out.println( userMessage.toString() );
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        if (StringUtils.isNotBlank(userInput)) {
            if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
                userFlag = true;
            } else if (userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("no")) {
                userFlag = false;
            } else {
                System.out.println("Incorrect input, please enter a valid input.");
                userFlag = promptKeyInput(hfName, isSilent);
            }
        } else {
            System.out.println("Incorrect input, please enter a valid input.");
            userFlag = promptKeyInput(hfName, isSilent );
        }

        return userFlag;
    }

    public Status getPackageInstallStatus() throws IOException {
        Status packageInstallStatus = null;

        GetMethod getMethod = new GetMethod( httpHost.toString() + Constants.CRX_PACKMGR_INSTALL_STATUS_JSP );
        int statusCode = httpClient.executeMethod( getMethod );

        if (statusCode != HttpStatus.SC_OK) {
            log.error("Method failed: {}", getMethod.getStatusLine());
        }

        String response = IOUtils.toString(getMethod.getResponseBodyAsStream(), "UTF-8");

        if( StringUtils.isNotEmpty( response ) ){
            Gson gson = new Gson();
            InstallStatus installStatus = gson.fromJson(response, InstallStatus.class);

            if( installStatus != null ){
                Status status = installStatus.getStatus();

                if( status != null ){
                    packageInstallStatus = status;
                }
            }
        }

        return packageInstallStatus;
    }

    public boolean isSafeToInstallPackage() throws IOException {
        boolean isSafeToInstallPackage = false;

        Status status = getPackageInstallStatus();

        if( status != null ){
            boolean isFinished = status.getFinished();

            int itemCount = status.getItemCount();

            if( itemCount == 0 && isFinished ){
                isSafeToInstallPackage = true;
            }
        }

        return isSafeToInstallPackage;
    }

    public String getPackagePath( String hfName ){

        return basePath + "/" + hfName;

    }
}
