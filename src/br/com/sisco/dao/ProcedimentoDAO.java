
package br.com.sisco.dao;

import br.com.sisco.database.ConnectionFactory;
import br.com.sisco.models.Procedimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author brunorocha
 */
public class ProcedimentoDAO {
    public static void adicionarProcedimento(Procedimento procedimento) {
        
        PreparedStatement ps;
        
        try(Connection conn = ConnectionFactory.getConnection()) {
            
            ps = conn.prepareStatement("INSERT INTO procedimento VALUES(NULL, ?, ?, ?");                                   
            
            ps.setString(1, procedimento.getResumo());
            ps.setInt(2, procedimento.getIdProntuario());
            ps.setInt(3, procedimento.getIdConsulta());           
            
            System.out.println("Procedimento adicionado!");
            
            ps.execute();
            ps.close();
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void removerProcedimento(int id) {
         PreparedStatement ps;
        
        try(Connection conn = ConnectionFactory.getConnection()) {
            
            ps = conn.prepareStatement("DELETE FROM procedimento WHERE idProcedimento = ?");                                   
            
            ps.setInt(1, id);
                                
            System.out.println("Procedimento removido!");
            
            ps.execute();
            ps.close();
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void editarProcedimento(Procedimento procedimento) {
        
        PreparedStatement ps;
        
        try(Connection conn = ConnectionFactory.getConnection()) {
            
            ps = conn.prepareStatement("UPDATE procedimento SET resumo = ? WHERE idProcedimento = ?");                                   
            
            ps.setString(1, procedimento.getResumo());
            ps.setInt(2, procedimento.getIdProcedimento());
                       
            
            System.out.println("Procedimento atualizado!");
            
            ps.execute();
            ps.close();
            
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void listarProcedimento(Procedimento procedimento) {
    }
    
    
}
