package mundo;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public ArrayList<String> read(String placa) {
        ArrayList<String> info = select(2, placa);
        ArrayList<String> existencia = new ArrayList<>();
        if (info.contains("SIN_DATOS")) {
            existencia.add("El vehiculo no exixte");
        }
        if (info.contains("0")) {
            info = select(1, placa);
            existencia.add(info.get(1)); //H ingreso
            existencia.add(info.get(2)); //H Salida
            existencia.add(info.get(6)); //Pago
            existencia.add(info.get(4)); //Cedula

            info = select(3, existencia.get(3));
            existencia.add(info.get(1)); //Nombre
            existencia.add(info.get(2)); //Numero            
            existencia.add("Vehiculo no parqueado\n"
                    + "Mostrando ultimo registro de entrada");
        }

        if (info.contains("1")) {
            info = select(1, placa);
            existencia.add(info.get(4)); //Cedula

            info = select(3, existencia.get(0));
            existencia.add(info.get(1)); //Nombre
            existencia.add(info.get(2)); //Numero            
            existencia.add("El vehiculo esta parqueado\n"
                    + "Mostrando datos ...");

//            
//            LocalDateTime ahora = LocalDateTime.now();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            String fechaHoraIngreso = ahora.format(formatter);
//            try {
//                pers.update("UPDATE vehiculo SET Estado = 1 WHERE Placa = '" + placa + "'");
//            } catch (SQLException ex) {
//                System.out.println("WAWAWWAWAWWAWAW");
//            }
        }

        for (String a : existencia) {
            System.out.println(a);
        }
        return existencia;
    }

    public void insert() throws SQLException {
        pers.update("INSERT INTO registro ( idRegistro, Hora_Ingreso, Hora_Salida, Vehiculo_Placa, Visitante_Cédula, Tarifa_Anio, Pago ) VALUES ( " + null + ", '11:50' , " + null + ", BAT000, 515151, 2025, " + null + ")");
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

    public ArrayList<String> getLiquidation() {
        ArrayList<String> info = null;
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHoraIngreso = ahora.format(formatter);
        
        try {
            pers.update("UPDATE registro SET Hora_Salida = '" + fechaHoraIngreso + "' WHERE ID_CodTipo = " + 7);
        } catch (SQLException ex) {
            Logger.getLogger(Parking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return info;
    }
}
