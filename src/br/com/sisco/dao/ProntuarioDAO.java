
package br.com.sisco.dao;

import br.com.sisco.database.ConnectionFactory;
import br.com.sisco.models.Procedimento;
import br.com.sisco.models.Prontuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author brunorocha
 */
public class ProntuarioDAO {
    public static void adicionarProntuario(int idPaciente) {
        
        PreparedStatement ps;
        
        try(Connection conn = ConnectionFactory.getConnection()) {
            
            ps = conn.prepareStatement("INSERT INTO prontuario VALUES(NULL, '', '', '', '', '', '', '', '', '', '', '', '', ?)");
            ps.setInt(1, idPaciente);                             
            
            ps.execute();
            System.out.println("Prontuario criado!");
            
            ps.close();
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void editarProntuario(Prontuario prontuario) {
        
        PreparedStatement ps;
        
        try(Connection conn = ConnectionFactory.getConnection()) {
            
            ps = conn.prepareStatement("UPDATE prontuario SET queixaPrincipal = ?, doencaGrave = ?, febreReumatica = ?, "
                    + "tratamentoMedico = ?, medicacao = ?, gravida = ?, alergico = ?, hipertenso = ?, diabetico = ?,"
                    + " problemasGastricos = ?, anestesiaLocal = ?, hemorragia = ? WHERE idPaciente = ?");                                   
            
            ps.setString(1, prontuario.getQueixaPrincipal());
            ps.setString(2, prontuario.getDoencaGrave());
            ps.setString(3, prontuario.getFebreReumatica());
            ps.setString(4, prontuario.getTratamentoMedico());
            ps.setString(5, prontuario.getMedicacao());
            ps.setString(6, prontuario.getGravida());
            ps.setString(7, prontuario.getAlergico());
            ps.setString(8, prontuario.getHipertenco());
            ps.setString(9, prontuario.getDiabetico());
            ps.setString(10, prontuario.getProblemasGrastricos());
            ps.setString(11, prontuario.getAnestesiaLocal());
            ps.setString(12, prontuario.getHemorragia());
            ps.setInt(13, prontuario.getIdPaciente());
                     
            
            System.out.println("Informações do prontuario adicionadas!");
            
            ps.execute();
            ps.close();
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Prontuario listarProntuario(int idPaciente){
         
        PreparedStatement ps;
        Prontuario prontuario = null;
        
        try(Connection conn = ConnectionFactory.getConnection()) {
            
            ps = conn.prepareStatement("SELECT * FROM prontuario WHERE idPaciente = ?");
            ps.setInt(1, idPaciente);
            
            ResultSet resultSet = ps.executeQuery();
            prontuario = new Prontuario();
            
            if(resultSet.next()) {                
                prontuario.setIdProntuario(resultSet.getInt("idProntuario"));
                prontuario.setQueixaPrincipal(resultSet.getString("queixaPrincipal"));
                prontuario.setDoencaGrave(resultSet.getString("doencaGrave"));
                prontuario.setFebreReumatica(resultSet.getString("febreReumatica"));
                prontuario.setTratamentoMedico(resultSet.getString("tratamentoMedico"));
                prontuario.setMedicacao(resultSet.getString("medicacao"));
                prontuario.setGravida(resultSet.getString("gravida"));
                prontuario.setAlergico(resultSet.getString("alergico"));
                prontuario.setHipertenco(resultSet.getString("hipertenso"));
                prontuario.setDiabetico(resultSet.getString("diabetico"));
                prontuario.setProblemasGrastricos(resultSet.getString("problemasGastricos"));
                prontuario.setAnestesiaLocal(resultSet.getString("anestesiaLocal"));
                prontuario.setHemorragia(resultSet.getString("hemorragia"));
            }            
            
            ps.close();
            return prontuario;
           
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
