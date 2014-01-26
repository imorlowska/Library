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
    
    public void load(Connect connect) throws SQLException {
        orders.clear();
        ResultSet rs = connect.execute(
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
