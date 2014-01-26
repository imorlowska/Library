/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Izabela
 */
public class EmployeeVerificator {
    
    public static boolean verify(String login, String password, Connect connect) throws SQLException {
        ResultSet rs = connect.execute("select login, password from employees;");
        while (rs.next()) {
            if (rs.getString(1).equals(login) && rs.getString(2).equals(password)) {
                return true;
            }
        }
        return false;
    }
    
}
