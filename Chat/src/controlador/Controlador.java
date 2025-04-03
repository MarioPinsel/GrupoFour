
package controlador;

import interfaz.panelChat;
import interfaz.panelInformacion;
import mundo.Cliente;
import mundo.Remisor;

/**
 *
 * @author User
 */
public class Controlador {
    
        private panelInformacion panelInfo;
        private panelChat panelChat;
        private Remisor remisor;
        private Cliente cliente;
        private String texto, ip, nick;

    public Controlador() {
        cliente = new Cliente(texto,ip);
        remisor = new Remisor(cliente);
        
    }
        
        public void setInstance(panelInformacion panelInfo, panelChat panelChat) {
        this.panelInfo = panelInfo;
        this.panelChat = panelChat;
    }
        
        public void obtenerTodaInformacion(String msg){
             texto = msg;
             panelChat.agregarMensaje("Yo: " + msg);
             ip = panelInfo.getDireccion();
             nick = panelInfo.getNick();             
        }
        
        public void mostrarTexto(String msg){
            panelChat.agregarMensaje("Mario: " + msg);
        }
        
}
