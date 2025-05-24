package mundo;

import com.mysql.jdbc.PreparedStatement;
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

    private Persistencia pers;
    private String sql;

    public Parking() {
        pers = new Persistencia();
    }

//    public boolean create(String placa, String numero, String nombre, String cedula) {
//        try {
//            ArrayList<String> info = select.
//            if () {
//                return true; // true, el vehiculo si existe
//            }
//            insert(); // info para insert
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Parking.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//
//    public ArrayList<String> read(String placa) {
//        ArrayList<String> info = new ArrayList<>();
//        try {
//            if (!selectVehiculo(placa)) {
//                info.add("VACIO");  //Si no existe el vehiculo, manda vacio
//            } else {
//                if (selectParqueo(placa, '0')) {
//                    info.add()
//                }
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Parking.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return info;
//    }
    public void insert() throws SQLException {
        //pers.update("INSERT INTO registr ( Placa, Marca, Modelo, Tipo_de_Vehiculo_idTipo ) VALUES (  'JHG'  , 'audi' , 'r8', " + 1 + ")" );
        /*pers.update("INSERT INTO TipoProducto ( ID_CodTipo, nombreTipo ) VALUES ( " + null + ", 'Impresoras')");*/

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

    public ArrayList<String> select(int opcion, String filtro) {
        ArrayList<String> datos = new ArrayList<>();
        selectRoute(opcion, filtro);
       
        try {           
            ResultSet rst = pers.select(sql);            
            if (rst.next()) {
                do {
                    int columnCount = rst.getMetaData().getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        datos.add(rst.getString(i));
                    }
                } while (rst.next());
            } else {
                datos.add("SIN_DATOS");
            }
            for(String blah : datos){
                System.out.println(blah);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Parking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }

    private void selectRoute(int option, String filter) {
        switch (option) {
            case 1: //TABLA REGISTRO POR PLACA
                sql = "SELECT idRegistro, Hora_Ingreso, Hora_Salida, Vehiculo_Placa, Visitante_Cédula, Tarifa_Anio, Pago "
                        + "FROM registro "
                        + "WHERE Vehiculo_Placa = '" + filter + "' "
                        + "ORDER BY Hora_Ingreso DESC "
                        + "LIMIT 1";

                break;

            case 2: //TABLA VEHICULO POR PLACA
                sql = "SELECT Placa, Estado "
                        + "FROM vehiculo "
                        + "WHERE Placa = '" + filter + "' ";
//                + "ORDER BY horaIngreso DESC "
//                + "LIMIT 1";
                break;
            case 3:
                sql = "SELECT Cédula, Nombre, Número "
                        + "FROM visitante "
                        + "WHERE Cédula = '" + filter + "' ";
//                + "ORDER BY horaIngreso DESC "
//                + "LIMIT 1";
                break;
            default:
                System.out.println("CAGO");
        }
    }
}
