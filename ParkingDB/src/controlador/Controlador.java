package controlador;

import interfaz.PanelConsole;
import interfaz.PanelCrud;
import interfaz.PanelVehicle;
import java.util.ArrayList;

import mundo.*;

public class Controlador {
    
    private Parking parking;
    private PanelCrud panelCrud;
    private PanelVehicle panelVehicle;
    private PanelConsole panelConsole;
    
    public Controlador() {
        parking = new Parking();
    }
    
    public void setIntance(PanelCrud pC, PanelVehicle pV, PanelConsole pCo) {
        this.panelCrud = pC;
        this.panelVehicle = pV;
        this.panelConsole = pCo;
    }
    
    public void create(String placa, String numero, String nombre, String cedula) {        
        panelConsole.log(parking.create(placa, numero, nombre, cedula));        
    }
    
    public void read(String placa) {
        ArrayList<String> info = parking.read(placa);        
        panelConsole.log(info.get(0));
        info.remove(0);        
        panelVehicle.actualizarInterfaz(info);
        
    }

//    public void update(String placa, String numero, String nombre, String cedula) {
//        ArrayList<String> info = new ArrayList<>();
//        info.add(placa);
//        info.add(numero);
//        info.add(nombre);
//        info.add(cedula);
//        parking.update(placa, numero, nombre, cedula);
//    }
//    public void delete(String placa) {
//        if (parking.delete(placa)) {
//            panelConsole.log("Vehículo eliminado correctamente.");
//        } else {
//            panelConsole.log("No se encontró el vehículo con la placa: " + placa);
//        }
//    }
}
