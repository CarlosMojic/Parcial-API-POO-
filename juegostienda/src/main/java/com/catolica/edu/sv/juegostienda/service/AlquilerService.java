package com.catolica.edu.sv.juegostienda.service;

import com.catolica.edu.sv.juegostienda.model.Alquiler;

import java.util.List;
import java.util.Optional;

public interface AlquilerService {

    List<Alquiler> getAllAlquileres();

    Optional<Alquiler> getAlquilerById(Integer id);

    Alquiler createAlquiler(Alquiler alquiler);

    Optional<Alquiler> updateAlquiler(Integer id, Alquiler alquiler);

    boolean deleteAlquiler(Integer id);
}
