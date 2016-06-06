package com.adobe.aem.utilities.hotfix.installer.utility;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by kmall.
 */
public class Config {

    public static ResourceBundle properties = null;

    public static void loadProperties() {

        if( properties == null ){
            properties = ResourceBundle.getBundle( Constants.PROPERTIES_FILENAME );
        }
    }

    public static void loadProperties( String baseFolder ) throws MalformedURLException {
        File file = new File( baseFolder );
        URL[] urls = {file.toURI().toURL()};
        ClassLoader loader = new URLClassLoader( urls );
        if( properties == null ){
            properties = ResourceBundle.getBundle( Constants.PROPERTIES_FILENAME, Locale.getDefault(), loader );
        }
    }
}
