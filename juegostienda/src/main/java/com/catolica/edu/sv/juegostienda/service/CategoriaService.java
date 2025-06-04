package com.catolica.edu.sv.juegostienda.service;

import com.catolica.edu.sv.juegostienda.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    List<Categoria> findAll();

    Optional<Categoria> findById(Integer id);

    Categoria save(Categoria categoria);

    Categoria update(Integer id, Categoria categoria);

    void delete(Integer id);
}

