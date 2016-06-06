# aem-hotfix-installer
This utility automates the process of installing hotfixes on an AEM instance in the order specified.
It is still a work in progress and will keep evolving so, please **_use at your own risk._**
If you have any nice additions to the code, please submit an issue and a corresponding PR and I will try my best to include it.

Please look at the current list of issues to see what additions are being considered.

### Sample config.properties file
```
host=localhost
port=4502
username=admin
password=admin
installPackages=false
hotfixes=acs-aem-commons-content-2.1.0-min.zip,cq-6.1.0-hotfix-9104-1.0.zip
```


### To use
* Create a folder that will have the config.properties and the hotfix packcages.
* Update the config.properties file.
    * host= AEM Host
    * port= AEM Port
    * username= AEM Username
    * password= AEM Password
    * installPackages= Flag to determine if the packages should be uploaded and installed (true) or just uploaded (false)
    * hotfixes=List of HFs to be installed, in order. The names here should match the file names of the packages.
        * for example "acs-aem-commons-content-2.1.0.zip, cq-6.1.0-hotifx-9104-1.0.zip"
* Then run executable jar like `java -jar aem-hotfix-installer-1.0-SNAPSHOT.jar /path/to/directory/containing/config.properties/and/hotfix/packages`

### To use (alternatively via your IDE)
* Add/remove your hotfix packages to the "resources" directory of the project.
* Update the config.properties file.
    * host= AEM Host
    * port= AEM Port
    * username= AEM Username
    * password= AEM Password
    * installPackages= Flag to determine if the packages should be uploaded and installed (true) or just uploaded (false)
    * hotfixes=List of HFs to be installed, in order. The names here should match the file names of the packages.
        * for example "acs-aem-commons-content-2.1.0.zip, cq-6.1.0-hotifx-9104-1.0.zip"
* Then run HotfixInstaller main class.

