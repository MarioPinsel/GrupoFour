package mundo;

import java.awt.Color;

/**
 *
 * @author Esteban
 */
public class Cubo {

    private Color fro, der, izq, inf, sup, pos;

    public Cubo() {
        this.fro = Color.BLUE;
        this.izq = Color.RED;
        this.der = Color.ORANGE;
        this.inf = Color.YELLOW;
        this.sup = Color.BLACK;
        this.pos = Color.GREEN;
        
    }

    public void Horizontal() {
        Color vacio;
        
        vacio = fro;
        fro =  izq;
        izq = pos;
        pos = der;
        der = vacio;
        
    }
    public void Vertical() {
        Color vacio;
        
        vacio = this.fro;
        this.fro = this.sup;
        this.sup = this.pos;
        this.pos = this.inf;
        this.inf = vacio;
        
    }
    public void Transversal() {
        Color vacio;
        
        vacio = this.sup;
        this.sup = this.der;
        this.der = this.inf;
        this.inf = this.izq;
        this.izq = vacio;
        
    }

    public Color getFro() {
        return fro;
    }

    public void setFro(Color fro) {
        this.fro = fro;
    }

    public Color getDer() {
        return der;
    }

    public void setDer(Color der) {
        this.der = der;
    }

    public Color getIzq() {
        return izq;
    }

    public void setIzq(Color izq) {
        this.izq = izq;
    }

    public Color getInf() {
        return inf;
    }

    public void setInf(Color inf) {
        this.inf = inf;
    }

    public Color getSup() {
        return sup;
    }

    public void setSup(Color sup) {
        this.sup = sup;
    }

    public Color getPos() {
        return pos;
    }

    public void setPos(Color pos) {
        this.pos = pos;
    }
    
    
}
