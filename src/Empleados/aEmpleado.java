package Empleados;

import javax.swing.JTextField;

public abstract class aEmpleado {
	
	private int idEmpleado;
	private String nombreEmpleado;
	private String apellido;
	private Double totalComision;
	
	public aEmpleado(int idEmpleado, String nombreEmpleado, String apellido, Double totalComision) {
		// TODO Auto-generated constructor stub
		this.idEmpleado = idEmpleado;
		this.nombreEmpleado = nombreEmpleado; 
		this.apellido = apellido;
		this.totalComision = totalComision;
	}
	
	public aEmpleado(String nombreEmpleado, String apellido, Double totalComision) {
		// TODO Auto-generated constructor stub
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
	aEmpleado(JTextField nombreE, JTextField apellidoE, JTextField comision) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
	
	public Object[] obtenerDatos() {
        return new Object[]{nombreEmpleado, apellido, totalComision};
    }

}
