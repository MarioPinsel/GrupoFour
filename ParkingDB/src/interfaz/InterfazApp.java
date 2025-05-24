package interfaz;

import controlador.Controlador;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import mundo.Parking;
import mundo.Persistencia;

public class InterfazApp extends JFrame {

    private PanelCrud panelCrud;
    private PanelVehicle panelVehicle;
    private PanelConsole panelConsole;
    private Controlador ctrl;

    public InterfazApp() {
        
        setTitle("Parking");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panelVehicle = new PanelVehicle();
        panelCrud = new PanelCrud(panelVehicle); 
        panelConsole = new PanelConsole();
        ctrl = new Controlador();
        ctrl.setIntance(panelCrud, panelVehicle, panelConsole);    
        
        add(panelCrud, BorderLayout.NORTH);
        add(panelVehicle, BorderLayout.CENTER);
        add(panelConsole, BorderLayout.SOUTH);
        
            

        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) throws SQLException {
EST-wawa
        new InterfazApp();
        Parking pers = new Parking();
        //pers.insert();
        pers.read("BAT000");

        new InterfazApp();       
       
main

    }
}