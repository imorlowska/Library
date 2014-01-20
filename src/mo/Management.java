package mo;
import com.mysql.jdbc.Driver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
/**
 *
 * @author Ferdiansyah Dolot
 */
public class Management {
    private static Connect connect;

     public static void main(String args[]) throws IOException {
         try {
             connect = new Connect();
             System.out.println("Connection established");
             while(true) {
                 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                 String s = br.readLine();
                 connect.execute(s);
             }
         }
         catch (SQLException e) {
             e.printStackTrace();
             System.err.println("Connection Failure");
         }  
    }
}