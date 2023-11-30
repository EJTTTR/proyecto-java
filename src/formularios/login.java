package formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import Empleados.GestorUsuario;
import Empleados.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class login {

	private JFrame frame;
	private JTextField txtNom;
	private JPasswordField txtContra;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
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
	public login() {
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
		
		txtNom = new JTextField();
		txtNom.setBounds(125, 68, 155, 20);
		frame.getContentPane().add(txtNom);
		txtNom.setColumns(10);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ingresar();
			}
		});
		btnNewButton.setBounds(161, 161, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(161, 37, 68, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contraseña");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(161, 99, 68, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtContra = new JPasswordField();
		txtContra.setBounds(125, 130, 155, 20);
		frame.getContentPane().add(txtContra);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenuItem menuNewUser = new JMenuItem("Nuevo Usuario");
		menuNewUser.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(menuNewUser);
		menuNewUser.addActionListener(e -> {
		    NuevoUsuario nuevoUsuario = new NuevoUsuario();
		    nuevoUsuario.mostrarVentana();
		    frame.dispose();
		});
	}

	protected void Ingresar() {
		String user = txtNom.getText();
		String contra = String.valueOf(txtContra.getPassword());
		
		if (user.isEmpty() || contra.isEmpty()) {
	        JOptionPane.showMessageDialog(frame, "Por favor, completa ambos campos.", "Campos vacíos", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
		
		GestorUsuario gestorUser = new GestorUsuario();
		
		Usuario usuarios = new Usuario();
		usuarios.setNombreUsuario(user);
		usuarios.setContraseña(contra);
		
		Usuario usu = gestorUser.obtenerUsuario(usuarios);
		
		if(usu != null) {
			frame.dispose();
			
			Prueba l = new Prueba();
			l.mostrarVentana();
		}else {
			JOptionPane.showMessageDialog(frame, "Usuario no valido o contraseña incorrecta" ,"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void mostrarVentana() {
        frame.setVisible(true);
    }
}
