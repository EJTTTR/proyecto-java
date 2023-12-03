package Empleados;

public class Empleados {
	
	private int idEmpleado;
	private String nombreEmpleado;
	private String apellido;
	private double totalComision;
	
	public Empleados(int idEmpleado, String nombreEmpleado, String apellido, double totalComision) {
		// TODO Auto-generated constructor stub
		this.idEmpleado = idEmpleado;
		this.nombreEmpleado = nombreEmpleado;
		this.apellido = apellido;
		this.totalComision = totalComision;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public double getTotalComision() {
		return totalComision;
	}

	public void setTotalComision(double totalComision) {
		this.totalComision = totalComision;
	}

}
