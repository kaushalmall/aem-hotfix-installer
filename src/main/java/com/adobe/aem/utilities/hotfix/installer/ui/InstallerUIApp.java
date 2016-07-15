/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adobe.aem.utilities.hotfix.installer.ui;

import com.adobe.aem.utilities.hotfix.installer.utility.ConnectionManager;
import java.util.Locale;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author brobert
 */
public class InstallerUIApp extends Application {

    private Stage applicationWindow;
    private InstallerAppController appController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        applicationWindow = primaryStage;
        Locale locale = Locale.getDefault();
//        ApplicationState.getInstance().setResourceBundle(ResourceBundle.getBundle("Bundle", locale));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InstallerApp.fxml"));
//        loader.setResources(ApplicationState.getInstance().getResourceBundle());
        loader.load();
        Parent root = loader.getRoot();
        appController = loader.getController();

        Scene scene = new Scene(root);
//        scene.getStylesheets().add("/styles/style.css");

        primaryStage.setTitle("AEM Hotfix Installer");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            ConnectionManager.getInstance().shutdown();
            Platform.exit();
            System.exit(0);
        });    }
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
