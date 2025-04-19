package vista;

import controlador.VentanaPrincipalControlador;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Ventana principal de la aplicación que muestra la lista de clientes
 * y proporciona botones para realizar operaciones CRUD.
 */
public class VentanaPrincipal extends JFrame {
    /** Tabla que muestra la lista de clientes */
    private final JTable tablaClientes;
    /** Modelo de datos para la tabla de clientes */
    private final DefaultTableModel modeloTabla;
    /** Botón para agregar nuevos clientes */
    private final JButton btnAgregar;
    /** Botón para editar clientes existentes */
    private final JButton btnEditar;
    /** Botón para eliminar clientes */
    private final JButton btnEliminar;
    /** Controlador que maneja los eventos de la ventana */
    private final VentanaPrincipalControlador controlador;

    /**
     * Constructor de la ventana principal.
     * Inicializa y configura todos los componentes de la interfaz.
     */
    public VentanaPrincipal() {
        // Configuración de la ventana
        setTitle("Gestión de Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        
        // Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        
        // Configuración de la tabla
        String[] columnas = {"Cédula", "Nombre", "Teléfono"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaClientes = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaClientes);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel panelBotones = new JPanel();
        btnAgregar = new JButton("Agregar Cliente");
        btnEditar = new JButton("Editar Cliente");
        btnEliminar = new JButton("Eliminar Cliente");
        
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        // Agregar panel a la ventana
        add(panel);
        
        // Inicializar controlador
        controlador = new VentanaPrincipalControlador(this, modeloTabla);
        
        // Configurar eventos de botones
        configurarEventos();
        
        // Cargar datos iniciales
        controlador.actualizarTabla();
    }
    
    /**
     * Configura los eventos de los botones asociándolos al controlador.
     */
    private void configurarEventos() {
        btnAgregar.addActionListener(controlador);
        btnEditar.addActionListener(controlador);
        btnEliminar.addActionListener(controlador);
    }
}