package proyecto1;

    /**
     * Clase Libro, aqui crearemos todos los atributos que nos pida el enunciado.
     * Almacena su identificador, título, autor, precio y stock disponible.
     * 
     * @author Marcos, Nabil
     * @version 1.0
     */

public class Libro {
    
    protected String id;
    protected String titulo;
    protected String autor;
    protected double precio;
    protected int stock;
    
    /**
     * Aquí creamos un libro con todos sus datos, incluido el id.
     * Se usa normalmente al leer un libro ya existente.
     *
     * @param id identificador único del libro
     * @param titulo título del libro
     * @param autor autor del libro
     * @param precio precio del libro
     * @param stock cantidad de copias disponibles
     */
    
    public Libro(String id, String titulo, String autor, double precio, int stock) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.stock = stock;
    }
    
    /**
     * Crea un libro sin id, pensado para libros nuevos que todavía no han sido insertados
     *
     * @param titulo título del libro
     * @param autor autor del libro
     * @param precio precio del libro
     * @param stock cantidad de copias disponibles
     */
    
    public Libro(String titulo, String autor, double precio, int stock) {
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.stock = stock;
    }
    
    /**
     * Crea un libro vacío, sin ningún dato.
     */
    
    public Libro() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", precio=" + precio + ", stock=" + stock + '}';
    }

}
