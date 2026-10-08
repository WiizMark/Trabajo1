package proyecto1;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import proyecto1.Libro;
import proyecto1.LibroRepository;
import proyecto1.LibroRepositoryArchivo;
import proyecto1.LibroRepositoryMySQL;

/**
 * El main donde se ejecutan todos los comandos
 * 
 * Menu interactivo para poder ver y consultar y modificar cualquier cosa de la base de datos
 * 
 * @author Nabil y Marcos
 * @version 1.0
 */

public class Main {

    /**
     * Crea una nueva instancia de Main.
     */
    public Main() {
    }

    /**
     * Punto de entrada del programa.
     * Pide al usuario que elija un repositorio y muestra un menú en bucle
     * hasta que elige la opción 0 para salir.
     *
     * @param args argumentos de línea de comandos, no se utilizan
     */
    public static void main(String[] args) {
        Scanner sc;
        sc = new Scanner(System.in).useLocale(Locale.US);

        LibroRepository<Libro> reposi;

        System.out.println("""
                           Elige con cual quieres trabajar?
                           1. Archivo de texto
                           2. Base de datos MySQL""");
        System.out.print("Opcion: ");
        int tipo = sc.nextInt();
        sc.nextLine();

        if (tipo == 1) {
            reposi = new LibroRepositoryMySQL();
            System.out.println("Base de datos MySQL.");
        } else if (tipo == 2) {
            reposi = new LibroRepositoryArchivo();
            System.out.println("Achivo de texto.");
        } else {
            System.out.println("No disponible.");
            sc.close();
            return;
        }
        int opcion;
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Mostrar todos los libros: mostrará por pantalla todos los libros disponibles en el sistema.");
            System.out.println("2. Buscar libro por título: permite buscar un libro específico por su título.");
            System.out.println("3. Buscar libros por autor: permite buscar libros de un autor específico.");
            System.out.println("4. Buscar libros por rango de precios permite buscar libros dentro de un rango de precios indicado por el usuario.");
            System.out.println("5. Buscar libros por cantidad mínima en stock permite buscar libros con stock igual o mayor al especificado.");
            System.out.println("6. Insertar nuevo libro: el usuario proporcionará id, título, autor, precio y stock del nuevo libro.");
            System.out.println("7. Eliminar libro por título elimina un libro por su título. Si hay varios con el mismo título, el usuario elige por id.");
            System.out.println("0. Salir");
            System.out.print("Elige opción: ");
            
            
            /*
            *Aqui empiezan todos los comandos dependiendo el numero que pongamos hara uno de los case 
            */
            
            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1 -> {
                    List<Libro> libros = reposi.mostrarLibros();
                    if (libros.isEmpty()) {
                        System.out.println("No tienes libros.");
                    } else {
                        for (Libro libro : libros) {
                            System.out.println(libro);
                        }
                    }
                }
                case 2 -> {
                    System.out.print("Introduce el titulo del libro: ");
                    String titulo = sc.nextLine();
                    List<Libro> libros = reposi.obtenerPorTitulo(titulo);
                    if (!libros.isEmpty()) {
                        for (Libro libro : libros) {
                            System.out.println(libro);
                        }
                    } else {
                        System.out.println("No hay ningun libro con ese titulo.");
                    }
                }
                case 3 -> {
                    System.out.print("Introduce el autor: ");
                    String autor = sc.nextLine();

                    List<Libro> libros = reposi.buscarPorAutor(autor);
                    if (libros.isEmpty()) {
                        System.out.println("No hay libros de ese autor.");
                    } else {
                        for (Libro libro : libros) {
                            System.out.println(libro);
                        }
                    }
                }
                case 4 -> {
                    System.out.print("Precio min ");
                    double precioMin = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Precio max ");
                    double precioMax = sc.nextDouble();
                    sc.nextLine();
                    if (precioMin > precioMax) {
                        double aux = precioMin;
                        precioMin = precioMax;
                        precioMax = aux;
                    }
                    List<Libro> libros = reposi.buscarPorRango(precioMin, precioMax);
                    if (libros.isEmpty()) {
                        System.out.println("No quedan libros.");
                    } else {
                        for (int i = 0; i < libros.size(); i++) {
                            System.out.println(libros.get(i));
                        }
                    }
                }
                case 5 -> {
                  System.out.print("minimo ");
                    int stock = sc.nextInt();
                    sc.nextLine();

                    List<Libro> libros = reposi.buscarPorCantidadStock(stock);
                    if (libros.isEmpty()) {
                        System.out.println("No hay");
                    } else {
                        for (Libro libro : libros) {
                            System.out.println(libro);
                        }
                    }
                }
                case 6 -> {
                    System.out.print("Dame un id ");
                    
                    String id = sc.nextLine();
                    
                    System.out.print("dame titulo ");
                    
                    String titulo = sc.nextLine();
                    
                    System.out.print("dame autor");
                    
                    String autor = sc.nextLine();
                    
                    System.out.print("dame precio");
                    
                    double precio = sc.nextDouble();
                    
                    sc.nextLine();
                    System.out.print("stock");
                    int stock = sc.nextInt();
                    sc.nextLine();

                    Libro libro = new Libro(id, titulo, autor, precio, stock);
                    if (reposi.insertar(libro)) {
                        System.out.println("Insertado");
                    } else {
                        System.out.println("No insertado");
                    }
                }
                case 7 -> {
                 
                }
                case 8 -> {
                    reposi.CopiarArchivos();
                }
                case 0 -> {
                    System.out.println("Saliendo...");
                }
                default -> System.out.println("error");
            }
        } while (opcion != 0);

    }


}