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
    private List<Client> clients;
    
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
        System.out.println("Loaded " + clients.size() + " clients(s)");
    }
}
