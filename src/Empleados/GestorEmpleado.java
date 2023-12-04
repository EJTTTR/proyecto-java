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
        }finally {
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

    public Empleados obtenerNombreApellidoE(String nombre, String apellido) {
    	java.sql.Connection conexion = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;
        Empleados empleado = null;
        String sql = "SELECT * FROM empleado WHERE nombre = ? AND apellido = ?";
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "qwerty");
            pst = conexion.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.setString(2, apellido);
            rs = pst.executeQuery();

            if (rs.next()) {
                int idEmpleado = rs.getInt("idEmpleado");
                double totalComision = rs.getDouble("totalComision");
                empleado = new Empleados(idEmpleado, nombre, apellido, totalComision);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
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
}
