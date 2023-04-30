package com.walther.inventario.servicio;

import com.walther.inventario.entidad.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoriaServicio {
    public Page<Categoria> listarCategorias(Pageable pageable);
    public List<Categoria> listarTodasCategorias();
    public Categoria obtenerCategoriaPorId(int id);
    public Categoria guardarCategoria(Categoria categoria);
    public Categoria actualizarCategoria(Categoria categoria);
    public void eliminarCategoriaPorId(int id);
    public long countCategorias();
}
