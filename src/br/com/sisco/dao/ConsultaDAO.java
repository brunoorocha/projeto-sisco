
package br.com.sisco.dao;

import br.com.sisco.database.ConnectionFactory;
import br.com.sisco.models.Consulta;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;

/**
 *
 * @author brunorocha
 */
public class ConsultaDAO {
    public static void agendarConsulta(Consulta novaConsulta){
        PreparedStatement ps;
        
        try(Connection conn = ConnectionFactory.getConnection()) {
            
            ps = conn.prepareStatement("INSERT INTO consulta VALUES(NULL,?,?,?,?)");                                   
            
            ps.setDate(1, new Date(novaConsulta.getData().getTime().getTime()));
            ps.setTime(2, new Time(Long.parseLong(novaConsulta.getHora())));
            ps.setInt(3, novaConsulta.getIdPaciente());
            ps.setInt(4, 0);
                                
            System.out.println("Consulta Agendada!");
            
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
           
}
