package mx.edu.itlapiedad.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.itlapiedad.dao.CajeroDAO;
import mx.edu.itlapiedad.dao.TIcketDAO;
import mx.edu.itlapiedad.models.Cajeros;
import mx.edu.itlapiedad.models.Tickets;

@Service
public class CajerosLogic implements CajeroService{

	@Autowired
	CajeroDAO repositorio;
	
	@Override
	public Cajeros buscar(int id) {
		return repositorio.buscar(id);
	}
	
	@Override
	public Cajeros insertar(Cajeros cajero) {
		return repositorio.insertar(cajero);
	}
	
	@Override
	public List<Cajeros> consultarCajeros() {
		return repositorio.consultarCajeros();
	}
	
	@Override
	public void eliminar(int id) {
		repositorio.eliminar(id);
	}
	
	@Override
	public void actualizar(Cajeros cajero) {
		repositorio.actualizar(cajero);
	}
	
}
