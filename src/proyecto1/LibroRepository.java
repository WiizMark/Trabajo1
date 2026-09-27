package proyecto1;

import java.util.List;

public interface LibroRepository<T> {

    T obtenerPorTitulo(String titulo);


    T buscarPorCantidadStock(int stock);


    List<T> buscarPorRango(double precioMin, double precioMax);


    T eliminarPorTitulo(String titulo);


    public void CopiarArchivos();

    
    List<T> mostrarLibros(); 


    boolean insertar(T objeto);
}
