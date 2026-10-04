package proyecto1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase se encarga de gestionar los libros guardados en un archivo.
 * Permite buscar, mostrar, insertar y eliminar libros del archivo.
 *
 * @author Marcos, Nabil
 * @version 1.0
 */

public class LibroRepositoryArchivo implements LibroRepository<Libro> {
     private static final String archivo = "Libros.txt";
    private static final String espacio = "^";
    
    /**
     * Busca los libros que tienen el título indicado.
     *
     * @param titulo Es el título del libro que quieres buscar.
     * @return Devuelve una lista con los libros que tienen ese título.
     */
    
    @Override
    public List<Libro> obtenerPorTitulo(String titulo) {
        List<Libro> libros = mostrarLibros();
        List<Libro> resultado = new ArrayList<>();

        for (int i = 0; i < libros.size(); i++) {
            Libro libro = libros.get(i);
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                resultado.add(libro);
            }
        }
        return resultado;    }

    /**
     * Busca los libros que pertenecen al autor indicado.
     *
     * @param autor Es el autor cuyos libros quieres buscar.
     * @return Devuelve una lista con los libros de ese autor.
     */
    
    @Override
    public List<Libro> buscarPorAutor(String autor) {
 List<Libro> libros = mostrarLibros();
        List<Libro> resultado = new ArrayList<>();

        for (int i = 0; i < libros.size(); i++) {
            Libro libro = libros.get(i);
            if (libro.getAutor().equalsIgnoreCase(autor)) {
                resultado.add(libro);
            }
        }
        return resultado;    }

    /**
     * Muestra todos los libros que están guardados en el archivo.
     *
     * @return Devuelve una lista con todos los libros del archivo.
     */    
    
    @Override
    public List<Libro> mostrarLibros() {
 List<Libro> libros = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea = br.readLine();
            while (linea != null) {
                if (!linea.trim().equals("")) {
                    libros.add(mapear(linea));
                }
                linea = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return libros;    }

    /**
     * Busca los libros que tienen el precio dentro del rango indicado.
     *
     * @param precioMin Es el precio mínimo que puede tener el libro.
     * @param precioMax Es el precio máximo que puede tener el libro.
     * @return Devuelve una lista con los libros que están dentro del rango de precios.
     */
    
    @Override
    public List<Libro> buscarPorRango(double precioMin, double precioMax) {
 List<Libro> libros = mostrarLibros();
        List<Libro> resultado = new ArrayList<>();

        for (int i = 0; i < libros.size(); i++) {
            Libro libro = libros.get(i);
            if (libro.getPrecio() >= precioMin && libro.getPrecio() <= precioMax) {
                resultado.add(libro);
            }
        }
        return resultado;    }

    /**
     * Inserta un nuevo libro en el archivo.
     *
     * @param objeto Es el libro que quieres insertar.
     * @return Devuelve true si el libro se ha insertado correctamente y false si ya existe o ha ocurrido un error.
     */
    
    @Override
    public boolean insertar(Libro objeto) {
List<Libro> libros = mostrarLibros();
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getId().equals(objeto.getId())) {
                System.out.println("Ya existe un libro con el id " + objeto.getId());
                return false;
            }
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true));
            bw.write(libroALinea(objeto));
            bw.newLine();
            bw.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }    }
    
    /**
     * Busca los libros que tienen una cantidad de stock igual o superior a la indicada.
     *
     * @param stockMinimo Es la cantidad mínima de stock que debe tener el libro.
     * @return Devuelve una lista con los libros que tienen el stock indicado o superior.
     */
    
    @Override
    public List<Libro> buscarPorCantidadStock(int stockMinimo) {
 List<Libro> libros = mostrarLibros();
        List<Libro> resultado = new ArrayList<>();

        for (int i = 0; i < libros.size(); i++) {
            Libro libro1 = libros.get(i);
            if (libro1.getStock() >= stockMinimo) {
                resultado.add(libro1);
            }
        }
        return resultado;    }
   
    /**
     * Elimina un libro del archivo utilizando el identificador.
     *
     * @param id Es el identificador del libro que quieres eliminar.
     * @return Devuelve true si el libro se ha eliminado correctamente y false si no se encuentra.
     */
    
    @Override
    public boolean eliminarPorId(String id) {
 List<Libro> libros = mostrarLibros();

        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getId().equals(id)) {
                libros.remove(i);
                return guardarLibros(libros);
            }
        }
        return false;    }

    /**
     * Copia todos los libros guardados en el archivo a la base de datos MySQL.
     */
    
    @Override
    public void CopiarArchivos() {
List<Libro> libros = mostrarLibros();

        if (libros.isEmpty()) {
            System.out.println("No hay libros");
            return;
        }

        LibroRepositoryMySQL repoMySQL = new LibroRepositoryMySQL();
        if (repoMySQL.guardarLibros(libros)) {
            System.out.println("Se han copiado " + libros.size());
        }    }
    
    /**
     * Guarda una lista de libros en el archivo.
     *
     * @param libros Es la lista de libros que quieres guardar en el archivo.
     * @return Devuelve true si los libros se han guardado correctamente y false si ha ocurrido un error.
     */
    
     public boolean guardarLibros(List<Libro> libros) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            for (int i = 0; i < libros.size(); i++) {
                bw.write(libroALinea(libros.get(i)));
                bw.newLine();
            }
            bw.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }
     
    /**
     * Convierte los datos de un libro en una línea para poder guardarlos en el archivo.
     *
     * @param objeto Es el libro que quieres convertir en una línea.
     * @return Devuelve una línea con los datos del libro separados por un simbolo.
     */  
     
     private String libroALinea(Libro objeto) {
        return objeto.getId() + espacio
                + objeto.getTitulo() + espacio
                + objeto.getAutor() + espacio
                + objeto.getPrecio() + espacio
                + objeto.getStock();
    }

    /**
     * Convierte una línea del archivo en un objeto Libro.
     *
     * @param linea Es la línea del archivo que contiene los datos del libro.
     * @return Devuelve un objeto Libro con los datos de la línea.
     */
     
    private Libro mapear(String linea) {

        String[] campos = linea.split("\\" + espacio);
        Libro libro = new Libro();
        libro.setId(campos[0]);
        libro.setTitulo(campos[1]);
        libro.setAutor(campos[2]);
        libro.setPrecio(Double.parseDouble(campos[3]));
        libro.setStock(Integer.parseInt(campos[4]));
        return libro;
    }

  
}
