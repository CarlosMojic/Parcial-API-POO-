package com.catolica.edu.sv.juegostienda.serviceimpl;

import com.catolica.edu.sv.juegostienda.model.Categoria;
import com.catolica.edu.sv.juegostienda.repository.CategoriaRepository;
import com.catolica.edu.sv.juegostienda.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> findById(Integer id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria update(Integer id, Categoria categoria) {
        Optional<Categoria> op = categoriaRepository.findById(id);
        if (!op.isPresent()) {
            return null;
        }
        Categoria existente = op.get();
        existente.setNombre(categoria.getNombre());
        return categoriaRepository.save(existente);
    }

    @Override
    public void delete(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
