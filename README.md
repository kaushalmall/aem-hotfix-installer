# aem-hotfix-installer
This utility automates the process of installing hotfixes on an AEM instance in the order specified.
It is still a work in progress and will keep evolving so, please **_use at your own risk._**.
If you have any nice additions to the code, please submit an issue and a corresponding PR and I will try my best to include it.

Please look at the current list of issues to see what additions are being considered.

### To use
* Add your hotfix packages to the resources directory.
* Update the config.properties file. By default, this will ONLY upload packages.
    * host= AEM Host
    * port= AEM Port
    * username= AEM Username
    * password= AEM Password
    * installPackages= Flag to determine if the packages should be uploaded and installed (true) or just uploaded (false)
    * hotfixes=List of HFs to be installed, in order. The names here should match the file names of the packages in resources director.
        * for example "acs-aem-commons-content-2.1.0.zip, cq-6.1.0-hotifx-9104-1.0.zip"
* Then run the HotfixInstaller class and follow prompts.
