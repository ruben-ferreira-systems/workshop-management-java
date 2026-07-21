package com.example.gestoroficina.service;
import com.example.gestoroficina.entity.Cliente;
import com.example.gestoroficina.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public List<Cliente> filtrar(String nome, String nif) {
        if (nome != null && !nome.isBlank()) {
            return clienteRepository.findByNomeContainingIgnoreCase(nome);
        }
        if (nif != null && !nif.isBlank()) {
            return clienteRepository.findByNifContaining(nif);
        }
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public void guardar(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public void apagar(Integer id) {
        clienteRepository.deleteById(id);
    }
}