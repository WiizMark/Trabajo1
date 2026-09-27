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

public class LibroRepositoryMySQL implements LibroRepository<Libro> {

    private Libro mapear(ResultSet rs) throws SQLException {
        Libro libro = new Libro();
        libro.setId(rs.getString("id"));
        libro.setTitulo(rs.getString("titulo"));
        libro.setAutor(rs.getString("autor"));
        libro.setPrecio(rs.getDouble("precio"));
        libro.setStock(rs.getInt("stock"));
        return libro;
    }

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

    @Override
    public List<Libro> buscarPorAutor(String autor) {
        List<Libro> libros = new ArrayList<>();
        String sql = "select * from libros where autor = ?";
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

    @Override
    public List<Libro> mostrarLibros() {
        List<Libro> libros = new ArrayList<>();
        String sql = "select * from libros";
        try (Connection conn = ConexionesDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                libros.add(mapear(rs));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return libros;
    }

    @Override
    public List<Libro> buscarPorRango(double precioMin, double precioMax) {
        List<Libro> libros = new ArrayList<>();
        String sql = "select * from libros where precio between ? and ?";

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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Libro> buscarPorCantidadStock(int stockMinimo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminarPorId(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void CopiarArchivos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
