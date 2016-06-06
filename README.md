# aem-hotfix-installer
This utility automates the process of installing hotfixes on an AEM instance in the order specified.
It is still a work in progress and will keep evolving so, please **_use at your own risk._**.
If you have any nice additions to the code, please submit a PR and I will try my best to include it.

### To use
* Add your hotfix jars to the resources directory.
* Update the config.properties file. By default, this will ONLY upload packages. Change installPackages to true.
* Then run the HotfixInstaller class and follow prompts.
