
package br.com.sisco.controllers;

import br.com.sisco.dao.ConsultaDAO;
import br.com.sisco.dao.PacienteDAO;
import br.com.sisco.models.Consulta;
import br.com.sisco.models.Paciente;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
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
    @FXML private Label labelData;
    @FXML private Label labelHora;
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
    private void btnAgendarConsultaAction() {
        
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
        
        String[] data = datePickerDataNascimento.getValue().toString().split("-");
        
        int dia = Integer.parseInt(data[2]);
        int mes = Integer.parseInt(data[1]);
        int ano = Integer.parseInt(data[0]);
        
        dataNascimento.set(ano, mes, dia);
                
        novoPaciente.setDataNascimento(dataNascimento);
        novoPaciente.setTelefone(textFieldTelefone.getText());
                
        PacienteDAO.adicionarPaciente(novoPaciente);
        
        novoPaciente = PacienteDAO.retornaPaciente(textFieldNomeCompleto.getText());
        
        // Agendando consulta               
        
        data = labelData.getText().split("/");
        
        dia = Integer.parseInt(data[0]);
        mes = Integer.parseInt(data[1]);
        ano = Integer.parseInt(data[2]);
        
        Calendar dataConsulta = Calendar.getInstance();
        dataConsulta.set(ano, mes - 1, dia);
        
        int idConsulta = ConsultaDAO.retornaIdConsulta(dataConsulta, labelHora.getText()+ ":00");
              
        ConsultaDAO.agendarConsulta(idConsulta, novoPaciente.getIdPaciente());
        
        this.modalBtnCancelarAction();
        this.tableAgenda();
    }
    
    private void tableAgenda() {
        ObservableList<String> horarios = ConsultaDAO.listarHorarios();
        
        if(!horarios.isEmpty()) {
            for(int i = 0; i < 4; i++) {                                           
                HBox tc1 = this.tableCellFactory(false);
                Label hr = new Label(horarios.get(i));

                tc1.getChildren().add(hr);            
                gridPaneAgenda.add(tc1, 0, (i + 1));   

                ObservableList<Consulta> consultasNesseHorario = ConsultaDAO.listarConsultasPorHorario(horarios.get(i));

                for(int j = 0; j < 5; j++) {                    

                    if(consultasNesseHorario.get(j).getStatus() != 0) {
                        String nomePaciente = PacienteDAO.listarNomePeloId(consultasNesseHorario.get(j).getIdPaciente());
                        Label consultaMarcada = new Label(nomePaciente);
                        HBox tableCell = this.tableCellFactory(false);

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

                        tableCell.setOnMouseClicked((MouseEvent e) -> {
                            Node element = (Node) e.getSource();
                            Node parent = element.getParent();
                            
                            System.out.println(parent.toString());
                        });
                        
                        gridPaneAgenda.add(tableCell, (j + 1), (i + 1));
                    } else {
                        HBox tableCell = this.tableCellFactory(true);
                        
                        tableCell.setOnMouseClicked((MouseEvent e) -> {
                            Node element = (Node) e.getSource();
                            
                            int col = GridPane.getColumnIndex(element);
                            int row = GridPane.getRowIndex(element);
                            
                            String hora = horarios.get(row - 1);
                            
                            int ano = consultasNesseHorario.get(col - 1).getData().get(Calendar.YEAR);
                            int mes = consultasNesseHorario.get(col - 1).getData().get(Calendar.MONTH) + 1;
                            int dia = consultasNesseHorario.get(col - 1).getData().get(Calendar.DAY_OF_MONTH);
                            
                            String data = "";
                            
                            if(mes < 10) {
                                data = ""+ dia +"/0"+ mes +"/"+ ano;
                            } else {
                                data = ""+ dia +"/"+ mes +"/"+ ano;
                            }                 
                            
                            this.showModal(data, hora);
                        });
                        
                        gridPaneAgenda.add(tableCell, (j + 1), (i + 1));
                        
                    }                                        
                }
            }                

        }
    }
    
    private HBox tableCellFactory(boolean hover) {
        HBox tableCell = new HBox();

        if(hover) {
            tableCell.getStyleClass().add("table-cell");
        } else {
            tableCell.getStyleClass().add("table-cell-nohover");
        }
                
        tableCell.setAlignment(Pos.CENTER);               

        return tableCell;
    }          
    
    private void showModal(String data, String hora) {
        this.btnAgendarConsultaAction();
        
        this.labelData.setText(data);
        this.labelHora.setText(hora);
    }    
}
