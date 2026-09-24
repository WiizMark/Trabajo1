package proyecto1;
import proyecto1.Libro;
import proyecto1.LibroRepository;
import util.LibroRepositoryMySQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroRepositoryArchivo implements LibroRepository<Libro> {

    
    private Libro mapear(ResultSet rs) throws SQLException {
      return new Libro(
          rs.getString("id"),
          rs.getString("nombre"),
          rs.getString("autor"),
          rs.getDouble("precio"),
          rs.getInt("stock")
      );
  }

  @Override
    public boolean insertar(Libro libro) {
        String sql = "INSERT INTO libros(id, titulo, autor, precio, stock) VALUES(?, ?, ?, ?, ?)";
        try (Connection con = util.LibroRepositoryMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
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
        try (Connection con = util.LibroRepositoryMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
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
        String sql = "SELECT id,titulo,autor,precio,stock FROM libros WHERE id = ?";
        try (Connection con = util.LibroRepositoryMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
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
    
    public Libro obtenerPorTitulo(String titulo) {
        String sql = "SELECT id, titulo, autor, precio, stock FROM libros WHERE titulo = ?";
        try (Connection con = util.LibroRepositoryMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, titulo);
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

    @Override
    public boolean actualizar(Libro objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    
}
