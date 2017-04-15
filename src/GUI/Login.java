package GUI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class Login extends Application {
 
              String user = "JoseM";
              String pw = "password";
              String checkUser, checkPw;
 
              public static void main(String[] args) {
                           launch(args);
 
              }
 
              @Override
              public void start(Stage primaryStage) {
                           primaryStage.setTitle("Administrator Login");
 
                           Label error = new Label("User name/Password is Incorrect!");
                           error.setVisible(false);
 
                           BorderPane bp = new BorderPane();
                           bp.setPadding(new Insets(10, 50, 50, 50));
 
                           bp.setStyle("-fx-background-color: black");
 
                           // Adding HBox
                           HBox hb = new HBox();
                           hb.setPadding(new Insets(20, 20, 20, 30));
 
                           // Adding GridPane
                           GridPane gridPane = new GridPane();
                           gridPane.setPadding(new Insets(20, 20, 20, 20));
                           gridPane.setHgap(5);
                           gridPane.setVgap(5);
 
                           // Implementing Nodes for GridPane
                           Label lblUserName = new Label("Username");
                           final TextField txtUserName = new TextField();
                           Label lblPassword = new Label("Password");
                           final PasswordField pf = new PasswordField();
                           Button btnLogin = new Button("Login");
                           final Label lblMessage = new Label();
 
                           // Adding Nodes to GridPane layout
                           gridPane.add(lblUserName, 0, 0);
                           gridPane.add(txtUserName, 1, 0);
                           gridPane.add(lblPassword, 0, 1);
                           gridPane.add(pf, 1, 1);
                           gridPane.add(btnLogin, 2, 1);
                           gridPane.add(lblMessage, 1, 2);
 
                           // Reflection for gridPane
                           Reflection r = new Reflection();
                           r.setFraction(0.5f);
                           gridPane.setEffect(r);
 
                           // DropShadow effect
                           DropShadow dropShadow = new DropShadow();
                           dropShadow.setOffsetX(5);
                           dropShadow.setOffsetY(5);
 
                           // Adding text and DropShadow effect to it
                           Text text = new Text("Administrator Login");
                           text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
                           text.setEffect(dropShadow);
 
                           // Adding text to HBox
                           hb.getChildren().add(text);
 
                           // Add ID's to Nodes
                           bp.setId("bp");
                           gridPane.setId("root");
                           btnLogin.setId("btnLogin");
                           text.setId("text");
 
                           bp.setBottom(error);
                           error.setTextFill(Color.RED);
                           error.setFont(Font.font(java.awt.Font.SERIF, 30));
 
                           // Action for btnLogin
 
                           BorderPane bp2 = new BorderPane();
 
                           HBox hb2 = new HBox();
 
                           Text Welcome = new Text("Welcome Jose Macias!");
                           hb2.getChildren().add(Welcome);
                           Welcome.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
 
                           // Set id's for css for BorderPane2
                           bp2.setId("bp2");
                           hb2.setId("hb2");
 
                           // Create new scene for BorderPane2
                           Scene rosterSelectionScene = new Scene(bp2, 450, 350);
                           rosterSelectionScene.getStylesheets()
                                                       .add(getClass().getClassLoader().getResource("MainScreen.css").toExternalForm());
 
                            // Create dropdown menu for BorderPane2
                           ObservableList<String> options = FXCollections.observableArrayList("Fall 2016", "Spring 2017");
                           final ComboBox<String> SelectRoster = new ComboBox<String>(options);
 
                           SelectRoster.setValue("Select Roster");
 
                           Button next = new Button("submit");
 
                           HBox buttonAlign = new HBox();
                           buttonAlign.setAlignment(Pos.CENTER);
 
                           buttonAlign.getChildren().add(next);
 
                           // Add layouts to BorderPane2
                           bp2.setTop(hb2);
                           bp2.setCenter(SelectRoster);
                           bp2.setBottom(buttonAlign);
 
                           // Selecting a roster button action
                           next.setOnAction(value -> {
                                         System.out.println(SelectRoster.getValue());
                           });
 
                           // Login button action
                           btnLogin.setOnAction(value -> {
                                         if (txtUserName.getText().equals(user) && pf.getText().equals(pw)) {
                                                       primaryStage.setScene(rosterSelectionScene);
                                                       primaryStage.show();
                                         } else {
                                                       error.setVisible(true);
                                         }
                           });
 
                           // Add HBox and GridPane layout to BorderPane Layout
                           bp.setTop(hb);
                           bp.setCenter(gridPane);
 
                           // Adding BorderPane to the scene and loading CSS
                           Scene scene = new Scene(bp);
                            scene.getStylesheets().add(getClass().getClassLoader().getResource("login.css").toExternalForm());
                           primaryStage.setScene(scene);
                           primaryStage.titleProperty()
                                                       .bind(scene.widthProperty().asString().concat(" : ").concat(scene.heightProperty().asString()));
                           // primaryStage.setResizable(false);
                           primaryStage.show();
 
              }
 
}
