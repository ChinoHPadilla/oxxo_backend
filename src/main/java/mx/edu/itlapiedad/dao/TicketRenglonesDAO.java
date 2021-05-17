package mx.edu.itlapiedad.dao;

import java.util.List;

import mx.edu.itlapiedad.models.TicketRenglones;

public interface TicketRenglonesDAO {

	TicketRenglones insertar(TicketRenglones ticketRenglones);

	List<TicketRenglones> consultarTicketRenglones();

	TicketRenglones buscar(int id);

	void actualizar(TicketRenglones ticketRenglones);

	void eliminar(int id);

}
