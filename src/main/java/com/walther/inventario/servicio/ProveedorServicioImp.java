package com.walther.inventario.servicio;

import com.walther.inventario.entidad.Proveedor;
import com.walther.inventario.repositorio.ProveedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProveedorServicioImp implements ProveedorServicio{
    @Autowired
    ProveedorRepositorio proveedorRepositorio;
    @Override
    public Page<Proveedor> listarProveedores(Pageable pageable) {
        return proveedorRepositorio.findAll(pageable);
    }
    @Override
    public List<Proveedor> listarTodosProveedores() {
        return proveedorRepositorio.findAll();
    }
    @Override
    public Proveedor obtenerProveedorPorId(int id) {
        return proveedorRepositorio.findById(id).orElseThrow();
    }

    @Override
    public Proveedor guardarProveedor(Proveedor proveedor) {
        return proveedorRepositorio.save(proveedor);
    }

    @Override
    public Proveedor actualizarProveedor(Proveedor proveedor) {
        return proveedorRepositorio.save(proveedor);
    }

    @Override
    public void eliminarProveedorPorId(int id) {
    proveedorRepositorio.deleteById(id);
    }

    @Override
    public long countProveedor() {
        return proveedorRepositorio.count();
    }
}
