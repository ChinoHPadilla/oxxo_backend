package mx.edu.itlapiedad.services;

import java.util.List;

import mx.edu.itlapiedad.models.Cajeros;

public interface CajeroService {

	Cajeros insertar(Cajeros cajero);
	
	List<Cajeros> consultarCajeros();
	
	Cajeros buscar(int id);
	
	void eliminar(int id);

	void actualizar(Cajeros cajero);
	
}
