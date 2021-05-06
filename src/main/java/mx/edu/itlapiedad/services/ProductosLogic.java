------ProductLogic
package mx.edu.itlapiedad.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.itlapiedad.dao.ProductoDAO;
import mx.edu.itlapiedad.models.Productos;

@Service
public class ProductosLogic implements ProductoService {
	
	@Autowired
	ProductoDAO repositorio;
	
	@Override
	public Productos buscar(int id) {
		return repositorio.buscar(id);
	}
	
	@Override
	public Productos insertar(Productos producto) {
		return repositorio.insertar(producto);
	}
	
	@Override
	public List<Productos> consultarProductos() {
		return repositorio.consultarProductos();
	}

}