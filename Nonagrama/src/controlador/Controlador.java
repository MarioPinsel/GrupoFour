package controlador;

import interfaz.panelVidas;
import mundo.Verificar;

public class Controlador {

    private panelVidas panelVidas;
    private Verificar verificar;

    public Controlador() {
        verificar = new Verificar();
    }

    public void setInstance(panelVidas panelVidas) {
        this.panelVidas = panelVidas;
    }

    public boolean revisarX(int fila, int columna) {
        if (verificar.verificarX(fila, columna)) {
            panelVidas.perderVida(verificar.getVidasRestantes());
            return false;
        } else
            return true;
    }

    public boolean revisar0(int fila, int columna) {
        if (verificar.verificar0(fila, columna)) {
            panelVidas.perderVida(verificar.getVidasRestantes());
            return false;
        } else
            return true;
    }

    public Verificar getVerificar() {
        return verificar;
    }
    

}
