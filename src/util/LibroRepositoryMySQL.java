package util;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LibroRepositoryMySQL {

    private static final Dotenv dotenv = Dotenv.load();

    private static final String URL = dotenv.get("db.url");
    private static final String USER = dotenv.get("db.usuario");
    private static final String PASS = dotenv.get("db.contra");

    public static Connection getConnection() {
        Connection con = null; 
        try {
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
    System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return con;
    }
    a
}