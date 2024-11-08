public class MensajeTexto extends Mensaje {
    private String texto;

    public MensajeTexto(String destinatario, String emisor, String texto) {
        super(destinatario, emisor);
        this.texto = texto;
    }

    @Override
    public void reproducir() {
        System.out.println("El texto es: " + texto);
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return super.toString() + "\nTipo: Texto\nContenido: " + texto;
    }
}
