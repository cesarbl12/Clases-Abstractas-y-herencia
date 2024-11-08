public class MensajeFax extends Mensaje {
    private String nombreArchivo;

    public MensajeFax(String destinatario, String emisor, String nombreArchivo) {
        super(destinatario, emisor);
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo fax: " + nombreArchivo);
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public String toString() {
        return super.toString() + "\nTipo: Fax\nNombre de archivo: " + nombreArchivo;
    }
}
