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
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "qwerty");
    		
    		String sql = "SELECT * FROM users WHERE user_Name = ? AND password = ?";
    		
    		pst = con.prepareStatement(sql);
    		
    		pst.setString(1, user.getNombreUsuario());
    		pst.setString(2, user.getContraseña());
    		
    		rs = pst.executeQuery();
    		
    		while(rs.next()) {
    			usuario = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
    		}
    		
    	} catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
    	} finally {
        	try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    	return usuario;
    }
    
    public boolean insertarUsuario(String nombreUsuario, String contraseña, int idEmpleado) {
        Connection con = null;
        PreparedStatement pst = null;
        String sql = "INSERT INTO users (user_Name, password, idEmpleado) VALUES (?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "qwerty");

            pst = con.prepareStatement(sql);
            pst.setString(1, nombreUsuario);
            pst.setString(2, contraseña);
            pst.setInt(3, idEmpleado);

            int filasInsertadas = pst.executeUpdate();
            return filasInsertadas > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
    public boolean verificarExistenciaUsuario(String nombreUsuario) {
        boolean existe = false;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "contraseña");

            String sql = "SELECT * FROM users WHERE user_Name = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, nombreUsuario);
            rs = pst.executeQuery();

            if (rs.next()) {
                existe = true;
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
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return existe;
    }
}
