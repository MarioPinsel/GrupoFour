package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCrud extends JPanel {
    private JRadioButton rbCreate;
    private JRadioButton rbRead;
    private JRadioButton rbUpdate;
    private JRadioButton rbDelete;
    private ButtonGroup grupoBotones;
    private PanelVehicle panelVehicle;  

    public PanelCrud(PanelVehicle panelVehicle) {
        this.panelVehicle = panelVehicle; 

        setBorder(BorderFactory.createTitledBorder("CRUD"));
        setLayout(new FlowLayout());

        rbCreate = new JRadioButton("Create");
        rbRead = new JRadioButton("Read");
        rbUpdate = new JRadioButton("Update");
        rbDelete = new JRadioButton("Delete");

        grupoBotones = new ButtonGroup();
        grupoBotones.add(rbCreate);
        grupoBotones.add(rbRead);
        grupoBotones.add(rbUpdate);
        grupoBotones.add(rbDelete);

        add(rbCreate);
        add(rbRead);
        add(rbUpdate);
        add(rbDelete);  


        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcion = getOpcionSeleccionada();
                if (opcion != null) {
                    panelVehicle.setMode(opcion);
                }
            }
        };

        rbCreate.addActionListener(listener);
        rbRead.addActionListener(listener);
        rbUpdate.addActionListener(listener);
        rbDelete.addActionListener(listener);

       
    }

    public String getOpcionSeleccionada() {
        if (rbCreate.isSelected()) return "Create";
        if (rbRead.isSelected()) return "Read";
        if (rbUpdate.isSelected()) return "Update";
        if (rbDelete.isSelected()) return "Delete";
        return null;
    }
}

