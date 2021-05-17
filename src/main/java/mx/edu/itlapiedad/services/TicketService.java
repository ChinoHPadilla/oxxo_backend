package mx.edu.itlapiedad.services;

import java.util.List;

import mx.edu.itlapiedad.models.Tickets;

public interface TicketService {

Tickets insertar(Tickets ticket);
	
	List<Tickets> consultarTickets();
	
	Tickets buscar(int id);
	
	void eliminar(int id);

	void actualizar(Tickets ticket);
	
}
