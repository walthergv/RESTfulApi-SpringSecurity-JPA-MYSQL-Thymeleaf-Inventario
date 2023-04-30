package com.walther.inventario.controlador;

import com.walther.inventario.servicio.CategoriaServicio;
import com.walther.inventario.servicio.ProductoServicio;
import com.walther.inventario.servicio.ProveedorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeControlador {
    @Autowired
    CategoriaServicio categoriaServicio;
    @Autowired
    ProductoServicio productoServicio;
    @Autowired
    ProveedorServicio proveedorServicio;
    @GetMapping("/home")
    public String viewHome(Model model){
        model.addAttribute("countProductos", productoServicio.countProductos());
        model.addAttribute("countCategorias", categoriaServicio.countCategorias());
        model.addAttribute("countProveedores", proveedorServicio.countProveedor());
        return "home";
    }
}
