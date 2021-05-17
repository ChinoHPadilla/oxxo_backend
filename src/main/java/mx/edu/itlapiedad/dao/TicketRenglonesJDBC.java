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

import mx.edu.itlapiedad.models.TicketRenglones;

@Repository
public class TicketRenglonesJDBC implements TicketRenglonesDAO{
	
	private final String INSERT_SQL = "INSERT INTO ticket_renglones(id,TICKET_id,PRODUCTO_id,cantidad,precio) values(?,?,?,?,?)";
	
	@Autowired
	JdbcTemplate conexion;

	public TicketRenglones insertar(final TicketRenglones ticketRenglon) {
		KeyHolder holder = new GeneratedKeyHolder();
		conexion.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setLong(1, ticketRenglon.getId());
				ps.setLong(2, ticketRenglon.getTICKET_id());
				ps.setLong(3, ticketRenglon.getPRODUCTO_id());
				ps.setLong(4, ticketRenglon.getCantidad());
				ps.setFloat(5, ticketRenglon.getPrecio());
				return ps;
			}
		}, holder);

		int newUserId = holder.getKey().intValue();
		ticketRenglon.setId(newUserId);
		return ticketRenglon;
	}

	@Override
	public List<TicketRenglones> consultarTicketRenglones() {
		String sql_query = "SELECT * FROM ticket_renglones";
		return conexion.query(sql_query, new RowMapper<TicketRenglones>() {
			public TicketRenglones mapRow(ResultSet rs, int rowNum) throws SQLException{
				TicketRenglones ticketRenglon = new TicketRenglones();
				ticketRenglon.setId(rs.getInt("id"));
				ticketRenglon.setTICKET_id(rs.getInt("TICKET_id"));
				ticketRenglon.setPRODUCTO_id(rs.getInt("PRODUCTO_id"));
				ticketRenglon.setCantidad(rs.getInt("cantidad"));
				ticketRenglon.setPrecio(rs.getFloat("precio"));
				ticketRenglon.setImporte(rs.getFloat("importe"));
				return ticketRenglon;
			}
		});
	}

	@Override
	public TicketRenglones buscar(int id) {
		String sql_query = "SELECT * FROM ticket_renglones WHERE id = ?";
		return conexion.queryForObject(sql_query, new RowMapper<TicketRenglones>() {
			public TicketRenglones mapRow(ResultSet rs, int rowNum) throws SQLException{
				TicketRenglones ticketRenglon = new TicketRenglones();
				ticketRenglon.setId(rs.getInt("id"));
				ticketRenglon.setTICKET_id(rs.getInt("TICKET_id"));
				ticketRenglon.setPRODUCTO_id(rs.getInt("PRODUCTO_id"));
				ticketRenglon.setCantidad(rs.getInt("cantidad"));
				ticketRenglon.setPrecio(rs.getFloat("precio"));
				ticketRenglon.setImporte(rs.getFloat("importe"));
				return ticketRenglon;
			}
		}, id);
	}

	@Override
	public void actualizar(TicketRenglones ticketRenglon) {
		String sql_update = "UPDATE ticket_renglones SET TICKET_id=?, PRODUCTO_id=?, cantidad=?, precio=?, importe=? WHERE id=?";
		conexion.update(sql_update, 
				ticketRenglon.getTICKET_id(),
				ticketRenglon.getPRODUCTO_id(),
				ticketRenglon.getCantidad(),
				ticketRenglon.getPrecio(),
				ticketRenglon.getImporte(),
				ticketRenglon.getId());
		
	}

	@Override
	public void eliminar(int id) {
		String sql_update = "DELETE from ticket_renglones WHERE id = ?";
		conexion.update(sql_update, id);
	}

}
