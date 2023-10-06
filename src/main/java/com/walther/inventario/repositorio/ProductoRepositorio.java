package com.walther.inventario.repositorio;

import com.walther.inventario.entidad.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {
    Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
