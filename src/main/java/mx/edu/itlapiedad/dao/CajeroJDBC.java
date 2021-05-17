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

import mx.edu.itlapiedad.models.Cajeros;
import mx.edu.itlapiedad.models.Tickets;

@Repository
public class CajeroJDBC implements CajeroDAO{

private final String INSERT_SQL = "INSERT INTO cajeros(id,nombre) values(?,?)";
	
	@Autowired
	JdbcTemplate conexion;
	
	
	public Cajeros insertar(final Cajeros cajero) {
		KeyHolder holder = new GeneratedKeyHolder();
		conexion.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, cajero.getId());
				ps.setString(2, cajero.getNombre());
				return ps;
			}
		}, holder);

		int newUserId = holder.getKey().intValue();
		cajero.setId(newUserId);
		return cajero;
	}

	@Override
	public List<Cajeros> consultarCajeros() {
		String sql_query = "SELECT * FROM cajeros";
		return conexion.query(sql_query, new RowMapper<Cajeros>() {
			public Cajeros mapRow(ResultSet rs, int rowNum) throws SQLException{
				Cajeros cajero = new Cajeros();
				cajero.setId(rs.getInt("id"));
				cajero.setNombre(rs.getString("nombre"));
				return cajero;
			}
		});
	}
	
	@Override
	public Cajeros buscar(int id) {
		String sql_query = "SELECT * FROM cajeros WHERE id = ?";
		return conexion.queryForObject(sql_query, new RowMapper<Cajeros>() {
			public Cajeros mapRow(ResultSet rs, int rowNum) throws SQLException{
				Cajeros cajero = new Cajeros();
				cajero.setId(rs.getInt("id"));
				cajero.setNombre(rs.getString("nombre"));
				return cajero;
			}
		}, id);
	}
	
	@Override
	public void actualizar(Cajeros cajero) {
		String sql_update = "UPDATE cajeros SET nombre=? WHERE id=?";
		conexion.update(sql_update, cajero.getNombre(),
				cajero.getId());
	}

	@Override
	public void eliminar(int id) {
		String sql_update = "DELETE from cajeros WHERE id = ?";
		conexion.update(sql_update, id);
	}
	
}
