package mx.edu.itlapiedad.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.itlapiedad.models.Tickets;
import mx.edu.itlapiedad.models.TicketsImporteTotal;
import mx.edu.itlapiedad.services.TicketService;

@RestController
@RequestMapping("/devops/oxxo/tickets")
public class TicketsWS {

	@Autowired
	TicketService servicio;
	
	@PostMapping()
	public ResponseEntity<?> insertar(@RequestBody Tickets ticket){
		Tickets resultado = null;
		try {
			resultado = servicio.insertar(ticket);			
		} catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Tickets>(resultado, HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<?> consultarTickets() {
		
		List<Tickets> resultado = servicio.consultarTickets();
		return new ResponseEntity<List<Tickets>>(resultado, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable int id){
		Tickets resultado;
		try {
			resultado = servicio.buscar(id);
			
		}catch(DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Tickets>(resultado,HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<?> actualizar(@RequestBody Tickets ticket){
		try {
			servicio.actualizar(ticket);
			
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
			System.out.print(ticket.toString());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable int id){
		try {
			servicio.eliminar(id);
			
		}catch(DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Tickets>(HttpStatus.OK);
	}
	
	@GetMapping("/importe_total/id_cajero={id_cajero}&fecha={fecha_hora}")
	public ResponseEntity<?> importe_total(@PathVariable int id_cajero, @PathVariable String fecha_hora){
		TicketsImporteTotal resultado;
		try {
			resultado = servicio.importe_total(id_cajero,fecha_hora);
			System.out.print(fecha_hora);
			
		}catch(DataAccessException e) {
			System.out.println(e.getMessage());
			System.out.print(fecha_hora);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<TicketsImporteTotal>(resultado,HttpStatus.OK);
	}
	
}
