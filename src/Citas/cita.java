
package Citas;


public class cita extends aCita{

	public cita(String Nombre, String Servicio, String Fecha, Double Pago, int empleado) {
		super(Nombre, Servicio, Fecha, Pago, empleado);
		// TODO Auto-generated constructor stub
	}
	public cita(int idCita, String Nombre, String Servicio, String Fecha, Double Pago, int empleado) {
		super(idCita, Nombre, Servicio, Fecha, Pago, empleado);
    }
}

