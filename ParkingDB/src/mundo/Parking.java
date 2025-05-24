package mundo;

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

    public String create(String placa, String numero, String nombre, String cedula) {
        String rta = "";
        ArrayList<String> vehiculoData = select(2, placa);
        ArrayList<String> visitanteData = select(3, cedula);

        boolean vehiculoExiste = !vehiculoData.get(0).equals("SIN_DATOS");
        boolean vehiculoActivo = vehiculoExiste && vehiculoData.get(1).equals("1");

        boolean visitanteExiste = !visitanteData.get(0).equals("SIN_DATOS");

        try {
            if (visitanteExiste) {
                String nombreRegistrado = visitanteData.get(1);
                if (!nombreRegistrado.equals(nombre)) {
                    System.out.println("Error: Ya existe un visitante con esa cédula pero con nombre distinto: " + nombreRegistrado);
                    return "Error: Ya existe un visitante con esa cédula pero con nombre distinto: " + nombreRegistrado;
                }
            }
            if (vehiculoExiste && vehiculoActivo && !visitanteExiste) {
                rta = "Vehicle is already active with another visitor";
                System.out.println("Vehicle is already active with another visitor");
            }
            if (!vehiculoExiste && !visitanteExiste) {
                // Caso 1: No existe ni vehiculo ni visitante---- CREAR TODO
                insert(4, placa, numero, nombre, cedula);
                rta = "Created vehicle";
                System.out.println("el vehiculo se creo");
            } else if (vehiculoExiste && vehiculoActivo && visitanteExiste) {
                // Caso 2: Si todo existe, no hacer na
                rta = "The vehicle already exists";
                System.out.println("el vehiculo existe");
            } else if (vehiculoExiste && !vehiculoActivo && !visitanteExiste) {
                // Caso 3: Vehiculo existe, inactivo y visitante no existe--- se activa vehiculo, crear visitante, agregar registro
                pers.update("UPDATE vehiculo SET Estado = 1 WHERE Placa = '" + placa + "'");
                insert(1, placa, numero, nombre, cedula);
                insert(3, placa, numero, nombre, cedula);
                rta = "Created vehicle";
                System.out.println("el vehiculo existe, se cambio a activo, se crea un visitante, se agrega un registro");
            } else if (vehiculoExiste && !vehiculoActivo && visitanteExiste) {
                // Caso 4: Vehiculo existe, esta inactivo y visitante sí existe ---- Solo agregar registro
                pers.update("UPDATE vehiculo SET Estado = 1 WHERE Placa = '" + placa + "'");
                insert(3, placa, numero, nombre, cedula);
                rta = "Created vehicle";
                System.out.println("el vehiculo existe, se cambio a activo, el visitante ya existia, se agrega un registro");
            } else if (!vehiculoExiste && visitanteExiste) {
                // Caso 5:Vehiculo no existe y visitante sí existe --- crear vehiculo y agregar registro
                insert(2, placa, numero, nombre, cedula);
                insert(3, placa, numero, nombre, cedula);
                rta = "Created vehicle";
                System.out.println("el vehiculo no existe, visitante si, se creo el vehiculo, se agrega un registro");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Parking.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rta;
    }

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
    public void insert(int caso, String placa, String numero, String nombre, String cedula) throws SQLException {
        // Obtener fecha y hora completas en formato "yyyy-MM-dd HH:mm:ss"
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHoraIngreso = ahora.format(formatter);

        switch (caso) {
            case 1:
                // Insertar solo visitante
                pers.update("INSERT INTO visitante (Cédula, Nombre, Número) "
                        + "VALUES ('" + cedula + "', '" + nombre + "', '" + numero + "')");
                break;
            case 2:
                // Insertar solo vehículo
                pers.update("INSERT INTO vehiculo (Placa, Estado) "
                        + "VALUES ('" + placa + "', 1)");
                break;
            case 3:
                // Insertar solo registro
                pers.update("INSERT INTO registro (idRegistro, Hora_Ingreso, Vehiculo_Placa, Visitante_Cédula, Tarifa_Anio) "
                        + "VALUES (" + null + ", '" + fechaHoraIngreso + "', '" + placa + "', '" + cedula + "', 2025)");
                break;
            case 4:
                // Insertar todo (visitante, vehículo, registro)
                pers.update("INSERT INTO visitante (Cédula, Nombre, Número) "
                        + "VALUES ('" + cedula + "', '" + nombre + "', '" + numero + "')");
                pers.update("INSERT INTO vehiculo (Placa, Estado) "
                        + "VALUES ('" + placa + "', 1)");
                pers.update("INSERT INTO registro (idRegistro, Hora_Ingreso, Vehiculo_Placa, Visitante_Cédula, Tarifa_Anio) "
                        + "VALUES (" + null + ", '" + fechaHoraIngreso + "', '" + placa + "', '" + cedula + "', 2025)");
                break;
            default:
                System.out.println("Caso no válido.");
        }

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

    private ArrayList<String> getLiquidation(String placa) {
        ArrayList<String> info;

        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String horaSalida = ahora.format(formatter);

        info = select(1, placa);
        String horaIngreso = info.get(1);

        int ingresoSeg = conversion(horaIngreso);
        int salidaSeg = conversion(horaSalida);

        double pago = (salidaSeg - ingresoSeg) / 60 * 90;

        info.clear();
        info.add(String.valueOf(horaIngreso));
        info.add(String.valueOf(horaSalida));
        info.add(String.valueOf(pago));

        try {
            pers.update("UPDATE registro SET Hora_Salida = '" + horaSalida + "' WHERE Placa = " + placa);
            pers.update("UPDATE registro SET Pago = '" + pago + "' WHERE Placa = " + placa);
            pers.update("UPDATE veiculo SET Estado = '" + 0 + "' WHERE Placa = " + placa);
        } catch (SQLException ex) {
            Logger.getLogger("ERROR: No se pudo ingresar los datos de entrada o salida");
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
