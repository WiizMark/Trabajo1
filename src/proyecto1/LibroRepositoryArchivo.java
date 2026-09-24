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
    public boolean insertar(Libro objeto) {
String sql = "INSERT INTO facturas(id_cliente, id_veterinario, id_mascota, fecha, subtotal, total_iva, total) "
				+ "VALUES(?,?,?,?,?,?,?)";
		try (Connection con = LibroRepositoryMySQL.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, objeto.getTitulo());
			ps.setString(2, objeto.getAutor());
			ps.setInt(3, objeto.getStock());
			
			int filas = ps.executeUpdate();
			if (filas > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					objeto.setId(rs.getString(1));
				}
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.out.println("Error insertando factura: " + e.getMessage());
			return false;
		}    }

    @Override
    public List<Libro> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Libro obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
