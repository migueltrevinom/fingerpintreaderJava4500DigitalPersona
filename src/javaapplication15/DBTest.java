package javaapplication15;

/* Program:
 *   Setup database driver manager to understand and use ODBC MS-ACCESS data source.
 * Written by Arnav Mukhopadhyay (ARNAV.MUKHOPADHYAY@smude.edu.in)
 * Compile as: javac dbAccess.java
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBTest { 
    
    public static void main(String[] args) {
   
        try {
            Connection conn=DriverManager.getConnection(
                    "jdbc:ucanaccess://C:\\Users\\Miguel\\Documents\\NetBeansProjects\\Finger\\src\\javaapplication15\\lector.mdb");
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM  Alumnos");
            while (rs.next()) {
                 System.out.println(rs.getString("Nombre"));
                  System.out.println(rs.getString("Apellido"));

                      System.out.println(rs.getString("huella"));
            }       } catch (SQLException ex) {
            Logger.getLogger(DBTest.class.getName()).log(Level.SEVERE, null, ex);
        }


}
}