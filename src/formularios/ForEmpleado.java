/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package formularios;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Empleados.Empleados;
import Empleados.GestorEmpleado;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ForEmpleado extends javax.swing.JFrame {

	private DefaultTableModel modeloTabla;

	public ForEmpleado() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		tabla = new javax.swing.JTable();
		nombreE = new javax.swing.JTextField();
		apellidoE = new javax.swing.JTextField();
		comision = new javax.swing.JTextField();
		comision.setEditable(false);
		agregar = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		eliminar = new javax.swing.JButton();
		jButton1 = new javax.swing.JButton();
		salir = new javax.swing.JButton();

		modeloTabla = new DefaultTableModel();
		Object[][] filas = {};
		Object[] columnas = { "nombre", "apellido", "totalComision", "id" };
		modeloTabla = new DefaultTableModel(filas, columnas);

		tabla.setModel(modeloTabla);

		buscarTabla();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jScrollPane1.setViewportView(tabla);

		nombreE.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nombreEActionPerformed(evt);
			}
		});

		apellidoE.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				apellidoEActionPerformed(evt);
			}
		});

		comision.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comisionActionPerformed(evt);
			}
		});

		agregar.setText("agregar");
		agregar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				agregarActionPerformed(evt);
			}
		});

		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel1.setText("nombre");

		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel2.setText("apellido");

		jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel3.setText("comision");

		jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel4.setText("EMPLEADOS");

		eliminar.setText("eliminar");
		eliminar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				eliminarActionPerformed(evt);
			}
		});

		jButton1.setText("agendar");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		salir.setText("salir");
		salir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				salirActionPerformed(evt);
			}
		});

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (filaEditar != -1) {
					String nuevoNombre = nombreE.getText();
					String nuevoApellido = apellidoE.getText();
					String comisionStr = comision.getText();

					if (nuevoNombre.isEmpty() || nuevoApellido.isEmpty() || comisionStr.isEmpty()) {
						JOptionPane.showMessageDialog(ForEmpleado.this, "Por favor, completa todos los campos");
					} else {
						try {
							Double nuevaComision = Double.parseDouble(comisionStr);

							int idEmpleado = Integer.parseInt(modeloTabla.getValueAt(filaEditar, 3).toString());

							GestorEmpleado gestorEmpleado = new GestorEmpleado();
							boolean actualizacionExitosa = gestorEmpleado.actualizarEmpleado(idEmpleado, nuevoNombre,
									nuevoApellido, nuevaComision);

							if (actualizacionExitosa) {
								buscarTabla();
								JOptionPane.showMessageDialog(ForEmpleado.this,
										"Se actualizó el registro en la base de datos");
							} else {
								JOptionPane.showMessageDialog(ForEmpleado.this,
										"Error al actualizar en la base de datos");
							}
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(ForEmpleado.this, "Ingrese un valor válido para la comisión");
						}
					}
				} else {
					JOptionPane.showMessageDialog(ForEmpleado.this, "Selecciona una fila para editar");
				}
			}
		});
		
		lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblid = new JLabel(" ");
		lblid.setHorizontalAlignment(SwingConstants.CENTER);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createSequentialGroup()
					.addGap(39)
					.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(layout.createSequentialGroup()
							.addComponent(nombreE, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(apellidoE, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addGap(41))
						.addGroup(layout.createSequentialGroup()
							.addComponent(jLabel1)
							.addGap(45)
							.addComponent(jLabel2)
							.addGap(49)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(comision, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(lblid, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addGap(18, 18, Short.MAX_VALUE)
							.addComponent(btnEditar))
						.addGroup(layout.createSequentialGroup()
							.addComponent(jLabel3)
							.addGap(28)
							.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(4)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(agregar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(eliminar)
					.addGap(52))
				.addGroup(layout.createSequentialGroup()
					.addComponent(jButton1)
					.addGap(206)
					.addComponent(jLabel4)
					.addPreferredGap(ComponentPlacement.RELATED, 217, Short.MAX_VALUE)
					.addComponent(salir))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGap(19)
							.addComponent(jLabel4))
						.addComponent(jButton1)
						.addComponent(salir))
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jLabel1)
						.addComponent(jLabel2)
						.addComponent(jLabel3)
						.addComponent(lblId))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(nombreE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(apellidoE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comision, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(eliminar)
						.addComponent(agregar)
						.addComponent(btnEditar)
						.addComponent(lblid))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		getContentPane().setLayout(layout);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void comisionActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_comisionActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_comisionActionPerformed

	private void agregarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_agregarActionPerformed
		String nombre = nombreE.getText();
		String apellido = apellidoE.getText();
		Double comi = 0.0;
		if (nombre.isEmpty() || apellido.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos");
		} else {

			Empleados nuevoEmpleado = new Empleados(nombre, apellido, comi);

			GestorEmpleado gestorEmpleado = new GestorEmpleado();
			boolean insercionExitosa = gestorEmpleado.insertarEmpleado(nuevoEmpleado);

			if (insercionExitosa) {
				modeloTabla.addRow(new Object[] { nombre, apellido, comi });
				buscarTabla();
				JOptionPane.showMessageDialog(this, "Se agregó el empleado: " + nombre);
				nombreE.setText("");
				apellidoE.setText("");
				comision.setText("");
				lblid.setText("");
			} else {
				JOptionPane.showMessageDialog(this, "Error al agregar el empleado: " + nombre);
			}
		}
	}// GEN-LAST:event_agregarActionPerformed

	private void nombreEActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_nombreEActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_nombreEActionPerformed

	private void apellidoEActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_apellidoEActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_apellidoEActionPerformed

	private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_eliminarActionPerformed
		if (filaEditar != -1) {
			int idEmpleado = Integer.parseInt(modeloTabla.getValueAt(filaEditar, 3).toString());

			GestorEmpleado gestorEmpleado = new GestorEmpleado();
			boolean eliminacionExitosa = gestorEmpleado.eliminarEmpleado(idEmpleado);

			if (eliminacionExitosa) {
				buscarTabla();
				JOptionPane.showMessageDialog(this, "Se eliminó el empleado con ID: " + idEmpleado);
				nombreE.setText("");
				apellidoE.setText("");
				comision.setText("");
				lblid.setText("");
			} else {
				JOptionPane.showMessageDialog(this, "Error al eliminar el empleado con ID: " + idEmpleado);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Selecciona una fila para eliminar");
		}
	}// GEN-LAST:event_eliminarActionPerformed

	private void salirActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_salirActionPerformed

		this.dispose();
	}// GEN-LAST:event_salirActionPerformed

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		agendarCitas Cita = new agendarCitas();

		Cita.setVisible(true);
		Cita.setLocationRelativeTo(null);
		this.dispose();
	}// GEN-LAST:event_jButton1ActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		ForEmpleado n = new ForEmpleado();
		n.setVisible(true);
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton agregar;
	private javax.swing.JTextField apellidoE;
	private javax.swing.JTextField comision;
	private javax.swing.JButton eliminar;
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextField nombreE;
	private javax.swing.JButton salir;
	private javax.swing.JTable tabla;
	private int filaEditar = -1;
	private JLabel lblId;
	private JLabel lblid;
	// End of variables declaration//GEN-END:variables

	private void buscarTabla() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root",
					"qwerty");
			Statement statement = conexion.createStatement();

			ResultSet resultSet = statement.executeQuery("SELECT nombre, apellido, totalComision, id FROM empleado");

			modeloTabla.setRowCount(0);

			while (resultSet.next()) {
				String nombreE = resultSet.getString("nombre");
				String apellidoE = resultSet.getString("apellido");
				String comision = resultSet.getString("totalComision");
	            int idEmpleado = resultSet.getInt("id");
				modeloTabla.addRow(new Object[] { nombreE, apellidoE, comision, idEmpleado });
			}

			conexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filaEditar = tabla.getSelectedRow();
				if (filaEditar != -1) {
					String nombre = modeloTabla.getValueAt(filaEditar, 0).toString();
					String apellido = modeloTabla.getValueAt(filaEditar, 1).toString();
					String comi = modeloTabla.getValueAt(filaEditar, 2).toString();
					String ids = modeloTabla.getValueAt(filaEditar, 3).toString();

					nombreE.setText(nombre);
					apellidoE.setText(apellido);
					comision.setText(comi);
					lblid.setText(ids);
				}
			}
		});
	}
}
