package proyecto1;
import proyecto1.Libro;
import proyecto1.LibroRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroRepositoryArchivo implements LibroRepository<T> {

    @Override
    public boolean insertar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
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
