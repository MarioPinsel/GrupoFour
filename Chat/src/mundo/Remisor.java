
package mundo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

/**
 *
 * @author POWER
 */
public class Remisor {
    private String ip;
    private String mensaje;

    public Remisor(Cliente cliente) {
        this.ip = cliente.getIp() ;
        this.mensaje = cliente.getMensaje();
        socket();
    }
  
    
    private void socket() {
    try {
        Socket client = new Socket(ip, 5000); // portSend 5000
        DataOutputStream outBuffer = new DataOutputStream(client.getOutputStream());
        outBuffer.writeUTF(mensaje);
        client.close();
    } catch (UnknownHostException e) {
        JOptionPane.showMessageDialog(null, "socket() : UnknownHostException: " + e.getMessage());
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "socket() : IOException: " + e.getMessage());
    }
}
}
