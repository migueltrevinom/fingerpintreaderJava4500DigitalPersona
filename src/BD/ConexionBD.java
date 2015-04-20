package BD;
import static BD.FirstExample.DB_URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author ING JARC
 */
public class ConexionBD {

   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/huellas";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "xelha";
Connection conn=null;

public Connection conectar(){
    try{
     //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
    if (conn!=null){
    System.out.println("Conección a base de datos listo...");
    }
    else if (conn==null)
    {
    throw new SQLException();
    }
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    } catch (ClassNotFoundException e) {
        JOptionPane.showMessageDialog(null, "Se produjo el siguiente error: "+e.getMessage());
    }catch (NullPointerException e){
        JOptionPane.showMessageDialog(null, "Se produjo el siguiente error: "+e.getMessage());
    }finally{
    return conn;
    }
}
public void desconectar(){
    conn = null;
    System.out.println("Desconexion a base de datos listo...");
}
}
