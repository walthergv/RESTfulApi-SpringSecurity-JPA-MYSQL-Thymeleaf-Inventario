package com.walther.inventario.controlador;

import com.walther.inventario.entidad.Categoria;
import com.walther.inventario.servicio.CategoriaServicio;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class CategoriaControlador {
    @Autowired
    private CategoriaServicio categoriaServicio;
/*
    @GetMapping("/categorias")
    public List<Categoria> listarCategorias(){
        return categoriaServicio.listarCategorias();
    }
 */
    @GetMapping("/categorias")
    public String vistaListaCategorias(@RequestParam(name = "page", defaultValue = "0") int page, Model model, HttpServletRequest request){
        Pageable pageable = PageRequest.of(page, 6);
        Page<Categoria> categorias = categoriaServicio.listarCategorias(pageable);
        model.addAttribute("categorias", categorias);
        model.addAttribute("currentPage", categorias.getNumber());
        model.addAttribute("totalPages", categorias.getTotalPages());
        model.addAttribute("url", request.getRequestURI());

        return "listado-categorias";
    }

    @GetMapping("/categorias/crear")
    public String mostrarFormularioCrearCategoria(Model model){
        Categoria categoria = new Categoria();
        model.addAttribute("categoria", categoria);
        return "crear-categoria";
    }
    @PostMapping("/categorias")
    public String crearCategoria(@ModelAttribute("categoria") Categoria categoria){
        categoriaServicio.guardarCategoria(categoria);
        return "redirect:/categorias";
    }
    @GetMapping("/categorias/editar/{id}")
    public String mostrarFormEditarCategoria(@PathVariable int id, Model model){
        model.addAttribute("categoria", categoriaServicio.obtenerCategoriaPorId(id));
        return "editar-categoria" ;
    }
    @PostMapping("/categorias/{id}")
    public String actualizarCategoria(@PathVariable int id, @ModelAttribute("categoria") Categoria categoria){
        Categoria categoria1 = categoriaServicio.obtenerCategoriaPorId(id);
        categoria1.setId(id);
        categoria1.setNombre(categoria.getNombre());
        categoriaServicio.actualizarCategoria(categoria1);
        return "redirect:/categorias";
    }
    @GetMapping("/categorias/{id}")
    public String eliminarCategoria(@PathVariable int id){
        categoriaServicio.eliminarCategoriaPorId(id);
        return "redirect:/categorias";
    }

    @GetMapping("/categorias/buscar")
    public String buscarCategoriasPorNombre(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "nombre") String nombre, Model model, HttpServletRequest request){
        Pageable pageable = PageRequest.of(page, 6);
        Page<Categoria> categorias = categoriaServicio.buscarCategoriasPorNombre(nombre, pageable);
        model.addAttribute("categorias", categorias);
        model.addAttribute("currentPage", categorias.getNumber());
        model.addAttribute("totalPages", categorias.getTotalPages());
        model.addAttribute("url", request.getRequestURI());
        return "listado-categorias";
    }

/*
    @PostMapping("/categorias/crear")
    public Categoria crearCategoria(@RequestBody Categoria categoria){
        return categoriaServicio.guardarCategoria(categoria);
    }
 */
}
