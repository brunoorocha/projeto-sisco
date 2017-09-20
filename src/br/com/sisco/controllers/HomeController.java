
package br.com.sisco.controllers;

import br.com.sisco.dao.ConsultaDAO;
import br.com.sisco.dao.PacienteDAO;
import br.com.sisco.models.Consulta;
import br.com.sisco.models.Paciente;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author brunorocha
 */
public class HomeController implements Initializable {

    @FXML private StackPane rootPane;
    @FXML private Button btnAgendarConsulta;
    @FXML private Button btnCancelarConsulta;
    @FXML private ChoiceBox choiceBoxTurno;
    @FXML private ChoiceBox choiceBoxMes;
        
    @FXML private HBox modalPane;
    @FXML private Button modalBtnConcluir;
    @FXML private Button modalBtnCancelar;
    @FXML private Label pacientesTab;
    
    @FXML private TextField textFieldNomeCompleto;
    @FXML private TextField textFieldMatricula;
    @FXML private TextField textFieldTelefone;
    @FXML private ChoiceBox choiceBoxVinculo;
    @FXML private DatePicker datePickerDataNascimento;           
    
    @FXML private GridPane gridPaneAgenda;
    
    private Parent pacientesPane;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> turnoOptions = FXCollections.observableArrayList("Manhã", "Tarde", "Noite");
        ObservableList<String> mesOptions = FXCollections.observableArrayList("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro");
        ObservableList<String> vinculoOptions = FXCollections.observableArrayList("", "Aluno", "Professor", "Funcionário", "Outro");

        choiceBoxTurno.setItems(turnoOptions);
        choiceBoxTurno.setValue("Manhã");

        choiceBoxMes.setItems(mesOptions);
        choiceBoxMes.setValue("Setembro");
        
        choiceBoxVinculo.setItems(vinculoOptions);
        choiceBoxVinculo.setValue("");
        
        datePickerDataNascimento.setValue(LocalDate.now());

        pacientesPane = this.loadPacientesTab();
        pacientesPane.setVisible(false);
        
        
        ObservableList<String> listaNomes = PacienteDAO.listarNomes();
        TextFields.bindAutoCompletion(textFieldNomeCompleto, listaNomes);
        
        this.tableAgenda();
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
    
    @FXML
    private void btnAgendarConsultaAction(ActionEvent event) {
        
        this.modalPane.setVisible(true);
        
        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), this.modalPane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);
        fadeIn.play();
                
    }
    
    @FXML
    private void modalBtnCancelarAction() {
        
        textFieldNomeCompleto.setText("");
        textFieldMatricula.setText("");
        textFieldTelefone.setText("");
        
        choiceBoxVinculo.setValue("");
        datePickerDataNascimento.setValue(LocalDate.now());
        
        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), this.modalPane);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);
        
        fadeOut.setOnFinished((ActionEvent event) -> {
            modalPane.setVisible(false);
        });
        
        fadeOut.play();                
    }
    
    @FXML
    private void modalBtnConcluirAction() {
        Paciente novoPaciente = new Paciente();
        
        novoPaciente.setNomeCompleto(textFieldNomeCompleto.getText());
        novoPaciente.setVinculo(choiceBoxVinculo.getValue().toString());
        novoPaciente.setMatricula(textFieldMatricula.getText());
        
        Calendar dataNascimento = Calendar.getInstance();        
        dataNascimento.setTime(new Date(datePickerDataNascimento.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        
        novoPaciente.setDataNascimento(dataNascimento);
        novoPaciente.setTelefone(textFieldTelefone.getText());
        
        PacienteDAO.adicionarPaciente(novoPaciente);
        
        this.modalBtnCancelarAction();
    }
    
    private void tableAgenda() {
        ObservableList<String> horarios = ConsultaDAO.listarHorarios();
        
        if(!horarios.isEmpty()) {
        for(int i = 0; i < 4; i++) {                                           
            HBox tc1 = this.tableCellFactory();
            Label hr = new Label(horarios.get(i));
            
            tc1.getChildren().add(hr);            
            gridPaneAgenda.add(tc1, 0, (i + 1));   
            
            ObservableList<Consulta> consultasNesseHorario = ConsultaDAO.listarConsultasPorHorario(horarios.get(i));
                                    
            for(int j = 0; j < 5; j++) {
                HBox tableCell = this.tableCellFactory();                
                
                if(consultasNesseHorario.get(j).getStatus() != 0) {
                    String nomePaciente = PacienteDAO.listarNomePeloId(consultasNesseHorario.get(j).getIdPaciente());
                    Label consultaMarcada = new Label(nomePaciente);
                    
                    tableCell.getChildren().add(consultaMarcada);                                        
                    
                    if(consultasNesseHorario.get(j).getStatus() == 1) {                    
                        consultaMarcada.getStyleClass().add("agendamento-marcado");
                        
                        Tooltip tooltip = new Tooltip("Agendamento marcado para\n "+ nomePaciente);
                        consultaMarcada.setTooltip(tooltip);
                    } else if(consultasNesseHorario.get(j).getStatus() == 2) {
                        consultaMarcada.getStyleClass().add("agendamento-cancelado");
                        
                        Tooltip tooltip = new Tooltip("Agendamento cancelado para\n "+ nomePaciente);
                        consultaMarcada.setTooltip(tooltip);
                    }
                    
                    gridPaneAgenda.add(tableCell, (j + 1), (i + 1));
                }                                
            }
        }                
        
//        for(int i = 0; i < 6; i++) {
//            for(int j = 1; j < 5; j++) {
//                int idx = (6 * (j - 1)) + i;
//                
//                if(idx % 6 == 0 || idx == 0) {
//                    gridPaneAgenda.add(new Label(consultas.get(idx + 1).getHora()), i, j);
//                    
//                    for(int k = 0; k) {
//                    
//                    }
//                }
//            }
//        }
        }
    }
    
    private HBox tableCellFactory() {
        HBox tableCell = new HBox();

        //tableCell.getStyleClass().add("table-cell");
        tableCell.getStyleClass().add("table-cell-nohover");
        tableCell.setAlignment(Pos.CENTER);

        return tableCell;
    }
}
