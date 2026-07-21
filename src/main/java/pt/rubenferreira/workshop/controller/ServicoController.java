package pt.rubenferreira.workshop.controller;

import pt.rubenferreira.workshop.entity.Servico;
import pt.rubenferreira.workshop.service.ServicoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public String lista(@RequestParam(required = false) String q, Model model) {
        model.addAttribute("servicos", servicoService.pesquisar(q));
        model.addAttribute("q", q);
        model.addAttribute("activePage", "servicos");
        return "servicos/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("servico", new Servico());
        model.addAttribute("activePage", "servicos");
        return "servicos/form";
    }

    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Integer id, Model model) {
        model.addAttribute("servico", servicoService.buscarPorId(id));
        model.addAttribute("activePage", "servicos");
        return "servicos/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Servico servico) {
        servicoService.guardar(servico);
        return "redirect:/servicos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id) {
        servicoService.eliminar(id);
        return "redirect:/servicos";
    }
}