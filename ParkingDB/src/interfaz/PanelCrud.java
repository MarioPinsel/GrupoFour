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
        this.panelVehicle = panelVehicle;  // Pasar referencia de PanelVehicle

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

        // Agregar listeners para enviar la opción seleccionada
        rbCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendDataToPanelVehicle("Create");
            }
        });
        rbRead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendDataToPanelVehicle("Read");
            }
        });
        rbUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendDataToPanelVehicle("Update");
            }
        });
        rbDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendDataToPanelVehicle("Delete");
            }
        });
    }

    private void sendDataToPanelVehicle(String selectedOption) {
        // Llamar al método de PanelVehicle para actualizar el valor de la placa
        panelVehicle.setPlate(selectedOption);
    }

    public String getOpcionSeleccionada() {
        if (rbCreate.isSelected()) return "Create";
        if (rbRead.isSelected()) return "Read";
        if (rbUpdate.isSelected()) return "Update";
        if (rbDelete.isSelected()) return "Delete";
        return null;
    }
}
