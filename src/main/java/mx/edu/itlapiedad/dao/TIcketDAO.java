package mx.edu.itlapiedad.dao;

import java.util.List;

import mx.edu.itlapiedad.models.Tickets;

public interface TIcketDAO {

public Tickets insertar(Tickets ticket);
	
	List<Tickets> consultarTickets();
	
	public Tickets buscar(int id);
	
	public void eliminar(int id);
	
	public void actualizar(Tickets ticket);
	
}
