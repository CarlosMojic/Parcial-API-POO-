package com.catolica.edu.sv.juegostienda.serviceimpl;

import com.catolica.edu.sv.juegostienda.model.Cliente;
import com.catolica.edu.sv.juegostienda.repository.ClienteRepository;
import com.catolica.edu.sv.juegostienda.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente update(Integer id, Cliente cliente) {
        Optional<Cliente> op = clienteRepository.findById(id);
        if (!op.isPresent()) {
            return null;
        }
        Cliente existente = op.get();
        existente.setNombre(cliente.getNombre());
        existente.setCorreo(cliente.getCorreo());
        existente.setTelefono(cliente.getTelefono());
        return clienteRepository.save(existente);
    }

    @Override
    public void delete(Integer id) {
        clienteRepository.deleteById(id);
    }
}
