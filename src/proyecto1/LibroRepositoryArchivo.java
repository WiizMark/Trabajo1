package proyecto1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class LibroRepositoryArchivo implements LibroRepository<Libro> {
     private static final String archivo = "libros.txt";
    private static final String espacio = "^";
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
    
     private String libroALinea(Libro objeto) {
        return objeto.getId() + espacio
                + objeto.getTitulo() + espacio
                + objeto.getAutor() + espacio
                + objeto.getPrecio() + espacio
                + objeto.getStock();
    }

     
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
