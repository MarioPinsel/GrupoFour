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
public class panelVidas extends JPanel{
    private ImageIcon imagen1;
    private ImageIcon imagen2;
    private ImageIcon imagen3;
    
 public panelVidas() {
        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new TitledBorder("")));
        setLayout(new FlowLayout());        
        vidas();

    }    
 
 public void vidas(){
     imagen1 = new ImageIcon(getClass().getResource("/imagenes/live.png"));     
     JLabel vida1 = new JLabel(imagen1);     
     add(vida1);
     
     imagen2 = new ImageIcon(getClass().getResource("/imagenes/live.png"));     
     JLabel vida2 = new JLabel(imagen2);
     add(vida2);
     
     imagen3 = new ImageIcon(getClass().getResource("/imagenes/live.png"));     
     JLabel vida3 = new JLabel(imagen3);
     add(vida3);
 }
}
