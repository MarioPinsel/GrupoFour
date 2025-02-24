package interfaz;

import controlador.Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import mundo.Rubik;

/**
 *
 * @author sg701-19
 */
public class InterfazApp extends JFrame {
    private final Panel1 pnlPanel1;
    private final Panel2 pnlPanel2;
    private final Panel3 pnlPanel3;
    private final Panel4 pnlPanel4;
    private final Panel5 pnlPanel5;
    private final Panel6 pnlPanel6;
    private Rubik giro;
    private Controlador control;
    
    public InterfazApp() {
        //Definición de organizacion
        getContentPane().setLayout(null);

        giro = new Rubik();
        control = new Controlador();
        
        pnlPanel1 = new Panel1(giro);
        pnlPanel1.setBounds(300, 175, 100, 100);

        pnlPanel2 = new Panel2(giro);
        pnlPanel2.setBounds(203, 175, 100, 100);

        pnlPanel3 = new Panel3(giro);
        pnlPanel3.setBounds(400, 175, 100, 100);

        pnlPanel4 = new Panel4(giro);
        pnlPanel4.setBounds(500, 175, 100, 100);

        pnlPanel5 = new Panel5(giro);
        pnlPanel5.setBounds(300, 74, 100, 100);

        pnlPanel6 = new Panel6(giro);
        pnlPanel6.setBounds(300, 276, 100, 100);

        //Agregar paneles
        getContentPane().add(pnlPanel1);
        getContentPane().add(pnlPanel2);
        getContentPane().add(pnlPanel3);
        getContentPane().add(pnlPanel4);
        getContentPane().add(pnlPanel5);
        getContentPane().add(pnlPanel6);

        //Agregamos botones de giro normal 
        JButton boton1 = new JButton("1");
        boton1.setBounds(300, 43, 50, 30);
        getContentPane().add(boton1);

        JButton boton2 = new JButton("2");
        boton2.setBounds(350, 43, 50, 30);
        getContentPane().add(boton2);

        JButton boton3 = new JButton("3");
        boton3.setBounds(269, 74, 40, 50);
        getContentPane().add(boton3);

        JButton boton4 = new JButton("4");
        boton4.setBounds(269, 124, 40, 50);
        getContentPane().add(boton4);

        JButton boton5 = new JButton("5");
        boton5.setBounds(172, 176, 40, 50);
        getContentPane().add(boton5);

        JButton boton6 = new JButton("6");
        boton6.setBounds(172, 225, 40, 50);
        getContentPane().add(boton6);
        
        //Agregamos botones de giro inverso
        //.
        //.
        
        JButton boton7 = new JButton("7");
        boton7.setBounds(300, 378, 50, 30);
        getContentPane().add(boton7);

        JButton boton8 = new JButton("8");
        boton8.setBounds(350, 378, 50, 30);
        getContentPane().add(boton8);

        JButton boton9 = new JButton("9");
        boton9.setBounds(400, 74, 40, 50);
        getContentPane().add(boton9);

        JButton boton10 = new JButton("10");
        boton10.setBounds(400, 124, 50, 50);
        getContentPane().add(boton10);

        JButton boton11 = new JButton("11");
        boton11.setBounds(600, 176, 50, 50);
        getContentPane().add(boton11);
        
        
        JButton boton12 = new JButton("12");
        boton12.setBounds(600, 225, 50, 50);
        getContentPane().add(boton12);
        

        

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.giroYIzq(1, giro);
                actualizarInterfaz();
            }        
        }
        );
        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.giroYDer(1, giro);
                actualizarInterfaz();
            }        
        }
        );
        boton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.giroZPos(3, giro);
                actualizarInterfaz();
            }        
        }
        );
        boton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.giroZFro(3, giro);
                actualizarInterfaz();
            }        
        }
        );
        boton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.giroXSup(1, giro);
                actualizarInterfaz();
            }        
        }
        );

        boton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.giroXInf(1, giro);
                actualizarInterfaz();
            }        
        }
        );
        boton7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.giroYIzq(3, giro);
                actualizarInterfaz();
            }        
        }
        );
        boton8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.giroYDer(3, giro);
                actualizarInterfaz();
            }        
        }
        );
        boton9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.giroZPos(1, giro);
                actualizarInterfaz();
            }        
        }
        );
        boton10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.giroZFro(1, giro);
                actualizarInterfaz();
            }        
        }
        );
        boton11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.giroXSup(3, giro);
                actualizarInterfaz();
            }        
        }
        );

        boton12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.giroXInf(3, giro);
                actualizarInterfaz();
            }        
        }
        );
        
        
        this.setTitle("Rubik");
        this.setSize(800, 500);
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void actualizarInterfaz() {
    pnlPanel1.actualizarColores();
    pnlPanel2.actualizarColores();
    pnlPanel3.actualizarColores();
    pnlPanel4.actualizarColores();
    pnlPanel5.actualizarColores();
    pnlPanel6.actualizarColores();
    }
    
    public static void main(String[] args) {

        InterfazApp frmMain = new InterfazApp();
        frmMain.setVisible(true);

    }
    
}

