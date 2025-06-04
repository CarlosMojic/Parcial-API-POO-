package com.catolica.edu.sv.juegostienda.serviceimpl;

import com.catolica.edu.sv.juegostienda.model.Juego;
import com.catolica.edu.sv.juegostienda.repository.JuegoRepository;
import com.catolica.edu.sv.juegostienda.service.JuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JuegoServiceImpl implements JuegoService {

    @Autowired
    private JuegoRepository juegoRepository;

    @Override
    public List<Juego> findAll() {
        return juegoRepository.findAll();
    }

    @Override
    public Optional<Juego> findById(Integer id) {
        return juegoRepository.findById(id);
    }

    @Override
    public Juego save(Juego juego) {
        return juegoRepository.save(juego);
    }

    @Override
    public Juego update(Integer id, Juego juego) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void deleteById(Integer id) {
        juegoRepository.deleteById(id);
    }
}

