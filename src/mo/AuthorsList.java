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
    List<Author> authors;
    
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
    
    public void addAuthor(String name, String lastname, String nickname, Connect connect) 
            throws SQLException, InterruptedException {
        Author a = new Author();
        a.id = -1;
        a.name = name;
        a.lastname = lastname;
        a.nickname = nickname;
        
        connect.executeUpdate("insert into authors(name, lastname, nickname) values (" + 
                "'" + name + "', '" + lastname + "', '" + nickname+ "');");
         ResultSet rs = connect.executeQuery("select id, name, lastname, nickname from authors;");
        while(rs.next()) {
            if (rs.getString(2).equals(name) && rs.getString(3).equals(lastname)) {
                a.id = rs.getInt(1);
                authors.add(a);
                System.out.println("Successfully added new author, id = " + a.id);
                break;
            }
        }
        if (a.id == -1) {
            System.out.println("Failed to add new author.");
        }
    }
    
    public void load(Connect connect) throws SQLException {
        authors.clear();
        ResultSet rs = connect.executeQuery("select id, name, lastname, nickname from authors;");

        while (rs.next()) {
            Author a = new Author();
            a.id = rs.getInt(1);
            a.name = rs.getString(2);
            a.lastname = rs.getString(3);
            a.nickname = rs.getString(4);
            authors.add(a);
        }
        System.out.println("Loaded " + authors.size() + " authors(s)");
    }
}
