/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Izabela
 */
public class GenresList {
    private List<Genre> genres;

    public GenresList() {
        genres = new ArrayList();
    }

    public Genre findById(int id) {
        for (Genre p : genres) {
            if (p.id == id) {
                return p;
            }
        }
        return null;
    }

    public void load(Connect connect) throws SQLException {
        genres.clear();
        ResultSet rs = connect.execute("select id, name from genres;");

        while (rs.next()) {
            Genre p = new Genre();
            p.id = rs.getInt(1);
            p.name = rs.getString(2);
            genres.add(p);
        }
        System.out.println("Loaded " + genres.size() + " genre(s)");
    }
}
