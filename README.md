# aem-hotfix-installer
This utility automates the process of installing hotfixes on an AEM instance in the order specified.
It is still a work in progress and will keep evolving so, please **_use at your own risk._**
If you have any nice additions to the code, please submit an issue and a corresponding PR and I will try my best to include it.

Please look at the current list of issues to see what additions are being considered.

**I have not tested this for Service Pack Installs.**

### Sample config.properties file
```
host=localhost
port=4502
username=admin
password=admin
installPackages=false
maxNumberOfRetries=10
maxWaitTimeMS=4000
hotfixes=acs-aem-commons-content-2.1.0-min.zip,cq-6.1.0-hotfix-9104-1.0.zip
```


### To use
* Create a folder that will have the config.properties and the hotfix packcages.
* Update the config.properties file.
    * **host**= AEM Host
    * **port**= AEM Port
    * **username**= AEM Username
    * **password**= AEM Password
    * **installPackages**= Flag to determine if the packages should be uploaded and installed (true) or just uploaded (false)
    * **maxNumberOfRetries** = Only used with the "silent" flag. The number of calls that will be made to /crx/packmgr/installstatus.jsp to get the installation status.
    * **maxWaitTimeMS** = Only used with the "silent" flag. The time in ms between calls made to /crx/packmgr/installstatus.jsp.
    * **hotfixes**=List of HFs to be installed, in order. The names here should match the file names of the packages.
        * for example "acs-aem-commons-content-2.1.0.zip, cq-6.1.0-hotifx-9104-1.0.zip"
* Then run executable jar like `java -jar aem-hotfix-installer-1.4-SNAPSHOT-jar-with-dependencies.jar /path/to/directory/containing/config.properties/and/hotfix/packages`

* To check which hotfixes from the properties file is currently installed on the target system run the command with the "check" flag.
    * Example `java -jar aem-hotfix-installer-1.4-SNAPSHOT-jar-with-dependencies.jar /path/to/directory/containing/config.properties/and/hotfix/packages check`

* To run in silent mode execute using the command below. I would recommend using the silent mode **_ONLY_** on non production environments.
   * Example `java -jar aem-hotfix-installer-1.4-SNAPSHOT-jar-with-dependencies.jar /path/to/directory/containing/config.properties/and/hotfix/packages silent`

### Logging
* It creates a hotfix_installer.log file that logs all the package install details.