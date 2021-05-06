package mx.edu.itlapiedad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import mx.edu.itlapiedad.models.Productos;

@Repository
public class ProductoJDBC implements ProductoDAO {

	@Autowired
	JdbcTemplate conexion;
	
	
	@Override
	public Productos insertar(Productos producto) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(conexion)
				.withTableName("productos")
				.usingColumns("id","descripcion","precio","codigo_barras",
						"existencia")
				.usingGeneratedKeyColumns("id");
		Map<String, Object> datos = new HashMap<>();
		datos.put("id", producto.getId());
		datos.put("descripcion", producto.getDescripcion());
		datos.put("precio", producto.getPrecio());
		datos.put("codigo_barras", producto.getCodigo_barras());
		datos.put("existencia", producto.getExistencia());
		Number id = insert.executeAndReturnKey(datos);
		producto.setId(id.intValue());
		return producto;
		
	}

	
}

