package com.walther.inventario.repositorio;

import com.walther.inventario.entidad.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepositorio extends JpaRepository<Proveedor, Integer> {
}
