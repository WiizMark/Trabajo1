package trabajo1.utilidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import trabajo1.conectores.ConexionesDB;

public class LibroRepositoryMySQL implements LibroRepository<ModeloLibro> {

    private ModeloLibro mapear(ResultSet rs) throws SQLException {
        ModeloLibro libro = new ModeloLibro();
        libro.setId(rs.getString("id"));
        libro.setTitulo(rs.getString("titulo"));
        libro.setAutor(rs.getString("autor"));
        libro.setPrecio(rs.getDouble("precio"));
        libro.setStock(rs.getInt("stock"));
        return libro;
    }

}
