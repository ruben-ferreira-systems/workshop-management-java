package com.example.gestoroficina.controller;

import com.example.gestoroficina.entity.FolhaObra;
import com.example.gestoroficina.entity.Mecanico;
import com.example.gestoroficina.entity.Peca;
import com.example.gestoroficina.entity.Servico;
import com.example.gestoroficina.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/folhas")
public class FolhaObraController {

    private final FolhaObraService folhaObraService;
    private final VeiculoService veiculoService;
    private final MecanicoService mecanicoService;
    private final ServicoService servicoService;
    private final PecaService pecaService;
    private final ConfiguracaoService configuracaoService;

    public FolhaObraController(FolhaObraService folhaObraService,
                               VeiculoService veiculoService,
                               MecanicoService mecanicoService,
                               ServicoService servicoService,
                               PecaService pecaService,
                               ConfiguracaoService configuracaoService) {
        this.folhaObraService = folhaObraService;
        this.veiculoService = veiculoService;
        this.mecanicoService = mecanicoService;
        this.servicoService = servicoService;
        this.pecaService = pecaService;
        this.configuracaoService = configuracaoService;
    }

    @GetMapping
    public String lista(Model model) {
        List<FolhaObra> folhas = folhaObraService.listar();
        model.addAttribute("folhas", folhas);
        return "folhas/lista";
    }

    @GetMapping("/nova")
    public String novaForm(Model model) {
        model.addAttribute("folha", new FolhaObra());
        model.addAttribute("veiculos", veiculoService.listarTodos());
        model.addAttribute("mecanicos", mecanicoService.listarTodos());
        return "folhas/form";
    }

    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Integer id, Model model) {
        model.addAttribute("folha", folhaObraService.buscarPorId(id));
        model.addAttribute("veiculos", veiculoService.listarTodos());
        model.addAttribute("mecanicos", mecanicoService.listarTodos());
        return "folhas/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute FolhaObra folha,
                          @RequestParam Integer idVeiculo,
                          @RequestParam Integer idMecanico) {
        folha.setVeiculo(veiculoService.buscarPorId(idVeiculo));
        folha.setMecanico(mecanicoService.buscarPorId(idMecanico));
        FolhaObra guardada = folhaObraService.guardar(folha);
        return "redirect:/folhas/" + guardada.getId();
    }

    @GetMapping("/{id}")
    public String detalhe(@PathVariable Integer id, Model model) {
        FolhaObra folha = folhaObraService.buscarPorId(id);
        model.addAttribute("folha", folha);
        model.addAttribute("total", folhaObraService.calcularTotal(folha));
        model.addAttribute("servicos", servicoService.listarTodos());
        model.addAttribute("pecas", pecaService.listarTodas());
        return "folhas/detalhe";
    }

    @PostMapping("/{id}/adicionar-servico")
    public String adicionarServico(@PathVariable Integer id,
                                   @RequestParam Integer idServico,
                                   @RequestParam BigDecimal precoAplicado) {
        FolhaObra folha = folhaObraService.buscarPorId(id);
        Servico servico = servicoService.buscarPorId(idServico);
        folhaObraService.adicionarServico(folha, servico, precoAplicado);
        return "redirect:/folhas/" + id;
    }

    @PostMapping("/{id}/adicionar-peca")
    public String adicionarPeca(@PathVariable Integer id,
                                @RequestParam Integer idPeca,
                                @RequestParam Integer quantidade,
                                @RequestParam BigDecimal precoVenda,
                                RedirectAttributes redirectAttributes) {
        try {
            FolhaObra folha = folhaObraService.buscarPorId(id);
            Peca peca = pecaService.buscarPorId(idPeca);
            folhaObraService.adicionarPeca(folha, peca, quantidade, precoVenda);
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/folhas/" + id;
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id) {
        folhaObraService.eliminar(id);
        return "redirect:/folhas";
    }

    @GetMapping("/{id}/imprimir")
    public String imprimir(@PathVariable Integer id, Model model) {
        FolhaObra folha = folhaObraService.buscarPorId(id);
        model.addAttribute("folha", folha);
        model.addAttribute("total", folhaObraService.calcularTotal(folha));
        model.addAttribute("logoOficina", configuracaoService.getValor("logo_oficina"));
        model.addAttribute("nomeOficina", configuracaoService.getValor("nome_oficina"));
        model.addAttribute("moradaOficina", configuracaoService.getValor("morada_oficina"));
        model.addAttribute("telefoneOficina", configuracaoService.getValor("telefone_oficina"));
        return "folhas/imprimir";
    }
}