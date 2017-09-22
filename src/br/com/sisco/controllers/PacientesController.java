/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sisco.controllers;

import br.com.sisco.dao.PacienteDAO;
import br.com.sisco.dao.ProcedimentoDAO;
import br.com.sisco.dao.ProntuarioDAO;
import br.com.sisco.models.Paciente;
import br.com.sisco.models.Procedimento;
import br.com.sisco.models.Prontuario;
import br.com.sisco.views.PacientesListCell;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import javafx.scene.layout.VBox;

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
    @FXML private Button adicionarProcedimentoButton;
                
    
    // Campos da área de Dados Pessoais             
    @FXML private TextField textFieldNomeCompleto;
    @FXML private TextField textFieldMatricula;
    @FXML private TextField textFieldTelefone;
    @FXML private ChoiceBox choiceBoxVinculo;
    @FXML private DatePicker datePickerDataNascimento;
    
    // Campos da área de anamnese
    @FXML private TextField textFieldQueixa;
    @FXML private TextField textFieldDoenca;
    @FXML private TextField textFieldReumatica;
    @FXML private TextField textFieldTratamento;
    @FXML private TextField textFieldMedicacao;
    @FXML private TextField textFieldGravida;
    @FXML private TextField textFieldAlergico;
    @FXML private TextField textFieldHipertenso;
    @FXML private TextField textFieldDiabetico;
    @FXML private TextField textFieldGastricos;
    @FXML private TextField textFieldAnestesia;
    @FXML private TextField textFieldHemorragia;
    
    // Campos da área de procedimentos
    @FXML private TextField textFieldDataConsulta;
    @FXML private TextField textFieldResumo;
    @FXML private Button buttonCancelar;
    @FXML private Button buttonConcluir;
    
    @FXML private VBox modalAdicionarProcedimento;
    
    
    private int selectedIdPaciente = 0;
    private int selectedIdProntuario = 0;
    

    private ObservableList<Paciente> pacientesList;

    public PacientesController() {

        this.pacientesList = PacienteDAO.listarPacientes();
                       
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> vinculoOptions = FXCollections.observableArrayList("", "Aluno", "Professor", "Funcionário", "Outro");
        searchField.setPromptText("Pesquise um paciente pelo nome");

        pacientesListView.setItems(pacientesList);
        pacientesListView.setCellFactory(pacientesListCell -> new PacientesListCell());
        choiceBoxVinculo.setItems(vinculoOptions);                
        
        pacientesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Paciente p = (Paciente) newValue;
                
                Prontuario prontuario = ProntuarioDAO.listarProntuario(p.getIdPaciente());
                
                if(prontuario != null) {
                    selectedIdPaciente = p.getIdPaciente();
                    selectedIdProntuario = prontuario.getIdProntuario();                

                    preencherCampos(p.getNomeCompleto());        
                    if(prontuario != null) {
                        textFieldQueixa.setText(prontuario.getQueixaPrincipal());
                        textFieldDoenca.setText(prontuario.getDoencaGrave());
                        textFieldReumatica.setText(prontuario.getFebreReumatica());
                        textFieldTratamento.setText(prontuario.getTratamentoMedico());
                        textFieldMedicacao.setText(prontuario.getMedicacao());
                        textFieldGravida.setText(prontuario.getGravida());
                        textFieldAlergico.setText(prontuario.getAlergico());
                        textFieldHipertenso.setText(prontuario.getHipertenco());
                        textFieldDiabetico.setText(prontuario.getDiabetico());
                        textFieldGastricos.setText(prontuario.getProblemasGrastricos());
                        textFieldAnestesia.setText(prontuario.getAnestesiaLocal());
                        textFieldHemorragia.setText(prontuario.getHemorragia());
                    }   
                }
            }
        });
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
        this.pacientesListView.getSelectionModel().select(0);
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
        
        Prontuario pEditado = new Prontuario();
        
        pEditado.setIdPaciente(selectedIdPaciente);
        pEditado.setQueixaPrincipal(textFieldQueixa.getText());        
        pEditado.setDoencaGrave(textFieldDoenca.getText());                
        pEditado.setFebreReumatica(textFieldReumatica.getText());
        pEditado.setTratamentoMedico(textFieldTratamento.getText());
        pEditado.setMedicacao(textFieldMedicacao.getText());
        pEditado.setGravida(textFieldGravida.getText());
        pEditado.setAlergico(textFieldAlergico.getText());
        pEditado.setHipertenco(textFieldHipertenso.getText());
        pEditado.setDiabetico(textFieldDiabetico.getText());
        pEditado.setProblemasGrastricos(textFieldGastricos.getText());
        pEditado.setAnestesiaLocal(textFieldAnestesia.getText());
        pEditado.setHemorragia(textFieldHemorragia.getText());
        
        ProntuarioDAO.editarProntuario(pEditado);
        
        this.setDisableFields(true);
    }            
    
    private void setDisableFields(boolean value) {
        textFieldNomeCompleto.setDisable(value);
        textFieldMatricula.setDisable(value);
        textFieldTelefone.setDisable(value);
        choiceBoxVinculo.setDisable(value);
        datePickerDataNascimento.setDisable(value);
        
        textFieldQueixa.setDisable(value);
        textFieldDoenca.setDisable(value);
        textFieldReumatica.setDisable(value);
        textFieldTratamento.setDisable(value);
        textFieldMedicacao.setDisable(value);
        textFieldGravida.setDisable(value);
        textFieldAlergico.setDisable(value);
        textFieldHipertenso.setDisable(value);
        textFieldDiabetico.setDisable(value);
        textFieldGastricos.setDisable(value);
        textFieldAnestesia.setDisable(value);
        textFieldHemorragia.setDisable(value);
    }
    
    private void preencherCampos(String nome){
        Paciente paciente = PacienteDAO.retornaPaciente(nome);
        
        if(paciente != null) {
            textFieldNomeCompleto.setText(paciente.getNomeCompleto());
            textFieldMatricula.setText(paciente.getMatricula());
            textFieldTelefone.setText(paciente.getTelefone());
            choiceBoxVinculo.setValue(paciente.getVinculo());                

            int dia = paciente.getDataNascimento().get(Calendar.DAY_OF_MONTH);
            int mes = paciente.getDataNascimento().get(Calendar.MONTH) + 1;
            int ano = paciente.getDataNascimento().get(Calendar.YEAR);

            LocalDate data = LocalDate.of(ano, mes, dia);
            datePickerDataNascimento.setValue(data);
        }                                      
    }
    
    
    /*
    
        Action do botão de adicionar procedimento
    
    */
    @FXML
    private void adicionarProcedimentoButtonAction() {
        modalAdicionarProcedimento.setVisible(true);
    }
    
    
    @FXML
    private void buttonCancelarAction() {
        textFieldDataConsulta.setText("");
        textFieldResumo.setText("");
        
        modalAdicionarProcedimento.setVisible(false);
    }
    
    
    @FXML
    private void buttonConcluirAction() {
        
        Procedimento procedimento = new Procedimento();
        
        procedimento.setIdConsulta(1);
        procedimento.setIdProntuario(selectedIdProntuario);
        procedimento.setResumo(textFieldResumo.getText());        
        
        ProcedimentoDAO.adicionarProcedimento(procedimento);
        
        this.buttonCancelarAction();
    }
    
}
