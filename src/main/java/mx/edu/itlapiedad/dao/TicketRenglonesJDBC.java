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
	
	private final String INSERT_SQL = "INSERT INTO ticket_renglones(id,TICKET_id,PRODUCTO_id,cantidad,precio,importe) values(?,?,?,?,?,?)";
	
	@Autowired
	JdbcTemplate conexion;

	public TicketRenglones insertar(final TicketRenglones ticketRenglones) {
		KeyHolder holder = new GeneratedKeyHolder();
		conexion.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setLong(1, ticketRenglones.getId());
				ps.setLong(2, ticketRenglones.getId_ticket());
				ps.setLong(3, ticketRenglones.getId_producto());
				ps.setLong(4, ticketRenglones.getCantidad());
				ps.setFloat(5, ticketRenglones.getPrecio());
				ps.setFloat(6, ticketRenglones.getImporte());
				return ps;
			}
		}, holder);

		int newUserId = holder.getKey().intValue();
		ticketRenglones.setId(newUserId);
		return ticketRenglones;
	}

	@Override
	public List<TicketRenglones> consultarTicketRenglones() {
		String sql_query = "SELECT * FROM ticket_renglones";
		return conexion.query(sql_query, new RowMapper<TicketRenglones>() {
			public TicketRenglones mapRow(ResultSet rs, int rowNum) throws SQLException{
				TicketRenglones ticketRenglones = new TicketRenglones();
				ticketRenglones.setId(rs.getInt("id"));
				ticketRenglones.setId_ticket(rs.getInt("TICKET_id"));
				ticketRenglones.setId_producto(rs.getInt("PRODUCTO_id"));
				ticketRenglones.setCantidad(rs.getInt("cantidad"));
				ticketRenglones.setPrecio(rs.getFloat("precio"));
				ticketRenglones.setImporte(rs.getFloat("importe"));
				return ticketRenglones;
			}
		});
	}

	@Override
	public TicketRenglones buscar(int id) {
		String sql_query = "SELECT * FROM ticket_renglones WHERE id = ?";
		return conexion.queryForObject(sql_query, new RowMapper<TicketRenglones>() {
			public TicketRenglones mapRow(ResultSet rs, int rowNum) throws SQLException{
				TicketRenglones ticketRenglones = new TicketRenglones();
				ticketRenglones.setId(rs.getInt("id"));
				ticketRenglones.setId_ticket(rs.getInt("TICKET_id"));
				ticketRenglones.setId_producto(rs.getInt("PRODUCTO_id"));
				ticketRenglones.setCantidad(rs.getInt("cantidad"));
				ticketRenglones.setPrecio(rs.getFloat("precio"));
				ticketRenglones.setImporte(rs.getFloat("importe"));
				return ticketRenglones;
			}
		}, id);
	}

	@Override
	public void actualizar(TicketRenglones ticketRenglones) {
		String sql_update = "UPDATE ticket_renglones SET TICKET_id=?, PRODUCTO_id=?, cantidad=?, precio=?, importe=? WHERE id=?";
		conexion.update(sql_update, 
				ticketRenglones.getId_ticket(),
				ticketRenglones.getId_producto(),
				ticketRenglones.getCantidad(),
				ticketRenglones.getPrecio(),
				ticketRenglones.getImporte(),
				ticketRenglones.getId());
		
	}

	@Override
	public void eliminar(int id) {
		String sql_update = "DELETE from ticket_renglones WHERE id = ?";
		conexion.update(sql_update, id);
	}

}
