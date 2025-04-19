package modelo;

/**
 * Clase que representa la entidad Cliente en el sistema.
 * Contiene la información básica de un cliente: cédula, nombre y teléfono.
 */
public class Cliente {
    /** Número de cédula que identifica al cliente */
    private int cedula;
    /** Nombre completo del cliente */
    private String nombre;
    /** Número de teléfono del cliente */
    private int telefono;
  
    /**
     * Constructor por defecto de la clase Cliente.
     */
    public Cliente(){      
    }
    
    /**
     * Obtiene el número de cédula del cliente.
     * @return número de cédula
     */
    public int getCedula() {
        return cedula;
    }

    /**
     * Obtiene el nombre del cliente.
     * @return nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el número de teléfono del cliente.
     * @return número de teléfono
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de cédula del cliente.
     * @param cedula nuevo número de cédula
     */
    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    /**
     * Establece el nombre del cliente.
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el número de teléfono del cliente.
     * @param telefono nuevo número de teléfono
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * Retorna una representación en cadena del cliente.
     * @return cadena con los datos del cliente
     */
    @Override
    public String toString() {
        return "Cliente{" + "cedula=" + cedula + ", nombre=" + nombre + ", telefono=" + telefono + "}";
    }
}