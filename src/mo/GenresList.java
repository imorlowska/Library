/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Izabela
 */
public class GenresList {
    private List<Pair> genres;
    
    private class Pair {
        public int id;
        public String name;
    }
    
    public GenresList() {
        genres = new ArrayList();
    }
    
    public int getId(String name) {
        for (Pair p : genres) {
            if (p.name.equalsIgnoreCase(name)) {
                return p.id;
            }
        }
        return -1;
    }
    
    //@Nullable
    public  String getName(int id) {
        for (Pair p : genres) {
            if (p.id == id) {
                return p.name;
            }
        }
        return null;
    }
    
    /**
     *  
     * @param connect
     * @throws java.sql.SQLException
     */
    public void load(Connect connect) throws SQLException {
        genres.clear();
        ResultSet rs = connect.execute("select * from genres;");

        while (rs.next()) {
            Pair p = new Pair();
            p.id = Integer.parseInt(rs.getString(1));
            p.name = rs.getString(2);
            genres.add(p);
        }
        System.out.println("Loaded " + genres.size() + " genre(s)");
    }
}
