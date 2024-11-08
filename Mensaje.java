public abstract class Mensaje {
    protected String destinatario;
    protected String emisor;

    public Mensaje(String destinatario, String emisor) {
        this.destinatario = destinatario;
        this.emisor = emisor;
    }

    public abstract void reproducir();

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String toString() {
        return "Remitente: " + emisor + "\nDestinatario: " + destinatario;
    }
}
