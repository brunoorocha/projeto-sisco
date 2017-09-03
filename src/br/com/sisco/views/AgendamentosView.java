/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sisco.views;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author brunorocha
 */
public class AgendamentosView extends Application {

    @Override
    public void start(Stage stage) {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        } catch (IOException ex) {
            System.out.println("Arquivo Home.fxml não existe ou não está disponível!");
        }

        Scene scene = new Scene(root);

        scene.getStylesheets().add("http://fonts.googleapis.com/css?family=Montserrat:400,700");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.setTitle("Sisco - Agendamentos");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
