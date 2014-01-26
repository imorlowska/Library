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
    private static String loginname;
    private static GenresList genresList;
    private static ItemsList itemsList;
    private static AuthorsList authorsList;
    private static ClientsList clientsList;
    private static OrdersList ordersList;

    private static void load() throws SQLException {
        genresList = new GenresList();
        genresList.load(connect);
        itemsList = new ItemsList();
        itemsList.load(connect);
        authorsList = new AuthorsList();
        authorsList.load(connect);
        clientsList = new ClientsList();
        clientsList.load(connect);
        ordersList = new OrdersList();
        ordersList.load(connect);
        System.out.println("Loading complete.\n");
    }

    private static boolean login() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Provide login: ");
        loginname = br.readLine();
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

    public static void manage() throws IOException {
        System.out.println("What would you like to do?\n"
                + "1\t See clients list\n"
                + "2\t See client's details\n"
                + "3\t Add new client\n"
                + "4\t See items list\n"
                + "5\t See item's details\n"
                + "6\t Add new item\n"
                + "7\t See authors list\n"
                + "8\t See author's details\n"
                + "9\t Add new author\n"
                + "10\t Show orders list\n"
                + "11\t Add new order\n"
                + "12\t Finalize an order\n");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i = Integer.parseInt(br.readLine());

    }

    public static void main(String args[]) throws IOException {
        try {
            connect = new Connect();
            System.out.println("Connection established.");
            if (login()) {
                System.out.println("Logged in");
                load();

                manage();
            }
            connect.makeConnection().commit();
            connect.makeConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Connection Failure. Shutting down.");
        }
    }
}
