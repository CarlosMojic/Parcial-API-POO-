package com.catolica.edu.sv.juegostienda.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Alquileres")
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_juego", nullable = false)
    private Juego juego;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(name = "fechaAlquiler", nullable = false)  // <-- Aquí el cambio para NOT NULL
    private LocalDate fechaAlquiler;

    @Column(name = "fechaDevolucion")
    private LocalDate fechaDevolucion;

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Juego getJuego() { return juego; }
    public void setJuego(Juego juego) { this.juego = juego; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public LocalDate getFechaAlquiler() { return fechaAlquiler; }
    public void setFechaAlquiler(LocalDate fechaAlquiler) { this.fechaAlquiler = fechaAlquiler; }

    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(LocalDate fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }
}

