package mx.edu.itlapiedad.dao;

import java.util.List;

import mx.edu.itlapiedad.models.Tickets;
import mx.edu.itlapiedad.models.TicketsImporteTotal;

public interface TIcketDAO {

public Tickets insertar(Tickets ticket);
	
	List<Tickets> consultarTickets();
	
	public Tickets buscar(int id);
	
	public void eliminar(int id);
	
	public void actualizar(Tickets ticket);

	public TicketsImporteTotal importe_total(int id, String fecha_hora);
	
}
