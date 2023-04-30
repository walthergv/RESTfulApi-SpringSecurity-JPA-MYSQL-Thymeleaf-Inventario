package com.walther.inventario.controlador;

import com.walther.inventario.entidad.Proveedor;
import com.walther.inventario.servicio.ProveedorServicio;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProveedorControlador {
    @Autowired
    ProveedorServicio proveedorServicio;
    /*
    @GetMapping("/proveedores")
    public String vistaListaProveedores(Model model){
        model.addAttribute("proveedores", proveedorServicio.listarProveedor());
        return "listado-proveedores";
    }*/
    @GetMapping("/proveedores")
    public String vistaListaProveedores(@RequestParam(name = "page", defaultValue = "0") int page, Model model, HttpServletRequest request){
        Pageable pageable = PageRequest.of(page, 6);
        Page<Proveedor> proveedores = proveedorServicio.listarProveedores(pageable);
        model.addAttribute("proveedores", proveedores);
        model.addAttribute("currentPage", proveedores.getNumber());
        model.addAttribute("totalPages", proveedores.getTotalPages());
        model.addAttribute("url", request.getRequestURI());

        return "listado-proveedores";
    }
    @GetMapping("/proveedores/crear")
    public String mostrarFormularioCrearProveedores(Model model){
        Proveedor proveedor = new Proveedor();
        model.addAttribute("proveedor", proveedor);
        return "crear-proveedor";
    }
    @PostMapping("/proveedores")
    public String crearProveedores(@ModelAttribute("proveedor") Proveedor proveedor){
        proveedorServicio.guardarProveedor(proveedor);
        return "redirect:/proveedores";
    }
    @GetMapping("/proveedores/editar/{id}")
    public String mostrarFormEditarProveedor(@PathVariable int id, Model model){
        model.addAttribute("proveedor", proveedorServicio.obtenerProveedorPorId(id));
        return "editar-proveedor";
    }
    @PostMapping("/proveedor/{id}")
    public String actualizarProveedor(@PathVariable int id, @ModelAttribute("proveedor") Proveedor proveedor){
        Proveedor proveedor1 = proveedorServicio.obtenerProveedorPorId(id);
        proveedor1.setId(id);
        proveedor1.setNombre(proveedor.getNombre());
        proveedor1.setTelefono(proveedor.getTelefono());

        proveedorServicio.actualizarProveedor(proveedor1);
        return "redirect:/proveedores";
    }
    @GetMapping("/proveedores/{id}")
    public String eliminarProveedor(@PathVariable int id){
        proveedorServicio.eliminarProveedorPorId(id);
        return "redirect:/proveedores";
    }

}
