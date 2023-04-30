package com.walther.inventario.servicio;

import com.walther.inventario.entidad.Proveedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProveedorServicio {
    public Page<Proveedor> listarProveedores(Pageable pageable);
    public List<Proveedor> listarTodosProveedores();
    public Proveedor obtenerProveedorPorId(int id);
    public Proveedor guardarProveedor(Proveedor proveedor);
    public Proveedor actualizarProveedor(Proveedor proveedor);
    public void eliminarProveedorPorId(int id);
    public long countProveedor();
}
