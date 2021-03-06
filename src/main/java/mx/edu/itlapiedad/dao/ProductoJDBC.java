package mx.edu.itlapiedad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import mx.edu.itlapiedad.models.Productos;

@Repository
public class ProductoJDBC implements ProductoDAO {

	private final String INSERT_SQL = "INSERT INTO productos(id,descripcion,precio,codigo_barras,existencia) values(?,?,?,?,?)";
	
	@Autowired
	JdbcTemplate conexion;
	
	
	public Productos insertar(final Productos producto) {
		KeyHolder holder = new GeneratedKeyHolder();
		conexion.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setLong(1, producto.getId());
				ps.setString(2, producto.getDescripcion());
				ps.setFloat(3, producto.getPrecio());
				ps.setString(4, producto.getCodigo_barras());
				ps.setLong(5, producto.getExistencia());
				return ps;
			}
		}, holder);

		int newUserId = holder.getKey().intValue();
		producto.setId(newUserId);
		return producto;
	}

	@Override
	public List<Productos> consultarProductos() {
		String sql_query = "SELECT * FROM productos";
		return conexion.query(sql_query, new RowMapper<Productos>() {
			public Productos mapRow(ResultSet rs, int rowNum) throws SQLException{
				Productos producto = new Productos();
				producto.setId(rs.getInt("id"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getFloat("precio"));
				producto.setCodigo_barras(rs.getString("codigo_barras"));
				producto.setExistencia(rs.getInt("existencia"));
				return producto;
			}
		});
	}
	
	@Override
	public Productos buscar(int id) {
		String sql_query = "SELECT * FROM productos WHERE id = ?";
		return conexion.queryForObject(sql_query, new RowMapper<Productos>() {
			public Productos mapRow(ResultSet rs, int rowNum) throws SQLException{
				Productos producto = new Productos();
				producto.setId(rs.getInt("id"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getFloat("precio"));
				producto.setCodigo_barras(rs.getString("codigo_barras"));
				producto.setExistencia(rs.getInt("existencia"));
				return producto;
			}
		}, id);
	}
	
	@Override
	public void actualizar(Productos producto) {
		String sql_update = "UPDATE productos SET descripcion=?, precio=?, codigo_barras=?, existencia=? WHERE id=?";
		conexion.update(sql_update, producto.getDescripcion(),
				producto.getPrecio(),
				producto.getCodigo_barras(),
				producto.getExistencia(),
				producto.getId());
	}

	@Override
	public void eliminar(int id) {
		String sql_update = "DELETE from productos WHERE id = ?";
		conexion.update(sql_update, id);
	}
	
}

