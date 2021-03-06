package mx.edu.itlapiedad.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.itlapiedad.dao.TicketRenglonesDAO;
import mx.edu.itlapiedad.models.TicketRenglones;

@Service
public class TicketRenglonesLogic implements TicketRenglonesService {

	@Autowired
	TicketRenglonesDAO repositorio;
	
	@Override
	public TicketRenglones insertar(TicketRenglones ticketRenglones) {
		return repositorio.insertar(ticketRenglones);
	}

	@Override
	public List<TicketRenglones> consultarTicketRenglones() {
		return repositorio.consultarTicketRenglones();
	}

	@Override
	public TicketRenglones buscar(int id) {
		return repositorio.buscar(id);
	}

	@Override
	public void actualizar(TicketRenglones ticketRenglones) {
		repositorio.actualizar(ticketRenglones);
		
	}

	@Override
	public void eliminar(int id) {
		repositorio.eliminar(id);
		
	}

}
