package mundo;

public class Cliente {
    private String ip;
    private String mensaje;
    private String nick;

    public Cliente(String ip, String mensaje, String nick) {
        this.ip = ip;
        this.mensaje = mensaje;
        this.nick = nick;
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

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
    
}
