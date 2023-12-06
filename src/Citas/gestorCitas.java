package Citas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Empleados.Empleados;
import Empleados.GestorEmpleado;

public class gestorCitas {

	@SuppressWarnings("resource")
	public boolean insertarCita(cita citas) {
		Connection conexion = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		String sqlEmpleado = "SELECT * FROM empleado WHERE id = ?";
		String sqlInsertarCita = "INSERT INTO agendarcita (cliente, servicio, fecha, precio, idEmpleado) VALUES (?, ?, ?, ?, ?)";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "qwerty");

			pst = conexion.prepareStatement(sqlEmpleado);
			pst.setInt(1, citas.getidEmpleado());
			rs = pst.executeQuery();

			if (!rs.next()) {
				JOptionPane.showMessageDialog(null, "El empleado con ID " + citas.getidEmpleado() + " no existe.",
						"Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}

			// Insertar la cita si el empleado existe
			pst = conexion.prepareStatement(sqlInsertarCita, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, citas.getCliente());
			pst.setString(2, citas.getServicio());
			pst.setString(3, citas.getFecha());
			pst.setDouble(4, citas.getPago());
			pst.setInt(5, citas.getidEmpleado());

			int filasInsertadas = pst.executeUpdate();

			if (filasInsertadas > 0) {
				ResultSet generatedKeys = pst.getGeneratedKeys();
				if (generatedKeys.next()) {
					int idGenerado = generatedKeys.getInt(1);
					citas.setIdCita(idGenerado);
				}
				return true;
			} else {
				return false;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean eliminarCita(int idCita) {
		Connection conexion = null;
		PreparedStatement pst = null;

		String sql = "DELETE FROM agendarcita WHERE id = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "qwerty");
			pst = conexion.prepareStatement(sql);
			pst.setInt(1, idCita);

			int filasEliminadas = pst.executeUpdate();
			return filasEliminadas > 0;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("resource")
	public boolean actualizarCita(int idCita, String nuevoCliente, String nuevoServicio, String nuevaFecha,
			String nuevoPrecio, int nuevoEmpleado) {
		Connection conexion = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		String sqlEmpleado = "SELECT * FROM empleado WHERE id = ?";
		String sqlActualizarCita = "UPDATE agendarcita SET cliente = ?, servicio = ?, fecha = ?, precio = ?, idEmpleado = ? WHERE id = ?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "qwerty");

			// existe el empleado, antes de actualizar
			pst = conexion.prepareStatement(sqlEmpleado);
			pst.setInt(1, nuevoEmpleado);
			rs = pst.executeQuery();

			if (!rs.next()) {
				JOptionPane.showMessageDialog(null,
						"El empleado con ID " + nuevoEmpleado + " no existe. No se puede actualizar la cita.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}

			pst = conexion.prepareStatement(sqlActualizarCita);
			pst.setString(1, nuevoCliente);
			pst.setString(2, nuevoServicio);
			pst.setString(3, nuevaFecha);
			pst.setString(4, nuevoPrecio);
			pst.setInt(5, nuevoEmpleado);
			pst.setInt(6, idCita);

			int filasActualizadas = pst.executeUpdate();
			return filasActualizadas > 0;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("resource")
	public boolean completarCita(int idCita, double precio) {
		Connection conexion = null;
		PreparedStatement pst = null;

		String UpdateCita = "UPDATE agendarCita SET fecha = 'completada' WHERE id = ?";
		String ObtenerEmpleado = "SELECT idEmpleado FROM agendarcita WHERE id = ?";

		boolean actualizacionExitosa = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "qwerty");
			conexion.setAutoCommit(false);

			pst = conexion.prepareStatement(UpdateCita);
			pst.setInt(1, idCita);
			int filasActualizadas = pst.executeUpdate();

			if (filasActualizadas > 0) {
				// ID del empleado de la cita
				pst = conexion.prepareStatement(ObtenerEmpleado);
				pst.setInt(1, idCita);
				ResultSet rs = pst.executeQuery();

				if (rs.next()) {
					int idEmpleado = rs.getInt("idEmpleado");

					// Obtener el empleado por ID
					GestorEmpleado gestorEmpleado = new GestorEmpleado();
					Empleados empleado = gestorEmpleado.obtenerEmpleado(idEmpleado);

					if (empleado != null) {
						// Actualizar el totalComision del empleado
						double comisionActualizada = empleado.getTotalComision() + precio;
						boolean actualizacionComision = gestorEmpleado.actualizarEmpleado(idEmpleado,
								empleado.getNombreEmpleado(), empleado.getApellido(), comisionActualizada);

						if (actualizacionComision) {
							actualizacionExitosa = true;
							conexion.commit();
						} else {
							conexion.rollback();
						}
					} else {
						// Si no existe, se revierte la actualización de la cita
						JOptionPane.showMessageDialog(null, "El empleado con ID " + idEmpleado + " no existe.", "Error",
								JOptionPane.ERROR_MESSAGE);
						conexion.rollback();
					}
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			if (conexion != null) {
				try {
					conexion.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				if (conexion != null) {
					conexion.setAutoCommit(true);
					conexion.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return actualizacionExitosa;
	}

	public boolean estaCompletada(int idCita) {
		Connection conexion = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean citaCompletada = false;

		String sql = "SELECT fecha FROM agendarCita WHERE id = ?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "qwerty");

			pst = conexion.prepareStatement(sql);
			pst.setInt(1, idCita);
			rs = pst.executeQuery();

			if (rs.next()) {
				String fecha = rs.getString("fecha");
				if (fecha.equals("completada")) {
					citaCompletada = true;
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return citaCompletada;
	}
}
