package com.walther.inventario.servicio;

import com.walther.inventario.entidad.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioServicio extends UserDetailsService {
    public Usuario save(Usuario usuario);
}
