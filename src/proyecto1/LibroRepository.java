package proyecto1;

import java.util.List;

public interface LibroRepository<T> {

    List<T> obtenerPorTitulo(String titulo);

    List<T> buscarPorAutor(String autor);
    
     List<T> mostrarLibros();

    List<T> buscarPorRango(double precioMin, double precioMax);
 
    boolean insertar(T objeto);
    
     List<T> buscarPorCantidadStock(int stockMinimo);

  
    boolean eliminarPorId(String id);

   
    public void CopiarArchivos();
}
