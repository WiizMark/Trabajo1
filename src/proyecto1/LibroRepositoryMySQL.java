package proyecto1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ConexionesDB;
import proyecto1.Libro;

 
/**
 * Usa {@link ConexionesDB} para conectarse
 * Usa {@link LibroRepository} usar el repositorio con la informacion de la base de datos
 * @author Nabil,Marcos
 * @version 1.0
 */

public class LibroRepositoryMySQL implements LibroRepository<Libro> {
    
    
    /**
     * Convierte la fila {@link ResultSet} en una variable usable {@link Libro}.
     * @param rs se posiciona en cada linea para pillar la variable
     * @return un {@link Libro} con todos los datos enteros agregados
     * @throws SQLException da error si algun valor no esta o hay algun error 
     */

    private Libro mapear(ResultSet rs) throws SQLException {
        Libro libro = new Libro();
        libro.setId(rs.getString("id"));
        libro.setTitulo(rs.getString("titulo"));
        libro.setAutor(rs.getString("autor"));
        libro.setPrecio(rs.getDouble("precio"));
        libro.setStock(rs.getInt("stock"));
        return libro;
    }
    
    /**
     * Busca los libros segun el titulo que tu le digas
     *
     * @param titulo el titulo que ponemos
     * @return devuelve la lista de los libros segun el nombre del titulo
     *  
     */
    
    @Override
    public List<Libro> obtenerPorTitulo(String titulo) {
        List<Libro> libros = new ArrayList<>();
        String sql = "select id, titulo, autor, precio, stock from libros where titulo = ?";
        try (Connection conn = ConexionesDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    libros.add(mapear(rs));
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return libros;
    }

    
     /**
     * Busca los libros hechpos por un autor en concreto
     *
     * @param autor nombre que pongamos
     * @return lista de libros del autor dependiendo de lo que hemos puesto en autor
     */
    @Override
    public List<Libro> buscarPorAutor(String autor) {
        List<Libro> libros = new ArrayList<>();
        String sql = "select id, titulo, autor, precio, stock from libros where autor = ?";
        try (Connection conn = ConexionesDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, autor);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    libros.add(mapear(rs));
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return libros;
    }

    /**
     * Busca todos los libros
     *
     * @return lista todos los libros
     */
    @Override
    public List<Libro> mostrarLibros() {
        List<Libro> libros = new ArrayList<>();
        String sql = "select id, titulo, autor, precio, stock from libros";
        try (Connection conn = ConexionesDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                libros.add(mapear(rs));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return libros;
    }
    
     /**
     * Busca libros buscando el precio minimo y el precio maximo
     *
     * @param precioMin precio minimo que ponemos
     * @param precioMax precio maximo que ponemos
     * @return lista de libros que esten entre precio minimo y precio maximo
     */

    @Override
    public List<Libro> buscarPorRango(double precioMin, double precioMax) {
        List<Libro> libros = new ArrayList<>();
        String sql = "select id, titulo, autor, precio, stock from libros where precio between ? and ?";

        try (Connection conn = ConexionesDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, precioMin);
            pstmt.setDouble(2, precioMax);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    libros.add(mapear(rs));
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return libros;
    }

    @Override
    public boolean insertar(Libro objeto) {
 String sql = "insert into libros (id, titulo, autor, precio, stock) values (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionesDB.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, objeto.getId());
            pstmt.setString(2, objeto.getTitulo());
            pstmt.setString(3, objeto.getAutor());
            pstmt.setDouble(4, objeto.getPrecio());
            pstmt.setInt(5, objeto.getStock());

            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;    }

    @Override
    public List<Libro> buscarPorCantidadStock(int stockMinimo) {
    List<Libro> libros = new ArrayList<>();
        String sql = "select id, titulo, autor, precio, stock from libros where stock >= ?";
        try (Connection conn = ConexionesDB.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, stockMinimo);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    libros.add(mapear(rs));
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return libros;    }


    @Override
    public boolean eliminarPorId(String id) {
    String sql = "delete from libros where id = ?";
        try (Connection conn = ConexionesDB.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;    }

    @Override
    public void CopiarArchivos() {
 List<Libro> libros = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("libros.txt"))) {
            String linea = br.readLine();
            while (linea != null) {
                if (!linea.trim().equals("")) {
                    String[] campos = linea.split("\\^");
                    Libro libro = new Libro();
                    libro.setId(campos[0]);
                    libro.setTitulo(campos[1]);
                    libro.setAutor(campos[2]);
                    libro.setPrecio(Double.parseDouble(campos[3]));
                    libro.setStock(Integer.parseInt(campos[4]));
                    libros.add(libro);
                }
                linea = br.readLine();
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
            return;
        }

        if (guardarLibros(libros)) {
            System.out.println("Se han copiado " + libros.size());
        }    }

    boolean guardarLibros(List<Libro> libros) {
        String sql = "delete from libros";

        try (Connection conn = ConexionesDB.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }

        for (int i = 0; i < libros.size(); i++) {
            insertar(libros.get(i));
        }
        return true;    }

}
