import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SistemaMensajes {
    private ArrayList<Mensaje> misMensajes;

    public SistemaMensajes() {
        misMensajes = new ArrayList<>();
    }

    public void insertarMensaje(Mensaje mensaje) {
        misMensajes.add(mensaje);
    }

    public void reproducirTodosMisMensajes() {
        for (Mensaje miMensaje : misMensajes) {
            System.out.println(miMensaje.toString());
            miMensaje.reproducir();
        }
    }

    public void borrarMensajesPorRemitente(String remitente) {
        misMensajes.removeIf(m -> m.getEmisor().equals(remitente));
        System.out.println("Mensajes de " + remitente + " eliminados.");
    }

    public HashMap<String, List<Mensaje>> buscarMensajesPorRemitente() {
        return misMensajes.stream()
                .collect(Collectors.groupingBy(Mensaje::getEmisor, HashMap::new, Collectors.toList()));
    }

    public ArrayList<Mensaje> buscarMensajesPorTipo(Class<? extends Mensaje> tipo) {
        return misMensajes.stream()
                .filter(tipo::isInstance)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void ordenarMensajesPorRemitente() {
        misMensajes.sort((m1, m2) -> m1.getEmisor().compareTo(m2.getEmisor()));
        System.out.println("Mensajes ordenados por remitente.");
    }

    public void reproducirMensajePorIndice(int indice) {
        if (indice >= 0 && indice < misMensajes.size()) {
            System.out.println("Reproduciendo mensaje en el índice " + indice + ":");
            misMensajes.get(indice).reproducir();
        } else {
            System.out.println("Índice fuera de rango.");
        }
    }

    public static void main(String[] args) {
        SistemaMensajes sistema = new SistemaMensajes();
        Scanner input = new Scanner(System.in);
        int op;

        do {
            System.out.println("\nBienvenido al super sistema de mensajes.");
            System.out.println("1. Insertar mensajes.");
            System.out.println("2. Leer todos los mensajes.");
            System.out.println("3. Borrar los mensajes de un remitente.");
            System.out.println("4. Ver todos los mensajes de un remitente.");
            System.out.println("5. Ver todos los mensajes de un tipo (Texto o Fax).");
            System.out.println("6. Ordenar los mensajes por remitente.");
            System.out.println("7. Reproducir un mensaje en particular con el índice.");
            System.out.println("8. Salir.");
            op = input.nextInt();
            input.nextLine(); // Limpiar el buffer

            switch (op) {
                case 1:
                    System.out.println("Seleccione el tipo de mensaje para insertar:");
                    System.out.println("1. Mensaje de Texto");
                    System.out.println("2. Mensaje Fax");
                    int tipoMensaje = input.nextInt();
                    input.nextLine(); // Limpiar el buffer

                    System.out.println("Ingrese el destinatario:");
                    String destinatario = input.nextLine();
                    System.out.println("Ingrese el remitente:");
                    String emisor = input.nextLine();

                    if (tipoMensaje == 1) {
                        System.out.println("Ingrese el contenido del texto:");
                        String texto = input.nextLine();
                        Mensaje nuevoMensajeTexto = new MensajeTexto(destinatario, emisor, texto);
                        sistema.insertarMensaje(nuevoMensajeTexto);
                        System.out.println("Mensaje de texto insertado con éxito.");
                    } else if (tipoMensaje == 2) {
                        System.out.println("Ingrese el nombre del archivo:");
                        String nombreArchivo = input.nextLine();
                        Mensaje nuevoMensajeFax = new MensajeFax(destinatario, emisor, nombreArchivo);
                        sistema.insertarMensaje(nuevoMensajeFax);
                        System.out.println("Mensaje fax insertado con éxito.");
                    } else {
                        System.out.println("Tipo de mensaje no válido.");
                    }
                    break;

                case 2:
                    sistema.reproducirTodosMisMensajes();
                    break;

                case 3:
                    System.out.println("Ingrese el remitente cuyos mensajes desea borrar:");
                    String remitenteBorrar = input.nextLine();
                    sistema.borrarMensajesPorRemitente(remitenteBorrar);
                    break;

                case 4:
                    System.out.println("Mensajes agrupados por remitente:");
                    HashMap<String, List<Mensaje>> mensajesPorRemitente = sistema.buscarMensajesPorRemitente();
                    mensajesPorRemitente.forEach((remitente, mensajes) -> {
                        System.out.println("Remitente: " + remitente);
                        mensajes.forEach(m -> System.out.println(" - " + m));
                    });
                    break;

                case 5:
                    System.out.println("Seleccione el tipo de mensaje que desea ver:");
                    System.out.println("1. Mensaje de Texto");
                    System.out.println("2. Mensaje Fax");
                    int tipoBusqueda = input.nextInt();
                    input.nextLine(); // Limpiar el buffer

                    ArrayList<Mensaje> mensajesTipo;
                    if (tipoBusqueda == 1) {
                        mensajesTipo = sistema.buscarMensajesPorTipo(MensajeTexto.class);
                    } else if (tipoBusqueda == 2) {
                        mensajesTipo = sistema.buscarMensajesPorTipo(MensajeFax.class);
                    } else {
                        System.out.println("Tipo no válido.");
                        break;
                    }
                    mensajesTipo.forEach(System.out::println);
                    break;

                case 6:
                    sistema.ordenarMensajesPorRemitente();
                    break;

                case 7:
                    System.out.println("Ingrese el índice del mensaje que desea reproducir:");
                    int indice = input.nextInt();
                    sistema.reproducirMensajePorIndice(indice);
                    break;

                case 8:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (op != 8);
        input.close();
    }
}
