package proyecto1;

import java.util.List;

public interface LibroRepository<T> {

    boolean insertar(T objeto);

    List<T> obtenerTodos();

    T obtenerPorId(int id);

    boolean actualizar(T objeto);

    boolean eliminar(int id);

}
