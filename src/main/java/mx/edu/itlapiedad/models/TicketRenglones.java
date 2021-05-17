package mx.edu.itlapiedad.models;

public class TicketRenglones {
	
	private int TICKET_id, PRODUCTO_id, cantidad;
	private Float precio, importe;
	private int id;
	
	
	public int getTICKET_id() {
		return TICKET_id;
	}
	public void setTICKET_id(int tICKET_id) {
		TICKET_id = tICKET_id;
	}
	public int getPRODUCTO_id() {
		return PRODUCTO_id;
	}
	public void setPRODUCTO_id(int pRODUCTO_id) {
		PRODUCTO_id = pRODUCTO_id;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Float getImporte() {
		return importe;
	}
	public void setImporte(Float importe) {
		this.importe = importe;
	}
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	

}
