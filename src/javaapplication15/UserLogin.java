/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication15;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class UserLogin {
public static void main(String[] args) throws SQLException {
Connection conn = null;
    try {
        conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Miguel/Documents/NetBeansProjects/Finger/src/javaapplication15/lector.mdb");
    } catch (SQLException ex) {
        Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
    }
Statement s = conn.createStatement();
ResultSet rs = s.executeQuery("INSERT INTO Alumnos (Nombre, Matricula,tipo) VALUES ('Registro','ingresodeusuarionuevo783','Administrador')");
while (rs.next()) {
    System.out.println(rs.getString(3));
}
    
}
}