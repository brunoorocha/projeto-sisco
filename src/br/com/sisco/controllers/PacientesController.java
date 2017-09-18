/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sisco.controllers;

import br.com.sisco.dao.PacienteDAO;
import br.com.sisco.models.Paciente;
import br.com.sisco.views.PacientesListCell;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

/**
 *
 * @author brunorocha
 */
public class PacientesController implements Initializable {

    @FXML private TextField searchField;
    @FXML private ListView pacientesListView;
    @FXML private StackPane rootPane;
    @FXML private Label agendaTab;
    
    @FXML private Button editarButton;
    @FXML private Button salvarButton;
                
    
    // Campos da área de Dados Pessoais             
    @FXML private TextField textFieldNomeCompleto;
    @FXML private TextField textFieldMatricula;
    @FXML private TextField textFieldTelefone;
    @FXML private ChoiceBox choiceBoxVinculo;
    @FXML private DatePicker datePickerDataNascimento;
    

    private ObservableList<Paciente> pacientesList;

    public PacientesController() {

        this.pacientesList = PacienteDAO.listarPacientes();
                       
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
    
    @FXML
    private void buscarPaciente() {
        String nome = this.searchField.getText();
        
        this.pacientesList.clear();        
        this.pacientesList.setAll(new PacienteDAO().buscarPaciente(nome));
    }
    
    
    /*
    
        Action do botão de editar
    
    */   
    
    @FXML
    private void editarButtonAction() {
        salvarButton.setVisible(true);
        
        this.setDisableFields(false);
    }
    
    
    /*
    
        Action do botão de salvar
    
    */   
    
    @FXML
    private void salvarButtonAction() {
        salvarButton.setVisible(false);
        
        this.setDisableFields(true);
    }            
    
    private void setDisableFields(boolean value) {
        textFieldNomeCompleto.setDisable(value);
        textFieldMatricula.setDisable(value);
        textFieldTelefone.setDisable(value);
        choiceBoxVinculo.setDisable(value);
        datePickerDataNascimento.setDisable(value);
    }
}
