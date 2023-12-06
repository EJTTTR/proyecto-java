
package Citas;


public class aCita {
    
	private int idCita, idEmpleado;
    private String cliente, Servicio, Fecha;  
    private Double  pago;

    public aCita(String Nombre, String Servicio, String Fecha, Double Pago, int empleado) {
        this.cliente = Nombre;
        this.Servicio = Servicio;
        this.Fecha = Fecha;
        this.pago = Pago;
        this.idEmpleado = empleado;
    }
    
    public aCita(int idCita, String Nombre, String Servicio, String Fecha, Double Pago, int empleado) {
    	this.idCita = idCita;
        this.cliente = Nombre;
        this.Servicio = Servicio;
        this.Fecha = Fecha;
        this.pago = Pago;
        this.idEmpleado = empleado;
    }

	public String getCliente() {
		return cliente;
	}


	public void setCliente(String cliente) {
		this.cliente = cliente;
	}


	public String getServicio() {
		return Servicio;
	}


	public void setServicio(String servicio) {
		Servicio = servicio;
	}


	public String getFecha() {
		return Fecha;
	}


	public void setFecha(String fecha) {
		Fecha = fecha;
	}


	public Double getPago() {
		return pago;
	}


	public void setPago(Double pago) {
		this.pago = pago;
	}


	public int getidEmpleado() {
		return idEmpleado;
	}


	public void setidEmpleado(int empleado) {
		this.idEmpleado = empleado;
	}


	public Object[] obtenerDatos() {
        return new Object[]{cliente, Servicio, Fecha, pago};
    }

	public int getIdCita() {
		return idCita;
	}

	public void setIdCita(int idCita) {
		this.idCita = idCita;
	}
    
}

