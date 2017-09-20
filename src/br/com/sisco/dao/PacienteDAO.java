
package br.com.sisco.dao;

import br.com.sisco.database.ConnectionFactory;
import br.com.sisco.models.Paciente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author brunorocha
 */
public class PacienteDAO {
    
    public static ObservableList<Paciente> listarPacientes() {                     
        return buscarPaciente("");
    }        
    
    public static ObservableList<Paciente> buscarPaciente(String nome) {        
        
        ObservableList<Paciente> lista = FXCollections.observableArrayList();                
        PreparedStatement ps;
        
        try(Connection conn = ConnectionFactory.getConnection()) {
            
            ps = conn.prepareStatement("SELECT * FROM paciente WHERE nomeCompleto LIKE ? ORDER BY nomeCompleto ASC");
            ps.setString(1, '%'+ nome +'%');
            
            ResultSet resultSet = ps.executeQuery();
            
            while(resultSet.next()) {
                Paciente paciente = new Paciente();
                
                paciente.setIdPaciente(resultSet.getInt("idPaciente"));                
                paciente.setNomeCompleto(resultSet.getString("nomeCompleto"));                
                
                Calendar dataNascimento = Calendar.getInstance();
                dataNascimento.setTime(resultSet.getDate("dataNascimento"));
                paciente.setDataNascimento(dataNascimento);
                
                paciente.setMatricula(resultSet.getString("matricula"));
                paciente.setVinculo(resultSet.getString("vinculo"));
                paciente.setTelefone(resultSet.getString("telefone"));
                
                lista.add(paciente);
            }
            
            ps.close();
            return lista;
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }                
    }
    
    public static ObservableList<String> listarNomes() {
        ObservableList<String> lista = FXCollections.observableArrayList();                
        PreparedStatement ps;
        
        try(Connection conn = ConnectionFactory.getConnection()) {
            
            ps = conn.prepareStatement("SELECT nomeCompleto FROM paciente");                        
            ResultSet resultSet = ps.executeQuery();
            
            while(resultSet.next()) {                                
                lista.add(resultSet.getString("nomeCompleto"));
            }
            
            ps.close();
            return lista;
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }    
    }
    
    public static void adicionarPaciente(Paciente paciente) {
        PreparedStatement ps;
        
        try(Connection conn = ConnectionFactory.getConnection()) {
            
            ps = conn.prepareStatement("INSERT INTO paciente VALUES(NULL, ?, ?, ?, ?, ?)");                                   
            
            ps.setString(1, paciente.getNomeCompleto());
            ps.setDate(2, new Date(paciente.getDataNascimento().getTime().getTime()));
            ps.setString(3, paciente.getMatricula());
            ps.setString(4, paciente.getVinculo());
            ps.setString(5, paciente.getTelefone());            
            
            ps.execute();
            System.out.println("Paciente adicionado!");                        
            
            ObservableList<Paciente> pesquisa = buscarPaciente(paciente.getNomeCompleto());
            
            if(pesquisa.isEmpty()){
                System.out.println("EMPTY!");
            } else {
                paciente.setIdPaciente(pesquisa.get(0).getIdPaciente());            
                ProntuarioDAO.adicionarProntuario(paciente.getIdPaciente());                        
            }
            
            ps.close();
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static String listarNomePeloId(int id) {        
        String nome = null;
        PreparedStatement ps;
        
        try(Connection conn = ConnectionFactory.getConnection()) {
            
            ps = conn.prepareStatement("SELECT nomeCompleto FROM paciente WHERE idPaciente = ?");                        
            ps.setInt(1, id);
            
            ResultSet resultSet = ps.executeQuery();
            
            if(resultSet.next()) {                                
                nome = resultSet.getString("nomeCompleto");
            }
            
            ps.close();
            return nome;
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }    
    }
    
    public static void atualizarPaciente(Paciente paciente) {
        
    }
    
    public static void removerPaciente(int id) {
        
    }
    
    
    public static Paciente retornaPaciente (String nome){
        
        PreparedStatement ps;
        Paciente paciente = null;
        try(Connection conn = ConnectionFactory.getConnection()) {
            
            ps = conn.prepareStatement("SELECT * FROM paciente WHERE nomeCompleto = ?");
            ps.setString(1, nome);
            ResultSet resultSet = ps.executeQuery();
            
            if(resultSet.next()){
                paciente = new Paciente();
                paciente.setNomeCompleto(resultSet.getString("nomeCompleto"));                
                
                Calendar dataNascimento = Calendar.getInstance();
                dataNascimento.setTime(resultSet.getDate("dataNascimento"));
                paciente.setDataNascimento(dataNascimento);
                
                paciente.setMatricula(resultSet.getString("matricula"));
                paciente.setVinculo(resultSet.getString("vinculo"));
                paciente.setTelefone(resultSet.getString("telefone"));
                                
            }
            
            ps.close();            
        }catch(SQLException e) {
            throw new RuntimeException(e);
            
        }
        
        return paciente;
    }
    
    
}
