/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mo;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Izabela
 */
public class Connect {
    public Connect() throws SQLException{
        makeConnection();
    } 

    private Connection koneksi;  

     public  Connection makeConnection() throws SQLException {
        if (koneksi == null) {
             new Driver();
            // buat koneksi
             koneksi = DriverManager.getConnection(
                       "jdbc:mysql://localhost/mo",
                       "root",
                       "password");
         }
         return koneksi;
     }  
     
     public void execute(String command)throws SQLException{
        Statement statement = makeConnection().createStatement();
        statement.execute(command);
    }
}
