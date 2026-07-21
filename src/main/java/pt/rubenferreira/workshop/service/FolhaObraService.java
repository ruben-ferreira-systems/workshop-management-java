package pt.rubenferreira.workshop.service;

import pt.rubenferreira.workshop.entity.FolhaObra;
import pt.rubenferreira.workshop.entity.LinhaPeca;
import pt.rubenferreira.workshop.entity.LinhaServico;
import pt.rubenferreira.workshop.entity.Peca;
import pt.rubenferreira.workshop.entity.Servico;
import pt.rubenferreira.workshop.repository.FolhaObraRepository;
import pt.rubenferreira.workshop.repository.PecaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class FolhaObraService {

    private final FolhaObraRepository folhaObraRepository;
    private final PecaRepository pecaRepository;
    private final ConfiguracaoService configuracaoService;

    public FolhaObraService(FolhaObraRepository folhaObraRepository,
                            PecaRepository pecaRepository,
                            ConfiguracaoService configuracaoService) {
        this.folhaObraRepository = folhaObraRepository;
        this.pecaRepository = pecaRepository;
        this.configuracaoService = configuracaoService;
    }

    public List<FolhaObra> listar() {
        return folhaObraRepository.findAll();
    }

    public FolhaObra buscarPorId(Integer id) {
        return folhaObraRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Folha não encontrada: " + id));
    }

    public FolhaObra guardar(FolhaObra folha) {
        return folhaObraRepository.save(folha);
    }

    public void adicionarServico(FolhaObra folha, Servico servico, BigDecimal precoAplicado) {
        LinhaServico linha = new LinhaServico();
        linha.setFolhaObra(folha);
        linha.setServico(servico);
        linha.setPrecoAplicado(precoAplicado);
        folha.getLinhasServico().add(linha);
        folhaObraRepository.save(folha);
    }

    public void adicionarPeca(FolhaObra folha, Peca peca, Integer quantidade, BigDecimal precoVenda) {
        if (configuracaoService.isGestaoStockAtiva()) {
            if (peca.getStockAtual() < quantidade) {
                throw new IllegalStateException("Stock insuficiente para: " + peca.getNome());
            }
            peca.setStockAtual(peca.getStockAtual() - quantidade);
            pecaRepository.save(peca);
        }
        LinhaPeca linha = new LinhaPeca();
        linha.setFolhaObra(folha);
        linha.setPeca(peca);
        linha.setQuantidade(quantidade);
        linha.setPrecoVendaHist(precoVenda);
        folha.getLinhasPeca().add(linha);
        folhaObraRepository.save(folha);
    }

    public void eliminar(Integer id) {
        FolhaObra folha = buscarPorId(id);
        if (configuracaoService.isGestaoStockAtiva()) {
            for (LinhaPeca lp : folha.getLinhasPeca()) {
                Peca peca = lp.getPeca();
                peca.setStockAtual(peca.getStockAtual() + lp.getQuantidade());
                pecaRepository.save(peca);
            }
        }
        folhaObraRepository.delete(folha);
    }

    public BigDecimal calcularTotal(FolhaObra folha) {
        BigDecimal totalServicos = folha.getLinhasServico().stream()
                .map(LinhaServico::getPrecoAplicado)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalPecas = folha.getLinhasPeca().stream()
                .map(lp -> lp.getPrecoVendaHist().multiply(BigDecimal.valueOf(lp.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalServicos.add(totalPecas);
    }
}