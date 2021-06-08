package mx.edu.itlapiedad.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.itlapiedad.dao.TIcketDAO;
import mx.edu.itlapiedad.models.Tickets;
import mx.edu.itlapiedad.models.TicketsImporteTotal;

@Service
public class TicketsLogic implements TicketService {

	@Autowired
	TIcketDAO repositorio;
	
	@Override
	public Tickets buscar(int id) {
		return repositorio.buscar(id);
	}
	
	@Override
	public Tickets insertar(Tickets ticket) {
		return repositorio.insertar(ticket);
	}
	
	@Override
	public List<Tickets> consultarTickets() {
		return repositorio.consultarTickets();
	}
	
	@Override
	public void eliminar(int id) {
		repositorio.eliminar(id);
	}
	
	@Override
	public void actualizar(Tickets ticket) {
		repositorio.actualizar(ticket);
	}

	@Override
	public TicketsImporteTotal importe_total(int id, String fecha_hora) {
		return repositorio.importe_total(id, fecha_hora);
	}
	
}
