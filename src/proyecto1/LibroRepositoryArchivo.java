package proyecto1;
import proyecto1.Libro;
import proyecto1.LibroRepository;
import util.LibroRepositoryMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class LibroRepositoryArchivo implements LibroRepository<Libro> {

 @Override
    public boolean insertar(Libro libro) {
    String sql = "INSERT INTO libros(id, titulo, autor, precio, stock) VALUES(?, ?, ?, ?, ?)";
    try (Connection con = LibroRepositoryMySQL.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, libro.getId());
        ps.setString(2, libro.getTitulo());
        ps.setString(3, libro.getAutor());
        ps.setDouble(4, libro.getPrecio());
        ps.setInt(5, libro.getStock());
        
        return ps.executeUpdate() > 0;
        
    } catch (SQLException e) {
        System.out.println("Error insertando libro en MySQL: " + e.getMessage());
        return false;
    }
}

    @Override
    public List<Object> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean actualizar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    private Libro mapear(ResultSet rs) throws SQLException {
      return new Libro(
          rs.getString("id"),
          rs.getString("nombre"),
          rs.getString("autor"),
          rs.getDouble("precio"),
          rs.getInt("stock")
      );
  }
    
}
