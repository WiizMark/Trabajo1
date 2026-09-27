package proyecto1;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import proyecto1.Libro;
import proyecto1.LibroRepository;
import proyecto1.LibroRepositoryArchivo;
import proyecto1.LibroRepositoryMySQL;

public class Main {

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

            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1 -> {
                
                }
                case 2 -> {
               
                }
                case 3 -> {
                 
                }
                case 4 -> {
                  
                }
                case 5 -> {
                 
                }
                case 6 -> {
                 
                }
                case 7 -> {
                 
                }
                case 8 -> {
                }
                case 0 -> {
                    System.out.println("Saliendo...");
                }
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);

    }


}