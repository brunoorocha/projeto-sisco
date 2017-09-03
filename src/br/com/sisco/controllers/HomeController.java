/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sisco.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author brunorocha
 */
public class HomeController implements Initializable {

    @FXML
    private StackPane rootPane;

    @FXML
    private Button btnAgendarConsulta, btnCancelarConsulta;

    @FXML
    private ChoiceBox choiceBoxTurno, choiceBoxMes;

    @FXML
    private HBox modalPane;

    @FXML
    private Label pacientesTab;

    private Parent pacientesPane;

    @FXML
    private void btnAgendarConsultaAction(ActionEvent event) {
        this.modalPane.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> turnoOptions = FXCollections.observableArrayList("Manhã", "Tarde", "Noite");
        ObservableList<String> mesOptions = FXCollections.observableArrayList("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro");

        choiceBoxTurno.setItems(turnoOptions);
        choiceBoxTurno.setValue("Manhã");

        choiceBoxMes.setItems(mesOptions);
        choiceBoxMes.setValue("Agosto");

        pacientesPane = this.loadPacientesTab();
        pacientesPane.setVisible(false);
    }

    @FXML
    public void showPacientesTab() {
        pacientesPane.setVisible(true);
    }

    private Parent loadPacientesTab() {
        try {

            Parent pacientesPane = (StackPane) FXMLLoader.load(getClass().getResource("/br/com/sisco/views/Pacientes.fxml"));
            rootPane.getChildren().add(pacientesPane);

            return pacientesPane;

        } catch (IOException ex) {
            System.out.println("Não foi possível carregar o arquivo Pacientes.fxml");
        }

        return null;
    }
}
