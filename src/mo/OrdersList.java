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
public class OrdersList {

    private List<Order> orders;

    public OrdersList() {
        this.orders = new ArrayList();
    }

    public Order findById(int id) {
        for (Order o : orders) {
            if (o.id == id) {
                return o;
            }
        }
        return null;
    }
    
    public List<Order> findByClientId(int id) {
        List<Order> found = new ArrayList();
        for (Order o : orders) {
            if (o.client_id == id) {
                found.add(o);
            }
        }
        return found;
    }
    
    public List<Order> findByItemId(int id) {
        List<Order> found = new ArrayList();
        for (Order o : orders) {
            if (o.item_id == id) {
                found.add(o);
            }
        }
        return found;
    }
    
    public List<Order> findNotReturned() {
        List<Order> found = new ArrayList();
        for(Order o : orders) {
            if (o.return_date == null || o.return_date.equals("") || o.return_date.equals("null")) {
                found.add(o);
            }
        }
        return found;
    }

    public void load(Connect connect) throws SQLException {
        orders.clear();
        ResultSet rs = connect.executeQuery(
                "select id, item_id, client_id, lend_date, return_date from orders;");

        while (rs.next()) {
            Order o = new Order();
            o.id = Integer.parseInt(rs.getString(1));
            o.item_id = rs.getInt(2);
            o.client_id = rs.getInt(3);
            o.lend_date = rs.getString(4);
            o.return_date = rs.getString(5);
            orders.add(o);
        }
        System.out.println("Loaded " + orders.size() + " orders(s)");
    }
}
