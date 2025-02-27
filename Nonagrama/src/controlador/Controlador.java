package controlador;

import interfaz.panelVidas;
import mundo.Verificar;

public class Controlador {

    
    private panelVidas panelVidas;
    private Verificar verificar;

    public Controlador() {
        panelVidas = new panelVidas();
        verificar = new Verificar();
    }

    public void revisarX(int fila, int columna){
           if(verificar.verificarX(fila, columna)){
               panelVidas.perderVida(verificar.getVidasRestantes());
           }
    }
    
    public void revisar0(int fila, int columna){
            if(verificar.verificar0(fila, columna)){
                panelVidas.perderVida(verificar.getVidasRestantes());
            }
    }
    
}
