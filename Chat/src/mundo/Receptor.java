package mundo;

import controlador.Controlador;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Receptor extends Thread {

    private Controlador controlador;

    public Receptor(Controlador ctrl) {
        this.controlador = ctrl;
        start();
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                try (Socket socket = serverSocket.accept(); 
                        DataInputStream inBuffer = new DataInputStream(socket.getInputStream())) { 

                    String msg = inBuffer.readUTF(); 

                    if (msg != null) {
                        controlador.mostrarTexto(msg);
                    }

                } catch (IOException e) {
                    System.err.println("Error al aceptar la conexión: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }

}
