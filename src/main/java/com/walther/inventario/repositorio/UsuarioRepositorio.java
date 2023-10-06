package com.walther.inventario.repositorio;

import com.walther.inventario.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
    public Usuario findByEmail(String email);
}
