package com.catolica.edu.sv.juegostienda.serviceimpl;

import com.catolica.edu.sv.juegostienda.model.Alquiler;
import com.catolica.edu.sv.juegostienda.repository.AlquilerRepository;
import com.catolica.edu.sv.juegostienda.repository.ClienteRepository;
import com.catolica.edu.sv.juegostienda.repository.JuegoRepository;
import com.catolica.edu.sv.juegostienda.service.AlquilerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlquilerServiceImpl implements AlquilerService {

    @Autowired
    private AlquilerRepository alquilerRepository;

    @Autowired
    private JuegoRepository juegoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Alquiler> getAllAlquileres() {
        return alquilerRepository.findAll();
    }

    @Override
    public Optional<Alquiler> getAlquilerById(Integer id) {
        return alquilerRepository.findById(id);
    }

    @Override
    public Alquiler createAlquiler(Alquiler alquiler) {
        if (alquiler.getJuego() == null || alquiler.getCliente() == null) {
            throw new IllegalArgumentException("Juego o Cliente no pueden ser nulos");
        }
        if (!juegoRepository.existsById(alquiler.getJuego().getId()) ||
                !clienteRepository.existsById(alquiler.getCliente().getId())) {
            throw new IllegalArgumentException("Juego o Cliente no existen");
        }
        return alquilerRepository.save(alquiler);
    }

    @Override
    public Optional<Alquiler> updateAlquiler(Integer id, Alquiler alquiler) {
        Optional<Alquiler> op = alquilerRepository.findById(id);
        if (!op.isPresent()) {
            return Optional.empty();
        }
        Alquiler existente = op.get();

        if (alquiler.getJuego() != null && juegoRepository.existsById(alquiler.getJuego().getId())) {
            existente.setJuego(alquiler.getJuego());
        }
        if (alquiler.getCliente() != null && clienteRepository.existsById(alquiler.getCliente().getId())) {
            existente.setCliente(alquiler.getCliente());
        }
        existente.setFechaAlquiler(alquiler.getFechaAlquiler());
        existente.setFechaDevolucion(alquiler.getFechaDevolucion());

        Alquiler actualizado = alquilerRepository.save(existente);
        return Optional.of(actualizado);
    }

    @Override
    public boolean deleteAlquiler(Integer id) {
        Optional<Alquiler> op = alquilerRepository.findById(id);
        if (op.isPresent()) {
            alquilerRepository.delete(op.get());
            return true;
        }
        return false;
    }
}

