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
public class ClientsList {
    public List<Client> clients;
    
    public ClientsList() {
        this.clients = new ArrayList();
    }
    
    public Client findById(int id) {
        for (Client c : clients) {
            if (c.id == id) {
                return c;
            }
        }
        return null;
    }
    
    public void addClient(String name, String surname, String email, Connect connect) 
            throws SQLException, InterruptedException {
        Client c = new Client();
        c.id = -1;
        c.name = name;
        c.lastname = surname;
        c.email = email;
        
        connect.executeUpdate("insert into clients(name, surname, email) values (" + 
                "'" + name + "', '" + surname + "', '" + email+ "');");
        ResultSet rs = connect.executeQuery("select id, name, surname, email from clients;");
        while(rs.next()) {
            if (rs.getString(2).equals(name) && rs.getString(3).equals(surname)) {
                c.id = rs.getInt(1);
                clients.add(c);
                System.out.println("Successfully added new client, id = " + c.id);
                break;
            }
        }
        if (c.id == -1) {
            System.out.println("Failed to add new client.");
        }
    }
    
    public void changeBanned(Client c, Connect connect) 
            throws SQLException, InterruptedException {
        connect.executeUpdate("update clients set banned = " + (c.banned? "false":"true") + " where id = '"
                + c.id + "';");
        
        ResultSet rs = connect.executeQuery("select id, banned from clients where id = '" + c.id + "';");
        boolean succ = false;
        while(rs.next()) {
            if (rs.getInt(1) == c.id && rs.getBoolean(2) == !c.banned) {
                c.banned = !c.banned;
                succ = true;
                System.out.println("Successfully " + (c.banned?"":"un") + "banned user");
            }
        }
        if (!succ) {
            System.out.println("Failed to ban/unban client");
        }
    }
  
    public void load(Connect connect) throws SQLException {
        clients.clear();
        ResultSet rs = connect.executeQuery("select id, name, surname, email, banned from clients;");

        while (rs.next()) {
            Client a = new Client();
            a.id = rs.getInt(1);
            a.name = rs.getString(2);
            a.lastname = rs.getString(3);
            a.email = rs.getString(4);
            a.banned = rs.getBoolean(5);
            clients.add(a);
        }
        //System.out.println("Loaded " + clients.size() + " clients(s)");
    }
}
