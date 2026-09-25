package proyecto1;

import java.util.List;
import java.util.Scanner;
import proyecto1.Libro;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

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
            System.out.println("8. Hacer copia copia todos los datos del repositorio activo al otro (de archivo a MySQL o viceversa).");
            System.out.println("0. Salir");
            System.out.print("Elige opción: ");

            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
    }

}
