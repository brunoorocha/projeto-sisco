/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sisco.views;

import br.com.sisco.models.Paciente;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

/**
 *
 * @author brunorocha
 */
public class PacientesListCell extends ListCell<Paciente> {

    @FXML
    private Label nomePaciente;

    @FXML
    private Label telefonePaciente;

    @FXML
    private HBox hbox;

    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(Paciente paciente, boolean empty) {
        super.updateItem(paciente, empty);

        if (empty || paciente == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("PacientesListItem.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            nomePaciente.setText(paciente.getNome());
            telefonePaciente.setText(paciente.getTelefone());

            setText(null);
            setGraphic(hbox);
        }
    }
}
