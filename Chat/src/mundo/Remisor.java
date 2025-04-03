package mundo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

public class Remisor {
    private Cliente cliente;

    public Remisor(Cliente cliente) {
        this.cliente = cliente;
    }

    public void enviarMensaje() {
        String ip = cliente.getIp();
        String mensaje = cliente.getMensaje();

        try {
            Socket client = new Socket(ip, 5050); // portSend 5000
            DataOutputStream outBuffer = new DataOutputStream(client.getOutputStream());
            outBuffer.writeUTF(mensaje);
            client.close();
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "Error: IP desconocida " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
        }
    }
}
