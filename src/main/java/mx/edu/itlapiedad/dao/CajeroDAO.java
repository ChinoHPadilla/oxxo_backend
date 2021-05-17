package mx.edu.itlapiedad.dao;

import java.util.List;

import mx.edu.itlapiedad.models.Cajeros;

public interface CajeroDAO {

public Cajeros insertar(Cajeros cajero);
	
	List<Cajeros> consultarCajeros();
	
	public Cajeros buscar(int id);
	
	public void eliminar(int id);
	
	public void actualizar(Cajeros cajero);
	
}
