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

import mx.edu.itlapiedad.models.Cajeros;
import mx.edu.itlapiedad.services.CajeroService;

/*Servicios cajeros*/
@RestController
@RequestMapping("/devops/oxxo/cajeros")
public class CajerosWS {

	@Autowired
	CajeroService servicio;
	
	@PostMapping()
	public ResponseEntity<?> insertar(@RequestBody Cajeros cajero){
		Cajeros resultado = null;
		try {
			resultado = servicio.insertar(cajero);			
		} catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Cajeros>(resultado, HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<?> consultarCajeros() {
		
		List<Cajeros> resultado = servicio.consultarCajeros();
		return new ResponseEntity<List<Cajeros>>(resultado, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable int id){
		Cajeros resultado;
		try {
			resultado = servicio.buscar(id);
			
		}catch(DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Cajeros>(resultado,HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<?> actualizar(@RequestBody Cajeros cajero){
		try {
			servicio.actualizar(cajero);
			
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
			System.out.print(cajero.toString());
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
		return new ResponseEntity<Cajeros>(HttpStatus.OK);
	}
	
}
