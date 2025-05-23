package controlador;

import modelo.Cliente;
import modelo.ClienteServicio;
import vista.FormularioCliente;
import vista.VentanaPrincipal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controlador principal que maneja la lógica de la aplicación y
 * coordina la interacción entre la vista y el modelo.
 */
public class VentanaPrincipalControlador implements ActionListener {
    /** Referencia a la ventana principal */
    private final VentanaPrincipal vista;
    /** Servicio que maneja las operaciones con clientes */
    private final ClienteServicio servicio;
    /** Modelo de la tabla que muestra los clientes */
    private final DefaultTableModel modeloTabla;

    /**
     * Constructor del controlador.
     * @param vista ventana principal de la aplicación
     * @param modeloTabla modelo de datos de la tabla de clientes
     */
    public VentanaPrincipalControlador(VentanaPrincipal vista, DefaultTableModel modeloTabla) {
        this.vista = vista;
        this.servicio = new ClienteServicio();
        this.modeloTabla = modeloTabla;
    }

    /**
     * Maneja los eventos de los botones de la interfaz.
     * @param e evento de acción recibido
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        
        switch (comando) {
            case "Agregar Cliente" -> manejarAgregarCliente();
            case "Editar Cliente" -> manejarEditarCliente();
            case "Eliminar Cliente" -> manejarEliminarCliente();
        }
    }

    /**
     * Maneja la acción de agregar un nuevo cliente.
     * Muestra el formulario de cliente y procesa los datos ingresados.
     */
    private void manejarAgregarCliente() {
        FormularioCliente formulario = new FormularioCliente(vista, true);
        formulario.setVisible(true);
        if (formulario.getCliente() != null) {
            servicio.crearCliente(formulario.getCliente());
            actualizarTabla();
        }
    }

    /**
     * Maneja la acción de editar un cliente existente.
     * Solicita la cédula del cliente y muestra el formulario con sus datos.
     */
    private void manejarEditarCliente() {
        String cedulaStr = JOptionPane.showInputDialog(vista, "Ingrese la cédula del cliente a editar:");
        if (cedulaStr != null && !cedulaStr.trim().isEmpty()) {
            try {
                int cedula = Integer.parseInt(cedulaStr);
                ArrayList<Cliente> clientes = servicio.leerClientes();
                Cliente clienteEncontrado = null;

                for (Cliente c : clientes) {
                    if (c.getCedula() == cedula) {
                        clienteEncontrado = c;
                        break;
                    }
                }

                if (clienteEncontrado != null) {
                    FormularioCliente formulario = new FormularioCliente(vista, true, clienteEncontrado);
                    formulario.setVisible(true);

                    if (formulario.getCliente() != null) {
                        servicio.actualizarCliente(formulario.getCliente());
                        actualizarTabla();
                    }
                } else {
                    JOptionPane.showMessageDialog(vista, "No se encontró un cliente con la cédula ingresada");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Por favor, ingrese un número de cédula válido");
            }
        }
    }

    /**
     * Maneja la acción de eliminar un cliente.
     * Solicita la cédula del cliente y confirma la eliminación.
     */
    private void manejarEliminarCliente() {
        String cedulaStr = JOptionPane.showInputDialog(vista, "Ingrese la cédula del cliente a eliminar:");
        if (cedulaStr != null && !cedulaStr.trim().isEmpty()) {
            try {
                int cedula = Integer.parseInt(cedulaStr);
                ArrayList<Cliente> clientes = servicio.leerClientes();
                boolean clienteExiste = false;

                for (Cliente c : clientes) {
                    if (c.getCedula() == cedula) {
                        clienteExiste = true;
                        break;
                    }
                }

                if (clienteExiste) {
                    int confirmacion = JOptionPane.showConfirmDialog(vista,
                            "¿Está seguro de eliminar este cliente?",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION);

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        if (servicio.eliminarCliente(cedula)) {
                            actualizarTabla();
                            JOptionPane.showMessageDialog(vista, "Cliente eliminado con éxito");
                        } else {
                            JOptionPane.showMessageDialog(vista, "No se pudo eliminar el cliente");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(vista, "No se encontró un cliente con la cédula ingresada");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Por favor, ingrese un número de cédula válido");
            }
        }
    }

    /**
     * Actualiza la tabla de clientes con los datos más recientes.
     */
    public void actualizarTabla() {
        modeloTabla.setRowCount(0);
        ArrayList<Cliente> clientes = servicio.leerClientes();
        for (Cliente cliente : clientes) {
            Object[] fila = {
                cliente.getCedula(),
                cliente.getNombre(),
                cliente.getTelefono()
            };
            modeloTabla.addRow(fila);
        }
    }
}