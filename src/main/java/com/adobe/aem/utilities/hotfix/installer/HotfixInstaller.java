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
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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
    private static boolean isCheckRun = false;
    private static boolean isSilentRun = false;
    private static int maxNumberOfRetries = 0;
    private static int maxWaitTimeMS = 0;

    public static void main(String[] args) {

        String basePath = "";

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
                    isSilentRun = true;
                }

            } else {
                System.out.println( "Please provide valid arguments [/path/to/folder/containing/files] [check|silent]" );
                return;
            }

            final String host = Config.properties.getString(Constants.HOST);
            final String port = Config.properties.getString(Constants.PORT);
            final String userName = Config.properties.getString(Constants.USER_NAME);
            final String password = Config.properties.getString(Constants.PASSWORD);
            final String installPackages = Config.properties.getString(Constants.INSTALL_PACKAGES);
            final List<String> hotfixes = new ArrayList<String>(Arrays.asList(Config.properties.getString(Constants.HOTFIXES).split(",")));

            if( isSilentRun ){
                maxNumberOfRetries = Integer.parseInt( Config.properties.getString( Constants.MAX_RETRIES ) );
                maxWaitTimeMS = Integer.parseInt( Config.properties.getString( Constants.MAX_TIMEOUT ) );
            }

            log.info("host: " + host);
            log.info("port: " + port);
            log.info("username: " + userName);
            log.info("password: " + password);
            log.info("hotfixes: " + hotfixes.toString());

            System.out.println("host: " + host);
            System.out.println("port: " + port);
            System.out.println("username: " + userName);
            System.out.println("password: " + password);
            System.out.println("hotfixes: " + hotfixes.toString());

            HttpHost httpHost = new HttpHost(host, Integer.parseInt(port));
            Credentials credentials = new UsernamePasswordCredentials(userName, password);

            HFInstallerHelper hfInstallerHelper = new HFInstallerHelper( httpHost, credentials );
            hfInstallerHelper.setBasePath( basePath );

            List<Crx.Response.Data.Packages.Package> currentPackagesList = hfInstallerHelper.getCurrentPackagesList();

            if( isCheckRun ){
                hfInstallerHelper.checkCurrentInstalledHotfixes( hotfixes );
                return;
            }

            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
            
            executor.execute(() -> {
                try {
                    processPackages(installPackages, hotfixes, hfInstallerHelper);
                    System.out.println( "Finished Processing!" );
                    log.info("Finished!");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processPackages(String installPackages, List<String> hotfixes, HFInstallerHelper hfInstallerHelper) throws IOException, InterruptedException {
        for (String hfName : hotfixes) {

            if( isSilentRun ){

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
                    processPackage( hfInstallerHelper, hfName, installPackages, isSilentRun );
                }

            } else {
                processPackage( hfInstallerHelper, hfName, installPackages, isSilentRun );
            }
        }
    }


    private synchronized static void processPackage(HFInstallerHelper hfInstallerHelper, String hfName, String installPackages, boolean isSilent) throws IOException {

        if (StringUtils.isNotEmpty(hfName)) {

            System.out.println("Now processing " + hfName);

            if (hfInstallerHelper.promptKeyInput(hfName, isSilent)) {

                boolean isPackageInstalled = hfInstallerHelper.isPackageInstalled( hfName );

                if( isPackageInstalled ){
                    System.err.println( "Hotfix: " + hfName + " is installed, skipping" );
                    return;
                } else {
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
}
