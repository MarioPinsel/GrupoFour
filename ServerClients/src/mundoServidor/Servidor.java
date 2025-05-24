package mundoServidor;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    private ArrayList<RedMensajes> listaIps;
    private Codificador cod;
    private Queue<Integer> salida;

    public Servidor(ArrayList<String> ips) {
        cod = new Codificador();
        listaIps = new ArrayList<>();
        salida = new LinkedList<>();

        for (String ip : ips) {
            RedMensajes red = new RedMensajes(ip);
            listaIps.add(red);

            new Thread(() -> {
                try {
                    // Esto envía un mensaje inicial (puedes cambiar el 0 si quieres que espere)
                    enviar(red, 0);
                } catch (IOException ex) {
                    System.err.println("ERROR: El socket petó para IP: " + red.getIP());
                    ex.printStackTrace();
                }
            }).start();
        }
    }

    private void enviar(RedMensajes red, int info) throws IOException {
        // Enviar un solo dato (puedes ponerlo en bucle si lo deseas)
        socket(red, info);
    }

    private void socket(RedMensajes red, int data) {
        try {
            DataOutputStream outBuffer = new DataOutputStream(red.getSocket().getOutputStream());
            outBuffer.writeInt(data);
        } catch (IOException e) {
            System.err.println("SOCKET: fallo al enviar en IP " + red.getIP());
            e.printStackTrace();
        }
    }

    public void añadirData() {
        ArrayList<Integer> datos = cod.getSalida();
        for (RedMensajes mensaje : listaIps) {
            for (int data : datos) {
                try {
                    enviar(mensaje, data);
                } catch (IOException ex) {
                    System.err.println("ERROR: No se están enviando los datos correctamente a " + mensaje.getIP());
                    ex.printStackTrace();
                }
            }
        }
    }

    private class RedMensajes {

        private String IP;
        private Socket socket;

        public RedMensajes(String ip) {
            this.IP = ip;
            try {
                this.socket = new Socket(ip, 5050);
            } catch (IOException ex) {
                System.err.println("ERROR: Creación de socket fallida para IP: " + ip);
                ex.printStackTrace();
            }
        }

        public String getIP() {
            return IP;
        }

        public Socket getSocket() {
            return socket;
        }
    }
}
