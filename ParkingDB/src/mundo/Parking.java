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

    public ArrayList<String> read(String placa) {
        ArrayList<String> info = select(2, placa);
        ArrayList<String> existencia = new ArrayList<>();

        if (info.contains("SIN_DATOS") || info.contains("2")) { //NO EXISTE
            existencia.add("El vehiculo no exixte");
        }
        if (info.contains("0")) { // EXISTE INACTIVO
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

        if (info.contains("1")) { //EXISTE ACTIVO
            info = select(1, placa);
            existencia.add(info.get(4)); //Cedula

            info = select(3, existencia.get(0));
            existencia.add(info.get(1)); //Nombre
            existencia.add(info.get(2)); //Numero            
            existencia.add("El vehiculo esta parqueado\n"
                    + "Mostrando datos ...");
        }

        for (String a : existencia) {
            System.out.println(a);
        }
        return existencia;
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

    public ArrayList<String> getLiquidation(String placa) {
        ArrayList<String> info;

        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHoraSalida = ahora.format(formatter);

        info = select(1, placa);
        String fechaHoraIngreso = info.get(1);

        int ingresoSeg = conversion(fechaHoraIngreso);
        int salidaSeg = conversion(fechaHoraSalida);

        double liquidacion = (salidaSeg - ingresoSeg) / 60 * 90;

        info.clear();
        info.add(String.valueOf(ingresoSeg));
        info.add(String.valueOf(salidaSeg));
        info.add(String.valueOf(liquidacion));

        try {
            pers.update("UPDATE registro SET Hora_Salida = '" + fechaHoraSalida + "' WHERE Placa = " + placa);
            pers.update("UPDATE registro SET Pago = '" + liquidacion + "' WHERE Placa = " + placa);
        } catch (SQLException ex) {
            Logger.getLogger("ERROR: No se pudo ingresar los datos de entrada o salida");
        }
        for (String wa : info) {
            System.out.println(wa);
        }
        return info;

    }

    private int conversion(String timestamp) {

        String horaCompleta = timestamp.split(" ")[1];
        String[] partes = horaCompleta.split(":");

        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
        int segundos = Integer.parseInt(partes[2].split("\\.")[0]);

        return horas * 3600 + minutos * 60 + segundos;
    }
}
