package controlador;

import java.util.ArrayList;

import interfaz.*;
import mundo.*;

public class Controlador {

    private Parking parking;
    private PanelCrud panelCrud;
    private PanelVehicle panelVehicle;
    private PanelConsole panelConsole;

    public Controlador() {
        parking = new Parking();
    }

    public void setIntance(PanelCrud pc, PanelVehicle pv, PanelConsole pco) {
        this.panelCrud = pc;
        this.panelVehicle = pv;
        this.panelConsole = pco;
    }

    public void create(String placa, String numero, String nombre, String cedula) {
        ArrayList<String> info = new ArrayList<>();
        info.add(placa, numero, nombre, cedula);
        parking.create(placa, numero, nombre, cedula);

    }

    public void read(String placa) {
        ArrayList<String> info = parking.read(placa);
    }

    public void update(String placa, String numero, String nombre, String cedula) {
        ArrayList<String> info = new ArrayList<>();
        info.add(placa);
        info.add(numero);
        info.add(nombre);
        info.add(cedula);
        parking.update(placa, numero, nombre, cedula);
    }

    public void delete(String placa) {
        if (parking.delete(placa)) {
            panelConsole.log("Vehículo eliminado correctamente.");
        } else {
            panelConsole.log("No se encontró el vehículo con la placa: " + placa);
        }
    }
}
