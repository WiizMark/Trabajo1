package proyecto1;

import java.util.List;
import java.util.Scanner;
import proyecto1.Libro;
import proyecto1.LibroRepositoryArchivo;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static LibroRepositoryArchivo LibroDAO = new LibroRepositoryArchivo();

    public static void main(String[] args) {

        System.out.println("proyecto1.Main.main()");

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

            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1:
                    mostrarTodo();

                    break;
                case 2:
                    buscarPorTitulo();

                    break;
                case 3:
                    buscarPorAutor();

                    break;
                case 4:
                    buscarPorRangoPrecio();

                    break;
                case 5:
                    buscarPorStockMinimo();

                    break;
                case 6:
                    insertarLibro();

                    break;
                case 7:
                    eliminarPorTitulo();

                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);

    }

    public static void mostrarTodo() {
        List<Libro> libros = LibroDAO.obtenerTodos();
        if (libros.isEmpty()) {
            System.out.println("No hay libros disponibles.");
        } else {
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        }

    }

    public static void buscarPorTitulo() {
        System.out.print("Introduce el titulo");
        String titulo = sc.nextLine();

        List<Libro> libros = LibroDAO.obtenerPorTitulo(titulo);
        if (libros.isEmpty()) {
            System.out.println("No hay libros.");
        } else {
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        }
    }

    public static void buscarPorAutor() {
        System.out.print("Introduce el autor ");
        String autor = sc.nextLine();

        List<Libro> libros = LibroDAO.obtenerPorAutor(autor);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros con autor");
        } else {
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        }
    }

    public static void buscarPorRangoPrecio() {
        System.out.print("Precio minimo ");
        double min = Double.parseDouble(sc.nextLine());
        System.out.print("Precio max ");
        double max = Double.parseDouble(sc.nextLine());

        List<Libro> libros = LibroDAO.buscarPorRangoPrecio(min, max);
        if (libros.isEmpty()) {
            System.out.println("No hay libros");
        } else {
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        }
    }

    public static void buscarPorStockMinimo() {
        System.out.print("Stock min");
        int stock = Integer.parseInt(sc.nextLine());

        List<Libro> libros = LibroDAO.buscarPorStockMinimo(stock);
        if (libros.isEmpty()) {
            System.out.println("No hay libros.");
        } else {
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        }
    }

    public static void insertarLibro() {
        System.out.print("titulo: ");
        String titulo = sc.nextLine();
        System.out.print("autor: ");
        String autor = sc.nextLine();
        System.out.print("precio: ");
        double precio = Double.parseDouble(sc.nextLine());
        System.out.print("stock: ");
        int stock = Integer.parseInt(sc.nextLine());

        Libro libro = new Libro(titulo, autor, precio, stock);
        boolean prueba = LibroDAO.insertar(libro);
        if (prueba) {
            System.out.println("Libro insertado correctamente.");
        } else {
            System.out.println("error");

        }
    }

    public static void eliminarPorTitulo() {
        System.out.print("Introduce el tilto del libro a eliminar: ");
        String titulo = sc.nextLine();

        List<Libro> libros = LibroDAO.obtenerPorTitulo(titulo);

        if (libros.isEmpty()) {
            System.out.println("No hay ningun libro.");
        } else if (libros.size() == 1) {
            LibroDAO.eliminar(libros.get(0).getId());
            System.out.println("Libro eliminado.");
        } else {
            System.out.println("Hay muchos con el titulo");
            for (Libro libro : libros) {
                System.out.println(libro);
            }
            System.out.print("Introduce el id del que quieres eliminar: ");
            int id = Integer.parseInt(sc.nextLine());
            boolean prueba = LibroDAO.eliminar(id);
            if (prueba) {
                System.out.println("Libro muerto.");
            } else {
                System.out.println("error");

            }
        }
    }

}
