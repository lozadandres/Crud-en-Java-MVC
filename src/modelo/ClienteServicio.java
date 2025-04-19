package modelo;

import java.io.*;
import java.util.ArrayList;

/**
 * Clase que gestiona las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * de clientes utilizando un archivo CSV como almacenamiento persistente.
 */
public class ClienteServicio {
    /** Ruta del archivo CSV donde se almacenan los clientes */
    private final String RUTA_CSV="Clientes.csv";
    /** Carácter separador usado en el archivo CSV */
    private final String SEPARADOR=";";
    
    /**
     * Constructor por defecto de la clase ClienteServicio.
     */
    public ClienteServicio(){}
    
    /**
     * Crea un nuevo cliente y lo almacena en el archivo CSV.
     * @param cliente objeto Cliente a guardar
     */
    public void crearCliente(Cliente cliente){
        try(FileWriter fw=new FileWriter(RUTA_CSV,true);
            BufferedWriter bw =new BufferedWriter(fw)){
            String renglon=cliente.getCedula()+SEPARADOR
                           +cliente.getNombre()+SEPARADOR
                           +cliente.getTelefono();
            bw.write(renglon);
            bw.newLine();
            System.out.println("Cliente guardado con exito");
        }catch (IOException ex) {
            System.out.println("Error al guardar el cliente");      
        } 
    }

    /**
     * Lee todos los clientes almacenados en el archivo CSV.
     * @return ArrayList con todos los clientes encontrados
     */
    public ArrayList<Cliente> leerClientes(){
        ArrayList<Cliente> listaClientes=new ArrayList<>(); 
        File archivo = new File(RUTA_CSV);
        if(!archivo.exists()){
            System.out.println("este archivo no existe");
            return listaClientes;
        }
        try(FileReader fr = new FileReader(archivo);
              BufferedReader br =new BufferedReader(fr)){
            System.out.println("Listado de Clientes");
            String linea;   
            while((linea=br.readLine())!=null){
                String[] campos=linea.split(SEPARADOR);
                Cliente cliente=new Cliente();
                cliente.setCedula(Integer.parseInt(campos[0]));
                cliente.setNombre(campos[1]);
                cliente.setTelefono(Integer.parseInt(campos[2]));
                listaClientes.add(cliente);
            }
        }catch (IOException ex) {
            System.out.println("este archivo no se pudo leer");
        }   
        return listaClientes;
    }
    
    /**
     * Actualiza los datos de un cliente existente.
     * @param cliente objeto Cliente con los nuevos datos
     * @return true si se actualizó correctamente, false en caso contrario
     */
    public boolean actualizarCliente(Cliente cliente){
        ArrayList<Cliente> listaClientes=leerClientes();
        boolean existeCliente=false;
        for(Cliente c: listaClientes){
               if(c.getCedula()==cliente.getCedula()){
                   c.setCedula(cliente.getCedula());
                   c.setNombre(cliente.getNombre());
                   c.setTelefono(cliente.getTelefono());
               existeCliente=true;
               }
            }
        if(existeCliente){
            return sobreEscribirArchivo(listaClientes);
        }else{
            System.out.println("Cliente no encontrado");
            return false;
        }
    }

    /**
     * Elimina un cliente del archivo CSV según su número de cédula.
     * @param cedula número de cédula del cliente a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean eliminarCliente(int cedula){
        ArrayList<Cliente> listaClientes=leerClientes();
        boolean existeCliente=false;
        existeCliente=listaClientes.removeIf(cliente -> cliente.getCedula()==cedula);
        if(existeCliente)
            return sobreEscribirArchivo(listaClientes);
        else{
            System.out.println("Cliente no encontrado");
            return false;
        }
    }

    /**
     * Método privado que sobreescribe todo el archivo CSV con la lista actualizada de clientes.
     * @param listaClientes lista actualizada de clientes
     * @return true si se escribió correctamente, false en caso contrario
     */
    private boolean sobreEscribirArchivo(ArrayList<Cliente> listaClientes){
        try(FileWriter fw=new FileWriter(RUTA_CSV);
            BufferedWriter bw =new BufferedWriter(fw)){
            for(Cliente cliente : listaClientes){
                String renglon=cliente.getCedula()+SEPARADOR
                               +cliente.getNombre()+SEPARADOR
                               +cliente.getTelefono();
                bw.write(renglon);
                bw.newLine();
            }
        }catch (IOException ex) {      
            return false;
        }
        return true;
    }
}