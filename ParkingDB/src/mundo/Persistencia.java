package mundo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Esteban
 */
public class Persistencia implements CRUD {
     private Connection connection;
     private ResultSet rst;
     private Statement statement;
	    	       
     private static final String driver = "com.mysql.jdbc.Driver";
     private static final String user     = "root";
     private static final String password = "";     
     private static final String url      = "jdbc:mysql://localhost:3306/Parking";

    public Persistencia() {
        connect();
    }
     
     
     public Connection connect()
     {          
       try
       { Class.forName("com.mysql.jdbc.Driver");
         this.connection = DriverManager.getConnection(url, user,password);   
       }
       catch (Exception e)
       { System.out.println("Mysql() :: " + e.getMessage());           
       }    
       return this.connection;
     }

    @Override
    public ResultSet select( String sql ) throws SQLException 
     { this.statement  = this.connection.createStatement();
       this.rst = statement.executeQuery( sql );
       return rst;
     }

    
    @Override
    public int update( String sql ) throws SQLException
     { //System.out.println(sql);
         this.statement  = this.connection.createStatement();
       return statement.executeUpdate( sql );
     }	
    
    public Connection getConnection() { return this.connection; }
     public void closeConnection() throws SQLException 
     { connection.close();  
     }

   
    
}
