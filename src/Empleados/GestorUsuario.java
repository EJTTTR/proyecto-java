package Empleados;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorUsuario {
    public Usuario obtenerUsuario(Usuario user) {
    	Usuario usuario = null;
    	
    	java.sql.Connection con = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	
    	try {
    		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "qwerty");
    		
    		String sql = "SELECT * FROM users WHERE user_Name = ? AND password = ?";
    		
    		pst = con .prepareStatement(sql);
    		
    		pst.setString(1, user.getNombreUsuario());
    		pst.setString(2, user.getContraseña());
    		
    		rs = pst.executeQuery();
    		
    		while(rs.next()) {
    			usuario = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
    		}
    		
    	} catch(Exception e){
    		System.out.print("error");
    	}
    	
    	return usuario;
    }
    public boolean esIdEmpleadoValido(String idEmpleado) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean existeID = false;

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "qwerty");

            // Consulta SQL para buscar el ID del empleado en la tabla de empleados
            String sql = "SELECT * FROM empleado WHERE idEmpleado = ?";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, idEmpleado);
            resultSet = statement.executeQuery();

            // Si el resultSet tiene al menos una fila, significa que el ID existe
            if (resultSet.next()) {
                existeID = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return existeID;
    }
}
