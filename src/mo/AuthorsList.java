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
public class AuthorsList {
    private List<Author> authors;
    
    public AuthorsList() {
        this.authors = new ArrayList();
    }
    
    public Author findById(int id) {
        for (Author a : authors) {
            if (a.id == id) {
                return a;
            }
        }
        return null;
    }
    
    public void load(Connect connect) throws SQLException {
        authors.clear();
        ResultSet rs = connect.execute("select id, name, lastname, nickname from authors;");

        while (rs.next()) {
            Author a = new Author();
            a.id = Integer.parseInt(rs.getString(1));
            a.name = rs.getString(2);
            a.lastname = rs.getString(3);
            a.nickname = rs.getString(4);
            authors.add(a);
        }
        System.out.println("Loaded " + authors.size() + " authors(s)");
    }
}
