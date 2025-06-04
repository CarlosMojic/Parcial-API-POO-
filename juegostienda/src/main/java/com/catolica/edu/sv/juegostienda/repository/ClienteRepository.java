package com.catolica.edu.sv.juegostienda.repository;

import com.catolica.edu.sv.juegostienda.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {}
