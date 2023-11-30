package formularios;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import Empleados.GestorUsuario;

public class NuevoUsuario {

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoUsuario window = new NuevoUsuario();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NuevoUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(105, 178, 170, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lbl1 = new JLabel("Contraseña");
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setBounds(143, 147, 95, 20);
		frame.getContentPane().add(lbl1);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(149, 23, 68, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ID Empleado");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(143, 85, 95, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(105, 54, 170, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(105, 116, 170, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(149, 209, 89, 23);
		frame.getContentPane().add(btnRegistrar);
		
		btnRegistrar.addActionListener(e -> {
			 	String nombreUsuario = textField.getText();
	            String contraseña = String.valueOf(passwordField.getPassword());
	            String idEmpleado = textField_1.getText();
	            
	            if (nombreUsuario.isEmpty() || contraseña.isEmpty() || idEmpleado.isEmpty()) {
	                JOptionPane.showMessageDialog(frame, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
	                return; 
	            }

	            if (nombreUsuario.length() < 3 || contraseña.length() < 5) {
	                JOptionPane.showMessageDialog(frame, "El nombre de usuario debe tener al menos 3 caracteres y la contraseña al menos 5 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            
	            GestorUsuario gestorUser = new GestorUsuario();
	            
	            if (!gestorUser.esIdEmpleadoValido(idEmpleado)) {
	                JOptionPane.showMessageDialog(frame, "El ID del empleado no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            Connection conexion = null;
	            try {
	                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "qwerty");
	                String sql = "INSERT INTO users (user_Name, password, idEmpleado) VALUES (?, ?, ?)";
	                PreparedStatement statement = conexion.prepareStatement(sql);
	                statement.setString(1, nombreUsuario);
	                statement.setString(2, contraseña);
	                statement.setString(3, idEmpleado);

	                int filasInsertadas = statement.executeUpdate();
	                if (filasInsertadas > 0) {
	                    System.out.println("Nuevo usuario registrado exitosamente.");
	                    frame.dispose(); 
	                    
	                    login lo = new login();
	                    lo.mostrarVentana();
	                }
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            } finally {
	                try {
	                    if (conexion != null) {
	                        conexion.close();
	                    }
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });;
	}
	public void mostrarVentana() {
        frame.setVisible(true);
    }
}
