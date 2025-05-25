package interfaz;

import controlador.Controlador;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelVehicle extends JPanel {

    private JTextField txtPlate;
    private JTextField txtOwner;
    private JTextField txtPhone;
    private JTextField txtCedula;
    private JTextField txtEntry;
    private JTextField txtDeparture;
    private JTextField txtPay;
    private JButton btnLiquidate;
    private JButton btnSend;
    private Controlador ctrl;
    private String mode;

    public PanelVehicle(Controlador ctrl) {
        this.ctrl = ctrl;
        setBorder(BorderFactory.createTitledBorder("VEHICLE"));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Plate:"), gbc);
        gbc.gridx = 1;
        txtPlate = new JTextField(10);
        add(txtPlate, gbc);

        gbc.gridx = 2;
        add(new JLabel("Owner/visitor:"), gbc);
        gbc.gridx = 3;
        txtOwner = new JTextField(20);
        add(txtOwner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Phone number:"), gbc);
        gbc.gridx = 1;
        txtPhone = new JTextField(10);
        add(txtPhone, gbc);

        gbc.gridx = 2;
        add(new JLabel("Cédula:"), gbc);
        gbc.gridx = 3;
        txtCedula = new JTextField(10);
        add(txtCedula, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(new JLabel("Entry:"), gbc);
        gbc.gridy = 3;
        txtEntry = new JTextField(8);
        txtEntry.setEditable(false);
        add(txtEntry, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        add(new JLabel("Departure:"), gbc);
        gbc.gridy = 3;
        txtDeparture = new JTextField(5);
        txtDeparture.setEditable(true);
        add(txtDeparture, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        add(new JLabel("Pay:"), gbc);
        gbc.gridy = 3;
        txtPay = new JTextField(5);
        txtPay.setEditable(true);
        add(txtPay, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        btnLiquidate = new JButton("Liquidate");
        add(btnLiquidate, gbc);

        gbc.gridx = 3;
        gbc.gridy = 4;
        btnSend = new JButton("Send");
        add(btnSend, gbc);

        btnSend.addActionListener(e -> {
            String placa = getPlate();

//            if (!esPlacaValida(placa)) {
//                JOptionPane.showMessageDialog(this, "La placa ingresada no es válida", "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }

            switch (mode) {
                case "Create":
                    String telefono = getPhone();
                    String cedula = getCedula();
                    String nombre = getOwner().toUpperCase();

                    if (!soloNumeros(telefono)) {
                        JOptionPane.showMessageDialog(this, "El número de teléfono debe contener solo números", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (!soloNumeros(cedula)) {
                        JOptionPane.showMessageDialog(this, "La cédula debe contener solo números", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if ("Create".equals(mode)) {
                        ctrl.create(placa, telefono, nombre, cedula);
                    }
                    break;
                case "Update":
                     telefono = getPhone();
                     cedula = getCedula();
                     nombre = getOwner().toUpperCase();

//                    if (!soloNumeros(telefono)) {
//                        JOptionPane.showMessageDialog(this, "El número de teléfono debe contener solo números", "Error", JOptionPane.ERROR_MESSAGE);
//                        return;
//                    }
//
                    if (!soloNumeros(cedula)) {
                        JOptionPane.showMessageDialog(this, "La cédula debe contener solo números", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if ("Create".equals(mode)) {
                        ctrl.create(placa, telefono, nombre, cedula);
                    } else {
                        String prevNumero = telefono;
                        String prevNombre = nombre;
                        txtPlate.setEditable(false);
                        ctrl.getInformation(cedula); 
                        txtCedula.setEditable(false);
                        
                        if (prevNumero.equals(telefono) || prevNombre.equals(nombre)) {
                            ArrayList<String> info = new ArrayList<>();
                            info.add(cedula);
                            info.add(nombre);
                            info.add(telefono);
                            
                            ctrl.update(info);
                        }
                    }

                    break;

                case "Read":
                    ctrl.read(placa);

                    break;

                case "Delete":
                    //ctrl.delete(placa);
                    break;

            }

        });

        btnLiquidate.addActionListener(e -> {
            String placa = getPlate();

            if (!esPlacaValida(placa)) {
                JOptionPane.showMessageDialog(this, "La placa ingresada no es válida", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            ArrayList<String> info = ctrl.getLiquidacion(placa);
            txtEntry.setText(info.get(0));
            txtDeparture.setText(info.get(1));
            txtPay.setText(info.get(2));
            btnLiquidate.setEnabled(false);

        });
    }

    public String getPlate() {
        return txtPlate.getText();
    }

    public String getOwner() {
        return txtOwner.getText();
    }

    public String getPhone() {
        return txtPhone.getText();
    }

    public String getCedula() {
        return txtCedula.getText();
    }

    public String getEntry() {
        return txtEntry.getText();
    }

    public String getDeparture() {
        return txtDeparture.getText();
    }

    public String getPay() {
        return txtPay.getText();
    }

    public JButton getBtnSend() {
        return btnSend;
    }

    public JButton getBtnLiquidate() {
        return btnLiquidate;
    }

    public void setMode(String mode) {
        this.mode = mode;
        if ("Create".equals(mode)) {
            txtEntry.setEnabled(true);
        } else {
            txtEntry.setText("");
            txtEntry.setEnabled(false);
        }

        switch (mode) {
            case "Create":
                txtPlate.setEditable(true);
                txtOwner.setEditable(true);
                txtPhone.setEditable(true);
                txtCedula.setEditable(true);
                txtEntry.setEditable(false);
                txtDeparture.setEditable(false);
                txtPay.setEditable(false);
                btnLiquidate.setEnabled(false);
                btnSend.setEnabled(true);
                break;
            case "Read":

            case "Delete":
                txtPlate.setEditable(true);
                txtOwner.setEditable(false);
                txtPhone.setEditable(false);
                txtCedula.setEditable(false);
                txtEntry.setEditable(false);
                txtDeparture.setEditable(false);
                txtPay.setEditable(false);
                btnLiquidate.setEnabled(false);
                btnSend.setEnabled(true);
                break;
            case "Update":
                txtPlate.setEditable(true);
                txtOwner.setEditable(true);
                txtPhone.setEditable(true);
                txtCedula.setEditable(true);
                txtEntry.setEditable(false);
                txtDeparture.setEditable(false);
                txtPay.setEditable(false);
                btnLiquidate.setEnabled(false);
                btnSend.setEnabled(true);
                break;
            default:
                txtPlate.setEditable(false);
                txtOwner.setEditable(false);
                txtPhone.setEditable(false);
                txtCedula.setEditable(false);
                txtEntry.setEditable(false);
                txtDeparture.setEditable(false);
                txtPay.setEditable(false);
                btnLiquidate.setEnabled(false);
                btnSend.setEnabled(false);
                break;
        }
    }

    public void actualizarCampos() {
        txtEntry.setText("");
        txtDeparture.setText("");
        txtPay.setText("");
        txtCedula.setText("");
        txtOwner.setText("");
        txtPhone.setText("");
        txtPlate.setText("");

    }

    public void actualizarInterfaz(ArrayList<String> informacion) {

        txtEntry.setText(informacion.get(0));
        txtDeparture.setText(informacion.get(1));
        txtPay.setText(informacion.get(2));
        txtCedula.setText(informacion.get(3));
        txtOwner.setText(informacion.get(4));
        txtPhone.setText(informacion.get(5));

        if (txtPay.getText().equals(".")) {
            btnLiquidate.setEnabled(true);
        }

    }

    private boolean esPlacaValida(String placa) {
        if (placa == null) {
            return false;
        }
        placa = placa.toUpperCase().trim();
        if (placa.matches("^[A-Z]{3}\\d{3}$")) {
            return true;
        }
        return placa.matches("^[A-Z]{3}\\d{2}[A-Z]$");
    }

    private boolean soloNumeros(String texto) {
        if (texto == null || texto.isEmpty()) {
            return false;
        }
        return texto.matches("\\d+");
    }
}
