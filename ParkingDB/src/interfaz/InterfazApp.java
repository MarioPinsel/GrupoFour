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
        
        ctrl = new Controlador();
        panelVehicle = new PanelVehicle(ctrl);
        panelCrud = new PanelCrud(panelVehicle); 
        panelConsole = new PanelConsole();
        
        ctrl.setIntance(panelCrud, panelVehicle, panelConsole);    
        
        add(panelCrud, BorderLayout.NORTH);
        add(panelVehicle, BorderLayout.CENTER);
        add(panelConsole, BorderLayout.SOUTH);
        
            

        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) throws SQLException {

        new InterfazApp();
        
        
        
       

    }
}