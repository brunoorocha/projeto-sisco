
package br.com.sisco.dao;

import br.com.sisco.database.ConnectionFactory;
import br.com.sisco.models.Consulta;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author brunorocha
 */
public class ConsultaDAO {
    
    
    public static void agendarConsulta(int idConsulta, int idPaciente){
        PreparedStatement ps;
        
        try(Connection conn = ConnectionFactory.getConnection()) {
            
            //ps = conn.prepareStatement("INSERT INTO consulta VALUES(NULL,?,?,?,?)");                                   
            
//            ps.setDate(1, new Date(novaConsulta.getData().getTime().getTime()));
//            ps.setTime(2, Time.valueOf(novaConsulta.getHora() + ":00"));
//            ps.setInt(3, novaConsulta.getIdPaciente());
//            ps.setInt(4, 0);

            ps = conn.prepareStatement("UPDATE consulta SET idPaciente = ?, status = 1 WHERE idConsulta = ?");
            
            ps.setInt(1, idPaciente);
            ps.setInt(2, idConsulta);
                                
            System.out.println("Consulta Agendada! Paciente: "+ idPaciente +", consulta: "+ idConsulta);
            
            ps.execute();
            ps.close();
            
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void alterarDataConsulta(Calendar novaData, String novoHorario, int idConsulta){
        
        PreparedStatement ps;
        
        try(Connection conn = ConnectionFactory.getConnection()) {
            
            ps = conn.prepareStatement("UPDATE consulta SET data=?,hora=? WHERE idConsulta=? ");                                   
            
            ps.setDate(1, new Date(novaData.getTime().getTime()));
            ps.setTime(2, new Time(Long.parseLong(novoHorario)));
            ps.setInt(3, idConsulta);
                                
            System.out.println("Consulta Agendada!");
            
            ps.execute();
            ps.close();
            
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
          
    public static ObservableList<Consulta> listarConsultas() {
        
        ObservableList<Consulta> consultas = FXCollections.observableArrayList();
        PreparedStatement ps;
        
        try(Connection conn = ConnectionFactory.getConnection()) {
            
            ps = conn.prepareStatement("SELECT * FROM consulta ORDER BY hora ASC, data ASC");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Consulta consulta = new Consulta();
                
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("data"));                
                consulta.setData(data);
                
                consulta.setHora(rs.getTime("hora").toString());
                consulta.setIdPaciente(rs.getInt("idPaciente"));
                consulta.setStatus(rs.getInt("status"));
                
                consultas.add(consulta);
            }
            
            ps.close();
            rs.close();
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }        
        
        return consultas;
    }
    
    public static ObservableList<String> listarHorarios() {
        ObservableList<String> horarios = FXCollections.observableArrayList();
        PreparedStatement ps;
        
        try(Connection conn = ConnectionFactory.getConnection()) {
            
            ps = conn.prepareStatement("SELECT COUNT(status), hora FROM consulta GROUP BY hora HAVING COUNT(status) > 1");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {                
                String[] hora = rs.getString("hora").split(":");                
                horarios.add(hora[0] +":"+ hora[1]);                
            }
            
            ps.close();
            rs.close();
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }        
        
        return horarios;
    }
    
    public static ObservableList<Consulta> listarConsultasPorHorario(String hora) {
        
        ObservableList<Consulta> consultas = FXCollections.observableArrayList();
        PreparedStatement ps;
        
        try(Connection conn = ConnectionFactory.getConnection()) {
            
            ps = conn.prepareStatement("SELECT * FROM consulta WHERE hora = ? ORDER BY hora ASC, data ASC");
            ps.setString(1, hora + ":00");
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Consulta consulta = new Consulta();
                
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("data"));                
                consulta.setData(data);
                
                consulta.setHora(rs.getTime("hora").toString());
                consulta.setIdPaciente(rs.getInt("idPaciente"));
                consulta.setStatus(rs.getInt("status"));
                
                consultas.add(consulta);                                
            }
            
            ps.close();
            rs.close();
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }        
        
        return consultas;
    }
    
    public static int retornaIdConsulta(Calendar data, String hora) {
        int idConsulta = 0;
        PreparedStatement ps;
        
        try(Connection conn = ConnectionFactory.getConnection()) {
            
            ps = conn.prepareStatement("SELECT idConsulta FROM consulta WHERE data = ? AND hora = ?");
            
            ps.setDate(1, new Date(data.getTime().getTime()));
            ps.setTime(2, Time.valueOf(hora));
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {                                
                idConsulta = rs.getInt("idConsulta");
            }
            
            ps.close();
            rs.close();
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }        
        
        return idConsulta;
    }
}
