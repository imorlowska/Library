package mo;

import java.io.IOException;
import java.sql.*;

/**
 *
 * @author Ferdiansyah Dolot
 */
public class Management {

    private static Connect connect;
    private static GenresList genresList;

    private static void load() throws SQLException {
        genresList = new GenresList();
        genresList.load(connect);
    }

    public static void main(String args[]) throws IOException {
        try {
            connect = new Connect();
            System.out.println("Connection established");
            load();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Connection Failure");
        }
    }
}
