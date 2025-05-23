package interfaz;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

    private String fechaHoraActual;

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public PanelVehicle() {
        setBorder(BorderFactory.createTitledBorder("VEHICLE"));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Plate:"), gbc);
        gbc.gridx = 1;
        txtPlate = new JTextField(10);
        add(txtPlate, gbc);

        gbc.gridx = 2;
        add(new JLabel("Owner/visitor:"), gbc);
        gbc.gridx = 3;
        txtOwner = new JTextField(20);
        add(txtOwner, gbc);

        
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Phone number:"), gbc);
        gbc.gridx = 1;
        txtPhone = new JTextField(10);
        add(txtPhone, gbc);

        gbc.gridx = 2;
        add(new JLabel("Cédula:"), gbc);
        gbc.gridx = 3;
        txtCedula = new JTextField(10);
        add(txtCedula, gbc);

        
        gbc.gridx = 1; gbc.gridy = 2;
        add(new JLabel("Entry:"), gbc);
        gbc.gridy = 3;
        txtEntry = new JTextField(8);
        txtEntry.setEditable(false);
        add(txtEntry, gbc);

        gbc.gridx = 2; gbc.gridy = 2;
        add(new JLabel("Departure:"), gbc);
        gbc.gridy = 3;
        txtDeparture = new JTextField(5);
        txtDeparture.setEditable(true);
        add(txtDeparture, gbc);

        gbc.gridx = 3; gbc.gridy = 2;
        add(new JLabel("Pay:"), gbc);
        gbc.gridy = 3;
        txtPay = new JTextField(5);
        txtPay.setEditable(true);
        add(txtPay, gbc);

      
        gbc.gridx = 0; gbc.gridy = 3;
        btnLiquidate = new JButton("Liquidate");
        add(btnLiquidate, gbc);

        gbc.gridx = 3; gbc.gridy = 4;
        btnSend = new JButton("Send");
        add(btnSend, gbc);


        btnSend.addActionListener(e -> {
            fechaHoraActual = LocalDateTime.now().format(dateTimeFormatter);
            txtEntry.setText(LocalTime.now().format(timeFormatter));
        });
    }

    
    public String getPlate() {
        return txtPlate.getText().trim();
    }

    public String getOwner() {
        return txtOwner.getText().trim();
    }

    public String getPhone() {
        return txtPhone.getText().trim();
    }

    public String getCedula() {
        return txtCedula.getText().trim();
    }

    public String getEntry() {
        return txtEntry.getText().trim();
    }

    public String getDeparture() {
        return txtDeparture.getText().trim();
    }

    public String getPay() {
        return txtPay.getText().trim();
    }

    public String getFechaHoraActual() {
        return fechaHoraActual;
    }

    public JButton getBtnSend() {
        return btnSend;
    }

    public JButton getBtnLiquidate() {
        return btnLiquidate;
    }

    private void actualizarHoraEntry() {
        String horaActual = LocalDateTime.now().format(timeFormatter);
        txtEntry.setText(horaActual);
    }

    public void setMode(String mode) {
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
}
