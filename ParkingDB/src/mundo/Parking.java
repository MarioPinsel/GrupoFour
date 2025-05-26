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
    private String placa;
    private String numero;
    private String nombre;
    private String cedula;

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
            existencia.add("Vehiculo no parqueado\n"
                    + "Mostrando ultimo registro de entrada");
            existencia.add(info.get(1)); //H ingreso
            existencia.add(info.get(2)); //H Salida
            existencia.add(info.get(6)); //Pago
            existencia.add(info.get(4)); //Cedula

            info = select(3, existencia.get(4));
            existencia.add(info.get(1)); //Nombre
            existencia.add(info.get(2)); //Numero            

        }

        if (info.contains("1")) { //EXISTE ACTIVO
            existencia.add("El vehiculo esta parqueado\n"
                    + "Mostrando datos ...");
            existencia.add(""); //H ingreso
            existencia.add(""); //H salida
            existencia.add("."); //Pago
            info = select(1, placa);
            info = select(3, info.get(4));

            existencia.add(info.get(0)); //Cedula
            existencia.add(info.get(1)); //Nombre
            existencia.add(info.get(2)); //Numero

        }
        return existencia;

    }

    public String create(String placa, String numero, String nombre, String cedula) {
        setAtributos(placa, numero, nombre, cedula);
        ArrayList<String> vehiculoData = select(2, placa);
        ArrayList<String> visitanteData = select(3, cedula);

        boolean vehiculoExiste = !vehiculoData.get(0).equals("SIN_DATOS");
        boolean vehiculoParqueado = vehiculoExiste && vehiculoData.get(1).equals("1");
        boolean vehiculoBorrado = vehiculoExiste && vehiculoData.get(1).equals("2");
        boolean visitanteExiste = !visitanteData.get(0).equals("SIN_DATOS");

        try {
            if (visitanteExiste) {
                String nombreRegistrado = visitanteData.get(1);
                if (!nombreRegistrado.equals(nombre)) {
                    return "Error: ya existe un visitante con esa cédula, pero con un nombre distinto: " + nombreRegistrado;
                }
            }
            if (vehiculoExiste && vehiculoParqueado && !visitanteExiste) {
                return "Error: el vehículo ya está activo con otro visitante.";
            }
            if (!vehiculoExiste && !visitanteExiste) {
                insert(4);
                return "Vehículo creado.";
            } else if (vehiculoExiste && vehiculoParqueado && visitanteExiste) {
                return "El vehículo ya existe.";
            } else if (!vehiculoExiste && visitanteExiste) {
                insert(2);
                insert(3);
                return "El vehículo no existe, pero el visitante sí. Se creó el vehículo y se agregó un registro.";
            } else if (vehiculoExiste && !vehiculoParqueado && !visitanteExiste) {
                pers.update("UPDATE vehiculo SET Estado = 1 WHERE Placa = '" + placa + "'");
                insert(1);
                insert(3);
                if (vehiculoBorrado) {
                    return "Vehículo creado.";
                } else {
                    return "El vehículo existe y se activó. Se creó un visitante y se agregó un registro.";
                }
            } else if (vehiculoExiste && !vehiculoParqueado && visitanteExiste) {
                pers.update("UPDATE vehiculo SET Estado = 1 WHERE Placa = '" + placa + "'");
                insert(3);
                if (vehiculoBorrado) {
                    return "El vehículo fue creado y el visitante ya existía.";
                } else {
                    return "El vehículo existe y se activó. El visitante ya existía, se agregó un registro.";
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Parking.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "No se pudo completar la operación.";

    }

    public void insert(int caso) throws SQLException {

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
    public ArrayList<String> existenceValidation(String cedula) {
        ArrayList<String> info = select(3, cedula);
        ArrayList<String> existencia = new ArrayList<>();

        if (info.get(0).equals("SIN_DATOS")) {
            existencia.add("Visitante no existente, creelo");
            return existencia;
        }

        existencia.add(""); //H ingreso
        existencia.add(""); //H salida
        existencia.add("."); //Pago            
        existencia.add(info.get(0)); //Cedula
        existencia.add(info.get(1)); //Nombre
        existencia.add(info.get(2)); //Numero
        return existencia;
    }

    public void update(ArrayList<String> info) {

        try {
            pers.update("UPDATE visitante SET Nombre = '" + info.get(1) + "' WHERE Cédula = '" + info.get(0) + "'");
            pers.update("UPDATE visitante SET Número = '" + info.get(2) + "' WHERE Cédula = '" + info.get(0) + "'");

        } catch (SQLException ex) {
            System.out.println("ERROR: No se puede actualizar la informacion pertinente");
            System.out.println(ex.getMessage());
        }

    }

    public ArrayList<String> delete(String placa) {
        ArrayList<String> vehiculoData = select(2, placa);
        ArrayList<String> info = new ArrayList<>();

        if (vehiculoData.get(0).equals("SIN_DATOS")) {
            info.add("El vehículo no existe.");
            return info;
        }
        if (vehiculoData.get(1).equals("1")) {
            info.add("El vehiculo está parqueado, no se puede eliminar");
            return info;
        }

        try {
            pers.update("UPDATE vehiculo SET Estado = 2 WHERE Placa = '" + placa + "'");
        } catch (SQLException ex) {
            Logger.getLogger(Parking.class.getName()).log(Level.SEVERE, null, ex);
            info.add("Error al actualizar el estado del vehículo.");
            return info;
        }

        ArrayList<String> registro = select(1, placa);
        if (registro.get(0).equals("SIN_DATOS")) {
            info.add("Vehículo borrado. No hay registros anteriores.");
        } else {
            info.add("Vehículo borrado.");
            info.add("Último ingreso: " + registro.get(1));
            info.add("Última salida: " + registro.get(2));
            info.add("Pago: " + registro.get(6));
            info.add("Visitante cédula: " + registro.get(4));

            ArrayList<String> visitante = select(3, registro.get(4));
            if (!visitante.get(0).equals("SIN_DATOS")) {
                info.add("Visitante nombre: " + visitante.get(1));
                info.add("Visitante número: " + visitante.get(2));
            } else {
                info.add("No se encontraron datos del visitante.");
            }
        }

        return info;
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

    public ArrayList<String> getLiquidation(String placa) {
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
            pers.update("UPDATE registro SET Hora_Salida = '" + horaSalida + "' WHERE Vehiculo_Placa = '" + placa + "'");
            pers.update("UPDATE registro SET Pago = " + pago + " WHERE Vehiculo_Placa = '" + placa + "'");
            pers.update("UPDATE vehiculo SET Estado = 0 WHERE Placa = '" + placa + "'");

        } catch (SQLException ex) {
            System.out.println("ERROR: No se pudo ingresar los datos de entrada o salida");
            System.out.println(ex.getMessage());
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

    public void setAtributos(String placa, String numero, String nombre, String cedula) {
        this.placa = placa;
        this.numero = numero;
        this.nombre = nombre;
        this.cedula = cedula;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

}
