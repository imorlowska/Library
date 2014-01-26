package mo;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

/**
 *
 * @author Ferdiansyah Dolot
 */
public class Management {

    private static Connect connect;
    private static GenresList genresList;
    private static ItemsList itemsList;

    private static void load() throws SQLException {
        genresList = new GenresList();
        genresList.load(connect);
        itemsList = new ItemsList();
        itemsList.load(connect);
    }

    public static void main(String args[]) throws IOException {
        try {
            connect = new Connect();
            System.out.println("Connection established. \nPlease provide login:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String loginname = br.readLine();
            System.out.println("Provide password: ");
            String pass = br.readLine();
            if (EmployeeVerificator.verify(loginname, pass, connect)) {
                System.out.println("Logged in");
                load();
            } else {
                System.out.println("Login failed. Shutting down.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Connection Failure. Shutting down.");
        }
    }
}
