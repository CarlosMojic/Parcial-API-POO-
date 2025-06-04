package com.catolica.edu.sv.juegostienda.controller;

import com.catolica.edu.sv.juegostienda.model.Alquiler;
import com.catolica.edu.sv.juegostienda.model.Juego;
import com.catolica.edu.sv.juegostienda.model.Cliente;
import com.catolica.edu.sv.juegostienda.repository.AlquilerRepository;
import com.catolica.edu.sv.juegostienda.repository.ClienteRepository;
import com.catolica.edu.sv.juegostienda.repository.JuegoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alquileres")
public class AlquilerController {

    @Autowired
    private AlquilerRepository alquilerRepository;

    @Autowired
    private JuegoRepository juegoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // Obtener todos los alquileres
    @GetMapping
    public List<Alquiler> getAll() {
        return alquilerRepository.findAll();
    }

    // Obtener alquiler por id
    @GetMapping("/{id}")
    public ResponseEntity<Alquiler> getById(@PathVariable Integer id) {
        return alquilerRepository.findById(id)
                .map(alquiler -> ResponseEntity.ok(alquiler))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear nuevo alquiler
    @PostMapping
    public ResponseEntity<Alquiler> create(@RequestBody Alquiler alquiler) {
        // Validar que juego y cliente existan
        if (alquiler.getJuego() == null || alquiler.getCliente() == null) {
            return ResponseEntity.badRequest().build();
        }
        if (!juegoRepository.existsById(alquiler.getJuego().getId()) || !clienteRepository.existsById(alquiler.getCliente().getId())) {
            return ResponseEntity.badRequest().build();
        }
        Alquiler nuevo = alquilerRepository.save(alquiler);
        return ResponseEntity.ok(nuevo);
    }

    // Actualizar alquiler
    @PutMapping("/{id}")
    public ResponseEntity<Alquiler> update(@PathVariable Integer id, @RequestBody Alquiler alquiler) {
        Optional<Alquiler> op = alquilerRepository.findById(id);
        if (!op.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Alquiler existente = op.get();

        // Actualizar campos
        if (alquiler.getJuego() != null && juegoRepository.existsById(alquiler.getJuego().getId())) {
            existente.setJuego(alquiler.getJuego());
        }
        if (alquiler.getCliente() != null && clienteRepository.existsById(alquiler.getCliente().getId())) {
            existente.setCliente(alquiler.getCliente());
        }
        existente.setFechaAlquiler(alquiler.getFechaAlquiler());
        existente.setFechaDevolucion(alquiler.getFechaDevolucion());

        Alquiler actualizado = alquilerRepository.save(existente);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar alquiler
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return alquilerRepository.findById(id).map(alquiler -> {
            alquilerRepository.delete(alquiler);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
