
package BIBLIOTECA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion_biblioteca {
    static String bd = "biblioteca";
    static String login = "root";
    static String password = "";
    String url = "jdbc:mysql://localhost/"+bd;
    Connection conn = null;
    public conexion_biblioteca()
    {      try
           {   //obtenemos el driver de para mysql
               Class.forName("com.mysql.jdbc.Driver");
               //obtenemos la conexión
               conn = DriverManager.getConnection(url, login, password);
               if (conn!=null)
               {   System.out.println("Conexion a base de datos "+bd+" OK");
               }
           }
           catch(SQLException e)
           {   System.out.println(e);      
           }
	   catch(ClassNotFoundException e)
	   {   System.out.println(e);      
           }
    }
    public Connection getConnection()
    {   return conn;}

    public void desconectar()
    {   conn = null;   }
}
