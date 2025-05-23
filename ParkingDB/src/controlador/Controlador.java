package controlador;

import interfaz.*;
import mundo.*;

public class Controlador {

    private Parking parking;
    private PanelCrud panelCrud;
    private PanelVehicle panelVehicle;
    private PanelConsole panelConsole;

    public Controlador(Parking parking) {
        this.parking = parking;
    }

    public void setPanels(PanelCrud pc, PanelVehicle pv, PanelConsole pco) {
        this.panelCrud = pc;
        this.panelVehicle = pv;
        this.panelConsole = pco;
    }

    public void create(String placa, String modelo) {
        Vehicle v = new Vehicle(placa, modelo);
        boolean ok = parking.create(v);
        if (ok) {
            panelConsole.log("Vehículo creado correctamente: " + placa);
            panelVehicle.addVehicle(v);
        } else {
            panelConsole.log("Error: el vehículo ya existe: " + placa);
        }
    }

    public void read(String placa) {
        Vehicle v = parking.read(placa);
        if (v == null) {
            panelConsole.log("Vehículo no existe con placa: " + placa);
        } else if (v.parqueado) { // ← acceso directo al atributo
            panelConsole.log("Vehículo ya está parqueado: " + placa);
        }
    }

    public void update(String placa, String newModelo) {
        Vehicle v = parking.read(placa);
        if (v != null) {
            v.modelo = newModelo; // o v.setModelo(newModelo) si hay encapsulamiento
            boolean ok = parking.update(v);
            if (ok) {
                panelConsole.log("Vehículo actualizado: " + placa);
                panelVehicle.updateVehicle(v);
            } else {
                panelConsole.log("No se pudo actualizar: " + placa);
            }
        } else {
            panelConsole.log("No existe vehículo con placa: " + placa);
        }
    }

    public void delete(String placa) {
        boolean ok = parking.delete(placa);
        if (ok) {
            panelConsole.log("Vehículo eliminado: " + placa);
            panelVehicle.removeVehicle(placa);
        } else {
            panelConsole.log("No existe vehículo con placa: " + placa);
        }
    }
}
