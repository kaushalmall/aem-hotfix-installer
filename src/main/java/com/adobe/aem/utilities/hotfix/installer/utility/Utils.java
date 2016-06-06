package com.adobe.aem.utilities.hotfix.installer.utility;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;

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
}
