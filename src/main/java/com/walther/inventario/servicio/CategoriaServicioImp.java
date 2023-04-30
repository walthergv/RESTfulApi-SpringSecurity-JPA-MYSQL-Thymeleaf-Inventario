package com.walther.inventario.servicio;

import com.walther.inventario.entidad.Categoria;
import com.walther.inventario.repositorio.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoriaServicioImp implements CategoriaServicio{
    @Autowired
    private CategoriaRepositorio categoriaRepositorio;
    @Override
    public List<Categoria> listarTodasCategorias() {
        return categoriaRepositorio.findAll();
    }
    @Override
    public Page<Categoria> listarCategorias(Pageable pageable) {
        return categoriaRepositorio.findAll(pageable);
    }
    @Override
    public Categoria obtenerCategoriaPorId(int id) {
        return categoriaRepositorio.findById(id).orElseThrow();
    }
    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepositorio.save(categoria);
    }
    @Override
    public Categoria actualizarCategoria(Categoria categoria) {
        return categoriaRepositorio.save(categoria);
    }

    @Override
    public void eliminarCategoriaPorId(int id) {
        categoriaRepositorio.deleteById(id);
    }

    @Override
    public long countCategorias() {
        return categoriaRepositorio.count();
    }
}
