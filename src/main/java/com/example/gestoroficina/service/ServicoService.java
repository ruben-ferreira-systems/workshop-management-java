package com.example.gestoroficina.service;

import com.example.gestoroficina.entity.Servico;
import com.example.gestoroficina.repository.ServicoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    public List<Servico> pesquisar(String termo) {
        if (termo == null || termo.isBlank()) return listarTodos();
        return servicoRepository.findByDesignacaoContainingIgnoreCase(termo);
    }

    public Servico buscarPorId(Integer id) {
        return servicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado: " + id));
    }

    public void guardar(Servico servico) {
        servicoRepository.save(servico);
    }

    public void eliminar(Integer id) {
        servicoRepository.deleteById(id);
    }
}