package proyecto1;

    /**
     * Creamos la clase LibroRepository, la cual servira para llamar luego a los metodos.
     * @author Marcos, Nabil
     * @version 1.0
     */
     
import java.util.List;


public interface LibroRepository<T> {
    
    /**
     * Obtiene una lista de libros, pero solo si el título coincide con el título que le han indicado.
     * 
     * @param titulo Es el título del libro que quieres buscar.
     * @return Devuelve el resultado de los libros con ese título.
     */

    List<T> obtenerPorTitulo(String titulo);
    
    /**
     * Busca una lista de libros que pertenecen al autor que le han indicado.
     *
     * @param autor Es el nombre del autor que quieres buscar.
     * @return Devuelve el resultado de los libros de ese autor.
     */
    
    List<T> buscarPorAutor(String autor);
    
    /**
     * Muestra todos los libros que hay actualmente en el repositorio.
     *
     * @return Devuelve una lista con todos los libros disponibles.
     */
    
    List<T> mostrarLibros();

    List<T> buscarPorRango(double precioMin, double precioMax);
 
    boolean insertar(T objeto);
    
     List<T> buscarPorCantidadStock(int stockMinimo);

  
    boolean eliminarPorId(String id);

   
    public void CopiarArchivos();
}
