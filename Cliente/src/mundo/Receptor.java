package mundo;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Receptor extends Thread {

    private Decodificador decodificador;

    public Receptor(Decodificador decodificador) {
        this.decodificador = decodificador;
        start();
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        DataInputStream inBuffer = new DataInputStream(socket.getInputStream())) {
                    int valor = inBuffer.readInt();
                    decodificador.procesar(valor);

                } catch (IOException e) {
                    System.err.println("Error al recibir datos: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}
