package mx.edu.itlapiedad.services;

import java.util.List;

import mx.edu.itlapiedad.models.TicketRenglones;

public interface TicketRenglonesService {

	TicketRenglones insertar(TicketRenglones ticketRenglon);

	List<TicketRenglones> consultarTicketRenglones();

	TicketRenglones buscar(int id);

	void actualizar(TicketRenglones ticketRenglon);

	void eliminar(int id);

}
