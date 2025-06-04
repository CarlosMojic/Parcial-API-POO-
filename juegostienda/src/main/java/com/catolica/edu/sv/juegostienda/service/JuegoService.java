package com.catolica.edu.sv.juegostienda.service;

import com.catolica.edu.sv.juegostienda.model.Juego;

import java.util.List;
import java.util.Optional;

public interface JuegoService {

    List<Juego> findAll();

    Optional<Juego> findById(Integer id);

    Juego save(Juego juego);

    Juego update(Integer id, Juego juego);

    void delete(Integer id);

    void deleteById(Integer id);
}
