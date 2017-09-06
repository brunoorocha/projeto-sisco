
package br.com.sisco.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brunorocha
 */
public class ConnectionFactory {
    public static Connection getConnection() {
        /*
            Essa url foi usada com o fim de testar a conexão com o SGBD, 
            deve ser alterada para a url correta
        */
        String url = "jdbc:mysql://localhost:9090/database";
        
        try {
            return DriverManager.getConnection(url, "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
