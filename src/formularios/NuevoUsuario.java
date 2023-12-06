package formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import Empleados.Empleados;
import Empleados.GestorEmpleado;
import Empleados.GestorUsuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NuevoUsuario {

	private JFrame frame;
	private JTextField txtNom;
	private JTextField txtApe;
	private JTextField txtNU;
	private JPasswordField passwordField;

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
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(84, 49, 77, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(84, 111, 77, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		txtNom = new JTextField();
		txtNom.setBounds(45, 80, 148, 20);
		frame.getContentPane().add(txtNom);
		txtNom.setColumns(10);
		
		txtApe = new JTextField();
		txtApe.setBounds(45, 151, 148, 20);
		frame.getContentPane().add(txtApe);
		txtApe.setColumns(10);
		
		JButton btnNewButton = new JButton("Añadir");
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String nombre = txtNom.getText();
		        String apellido = txtApe.getText();

		        Empleados nuevoEmpleado = new Empleados(0, nombre, apellido, (double) 0);  
		        
		        GestorUsuario gestorUsuario = new GestorUsuario();
                String nombreUsuario = nombre + apellido;
                
                while (gestorUsuario.verificarExistenciaUsuario(nombreUsuario)) {
                    // Si el usuario ya existe, agregar números aleatorios al final del nombre de usuario
                    int numeroAleatorio = (int) (Math.random() * 999);
                    nombreUsuario = nombreUsuario + numeroAleatorio;
                }
		        
		        GestorEmpleado gestorEmpleado = new GestorEmpleado(); 		        
		        boolean insercionExitosa = gestorEmpleado.insertarEmpleado(nuevoEmpleado);

		        if (insercionExitosa) {
		             nombreUsuario = nombre + apellido;
		            txtNU.setText(nombreUsuario);
		            JOptionPane.showMessageDialog(null, "Empleado registrado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            JOptionPane.showMessageDialog(null, "Error al insertar empleado", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		btnNewButton.setBounds(72, 197, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Registrar");
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Obtener el nombre de usuario y la contraseña
		        String nombreUsuario = txtNU.getText();
		        String contraseña = new String(passwordField.getPassword());

		        // Lógica para insertar un nuevo usuario en la base de datos
		        GestorUsuario gestorUsuario = new GestorUsuario();
		        boolean insercionExitosa = gestorUsuario.insertarUsuario(nombreUsuario, contraseña);

		        if (insercionExitosa) {
		            JOptionPane.showMessageDialog(null, "Usuario registrado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		            frame.dispose();
					
					login l = new login();
					l.mostrarVentana();
		        } else {
		            JOptionPane.showMessageDialog(null, "Error al registrar usuario", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		btnNewButton_1.setBounds(232, 197, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("Usuario");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(235, 49, 86, 20);
		frame.getContentPane().add(lblNewLabel_3);
		
		txtNU = new JTextField();
		txtNU.setEditable(false);
		txtNU.setBounds(235, 80, 86, 20);
		frame.getContentPane().add(txtNU);
		txtNU.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(235, 151, 86, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel_4 = new JLabel("Contraseña");
		lblNewLabel_4.setBounds(235, 111, 86, 20);
		frame.getContentPane().add(lblNewLabel_4);;
	}
	public void mostrarVentana() {
        frame.setVisible(true);
    }
}
