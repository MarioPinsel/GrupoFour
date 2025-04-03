package mundo;

public class Cliente {
    private String ip;
    private String mensaje;

    public Cliente(String ip, String mensaje) {
        this.ip = ip;
        this.mensaje = mensaje;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getIp() {
        return ip;
    }

    public String getMensaje() {
        return mensaje;
    }
}
