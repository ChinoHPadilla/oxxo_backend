package mx.edu.itlapiedad.models;

public class TicketsImporteTotal {

	private int id;
	private float importe_total;
	private String fecha_hora;

	public float getImporte_total() {
		return importe_total;
	}

	public void setImporte_total(float importe_total) {
		this.importe_total = importe_total;
	}

	public String getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(String fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
