package vista;

import modelo.Cliente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Formulario modal para agregar o editar datos de un cliente.
 * Se utiliza tanto para la creación como para la modificación de clientes.
 */
public class FormularioCliente extends JDialog {
    /** Campo para ingresar la cédula del cliente */
    private final JTextField txtCedula;
    /** Campo para ingresar el nombre del cliente */
    private final JTextField txtNombre;
    /** Campo para ingresar el teléfono del cliente */
    private final JTextField txtTelefono;
    /** Cliente que se está editando o creando */
    private Cliente cliente;
    
    /**
     * Constructor para crear un nuevo cliente.
     * @param parent ventana padre
     * @param modal indica si el diálogo es modal
     */
    public FormularioCliente(Frame parent, boolean modal) {
        this(parent, modal, null);
    }
    
    /**
     * Constructor para editar un cliente existente.
     * @param parent ventana padre
     * @param modal indica si el diálogo es modal
     * @param clienteExistente cliente a editar, null si es nuevo
     */
    public FormularioCliente(Frame parent, boolean modal, Cliente clienteExistente) {
        super(parent, modal);
        setTitle(clienteExistente == null ? "Agregar Cliente" : "Editar Cliente");
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setResizable(false);
        
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panel.add(new JLabel("Cédula:"));
        txtCedula = new JTextField();
        panel.add(txtCedula);
        
        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);
        
        panel.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panel.add(txtTelefono);
        
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        
        panel.add(btnGuardar);
        panel.add(btnCancelar);
        
        add(panel);
        
        if (clienteExistente != null) {
            txtCedula.setText(String.valueOf(clienteExistente.getCedula()));
            txtCedula.setEditable(false);
            txtNombre.setText(clienteExistente.getNombre());
            txtTelefono.setText(String.valueOf(clienteExistente.getTelefono()));
        }
        
        btnGuardar.addActionListener((ActionEvent e) -> {
            if (validarCampos()) {
                cliente = new Cliente();
                cliente.setCedula(Integer.parseInt(txtCedula.getText()));
                cliente.setNombre(txtNombre.getText());
                cliente.setTelefono(Integer.parseInt(txtTelefono.getText()));
                dispose();
            }
        });
        
        btnCancelar.addActionListener((ActionEvent e) -> {
            cliente = null;
            dispose();
        });
    }
    
    /**
     * Valida que todos los campos del formulario estén correctamente llenos.
     * @return true si los campos son válidos, false en caso contrario
     */
    private boolean validarCampos() {
        if (txtCedula.getText().trim().isEmpty() || 
            txtNombre.getText().trim().isEmpty() || 
            txtTelefono.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return false;
        }
        
        try {
            Integer.parseInt(txtCedula.getText());
            Integer.parseInt(txtTelefono.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cédula y el teléfono deben ser números");
            return false;
        }
        
        return true;
    }
    
    /**
     * Obtiene el cliente creado o editado en el formulario.
     * @return objeto Cliente con los datos ingresados, null si se canceló
     */
    public Cliente getCliente() {
        return cliente;
    }
}