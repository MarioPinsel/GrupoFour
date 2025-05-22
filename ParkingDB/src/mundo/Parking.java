/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mundo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Esteban
 */
public class Parking {
    Persistencia pers ;

    public Parking() {
        pers = new Persistencia();
    }
    
    
     
    public void insert() throws SQLException
    { pers.update("INSERT INTO vehiculo ( Placa, Marca, Modelo, Tipo_de_Vehiculo_idTipo ) VALUES (  'JHG'  , 'audi' , 'r8', " + 1 + ")" );
//      pers.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'Impresoras')" );
//      pers.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'PC')" );
//      pers.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'Celulares')" );
//      pers.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'Audifonos')" );
//      pers.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'iPad')" );
//    
//      pers.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'Cambiame')" );    
//      pers.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'Borrame')" );    
    }        
   
    /*
    "UPDATE <Nombre de la tabla> SET <campo> = " + "'GoPro'" + " WHERE <llave> = " + valor );
     CR(Update)D
    */
    public void update() throws SQLException
    { pers.update("UPDATE vehiculo SET Modelo = " + "'Caratumba'" + " WHERE Placa = " + "'JHG'" );
    }        

    /* 
    "DELETE FROM <Nombre de la tabla> WHERE <llave> = " + valor); 
     CRU(Delete)
    */
    public void delete() throws SQLException
    { pers.update("DELETE FROM vehiculo WHERE Placa = " + "'ABC123'");
    }        
    
    
    /* 
    "Select * from <Nombre tabla>"
     C(Read)UD
    */
    public void select(String sql) throws SQLException
    { ResultSet rst = pers.select(sql);
      while(rst.next())
      { System.out.println(rst.getString("ID_CodTipo") + " :: " + rst.getString("nombreTipo"));
      }
    }        
    
}
