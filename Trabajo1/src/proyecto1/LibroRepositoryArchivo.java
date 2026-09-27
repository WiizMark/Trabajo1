package proyecto1;

import proyecto1.Libro;
import proyecto1.LibroRepository;
import util.LibroRepositoryMySQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroRepositoryArchivo implements LibroRepository<Libro> {

    @Override
    public boolean insertar(Libro libro) {
        String sql = "INSERT INTO libros(titulo, autor, precio, stock) VALUES(?, ?, ?, ?)";
        try (Connection con = util.LibroRepositoryMySQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setDouble(3, libro.getPrecio());
            ps.setInt(4, libro.getStock());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error insertando libro en MySQL: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Libro> obtenerTodos() {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT id, titulo, autor, precio, stock FROM libros";
        try (Connection con = util.LibroRepositoryMySQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener todos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Libro obtenerPorId(int id) {
        String sql = "SELECT id, titulo, autor, precio, stock FROM libros WHERE id = ?";
        try (Connection con = util.LibroRepositoryMySQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener por id: " + e.getMessage());
        }
        return null;
    }

    public List<Libro> obtenerPorTitulo(String titulo) {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT id, titulo, autor, precio, stock FROM libros WHERE titulo = ?";
        try (Connection con = util.LibroRepositoryMySQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, titulo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener por título: " + e.getMessage());
        }
        return lista;
    }

    public List<Libro> obtenerPorAutor(String autor) {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT id, titulo, autor, precio, stock FROM libros WHERE autor = ?";
        try (Connection con = util.LibroRepositoryMySQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, autor);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener por autor: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean actualizar(Libro libro) {
        String sql = "UPDATE libros SET titulo = ?, autor = ?, precio = ?, stock = ? WHERE id = ?";
        try (Connection con = util.LibroRepositoryMySQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setDouble(3, libro.getPrecio());
            ps.setInt(4, libro.getStock());
            ps.setInt(5, libro.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM libros WHERE id = ?";
        try (Connection con = util.LibroRepositoryMySQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    public List<Libro> buscarPorRangoPrecio(double min, double max) {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT id, titulo, autor, precio, stock FROM libros WHERE precio BETWEEN ? AND ?";
        try (Connection con = util.LibroRepositoryMySQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, min);
            ps.setDouble(2, max);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error buscando por precio: " + e.getMessage());
        }
        return lista;
    }

    public List<Libro> buscarPorStockMinimo(int stock) {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT id, titulo, autor, precio, stock FROM libros WHERE stock >= ?";
        try (Connection con = util.LibroRepositoryMySQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, stock);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error buscando por stock: " + e.getMessage());
        }
        return lista;
    }

    public boolean eliminarPorTitulo(String titulo) {
        String sql = "DELETE FROM libros WHERE titulo = ?";
        try (Connection con = util.LibroRepositoryMySQL.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, titulo);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar libro por título: " + e.getMessage());
            return false;
        }
    }

    private Libro mapear(ResultSet rs) throws SQLException {
        return new Libro(
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getDouble("precio"),
                rs.getInt("stock")
        );
    }
}
