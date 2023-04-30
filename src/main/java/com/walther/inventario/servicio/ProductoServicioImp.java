package com.walther.inventario.servicio;

import com.walther.inventario.entidad.Producto;
import com.walther.inventario.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductoServicioImp implements ProductoServicio{
    @Autowired
    public ProductoRepositorio productoRepositorio;
    @Override
    public Page<Producto> listarProductos(Pageable pageable) {
        return productoRepositorio.findAll(pageable);
    }
    @Override
    public List<Producto> listarTodosProductos() {
        return productoRepositorio.findAll() ;
    }
    @Override
    public Producto obtenerProductoPorId(int id) {
        return productoRepositorio.findById(id).get();
    }
    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepositorio.save(producto);
    }
    @Override
    public Producto actualizarProducto(Producto producto) {
        return productoRepositorio.save(producto);
    }
    @Override
    public void eliminarProductoPorId(int id) {
        productoRepositorio.deleteById(id);
    }
    @Override
    public long countProductos() {
        return productoRepositorio.count();
    }
}
