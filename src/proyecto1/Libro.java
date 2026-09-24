package proyecto1;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        return "Libro{" + "id=" + id + ", nombre=" + nombre + ", autor=" + autor + ", precio=" + precio + ", stock=" + stock + '}';
    }
        
    
}
