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
        if (info.size() == 1) {
            panelConsole.log(info.get(0));
        } else {
            panelConsole.log(info.get(0));
            info.remove(0);
            for (String aya : info) {
                panelConsole.log(aya);
            }
            panelVehicle.actualizarInterfaz(info);
        }
    }

    public ArrayList<String> getLiquidacion(String Placa) {
        return parking.liquidar(Placa);
    }

    public boolean getInformation(String cedula) {
        ArrayList<String> info = parking.existenceValidation(cedula);

        if (info.size() == 1) {
            panelConsole.log(info.get(0));
            return false;
        }
        panelVehicle.actualizarInterfaz(info);
        return true;
    }

    public void update(ArrayList<String> info) {        
        parking.update(info);
        panelConsole.log("Datos actualizados");
    }

    public void delete(String placa) {
        ArrayList<String> info = parking.delete(placa);

        if (info.size() == 1) {
            panelConsole.log(info.get(0));
        } else {
            panelConsole.log(info.get(0));
            info.remove(0);

            ArrayList<String> infoLimpia = new ArrayList<>();
            for (String aya : info) {
                panelConsole.log(aya);
                String[] partes = aya.split(":", 2);
                if (partes.length == 2) {
                    infoLimpia.add(partes[1].trim());
                } else {
                    infoLimpia.add(aya);
                }
            }
            panelVehicle.actualizarInterfaz(infoLimpia);
        }
    }
}
