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
public class ItemsList {
    private List<Item> items;
    
    public ItemsList() {
        items = new ArrayList();
    }
    
    public void load(Connect connect) throws SQLException {
        items.clear();
        ResultSet rs = connect.execute(
                "select id, name, genre_id, author_id, type_id, iban, issue_nb, quantity from items;");

        while (rs.next()) {
            if (rs.getString(5).equals("1")) { //book
                Book b = new Book();
                b.id = Integer.parseInt(rs.getString(1));
                b.name = rs.getString(2);
                b.genre_id = Integer.parseInt(rs.getString(3));
                b.author_id = Integer.parseInt(rs.getString(4));
                b.iban = rs.getString(6);
                b.quantity = Integer.parseInt(rs.getString(8));
                items.add(b);
            } else { //magazine
                Magazine m = new Magazine();
                m.id = Integer.parseInt(rs.getString(1));
                m.name = rs.getString(2);
                m.genre_id = Integer.parseInt(rs.getString(3));
                m.author_id = Integer.parseInt(rs.getString(4));
                m.issue_nb = rs.getString(7);
                m.quantity = Integer.parseInt(rs.getString(8));
                items.add(m);
            }
        }
        System.out.println("Loaded " + items.size() + " item(s)");
    }
}
