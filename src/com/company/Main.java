package com.company;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.stage.Stage;



import java.util.Objects;

public class Main extends Application{
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FXML/firstapp.fxml")));// fxml файлмен байланыстыру
        primaryStage.setTitle("Bestemshe"); // Сценаға тақырып беру немесе аты
        primaryStage.resizableProperty().setValue(Boolean.FALSE); // Чтобы удалить кнопку этапа JavaFX развернуть.
        primaryStage.setScene(new Scene(root , 430, 500)); // Сцена өлшемі
        Image icon = new Image(getClass().getResourceAsStream("img/logo.png")); // логотип
        primaryStage.getIcons().add(icon); // логотипті сценаға енгізу
        primaryStage.show(); // экранға шығару
    }
    public static void main(String[] args) {launch(args);} // запуск
}