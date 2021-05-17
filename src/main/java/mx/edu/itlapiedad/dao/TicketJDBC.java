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

import mx.edu.itlapiedad.models.Tickets;

@Repository
public class TicketJDBC implements TIcketDAO{

private final String INSERT_SQL = "INSERT INTO tickets(id,fecha_hora,total,CAJERO_id) values(?,?,?,?)";
	
	@Autowired
	JdbcTemplate conexion;
	
	
	public Tickets insertar(final Tickets ticket) {
		KeyHolder holder = new GeneratedKeyHolder();
		conexion.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, ticket.getId());
				ps.setString(2, ticket.getFecha_hora());
				ps.setFloat(3, ticket.getTotal());
				ps.setInt(4, ticket.getCAJERO_id());
				return ps;
			}
		}, holder);

		int newUserId = holder.getKey().intValue();
		ticket.setId(newUserId);
		return ticket;
	}

	@Override
	public List<Tickets> consultarTickets() {
		String sql_query = "SELECT * FROM tickets";
		return conexion.query(sql_query, new RowMapper<Tickets>() {
			public Tickets mapRow(ResultSet rs, int rowNum) throws SQLException{
				Tickets ticket = new Tickets();
				ticket.setId(rs.getInt("id"));
				ticket.setFecha_hora(rs.getString("fecha_hora"));
				ticket.setTotal(rs.getFloat("total"));
				ticket.setCAJERO_id(rs.getInt("CAJERO_id"));
				return ticket;
			}
		});
	}
	
	@Override
	public Tickets buscar(int id) {
		String sql_query = "SELECT * FROM tickets WHERE id = ?";
		return conexion.queryForObject(sql_query, new RowMapper<Tickets>() {
			public Tickets mapRow(ResultSet rs, int rowNum) throws SQLException{
				Tickets ticket = new Tickets();
				ticket.setId(rs.getInt("id"));
				ticket.setFecha_hora(rs.getString("fecha_hora"));
				ticket.setTotal(rs.getFloat("total"));
				ticket.setCAJERO_id(rs.getInt("CAJERO_id"));
				return ticket;
			}
		}, id);
	}
	
	@Override
	public void actualizar(Tickets ticket) {
		String sql_update = "UPDATE tickets SET fecha_hora=?, total=?, CAJERO_id=? WHERE id=?";
		conexion.update(sql_update, ticket.getFecha_hora(),
				ticket.getTotal(),
				ticket.getCAJERO_id(),
				ticket.getId());
	}

	@Override
	public void eliminar(int id) {
		String sql_update = "DELETE from tickets WHERE id = ?";
		conexion.update(sql_update, id);
	}
	
}
