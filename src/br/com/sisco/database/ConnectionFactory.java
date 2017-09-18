
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
        
        String url = "jdbc:mysql://localhost/sisco";
        
        try {
            /*
            
                Vale lembrar que o usuário e senha mudam de um pc pro outro
            
            */
            return DriverManager.getConnection(url, "root", "123");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
