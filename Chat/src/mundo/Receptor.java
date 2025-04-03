package mundo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import controlador.Controlador;

public class Receptor extends Thread {
    private Controlador ctrl;

    public Receptor(Controlador ctrl) {
        this.ctrl = ctrl;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Servidor iniciado en el puerto 5000...");

            while (true) {
                try (Socket socket = serverSocket.accept();
                        BufferedReader inBuffer = new BufferedReader(
                                new InputStreamReader(socket.getInputStream()))) {

                    String msg = inBuffer.readLine();

                    if (msg != null) {
                        ctrl.mostrarTexto(msg);
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
