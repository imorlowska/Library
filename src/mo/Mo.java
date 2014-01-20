package mo;
import com.mysql.jdbc.Driver;
import java.sql.*;
/**
 *
 * @author Ferdiansyah Dolot
 */
public class Mo {
    public Mo() throws SQLException{
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

     public static void main(String args[]) {
         try {
             Mo c = new Mo();
             System.out.println("Connection established");
         }
         catch (SQLException e) {
             e.printStackTrace();
             System.err.println("Connection Failure");
         }  

    }
}