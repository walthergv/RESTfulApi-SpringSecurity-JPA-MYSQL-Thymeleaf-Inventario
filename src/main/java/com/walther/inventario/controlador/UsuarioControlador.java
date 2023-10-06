package com.walther.inventario.controlador;

import com.walther.inventario.entidad.Usuario;
import com.walther.inventario.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registro")
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @ModelAttribute("usuario")
    public Usuario retornarNuevoUsuario(){
        return new Usuario();
    }

    @GetMapping
    public String mostrarFormularioRegistro(){
        return "registro";
    }

    @PostMapping
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario){
        usuarioServicio.save(usuario);
        return "redirect:/registro?success";
    }
}
