# RESTfulApi-SpringBoot-Inventario
RESTful Api de gestion de inventario de productos.
- Sistema de Inicio de sesión y Registro de usuarios
- Buscar productos, categorias y proveedores registrados por sus nombres
- Lista productos, categorias y proveedores registrados.
- Mediante UI permite agregar, editar y eliminar informacion de las tablas. 
- Usa MySQL como gestor de base de datos

## Tecnologias
- Spring Boot | Spring Security | Thymeleaf | JPA | MYSQL

## Requerimientos

- Java JDK 17
- Spring Boot 3.0.5
- Maven 3.1.1, definida en .mvn/wrapper/maven-wrapper.properties
- MySQL 8.0.21

## Diagrama Entidad-Relacion

![Modelo Entidad-Relacion](https://github.com/walthergv/RESTfulApi-SpringBoot-Inventario/blob/master/screenshots/Diagrama%20Entidad-Relacion.jpg?raw=true)

- Muchos productos son ofrecidos por un proveedor
- Cada producto le pertenece a un proveedor

- Una categoria presenta muchos productos
- Cada producto le corresponde una categoria 

## Levantar proyecto:
### 1. Modificar las propiedades de la aplicacion
1.1.  Crear la base de datos, con el siguiente script SQL:
```sh
create database bd_inventario;
```
En el archivo ```src/main/resources/application.properties``` modificar las siguientes propiedades:
```sh 
    spring.datasource.url=jdbc:mysql://localhost:3306/bd_inventario
    spring.datasource.username=test
    spring.datasource.password=1234
    spring.jpa.hibernate.ddl-auto=none
    server.port=8091
```
1.2.  En "__username__" y "__password__" colocar sus credenciales de MySQL.

1.3.  Cambiar el valor a spring.jpa.hibernate.ddl-auto=__update__

1.4.  Cambiar el puerto al que deseen, siempre y cuando no este usado por otro servicio.

## 2. Correr la aplicacion
Correr la clase princial __InventarioApplication__ que contiene el metodo __main__

Abrir cualquier navegador e ingresar a la url: __localhost:8091/home__

# Screenshots
Algunas capturas del funcionamiento de la aplicacion.

![Registrate](https://github.com/walthergv/RESTfulApi-SpringBoot-Thymeleaf-Inventario/blob/master/screenshots/registrar.png?raw=true)
![Login](https://github.com/walthergv/RESTfulApi-SpringBoot-Thymeleaf-Inventario/blob/master/screenshots/login.png?raw=true)
![Pagina Principal](https://github.com/walthergv/RESTfulApi-SpringBoot-Inventario/blob/master/screenshots/home.png?raw=true)
![Listar Productos](https://github.com/walthergv/RESTfulApi-SpringBoot-Inventario/blob/master/screenshots/listarProductos.png?raw=true)
![Crear Producto](https://raw.githubusercontent.com/walthergv/RESTfulApi-SpringBoot-Inventario/master/screenshots/CrearProducto.png)
![Eliminar Categoria](https://github.com/walthergv/RESTfulApi-SpringBoot-Inventario/blob/master/screenshots/eliminarCategoria.png?raw=true)
![Editar Producto](https://github.com/walthergv/RESTfulApi-SpringBoot-Inventario/blob/master/screenshots/editarProducto.png?raw=true)
