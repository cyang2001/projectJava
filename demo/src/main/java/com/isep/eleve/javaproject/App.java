package com.isep.eleve.javaproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.isep.eleve.javaproject.Tools.Constants;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SpringBootApplication
@ComponentScan(basePackages = "com.isep.eleve.javaproject")
public class App extends Application {

  private static ConfigurableApplicationContext context;
  private static Stage primaryStage;

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

  /**
   * Switch to the login view.
   * 
   * @throws Exception
   */
  public static void switchToLogin() throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Constants.LOGIN_VIEW_PATH));
    fxmlLoader.setControllerFactory(context::getBean);
    Parent root = fxmlLoader.load();
    Scene scene = new Scene(root, 300, 275);
    scene.getStylesheets().add(App.class.getResource(Constants.LOGIN_VIEW_CSS_PATH).toExternalForm());
    primaryStage.setScene(scene);
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
    primaryStage.setScene(new Scene(root, 300, 275));
  }

  public static void switchToPortfolioPage() throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Constants.PORTFOLIO_INFORMATION_VIEW_PATH));
    fxmlLoader.setControllerFactory(context::getBean);
    Parent root = fxmlLoader.load();
    primaryStage.setScene(new Scene(root, 300, 275));
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
    primaryStage.setScene(new Scene(root, 300, 275));
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

  public static void main(String[] args) {
    launch(args);
  }
}
