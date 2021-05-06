package mx.edu.itlapiedad.services;

import java.util.List;

import mx.edu.itlapiedad.models.Productos;

public interface ProductoService {
	
	Productos insertar(Productos producto);
	
	List<Productos> consultarProductos();
	
	Productos buscar(int id);
	

}