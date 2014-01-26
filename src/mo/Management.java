package mo;

import java.awt.AWTException;
import java.awt.Robot;
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
    private static GenresList genresList;
    private static ItemsList itemsList;

    private static void load() throws SQLException {
        genresList = new GenresList();
        genresList.load(connect);
        itemsList = new ItemsList();
        itemsList.load(connect);
    }

    private static boolean login() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Provide login: ");
        String loginname = br.readLine();
        System.out.println("Provide password: ");
        String pass = br.readLine();
        try {
            Robot robbie = new Robot();
            robbie.keyPress(17); // Holds CTRL key.
            robbie.keyPress(76); // Holds L key.
            robbie.keyRelease(17); // Releases CTRL key.
            robbie.keyRelease(76); // Releases L key.
        } catch (AWTException ex) {
            System.err.println("Robot Failure. Cannot clear screen.");
        }
        return EmployeeVerificator.verify(loginname, pass, connect);
    }

    public static void main(String args[]) throws IOException {
        try {
            connect = new Connect();
            System.out.println("Connection established.");
            if (login()) {
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
