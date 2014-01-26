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
    
    public Item findById(int id) {
        for (Item i : items) {
            if (i.id == id) {
                return i;
            }
        }
        return null;
    }
    
    public List<Item> findByGenreId(int id) {
        List<Item> found = new ArrayList();
        for (Item i : items) {
            if (i.genre_id == id) {
                found.add(i);
            }
        }
        return found;
    }
    
    public List<Item> findByAuthorId(int id) {
        List<Item> found = new ArrayList();
        for (Item i : items) {
            if (i.author_id == id) {
                found.add(i);
            }
        }
        return found;
    }
    
    public List<Book> findAllBooks() {
        List<Book> found = new ArrayList();
        for (Item i : items) {
            if (i instanceof Book) {
                found.add((Book)i);
            }
        }
        return found;
    }
    
    public List<Magazine> findAllMagazines() {
        List<Magazine> found = new ArrayList();
        for (Item i : items) {
            if (i instanceof Magazine) {
                found.add((Magazine)i);
            }
        }
        return found;
    }
    
    public void addBook(String name, int genre_id, int author_id, String iban, int quantity, Connect connect) 
            throws SQLException, InterruptedException {
        Book b = new Book();
        b.id = -1;
        b.name = name;
        b.genre_id = genre_id;
        b.author_id = author_id;
        b.iban = iban;
        b.quantity = quantity;
        
        connect.executeUpdate("insert into items(name, genre_id, author_id, type_id, iban, quantity) values (" + 
                "'" + name + "', '" + genre_id + "', '" + author_id+ "', '1', '" + iban + "', '" + quantity + "');");
         ResultSet rs = connect.executeQuery("select id, name, iban from items;");
        while(rs.next()) {
            if (rs.getString(2).equals(name) && rs.getString(3).equals(iban)) {
                b.id = rs.getInt(1);
                items.add(b);
                System.out.println("Successfully added new book, id = " + b.id);
                break;
            }
        }
        if (b.id == -1) {
            System.out.println("Failed to add new book.");
        }
    }
    
    public void addMagazine(String name, int genre_id, int author_id, String issue_nb, int quantity, Connect connect) 
            throws SQLException, InterruptedException {
        Magazine b = new Magazine();
        b.id = -1;
        b.name = name;
        b.genre_id = genre_id;
        b.author_id = author_id;
        b.issue_nb = issue_nb;
        b.quantity = quantity;
        
        connect.executeUpdate("insert into items(name, genre_id, author_id, type_id, issue_nb, quantity) values (" + 
                "'" + name + "', '" + genre_id + "', '" + author_id+ "', '1', '" + issue_nb + "', '" + quantity + "');");
         ResultSet rs = connect.executeQuery("select id, name, issue_nb from items;");
        while(rs.next()) {
            if (rs.getString(2).equals(name) && rs.getString(3).equals(issue_nb)) {
                b.id = rs.getInt(1);
                items.add(b);
                System.out.println("Successfully added new magazine, id = " + b.id);
                break;
            }
        }
        if (b.id == -1) {
            System.out.println("Failed to add new magazine.");
        }
    }
    
    public void load(Connect connect) throws SQLException {
        items.clear();
        ResultSet rs = connect.executeQuery(
                "select id, name, genre_id, author_id, type_id, iban, issue_nb, quantity from items;");

        while (rs.next()) {
            if (rs.getString(5).equals("1")) { //book
                Book b = new Book();
                b.id = rs.getInt(1);
                b.name = rs.getString(2);
                b.genre_id = rs.getInt(3);
                b.author_id = rs.getInt(4);
                b.iban = rs.getString(6);
                b.quantity = rs.getInt(8);
                items.add(b);
            } else { //magazine
                Magazine m = new Magazine();
                m.id = rs.getInt(1);
                m.name = rs.getString(2);
                m.genre_id = rs.getInt(3);
                m.author_id = rs.getInt(4);
                m.issue_nb = rs.getString(7);
                m.quantity = rs.getInt(8);
                items.add(m);
            }
        }
        System.out.println("Loaded " + items.size() + " item(s)");
    }
}
