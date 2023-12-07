package Empleados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorEmpleado {
	public boolean insertarEmpleado(Empleados empleado) {
		Connection conexion = null;
		PreparedStatement pst = null;

		String sql = "INSERT INTO empleado (nombre, apellido, totalComision) VALUES (?, ?, 0)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "qwerty");

			pst = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, empleado.getNombreEmpleado());
			pst.setString(2, empleado.getApellido());
			int filasInsertadas = pst.executeUpdate();

			if (filasInsertadas > 0) {
				ResultSet generatedKeys = pst.getGeneratedKeys();
				if (generatedKeys.next()) {
					int idGenerado = generatedKeys.getInt(1);
					empleado.setIdEmpleado(idGenerado);
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

	public Empleados obtenerEmpleado(int idEmpleado) {
		java.sql.Connection conexion = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Empleados empleado = null;
		String sql = "SELECT * FROM empleado WHERE id = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "qwerty");
			pst = conexion.prepareStatement(sql);
			pst.setInt(1, idEmpleado);
			rs = pst.executeQuery();

			if (rs.next()) {
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				double totalComision = rs.getDouble("totalComision");
				empleado = new Empleados(idEmpleado, nombre, apellido, totalComision);
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
		return empleado;
	}

	public boolean eliminarEmpleado(int idEmpleado) {
	    Connection conexion = null;
	    PreparedStatement pstEmpleado = null;
	    PreparedStatement pstUser = null;

	    String sqlEmpleado = "DELETE FROM empleado WHERE id = ?";
	    String sqlUser = "DELETE FROM users WHERE idEmpleado = ?";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "qwerty");
	        conexion.setAutoCommit(false);

	        // Eliminar empleado
	        pstEmpleado = conexion.prepareStatement(sqlEmpleado);
	        pstEmpleado.setInt(1, idEmpleado);
	        int filasEliminadasEmpleado = pstEmpleado.executeUpdate();

	        // Eliminar usuario asociado al empleado
	        pstUser = conexion.prepareStatement(sqlUser);
	        pstUser.setInt(1, idEmpleado);
	        int filasEliminadasUser = pstUser.executeUpdate();

	        boolean eliminacionExitosa = filasEliminadasEmpleado > 0 && filasEliminadasUser > 0;

	        if (eliminacionExitosa) {
	            conexion.commit();
	        } else {
	            conexion.rollback();
	        }

	        return eliminacionExitosa;
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (pstEmpleado != null) {
	                pstEmpleado.close();
	            }
	            if (pstUser != null) {
	                pstUser.close();
	            }
	            if (conexion != null) {
	                conexion.setAutoCommit(true);
	                conexion.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


	public boolean actualizarEmpleado(int idEmpleado, String nuevoNombre, String nuevoApellido, double nuevaComision) {
		Connection conexion = null;
		PreparedStatement pst = null;

		String sql = "UPDATE empleado SET nombre = ?, apellido = ?, totalComision = ? WHERE id = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "qwerty");
			pst = conexion.prepareStatement(sql);
			pst.setString(1, nuevoNombre);
			pst.setString(2, nuevoApellido);
			pst.setDouble(3, nuevaComision);
			pst.setInt(4, idEmpleado);

			int filasActualizadas = pst.executeUpdate();
			return filasActualizadas > 0;
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
	
	public boolean insertarEmpleado(Empleados empleado, String nombreUsuario, String contraseña) {
	    Connection conexion = null;
	    PreparedStatement pstEmpleado = null;

	    String sqlEmpleado = "INSERT INTO empleado (nombre, apellido, totalComision) VALUES (?, ?, ?)";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "qwerty");
	        conexion.setAutoCommit(false);

	        pstEmpleado = conexion.prepareStatement(sqlEmpleado, Statement.RETURN_GENERATED_KEYS);
	        pstEmpleado.setString(1, empleado.getNombreEmpleado());
	        pstEmpleado.setString(2, empleado.getApellido());
	        pstEmpleado.setDouble(3, empleado.getTotalComision());

	        int filasInsertadasEmpleado = pstEmpleado.executeUpdate();
	        boolean insercionExitosa = filasInsertadasEmpleado > 0;

	        if (insercionExitosa) {
	            ResultSet generatedKeys = pstEmpleado.getGeneratedKeys();
	            int idEmpleadoGenerado = -1;
	            if (generatedKeys.next()) {
	                idEmpleadoGenerado = generatedKeys.getInt(1);
	            }

	            if (idEmpleadoGenerado != -1) {
	                // Utiliza GestorUsuario para insertar el usuario asociado al empleado
	                GestorUsuario gestorUsuario = new GestorUsuario();
	                boolean insercionUsuarioExitosa = gestorUsuario.insertarUsuario(nombreUsuario, contraseña, idEmpleadoGenerado);

	                if (insercionUsuarioExitosa) {
	                    conexion.commit();
	                } else {
	                    conexion.rollback();
	                }
	                return insercionUsuarioExitosa;
	            } else {
	                conexion.rollback();
	            }
	        } else {
	            conexion.rollback();
	        }

	        return false;
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (pstEmpleado != null) {
	                pstEmpleado.close();
	            }
	            if (conexion != null) {
	                conexion.setAutoCommit(true);
	                conexion.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	public int obtenerUltimoIdEmpleado() {
	    Connection conexion = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    int ultimoId = 0;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "qwerty");

	        String sql = "SELECT MAX(id) AS ultimoId FROM empleado"; 
	        pst = conexion.prepareStatement(sql);
	        rs = pst.executeQuery();

	        if (rs.next()) {
	            ultimoId = rs.getInt("ultimoId");
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
	    return ultimoId;
	}
}
