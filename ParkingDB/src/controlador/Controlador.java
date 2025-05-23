package controlador;

import interfaz.*;

public class Controlador {

    private PanelCrud panelCrud;
    private PanelVehicle panelVehicle;
    private PanelConsole panelConsole;

    public Controlador() {
        // Constructor vacío por ahora
    }

    // Método para inyectar los paneles desde la interfaz
    public void setPanels(PanelCrud pc, PanelVehicle pv, PanelConsole pco) {
        this.panelCrud = pc;
        this.panelVehicle = pv;
        this.panelConsole = pco;
    }

    // CREATE
    public void create(String placa, String modelo) {
        // Simulación: si la placa empieza con A, está "creado"
        if (placa.startsWith("A")) {
            panelConsole.log("Vehículo creado correctamente: " + placa);
            // panelVehicle.addVehicle(...) se puede simular luego
        } else {
            panelConsole.log("Error: el vehículo ya existe: " + placa);
        }
    }

    // READ
    public void read(String placa) {
        // Simulación: si la placa termina en 1, no existe
        if (placa.endsWith("1")) {
            panelConsole.log("Vehículo no existe con placa: " + placa);
        } else {
            // Simulación: todos los que no terminan en 1 están parqueados
            panelConsole.log("Vehículo ya está parqueado: " + placa);
        }
    }

    // UPDATE
    public void update(String placa, String nuevoModelo) {
        // Simulación: si la placa contiene "Z", no existe
        if (placa.contains("Z")) {
            panelConsole.log("No existe vehículo con placa: " + placa);
        } else {
            panelConsole.log("Vehículo actualizado: " + placa);
        }
    }

    // DELETE
    public void delete(String placa) {
        // Simulación: si la placa es "000", no existe
        if (placa.equals("000")) {
            panelConsole.log("No existe vehículo con placa: " + placa);
        } else {
            panelConsole.log("Vehículo eliminado: " + placa);
        }
    }
}
