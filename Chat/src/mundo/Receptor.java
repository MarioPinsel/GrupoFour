package mundo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import controlador.Controlador;

public class Receptor extends Thread {
    private Controlador ctrl;
    private String msg;

    public Receptor(String msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        ServerSocket serverSocket;
        Socket socket;
        BufferedReader inBuffer;

        try {
            serverSocket = new ServerSocket(5000); // portListen 5000

            while (true) {
                try {
                    socket = serverSocket.accept();

                    inBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    try {
                        ctrl.mostrarTexto(msg);
                    } catch (NullPointerException e) {
                        System.err.println("Error: El controlador no está inicializado. " + e.getMessage());
                        e.printStackTrace();
                    } catch (Exception e) {
                        System.err.println("Error inesperado al mostrar el mensaje: " + e.getMessage());
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    System.err.println("Error al aceptar la conexión: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}