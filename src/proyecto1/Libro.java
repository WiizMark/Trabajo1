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
     * Crea un libro vacío, sin ningún tipo de dato.
     */
    
    public Libro() {
    }

    /**
     * @return Devuelve el identificador del libro
     */
    
    public String getId() {
        return id;
    }
    
    /**
     * @param id Nuevo identificador del libro
     */
    
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return Devuelve el titulo del libro
     */
    
    public String getTitulo() {
        return titulo;
    }
    
    /**
     * @param titulo Nuevo título del libro
     */
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return Devuelve el autor del libro
     */
    
    public String getAutor() {
        return autor;
    }
    
    /**
     * @param autor Nuevo autor del libro
     */
    
    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    /**
     * @return Devuelve el precio del libro
     */
    
    public double getPrecio() {
        return precio;
    }
    
    /**
     * @param precio Nuevo precio del libro
     */
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    /**
     * @return Devuelve el stock del libro
     */
    
    public int getStock() {
        return stock;
    }
    /**
     * @param stock nuevo stock del libro
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Ahora hacemos un toString para que imprima todos los datos del libro.
     * @return Devuelve todos los datos del libro.
     */
    
    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", precio=" + precio + ", stock=" + stock + '}';
    }

}
