package interfaz;

import mundo.Movimiento;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author sg701-15
 */
public class PanelDatos extends JPanel {

    private Movimiento ctrl = new Movimiento();
    private JButton[][] botones;

    public PanelDatos() {

        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new TitledBorder("")));
        setLayout(null);
        SubPaneles();
    }

    public void SubPaneles() {

        removeAll();

        botones = new JButton[4][4];

        JButton B1 = new JButton("1");
        B1.setBounds(0, 0, 100, 100);
        B1.setBackground(Color.BLACK);
        B1.setForeground(Color.WHITE);
        add(B1);

        JButton B2 = new JButton("2");
        B2.setBounds(100, 0, 100, 100);
        B2.setBackground(Color.BLACK);
        B2.setForeground(Color.WHITE);
        add(B2);

        JButton B3 = new JButton("3");
        B3.setBounds(200, 0, 100, 100);
        B3.setBackground(Color.BLACK);
        B3.setForeground(Color.WHITE);
        add(B3);

        JButton B4 = new JButton("4");
        B4.setBounds(300, 0, 100, 100);
        B4.setBackground(Color.BLACK);
        B4.setForeground(Color.WHITE);
        add(B4);

        JButton B5 = new JButton("5");
        B5.setBounds(0, 100, 100, 100);
        B5.setBackground(Color.BLACK);
        B5.setForeground(Color.WHITE);
        add(B5);

        JButton B6 = new JButton("6");
        B6.setBounds(100, 100, 100, 100);
        B6.setBackground(Color.BLACK);
        B6.setForeground(Color.WHITE);
        add(B6);

        JButton B7 = new JButton("7");
        B7.setBounds(200, 100, 100, 100);
        B7.setBackground(Color.BLACK);
        B7.setForeground(Color.WHITE);
        add(B7);

        JButton B8 = new JButton("8");
        B8.setBounds(300, 100, 100, 100);
        B8.setBackground(Color.BLACK);
        B8.setForeground(Color.WHITE);
        add(B8);

        JButton B9 = new JButton("9");
        B9.setBounds(0, 200, 100, 100);
        B9.setBackground(Color.BLACK);
        B9.setForeground(Color.WHITE);
        add(B9);

        JButton B10 = new JButton("10");
        B10.setBounds(100, 200, 100, 100);
        B10.setBackground(Color.BLACK);
        B10.setForeground(Color.WHITE);
        add(B10);

        JButton B11 = new JButton("11");
        B11.setBounds(200, 200, 100, 100);
        B11.setBackground(Color.BLACK);
        B11.setForeground(Color.WHITE);
        add(B11);

        JButton B12 = new JButton("12");
        B12.setBounds(300, 200, 100, 100);
        B12.setBackground(Color.BLACK);
        B12.setForeground(Color.WHITE);
        add(B12);

        JButton B13 = new JButton("13");
        B13.setBounds(0, 300, 100, 100);
        B13.setBackground(Color.BLACK);
        B13.setForeground(Color.WHITE);
        add(B13);

        JButton B14 = new JButton("14");
        B14.setBounds(100, 300, 100, 100);
        B14.setBackground(Color.BLACK);
        B14.setForeground(Color.WHITE);
        add(B14);

        JButton B15 = new JButton("15");
        B15.setBounds(200, 300, 100, 100);
        B15.setBackground(Color.BLACK);
        B15.setForeground(Color.WHITE);
        add(B15);

        JButton B16 = new JButton("");
        B16.setBounds(300, 300, 100, 100);
        B16.setBackground(Color.WHITE);
        B16.setBorderPainted(false);
        add(B16);

        botones[0][0] = B1;
        botones[0][1] = B2;
        botones[0][2] = B3;
        botones[0][3] = B4;
        botones[1][0] = B5;
        botones[1][1] = B6;
        botones[1][2] = B7;
        botones[1][3] = B8;
        botones[2][0] = B9;
        botones[2][1] = B10;
        botones[2][2] = B11;
        botones[2][3] = B12;
        botones[3][0] = B13;
        botones[3][1] = B14;
        botones[3][2] = B15;
        botones[3][3] = B16;

        B1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ctrl.Cambio(B16, B1, botones, 0, 0);
            }
        });

        B2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ctrl.Cambio(B16, B2, botones, 0, 1);
            }
        });

        B3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ctrl.Cambio(B16, B3, botones, 0, 2);
            }
        });

        B4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ctrl.Cambio(B16, B4, botones, 0, 3);
            }
        });

        B5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ctrl.Cambio(B16, B5, botones, 1, 0);
            }
        });

        B6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrl.Cambio(B16, B6, botones, 1, 1);
            }
        });

        B7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrl.Cambio(B16, B7, botones, 1, 2);
            }
        });

        B8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrl.Cambio(B16, B8, botones, 1, 3);
            }
        });

        B9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrl.Cambio(B16, B9, botones, 2, 0);
            }
        });

        B10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrl.Cambio(B16, B10, botones, 2, 1);
            }
        });

        B11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrl.Cambio(B16, B11, botones, 2, 2);
            }
        });

        B12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrl.Cambio(B16, B12, botones, 2, 3);
            }
        });

        B13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrl.Cambio(B16, B13, botones, 3, 0);
            }
        });

        B14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrl.Cambio(B16, B14, botones, 3, 1);
            }
        });

        B15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrl.Cambio(B16, B15, botones, 3, 2);
            }
        });

        revalidate();

    }

    public JButton[][] getBotones() {
        return botones;
    }

    public void setBotones(JButton[][] botones) {
        this.botones = botones;
    }
}
