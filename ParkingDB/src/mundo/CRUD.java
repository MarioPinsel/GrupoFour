/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundo;

import java.sql.SQLException;

/**
 *
 * @author Esteban
 */
public interface CRUD {

    public Object select(String string) throws SQLException;

    public int update(String string) throws SQLException;

}
