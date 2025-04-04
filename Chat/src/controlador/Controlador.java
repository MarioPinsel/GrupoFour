package controlador;

import interfaz.panelChat;
import interfaz.panelInformacion;
import mundo.Cliente;
import mundo.Remisor;
import mundo.Receptor;

public class Controlador {

    private panelInformacion panelInfo;
    private panelChat panelChat;
    private Remisor remisor;
    private Cliente cliente;
    private Receptor receptor;

    public Controlador() {
        cliente = new Cliente("", "");
        remisor = new Remisor(cliente);

        receptor = new Receptor(this);

    }

    public void setInstance(panelInformacion panelInfo, panelChat panelChat) {
        this.panelInfo = panelInfo;
        this.panelChat = panelChat;
    }

    public void obtenerTodaInformacion(String msg) {
        panelChat.agregarMensaje("Yo: " + msg);

        cliente.setIp(panelInfo.getDireccion());
        cliente.setMensaje(msg);

        remisor.enviarMensaje();
    }

    public void mostrarTexto(String msg) {
        panelChat.agregarMensaje("Mario:" + msg);
    }

}
