package com.example.gestoroficina.service;

import com.example.gestoroficina.entity.Configuracao;
import com.example.gestoroficina.repository.ConfiguracaoRepository;
import org.springframework.stereotype.Service;

@Service
public class ConfiguracaoService {

    private final ConfiguracaoRepository configuracaoRepository;

    public ConfiguracaoService(ConfiguracaoRepository configuracaoRepository) {
        this.configuracaoRepository = configuracaoRepository;
    }

    public boolean isGestaoStockAtiva() {
        return configuracaoRepository.findById("gestao_stock")
                .map(c -> "true".equalsIgnoreCase(c.getValor()))
                .orElse(true);
    }

    public void setGestaoStock(boolean ativo) {
        Configuracao config = configuracaoRepository.findById("gestao_stock")
                .orElse(new Configuracao());
        config.setChave("gestao_stock");
        config.setValor(ativo ? "true" : "false");
        configuracaoRepository.save(config);
    }

    public String getValor(String chave) {
        return configuracaoRepository.findById(chave)
                .map(Configuracao::getValor)
                .orElse("");
    }

    public void setValor(String chave, String valor) {
        Configuracao c = configuracaoRepository.findById(chave)
                .orElse(new Configuracao());
        c.setChave(chave);
        c.setValor(valor);
        configuracaoRepository.save(c);
    }
}