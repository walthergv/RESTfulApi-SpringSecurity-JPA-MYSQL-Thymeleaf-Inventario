package com.walther.inventario.servicio;

import com.walther.inventario.entidad.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductoServicio {
    public Page<Producto> listarProductos(Pageable pageable);
    public List<Producto> listarTodosProductos();
    public Producto obtenerProductoPorId(int id);
    public Producto guardarProducto(Producto producto);
    public Producto actualizarProducto(Producto producto);
    public void eliminarProductoPorId(int id);
    public long countProductos();
}
