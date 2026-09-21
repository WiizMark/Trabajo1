package proyecto1;

/**
 *
 * @author Marcos
 */
public class Libro {
    protected String id;
    protected String nombre;
    protected String autor;
    protected double precio;
    protected int stock;

    public Libro(String id, String nombre, String autor, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.precio = precio;
        this.stock = stock;
    }
    
    public Libro(String nombre, String autor, double precio, int stock) {
        this.nombre = nombre;
        this.autor = autor;
        this.precio = precio;
        this.stock = stock;
    }   
    
        public Libro() {
    }   
}
