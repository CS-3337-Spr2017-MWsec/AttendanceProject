package GUI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class Main extends Application {
 
              Button button;
              Stage window;
 
              ArrayList<Student> studentList = new ArrayList<>();
 
              @Override
              public void start(Stage primaryStage) throws Exception {
 
                           window = primaryStage;
                           window.setTitle("Student Verification Window");
 
                           Student student = new Student("Teky", "A", 1, "ta.gmail.com");
                           Student student2 = new Student("sdfd", "A", 1, "ta.gmail.com");
                           studentList.add(student);
                           studentList.add(student2);
 
                           BorderPane bp = new BorderPane();
 
                           bp.setStyle("-fx-background-color: black");
 
                           Text text1 = new Text(120, 25, student.getFirstName() + ", You Have Been Logged in!");
 
                           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
                           LocalDateTime now = LocalDateTime.now();
 
                           Text text2 = new Text(dtf.format(now));
                           Text text3 = new Text();
 
                           // text3.setText(
 
                           // studentList.get(0).getFirstName() + ", " +
                           // studentList.get(0).getLastName() + ", " +
                           // studentList.get(0).getId());
 
                           Button button = new Button();
                           button.setText("OKAY");
                          
 
                           button.setOnAction(value -> {
                                         Stage stage = (Stage) button.getScene().getWindow();
                                         stage.close();
                           });
 
                           text2.setFill(Color.CHOCOLATE);
                           text2.setFont(Font.font(java.awt.Font.SERIF, 30));
 
                           text3.setFill(Color.CHOCOLATE);
                           text3.setFont(Font.font(java.awt.Font.SERIF, 30));
 
                           text1.setFill(Color.CHOCOLATE);
                           text1.setFont(Font.font(java.awt.Font.SERIF, 35));
 
                           VBox vbox = new VBox();
                           vbox.setAlignment(Pos.CENTER);
                           vbox.setPrefHeight(200);
                           vbox.setPadding(new Insets(120, 0, 0, 0));
 
                           HBox hbox = new HBox();
                           hbox.setAlignment(Pos.CENTER);
                           hbox.setPrefHeight(300);
                           hbox.setPrefWidth(300);
 
                           bp.setTop(vbox);
                           bp.setCenter(hbox);
 
                           vbox.getChildren().addAll(text1, text2, text3);
                           hbox.getChildren().addAll(button);
 
                           // DropShadow effect
                           DropShadow dropShadow = new DropShadow();
                           dropShadow.setOffsetX(5);
                           dropShadow.setOffsetY(5);
 
                           Scene scene = new Scene(bp, 550, 450);
                            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                           window.setScene(scene);
                           window.show();
 
              }
 
              public static void main(String[] args) {
                           launch(args);
              }
}