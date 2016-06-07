package com.adobe.aem.utilities.hotfix.installer;

import com.adobe.aem.utilities.hotfix.installer.jaxb.packages.object.Crx;
import com.adobe.aem.utilities.hotfix.installer.utility.Config;
import com.adobe.aem.utilities.hotfix.installer.utility.Constants;
import com.adobe.aem.utilities.hotfix.installer.utility.HFInstallerHelper;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kmall.
 * <p>
 * To run the program, make sure the packages are under the resources directory and the config.properties is correct.
 * To install packages, change installPackages property to true. By default, this will only upload packages.
 * <p>
 * <p>
 */
public class HotfixInstaller {

    private static Logger log = LogManager.getLogger(HotfixInstaller.class.getName());
    private static List<Crx.Response.Data.Packages.Package> currentPackagesList = null;

    public static void main(String[] args) {

        boolean isCheckRun = false;
        boolean isSilent = false;

        String basePath = HotfixInstaller.class.getResource( "." ).getPath();

        try {

            if( args != null && args.length == 1 ){

                basePath = args[0];

                Config.loadProperties( basePath );
            } else if( args != null && args.length == 2 ){
                basePath = args[0];
                Config.loadProperties( basePath );

                String runFlag = args[1];

                if( runFlag.equalsIgnoreCase( "check" ) ){
                    isCheckRun = true;
                }

                if( runFlag.equalsIgnoreCase( "silent" ) ){
                    isSilent = true;
                }

            } else {
                Config.loadProperties();
            }

            String host = Config.properties.getString(Constants.HOST);
            String port = Config.properties.getString(Constants.PORT);
            String userName = Config.properties.getString(Constants.USER_NAME);
            String password = Config.properties.getString(Constants.PASSWORD);
            String installPackages = Config.properties.getString(Constants.INSTALL_PACKAGES);
            List<String> hotfixes = new ArrayList<String>(Arrays.asList(Config.properties.getString(Constants.HOTFIXES).split(",")));

            int maxNumberOfRetries = 0;
            int maxWaitTimeMS = 0;

            if( isSilent ){
                maxNumberOfRetries = Integer.parseInt( Config.properties.getString( Constants.MAX_RETRIES ) );
                maxWaitTimeMS = Integer.parseInt( Config.properties.getString( Constants.MAX_TIMEOUT ) );
            }

            log.info("host: " + host);
            log.info("port: " + port);
            log.info("username: " + userName);
            log.info("password: " + password);
            log.info("hotfixes: " + hotfixes.toString());

            HttpHost httpHost = new HttpHost(host, Integer.parseInt(port));
            Credentials credentials = new UsernamePasswordCredentials(userName, password);

            HFInstallerHelper hfInstallerHelper = new HFInstallerHelper( httpHost, credentials );
            hfInstallerHelper.setBasePath( basePath );

            currentPackagesList = hfInstallerHelper.getCurrentPackagesList();

            if( isCheckRun ){
                hfInstallerHelper.checkCurrentInstalledHotfixes( hotfixes );
                return;
            }

            for (String hfName : hotfixes) {

                if( isSilent ){

                    boolean isSafeToInstallPackage = false;

                    int currentRetries = 0;

                    while( !isSafeToInstallPackage && currentRetries <= maxNumberOfRetries ) {
                        isSafeToInstallPackage = hfInstallerHelper.isSafeToInstallPackage();
                        currentRetries++;
                        if( !isSafeToInstallPackage ){
                            System.out.print( Constants.CRX_PACKMGR_INSTALL_STATUS_JSP + " page says there is something being installed. Retrying in " + maxWaitTimeMS + " ms.");
                            Thread.sleep( maxWaitTimeMS );
                        }
                    }

                    if( isSafeToInstallPackage ){
                        processHF( hfInstallerHelper, hfName, installPackages, isSilent);
                    }

                } else {
                    processHF( hfInstallerHelper, hfName, installPackages, isSilent );
                }


            }

            log.info("Finished!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private synchronized static void processHF(HFInstallerHelper hfInstallerHelper, String hfName, String installPackages, boolean isSilent) throws IOException {

        if (StringUtils.isNotEmpty(hfName)) {

            System.out.println("Now processing " + hfName);

            if (hfInstallerHelper.promptKeyInput(hfName, isSilent)) {

                PostMethod postMethod = new PostMethod(hfInstallerHelper.getHttpHost().toString() + Constants.CRX_PACKMGR_SERVICE_JSP);

                String hfPath = hfInstallerHelper.getPackagePath( hfName );

                if (StringUtils.isNotEmpty(hfPath)) {
                    Part[] parts = hfInstallerHelper.setupParts(hfPath, installPackages);

                    MultipartRequestEntity multipartRequestEntity = new MultipartRequestEntity(parts, postMethod.getParams());

                    postMethod.setRequestEntity(multipartRequestEntity);

                    hfInstallerHelper.getHttpClient().executeMethod(postMethod);

                    InputStream responseBody = postMethod.getResponseBodyAsStream();

                    StringWriter writer = new StringWriter();
                    IOUtils.copy(responseBody, writer, "UTF-8");
                    String responseString = writer.toString();

                    log.info(responseString);
                }
            }
        }
    }
}
