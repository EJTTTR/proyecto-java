package Empleados;

public abstract class aUsuario {

	private int idUsuario;
	private String nombreUsuario;
	private String contraseña;
	private int idEmpleado;
	
	public aUsuario(int idUsuario, String nombre, String contra, int idEmpleado) {
		// TODO Auto-generated constructor stub
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombre;
		this.contraseña = contra;
		this.idEmpleado = idEmpleado;
	}

	public aUsuario() {}

	public int getIdUsuario() {
		return idUsuario;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}



	public String getNombreUsuario() {
		return nombreUsuario;
	}



	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}



	public String getContraseña() {
		return contraseña;
	}



	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
}
