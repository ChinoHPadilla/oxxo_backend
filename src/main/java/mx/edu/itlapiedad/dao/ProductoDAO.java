package mx.edu.itlapiedad.dao;
import java.util.List;
import mx.edu.itlapiedad.models.Productos;

public interface ProductoDAO {

	public Productos insertar(Productos producto);
	
	List<Productos> consultarProductos();
	
	public Productos buscar(int id);
	
}