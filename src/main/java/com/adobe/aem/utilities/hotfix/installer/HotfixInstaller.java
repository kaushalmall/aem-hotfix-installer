package com.adobe.aem.utilities.hotfix.installer;

import com.adobe.aem.utilities.hotfix.installer.utility.Config;
import com.adobe.aem.utilities.hotfix.installer.utility.Constants;
import com.adobe.aem.utilities.hotfix.installer.utility.Utils;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
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
import java.util.Scanner;

/**
 * Created by kmall.
 * <p>
 * To run the program, make sure the packages are under the resources directory and the config.properties is correct.
 * To install packages, change installPackages property to true. By default, this will only upload packages.
 * <p>
 * <p>
 * TODO:1. add functionality to handle hotfixes from an external repository instead of having it in the classpath
 * TODO:2. add functionality where the code checks if the HF is installed already before installing it.
 */
public class HotfixInstaller {

    private static Logger log = LogManager.getLogger(HotfixInstaller.class.getName());

    public static void main(String[] args) {

        try {

            Config.loadProperties();

            String host = Config.properties.getString(Constants.HOST);
            String port = Config.properties.getString(Constants.PORT);
            String userName = Config.properties.getString(Constants.USER_NAME);
            String password = Config.properties.getString(Constants.PASSWORD);
            String installPackages = Config.properties.getString(Constants.INSTALL_PACKAGES);
            List<String> hotfixes = new ArrayList<String>(Arrays.asList(Config.properties.getString(Constants.HOTFIXES).split(",")));

            log.info("host: " + host);
            log.info("port: " + port);
            log.info("username: " + userName);
            log.info("password: " + password);
            log.info("hotfixes: " + hotfixes.toString());

            HttpHost httpHost = new HttpHost(host, Integer.parseInt(port));
            Credentials credentials = new UsernamePasswordCredentials(userName, password);

            for (String hfName : hotfixes) {

                HttpClient httpClient = Utils.setupClient(credentials);
                processHF(hfName, httpHost, installPackages, httpClient);
            }

            log.info("Finished!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean promptKeyInput(String hfName) {
        boolean userFlag = false;
        System.out.println("\nDo you want to install " + hfName + "? Please check the logs, make sure the previous HF installed correctly and then press \"Y\" to install or press \"N\" to skip.");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        if (StringUtils.isNotBlank(userInput)) {
            if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
                userFlag = true;
            } else if (userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("no")) {
                userFlag = false;
            } else {
                System.out.println("Incorrect input, please enter a valid input.");
                userFlag = promptKeyInput(hfName);
            }
        } else {
            System.out.println("Incorrect input, please enter a valid input.");
            userFlag = promptKeyInput(hfName);
        }

        return userFlag;
    }

    private synchronized static void processHF(String hfName, HttpHost httpHost, String installPackages, HttpClient httpClient) throws IOException {

        if (StringUtils.isNotEmpty(hfName)) {
            if (promptKeyInput(hfName)) {

                System.out.println("Now installing " + hfName);

                PostMethod postMethod = new PostMethod(httpHost.toString() + Constants.CRX_PACKMGR_SERVICE_JSP);

                String hfPath = HotfixInstaller.class.getResource("/" + hfName).getFile();

                if (StringUtils.isNotEmpty(hfPath)) {
                    Part[] parts = Utils.setupParts(hfPath, installPackages);

                    MultipartRequestEntity multipartRequestEntity = new MultipartRequestEntity(parts, postMethod.getParams());

                    postMethod.setRequestEntity(multipartRequestEntity);

                    httpClient.executeMethod(postMethod);

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
