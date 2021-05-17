package mx.edu.itlapiedad.dao;

import java.util.List;

import mx.edu.itlapiedad.models.TicketRenglones;

public interface TicketRenglonesDAO {

	TicketRenglones insertar(TicketRenglones ticketRenglon);

	List<TicketRenglones> consultarTicketRenglones();

	TicketRenglones buscar(int id);

	void actualizar(TicketRenglones ticketRenglon);

	void eliminar(int id);

}
