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

import mx.edu.itlapiedad.models.Productos;
import mx.edu.itlapiedad.services.ProductoService;

@RestController
@RequestMapping("/devops/oxxo/productos")
public class ProductoWS {
	
	@Autowired
	ProductoService servicio;
	
	@PostMapping()
	public ResponseEntity<?> insertar(@RequestBody Productos producto){
		Productos resultado = null;
		try {
			resultado = servicio.insertar(producto);			
		} catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Productos>(resultado, HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<?> consultarProductos() {
		
		List<Productos> resultado = servicio.consultarProductos();
		return new ResponseEntity<List<Productos>>(resultado, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable int id){
		Productos resultado;
		try {
			resultado = servicio.buscar(id);
			
		}catch(DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Productos>(resultado,HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<?> actualizar(@RequestBody Productos producto){
		try {
			servicio.actualizar(producto);
			
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
			System.out.print(producto.toString());
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
		return new ResponseEntity<Productos>(HttpStatus.OK);
	}
	
	
	
}

