package com.adobe.aem.utilities.hotfix.installer;

/**
 *
 * @author brobert
 */
public abstract class HotfixInstallerService {

    private static HotfixInstallerService instance;
    public static HotfixInstallerService getInstance() {
        return instance;
    }
    public static void registerInstance(HotfixInstallerService svc) {
        instance = svc;
    }
    
    
}
