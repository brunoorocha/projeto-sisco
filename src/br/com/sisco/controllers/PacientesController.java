/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sisco.controllers;

import br.com.sisco.models.Paciente;
import br.com.sisco.views.PacientesListCell;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

/**
 *
 * @author brunorocha
 */
public class PacientesController implements Initializable {

    @FXML
    private TextField searchField;

    @FXML
    private ListView pacientesListView;

    @FXML
    private StackPane rootPane;

    @FXML
    private Label agendaTab;

    private ObservableList<Paciente> pacientesList;

    public PacientesController() {

        this.pacientesList = FXCollections.observableArrayList();
        this.pacientesList.addAll(
                new Paciente("Bruno Rocha", "(88) 99999-9999"),
                new Paciente("Francisco Alves", "(88) 99999-9999"),
                new Paciente("Gabriela Duarte", "(88) 99999-9999"),
                new Paciente("Bruno Rocha", "(88) 99999-9999"),
                new Paciente("Francisco Alves", "(88) 99999-9999"),
                new Paciente("Gabriela Duarte", "(88) 99999-9999"),
                new Paciente("Bruno Rocha", "(88) 99999-9999"),
                new Paciente("Francisco Alves", "(88) 99999-9999"),
                new Paciente("Gabriela Duarte", "(88) 99999-9999"),
                new Paciente("Bruno Rocha", "(88) 99999-9999"),
                new Paciente("Francisco Alves", "(88) 99999-9999"),
                new Paciente("Gabriela Duarte", "(88) 99999-9999")
        );

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchField.setPromptText("Pesquise um paciente pelo nome");

        pacientesListView.setItems(pacientesList);
        pacientesListView.setCellFactory(pacientesListCell -> new PacientesListCell());

    }

    @FXML
    private void hidePacientesPane() {
        rootPane.setVisible(false);
    }
}
