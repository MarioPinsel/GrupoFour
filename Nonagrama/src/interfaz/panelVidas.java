package interfaz;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Esteban
 */
public class panelVidas extends JPanel {

    private ImageIcon imagen1;
    private ImageIcon imagen2;
    private JLabel vida1;
    private JLabel vida2;
    private JLabel vida3;

    public panelVidas() {
        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new TitledBorder("")));
        setLayout(new FlowLayout());
        vidas();

    }

    public void vidas() {
        imagen1 = new ImageIcon(getClass().getResource("/imagenes/live.png"));
        imagen2 = new ImageIcon(getClass().getResource("/imagenes/die.png"));

        vida1 = new JLabel(imagen1);
        vida2 = new JLabel(imagen1);
        vida3 = new JLabel(imagen1);

        add(vida1);
        add(vida2);
        add(vida3);
    }

    public void perderVida(int indice) {
        switch (indice) {
            case 0:
                vida1.setIcon(imagen2);
                break;
            case 1:
                vida2.setIcon(imagen2);
                break;
            case 2:
                vida3.setIcon(imagen2);
                break;
            default:
                System.out.println("Índice de vida inválido.");

        }

    }

    public ImageIcon getImagen1() {
        return imagen1;
    }

    public void setImagen1(ImageIcon imagen1) {
        this.imagen1 = imagen1;
    }

    public ImageIcon getImagen2() {
        return imagen2;
    }

    public void setImagen2(ImageIcon imagen2) {
        this.imagen2 = imagen2;
    }

}
