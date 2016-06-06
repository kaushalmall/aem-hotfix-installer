package com.adobe.aem.utilities.hotfix.installer.utility;


import java.util.ResourceBundle;

/**
 * Created by kmall.
 */
public class Config {

    public static ResourceBundle properties = null;

    public static void loadProperties() {

        if( properties == null ){
            properties = ResourceBundle.getBundle(Constants.PROPERTIES_FILENAME);
        }
    }
}
