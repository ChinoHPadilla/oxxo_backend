package mx.edu.itlapiedad.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.itlapiedad.models.Productos;
import mx.edu.itlapiedad.services.ProductoService;

@RestController
@RequestMapping("/devops")
public class ProductoWS {
	
	@Autowired
	ProductoService servicio;
	
	
	@PostMapping("/productos")
	public ResponseEntity<?> insertar(@RequestBody Productos producto){
		Productos resultado = null;
		try {
			resultado = servicio.insertar(producto);			
		} catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Productos>(resultado, HttpStatus.CREATED);
	}
	
	@GetMapping("/productos")
	public ResponseEntity<?> consultarProductos() {
		
		List<Productos> resultado = servicio.consultarProductos();
		return new ResponseEntity<List<Productos>>(resultado, HttpStatus.OK);
	}
	
}

