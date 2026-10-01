package util;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 
 * El uso de esta clase es para conectarse a SQL mediante el metodo .env necesario descargar
 * mysql y dotenv para que este en funcionamiento.
 * @version 1.0
 * @author Nabil,Marcos
 */
public class ConexionesDB {

    /** Carga las variables de la base de datos*/
    private static final Dotenv dotenv = Dotenv.load();
    /** carga la URL de la base de datos*/
    private static final String URL = dotenv.get("db.url");
    /** Carga el usuario de la base de datos*/
    private static final String USER = dotenv.get("db.usuario");
    /** Carga la contraseña de la base de datos*/
    private static final String PASS = dotenv.get("db.contra");

    
    
    /**
     * Establece la conexion con la base de datos usando una funcion
     * 
     *
     * @return returnea las variables si se hace con exito {@link Connection} y si hay algun fallo returnea un error
     * de que no se pudo conectar a la base de datos(puede ser por meter mal el user y la contra o por otro error).
     */
    public static Connection getConnection() {
        Connection con = null; 
        try {
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
    System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return con;
    }
}