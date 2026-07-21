package com.example.gestoroficina.controller;

import com.example.gestoroficina.entity.Peca;
import com.example.gestoroficina.service.PecaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pecas")
public class PecaController {

    private final PecaService pecaService;

    public PecaController(PecaService pecaService) {
        this.pecaService = pecaService;
    }

    @GetMapping
    public String lista(@RequestParam(required = false) String q, Model model) {
        model.addAttribute("pecas", pecaService.pesquisar(q));
        model.addAttribute("q", q);
        model.addAttribute("activePage", "pecas");
        return "pecas/lista";
    }

    @GetMapping("/nova")
    public String novaForm(Model model) {
        model.addAttribute("peca", new Peca());
        model.addAttribute("activePage", "pecas");
        return "pecas/form";
    }

    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Integer id, Model model) {
        model.addAttribute("peca", pecaService.buscarPorId(id));
        model.addAttribute("activePage", "pecas");
        return "pecas/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Peca peca) {
        pecaService.guardar(peca);
        return "redirect:/pecas";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id) {
        pecaService.eliminar(id);
        return "redirect:/pecas";
    }
}