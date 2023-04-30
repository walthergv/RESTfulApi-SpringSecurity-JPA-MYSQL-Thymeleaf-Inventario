package com.walther.inventario.controlador;

import com.walther.inventario.entidad.Categoria;
import com.walther.inventario.entidad.Producto;
import com.walther.inventario.entidad.Proveedor;
import com.walther.inventario.servicio.CategoriaServicio;
import com.walther.inventario.servicio.ProductoServicio;
import com.walther.inventario.servicio.ProveedorServicio;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductoControlador {
    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private CategoriaServicio categoriaServicio;
    @Autowired
    private ProveedorServicio proveedorServicio;
    /*
    @GetMapping("/productos")
    public List<Producto> listarProductos(){
        return productoServicio.productoList();
    }
     */
    @GetMapping("/productos")
    public String vistaListaProductos(@RequestParam(name = "page", defaultValue = "0") int page, Model model, HttpServletRequest request){
        Pageable pageable = PageRequest.of(page, 6);
        Page<Producto> productos = productoServicio.listarProductos(pageable);
        model.addAttribute("productos", productos);
        model.addAttribute("currentPage", productos.getNumber());
        model.addAttribute("totalPages", productos.getTotalPages());
        model.addAttribute("url", request.getRequestURI());
        return "listado-productos";
    }
    /*
    @PostMapping("/productos/crear")
    public Producto crearProducto(@RequestBody Map<String, Object> requestBody) {
        String nombre = (String) requestBody.get("nombre");
        String descripcion = (String) requestBody.get("descripcion");
        Float precioCompra = Float.parseFloat(requestBody.get("precioCompra").toString());
        Float precioVenta = Float.parseFloat(requestBody.get("precioVenta").toString());
        Integer categoriaId = Integer.parseInt(requestBody.get("categoriaId").toString());

        Categoria categoria = categoriaServicio.obtenerCategoriaPorId(categoriaId);

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecioCompra(precioCompra);
        producto.setPrecioVenta(precioVenta);
        producto.setCategoria(categoria);

        return productoServicio.guardarProducto(producto);
    }
     */

    @GetMapping("/productos/crear")
    public String mostrarFormularioCrearProducto(Model model) {
        List<Categoria> categorias = categoriaServicio.listarTodasCategorias();
        model.addAttribute("categorias", categorias);
        List<Proveedor> proveedores = proveedorServicio.listarTodosProveedores();
        model.addAttribute("proveedores", proveedores);
        model.addAttribute("producto", new Producto());

        return "crear-producto";
    }
/*
    @GetMapping("/productos/crear")
    public String mostrarFormularioCrearProducto(Model model) {
        Producto producto = new Producto();
        producto.setCategoria(new Categoria());
        producto.setProveedor(new Proveedor());
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaServicio.listarCategorias());
        model.addAttribute("proveedores", proveedorServicio.listarProveedor());
        return "crear-producto";
    }

 */
    /*
    @PostMapping("/productos")
    public String crearProducto(@ModelAttribute Producto producto) {
        Categoria categoria = categoriaServicio.obtenerCategoriaPorId(producto.getCategoria().getId());
        Proveedor proveedor = proveedorServicio.obtenerProveedorPorId(producto.getProveedor().getId());
        producto.setCategoria(categoria);
        producto.setProveedor(proveedor);
        productoServicio.guardarProducto(producto);
        return "redirect:/productos";
    }

     */

    @PostMapping("/productos")
    public String crearProducto(@RequestParam("nombre") String nombre,
                                @RequestParam("descripcion") String descripcion,
                                @RequestParam("stock") Float stock,
                                @RequestParam("precioCompra") Float precioCompra,
                                @RequestParam("precioVenta") Float precioVenta,
                                @RequestParam("categoriaId") Integer categoriaId,
                                @RequestParam("proveedorId") Integer proveedorId)

    {

        Categoria categoria = categoriaServicio.obtenerCategoriaPorId(categoriaId);
        Proveedor proveedor = proveedorServicio.obtenerProveedorPorId(proveedorId);

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setStock(stock);
        producto.setDescripcion(descripcion);
        producto.setPrecioCompra(precioCompra);
        producto.setPrecioVenta(precioVenta);
        producto.setCategoria(categoria);
        producto.setProveedor(proveedor);

        productoServicio.guardarProducto(producto);

        return "redirect:/productos";
    }

    @GetMapping("/productos/editar/{id}")
    public String mostrarFormEditarProducto(@PathVariable int id, Model model){
        model.addAttribute("producto", productoServicio.obtenerProductoPorId(id));
        model.addAttribute("categorias", categoriaServicio.listarTodasCategorias());
        model.addAttribute("proveedores", proveedorServicio.listarTodosProveedores());
        return "editar-producto";
    }

    @PostMapping("/productos/{id}")
    public String actuaizarProducto(@PathVariable int id, @ModelAttribute("producto") Producto producto, Model model){
        Producto producto1 = productoServicio.obtenerProductoPorId(id);
        producto1.setId(id);
        producto1.setNombre(producto.getNombre());
        producto1.setDescripcion(producto.getDescripcion());
        producto1.setStock(producto.getStock());
        producto1.setPrecioCompra(producto.getPrecioCompra());
        producto1.setPrecioVenta(producto.getPrecioVenta());
        producto1.setCategoria(producto.getCategoria());
        producto1.setProveedor(producto.getProveedor());

        productoServicio.actualizarProducto(producto1);
        return "redirect:/productos";
    }
    @GetMapping("/productos/{id}")
    public String eliminarProducto(@PathVariable int id){
        productoServicio.eliminarProductoPorId(id);
        return "redirect:/productos";
    }
}
