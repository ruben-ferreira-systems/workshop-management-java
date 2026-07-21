package pt.rubenferreira.workshop.controller;

import com.example.gestoroficina.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pt.rubenferreira.workshop.repository.*;

@Controller
public class DashboardController {

    private final ClienteRepository clienteRepository;
    private final MecanicoRepository mecanicoRepository;
    private final VeiculoRepository veiculoRepository;
    private final FolhaObraRepository folhaObraRepository;
    private final PecaRepository pecaRepository;

    public DashboardController(ClienteRepository clienteRepository,
                               MecanicoRepository mecanicoRepository,
                               VeiculoRepository veiculoRepository,
                               FolhaObraRepository folhaObraRepository,
                               PecaRepository pecaRepository) {
        this.clienteRepository = clienteRepository;
        this.mecanicoRepository = mecanicoRepository;
        this.veiculoRepository = veiculoRepository;
        this.folhaObraRepository = folhaObraRepository;
        this.pecaRepository = pecaRepository;
    }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("totalClientes", clienteRepository.count());
        model.addAttribute("totalMecanicos", mecanicoRepository.count());
        model.addAttribute("totalVeiculos", veiculoRepository.count());
        model.addAttribute("totalFolhas", folhaObraRepository.count());
        model.addAttribute("folhasRecentes", folhaObraRepository.findTop5ByOrderByDataEntradaDesc());
        model.addAttribute("pecasSemStock", pecaRepository.findByStockAtualEquals(0));
        return "dashboard";
    }
}