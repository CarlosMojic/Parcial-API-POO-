package com.catolica.edu.sv.juegostienda.controller;

import com.catolica.edu.sv.juegostienda.model.Juego;
import com.catolica.edu.sv.juegostienda.repository.JuegoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/juegos")
public class JuegoController {

    private final JuegoRepository juegoRepository;

    public JuegoController(JuegoRepository juegoRepository) {
        this.juegoRepository = juegoRepository;
    }

    // Obtener todos los juegos
    @GetMapping
    public List<Juego> getAll() {
        return juegoRepository.findAll();
    }

    // Obtener un juego por id
    @GetMapping("/{id}")
    public ResponseEntity<Juego> getById(@PathVariable Integer id) {
        Optional<Juego> juego = juegoRepository.findById(id);
        return juego.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo juego
    @PostMapping
    public Juego create(@RequestBody Juego juego) {
        return juegoRepository.save(juego);
    }

    // Actualizar un juego
    @PutMapping("/{id}")
    public ResponseEntity<Juego> update(@PathVariable Integer id, @RequestBody Juego juegoDetails) {
        return juegoRepository.findById(id).map(juego -> {
            juego.setNombre(juegoDetails.getNombre());
            juego.setDescripcion(juegoDetails.getDescripcion());
            juego.setCategoria(juegoDetails.getCategoria());
            juego.setStock(juegoDetails.getStock());
            Juego actualizado = juegoRepository.save(juego);
            return ResponseEntity.ok(actualizado);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un juego
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return juegoRepository.findById(id).map(juego -> {
            juegoRepository.delete(juego);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }




}