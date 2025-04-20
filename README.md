# Sistema de Gestión de Clientes (CRUD)

Este proyecto es una aplicación de escritorio desarrollada en Java que implementa un CRUD (Create, Read, Update, Delete) para la gestión de clientes, utilizando el patrón de arquitectura MVC (Modelo-Vista-Controlador) y almacenamiento en archivos CSV.

<img src="https://media.licdn.com/dms/image/v2/D4E2DAQEPZ4E5Qh11Ig/profile-treasury-image-shrink_800_800/B4EZZEpxD6HEAY-/0/1744908522124?e=1745514000&v=beta&t=6XfOI1QI4CcnPhjeT-M6G6G3AAF84q9twzEjXkixnFA" width="100%" height="500" alt="App"/>

## Características

- Interfaz gráfica intuitiva desarrollada con Swing
- Operaciones CRUD completas:
  - Crear nuevos clientes
  - Visualizar lista de clientes
  - Actualizar información de clientes
  - Eliminar clientes
- Almacenamiento persistente en archivo CSV
- Arquitectura MVC para mejor organización del código

## Estructura del Proyecto

```
src/
├── modelo/
│   ├── Cliente.java         # Clase modelo para los datos del cliente
│   └── ClienteServicio.java # Servicio para operaciones CRUD
├── vista/
│   ├── VentanaPrincipal.java   # Ventana principal de la aplicación
│   └── FormularioCliente.java  # Formulario para crear/editar clientes
└── controlador/
    └── VentanaPrincipalControlador.java # Controlador principal
```

## Requisitos

- Java JDK 8 o superior
- NetBeans IDE (recomendado para desarrollo)

## Instalación

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/lozadandres/Crud-en-Java-MVC.git
   ```
2. Abrir el proyecto en NetBeans
3. Compilar y ejecutar el proyecto

## Uso

1. Al iniciar la aplicación, se muestra la ventana principal con la lista de clientes
2. Utilizar los botones:
   - "Agregar Cliente": Para crear un nuevo registro
   - "Editar Cliente": Para modificar un cliente seleccionado
   - "Eliminar Cliente": Para eliminar un cliente seleccionado

## Características Técnicas

- Implementación del patrón MVC
- Manejo de archivos para persistencia de datos
- Interfaz gráfica con Java Swing
- Validación de datos de entrada
- Manejo de excepciones

## Contribuir

Si deseas contribuir al proyecto:

1. Haz un Fork del repositorio
2. Crea una nueva rama para tus cambios
3. Envía un Pull Request

## Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo LICENSE para más detalles.
