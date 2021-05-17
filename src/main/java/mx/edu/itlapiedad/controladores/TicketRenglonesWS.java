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

import mx.edu.itlapiedad.models.TicketRenglones;
import mx.edu.itlapiedad.services.TicketRenglonesService;

@RestController
@RequestMapping("/devops/oxxo/ticket_renglones")
public class TicketRenglonesWS {

	@Autowired
	TicketRenglonesService servicio;
	
	@PostMapping()
	public ResponseEntity<?> insertar(@RequestBody TicketRenglones ticketRenglon){
		TicketRenglones resultado = null;
		try {
			resultado = servicio.insertar(ticketRenglon);			
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<TicketRenglones>(resultado, HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<?> consultarTicketRenglones() {
		
		List<TicketRenglones> resultado = servicio.consultarTicketRenglones();
		return new ResponseEntity<List<TicketRenglones>>(resultado, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable int id){
		TicketRenglones resultado;
		try {
			resultado = servicio.buscar(id);
			
		}catch(DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<TicketRenglones>(resultado,HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<?> actualizar(@RequestBody TicketRenglones ticketRenglon){
		try {
			servicio.actualizar(ticketRenglon);
			
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
			System.out.print(ticketRenglon.toString());
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
		return new ResponseEntity<TicketRenglones>(HttpStatus.OK);
	}
	
}
