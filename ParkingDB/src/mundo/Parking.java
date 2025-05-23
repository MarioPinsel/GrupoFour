package mundo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Esteban
 */
public class Parking {

    Persistencia pers;
    

    public Parking() {
        pers = new Persistencia();
    }
    
    public boolean create(String placa, String numero, String nombre, String cedula){        
        try {
            if (select(placa)) {
                return true; // true, el vehiculo si existe
            }
            insert(); // info para insert
            
        } catch (SQLException ex) {
            Logger.getLogger(Parking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ArrayList<String> read(String placa){
        ArrayList<String> info = new ArrayList<>();
        try {
            if (!selectVehiculo(placa)) {
                info.add("VACIO");  //Si no existe el vehiculo, manda vacio
            }else{
                if (selectParqueo(placa,'0')) {
                    info.add()
                }
            }
                
            
        } catch (SQLException ex) {
            Logger.getLogger(Parking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return info;    
    }

    public void insert() throws SQLException {
        /*pers.update("INSERT INTO vehiculo ( Placa, Marca, Modelo, Tipo_de_Vehiculo_idTipo ) VALUES (  'JHG'  , 'audi' , 'r8', " + 1 + ")" );*/
        pers.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'Impresoras')");
        pers.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'PC')");
        pers.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'Celulares')");
        pers.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'Audifonos')");
        pers.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'iPad')");

        pers.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'Cambiame')");
        pers.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'Borrame')");
    }

    /*
    "UPDATE <Nombre de la tabla> SET <campo> = " + "'GoPro'" + " WHERE <llave> = " + valor );
     CR(Update)D
     */
    public void update() throws SQLException {
        pers.update("UPDATE vehiculo SET Modelo = " + "'Caratumba'" + " WHERE Placa = " + "'JHG'");
    }

    /* 
    "DELETE FROM <Nombre de la tabla> WHERE <llave> = " + valor); 
     CRU(Delete)
     */
    public void delete() throws SQLException {
        pers.update("DELETE FROM vehiculo WHERE Placa = " + "'ABC123'");
    }

    
    public ArrayList<String> select(String placa) {
    ArrayList<String> datos = new ArrayList<>();
    String sql = "SELECT idIngreso, cedula_visitante, Tarifa_año, horaIngreso, horaSalida, pago " +
                 "FROM Ingreso " +
                 "WHERE Vehiculo_placa = '" + placa + "' " +
                 "ORDER BY horaIngreso DESC " +
                 "LIMIT 1";
    try {
        ResultSet rst = pers.select(sql);
        if (rst.next()) {
            datos.add(rst.getString("idIngreso"));
            datos.add(rst.getString("cedula_visitante"));
            datos.add(rst.getString("Tarifa_año"));
            datos.add(rst.getString("horaIngreso"));
            datos.add(rst.getString("horaSalida"));
            datos.add(rst.getString("pago"));
        } else {
            datos.add("SIN_DATOS");
        }
    } catch (SQLException ex) {
        Logger.getLogger(Parking.class.getName()).log(Level.SEVERE, null, ex);
    }
    return datos;
}
}
