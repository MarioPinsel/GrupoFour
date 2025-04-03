
package mundo;

/**
 *
 * @author POWER
 */
public class Cliente {
    private String mensaje; 
    private String ip;

    public Cliente(String mensaje, String ip) {
        this.mensaje = mensaje;
        this.ip = ip;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getIp() {
        return ip;
    }
    
    
}
