package com.isep.eleve.javaproject;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.isep.eleve.javaproject.config.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.isep.eleve.javaproject.Tools.Constants;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

@SpringBootApplication
@ComponentScan(basePackages = "com.isep.eleve.javaproject")
public class App extends Application {

  private static ConfigurableApplicationContext context;
  private static Stage primaryStage;
  private static final Logger logger = Logger.getLogger(App.class.getName());
	private Stage stage;
  @Override
  public void init() throws Exception {
    context = SpringApplication.run(App.class);
  }

  @Override
  public void start(Stage stage) throws Exception {
    primaryStage = stage;
    primaryStage.setTitle("JavaFX App");
    switchToLogin();
    primaryStage.show();
  }
  public static void switchToAddEventsPage() throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Constants.ADD_EVENT_VIEW_PATH));
    fxmlLoader.setControllerFactory(context::getBean);
    Parent root = fxmlLoader.load();
    primaryStage.setScene(new Scene(root, 760, 700));
  }

  public static void switchToAnalysisPage() throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Constants.ANALYSIS_VIEW_PATH));
    fxmlLoader.setControllerFactory(context::getBean);
    Parent root = fxmlLoader.load();
    primaryStage.setScene(new Scene(root, 760, 600));
  }

  public static void switchToAssetInformationPage() throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Constants.ANALYSIS_VIEW_PATH));
    fxmlLoader.setControllerFactory(context::getBean);
    Parent root = fxmlLoader.load();
    primaryStage.setScene(new Scene(root, 760, 700));
  }

  public static void switchToBuyAssetPage() throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Constants.BUY_ASSET_VIEW_PATH));
    fxmlLoader.setControllerFactory(context::getBean);
    Parent root = fxmlLoader.load();
    primaryStage.setScene(new Scene(root, 760, 700));
  }

  public static void switchToBuyCryptoPage() throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Constants.BUY_CRYPTO_VIEW_PATH));
    fxmlLoader.setControllerFactory(context::getBean);
    Parent root = fxmlLoader.load();
    primaryStage.setScene(new Scene(root, 760, 700));
  }

  public static void switchToClonePortfolioPage() throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Constants.CLONE_PORTFOLIO_VIEW_PATH));
    fxmlLoader.setControllerFactory(context::getBean);
    Parent root = fxmlLoader.load();
    primaryStage.setScene(new Scene(root, 760, 700));
  }

  public static void switchToCryptoInformationPage() throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Constants.CRYPTO_INFORMATION_VIEW_PATH));
    fxmlLoader.setControllerFactory(context::getBean);
    Parent root = fxmlLoader.load();
    primaryStage.setScene(new Scene(root, 760, 700));
  }

  public static void switchToDeleteEventPage() throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Constants.DELETE_EVENTS_VIEW_PATH));
    fxmlLoader.setControllerFactory(context::getBean);
    Parent root = fxmlLoader.load();
    primaryStage.setScene(new Scene(root, 760, 700));
  }

  public static void switchToDemonstrateValuePage() throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Constants.DEMONSTRATE_VALUE_VIEW_PATH));
    fxmlLoader.setControllerFactory(context::getBean);
    Parent root = fxmlLoader.load();
    primaryStage.setScene(new Scene(root, 760, 700));
  }

  /**
   * Switch to the home view.
   *
   * @throws Exception
   */
  public static void switchToHome() throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Constants.HOME_VIEW_PATH));
    fxmlLoader.setControllerFactory(context::getBean);
    Parent root = fxmlLoader.load();
    primaryStage.setScene(new Scene(root));
  }

  /**
   * Switch to the login view.
   *
   * @throws Exception
   */
  public static void switchToLogin() throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Constants.LOGIN_VIEW_PATH));
    fxmlLoader.setControllerFactory(context::getBean);
    Parent root = fxmlLoader.load();
    Scene scene = new Scene(root);
    scene.getStylesheets().add(App.class.getResource(Constants.LOGIN_VIEW_CSS_PATH).toExternalForm());
    primaryStage.setScene(scene);
  }

  public static void switchToNewPortfolioPage() throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Constants.NEW_PORTFOLIO_VIEW_PATH));
    fxmlLoader.setControllerFactory(context::getBean);
    Parent root = fxmlLoader.load();
    primaryStage.setScene(new Scene(root, 760, 700));
  }

  public static void switchToPortfolioPage() throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Constants.PORTFOLIO_INFORMATION_VIEW_PATH));
    fxmlLoader.setControllerFactory(context::getBean);
    Parent root = fxmlLoader.load();
    primaryStage.setScene(new Scene(root, 760, 700));
  }

  /**
   * Switch to the registration view.
   * 
   * @throws Exception
   */
  public static void switchToRegistration() throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Constants.REGISTRATION_VIEW_PATH));
    fxmlLoader.setControllerFactory(context::getBean);
    Parent root = fxmlLoader.load();
    Scene scene = new Scene(root);
    scene.getStylesheets().add(App.class.getResource(Constants.REGISTRATION_VIEW_CSS_PATH).toExternalForm());
    primaryStage.setScene(scene);
  }

  public static void switchToSellAssetPage() throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Constants.SELL_ASSET_VIEW_PATH));
    fxmlLoader.setControllerFactory(context::getBean);
    Parent root = fxmlLoader.load();
    primaryStage.setScene(new Scene(root, 760, 700));
  }

  public static void switchToSellCryptoPage() throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Constants.SELL_CRYPTO_VIEW_PATH));
    fxmlLoader.setControllerFactory(context::getBean);
    Parent root = fxmlLoader.load();
    primaryStage.setScene(new Scene(root, 760, 700));
  }

  /**
   * Show an alert.
   * 
   * @param title
   * @param header
   * @param content
   */
  public static void showAlert(String title, String header, String content) {
    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
  }

  @Override
  public void stop() throws Exception {
    context.close();
  }


  // new
  private Initializable replaceSceneContent(String fxml) throws Exception {

		FXMLLoader fxmlLoader = new FXMLLoader();
		InputStream in = App.class.getResourceAsStream(fxml);
		fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
    fxmlLoader.setControllerFactory(context::getBean);
		fxmlLoader.setLocation(App.class.getResource(fxml));
		try {
			AnchorPane page = (AnchorPane) fxmlLoader.load(in);
			Scene scene = new Scene(page, ViewConfig.STAGE_WIDTH, ViewConfig.STAGE_HEIGHT);
			stage.setScene(scene);
			stage.sizeToScene();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error");
		} finally {
			in.close();
		}
		return (Initializable) fxmlLoader.getController();
	}


  public static void main(String[] args) {
    launch(args);
  }
}
