package mundoServidor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

/**
 *
 * @author Esteban
 */
public class Servidor {

    private ArrayList<redMensajes> listaIps;

    public Servidor(ArrayList<String> ips) {
        listaIps = new ArrayList<>();

        for (String ip : ips) {
            LinkedBlockingQueue<Integer> cola;
            listaIps.add(new redMensajes(cola = new LinkedBlockingQueue<>(), ip));
            new Thread(
                    new Runnable() {
                @Override
                public void run() {
                    enviar(ip, cola);
                }
            }
            ).start();
        }
    }

    private void enviar(String ip, LinkedBlockingQueue<Integer> cola) {
        while(true){
            try {
                int data = cola.take();
                socket(ip, data);
            } catch (InterruptedException ex) {
                Logger.getLogger("ENVIAR: fallo en el hilo de envio");
            }
        }

    }

    private void socket(String ip, int data) {
        try {
            Socket cliente = new Socket(ip, 5000);
            DataOutputStream outBuffer = new DataOutputStream(cliente.getOutputStream());
            outBuffer.write(data);
            cliente.close();

        } catch (IOException e) {
            Logger.getLogger("SOCKET: fallo al enviar en ip " + ip);
        }
    }
    
    public void añadirData(int data){
        for(redMensajes mensaje: listaIps){
            mensaje.getColaEspecifica().add(data);
        }
    }

    private class redMensajes {

        private BlockingQueue<Integer> colaEspecifica;
        private String IP;

        public redMensajes(BlockingQueue<Integer> colaEspecifica, String IP) {
            this.colaEspecifica = colaEspecifica;
            this.IP = IP;
        }

        public BlockingQueue<Integer> getColaEspecifica() {
            return colaEspecifica;
        }

        public String getIP() {
            return IP;
        }
        

    }
}
