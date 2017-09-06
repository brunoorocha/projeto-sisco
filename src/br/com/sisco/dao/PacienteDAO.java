
package br.com.sisco.dao;

import br.com.sisco.models.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author brunorocha
 */
public class PacienteDAO {
    
    public ObservableList<Paciente> listarPacientes() {        
        ObservableList<Paciente> lista = FXCollections.observableArrayList();                
        
        return lista;
    }        
    
    public Paciente buscarPaciente(String nome) {
        Paciente paciente = new Paciente();
        
        return paciente;
    }
    
    public void adicionarPaciente(Paciente paciente) {
        
    }
    
    public void atualizarPaciente(Paciente paciente) {
        
    }
    
    public void removerPaciente(int id) {
        
    }
    
}
