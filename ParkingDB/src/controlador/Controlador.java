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

    public void create(String placa, String numero, String nombre, String cedula) {

    }

    public void read(String placa) {

    }

    public void update(String placa, String numero, String nombre, String cedula) {
    }

    public void delete(String placa) {

    }
}
