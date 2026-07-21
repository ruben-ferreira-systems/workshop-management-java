package pt.rubenferreira.workshop.controller;

import pt.rubenferreira.workshop.entity.Mecanico;
import pt.rubenferreira.workshop.service.MecanicoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mecanicos")
public class MecanicoController {

    private final MecanicoService mecanicoService;

    public MecanicoController(MecanicoService mecanicoService) {
        this.mecanicoService = mecanicoService;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String nome,
                         @RequestParam(required = false) String nif,
                         @RequestParam(required = false) String especialidade,
                         Model model) {
        model.addAttribute("mecanicos", mecanicoService.filtrar(nome, nif, especialidade));
        model.addAttribute("nome", nome);
        model.addAttribute("nif", nif);
        model.addAttribute("especialidade", especialidade);
        model.addAttribute("activePage", "mecanicos");
        return "mecanicos/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("mecanico", new Mecanico());
        model.addAttribute("activePage", "mecanicos");
        return "mecanicos/form";
    }

    @PostMapping("/novo")
    public String novoGuardar(@ModelAttribute Mecanico mecanico) {
        mecanicoService.guardar(mecanico);
        return "redirect:/mecanicos";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        model.addAttribute("mecanico", mecanicoService.buscarPorId(id));
        model.addAttribute("activePage", "mecanicos");
        return "mecanicos/form";
    }

    @PostMapping("/editar/{id}")
    public String editarGuardar(@PathVariable Integer id,
                                @ModelAttribute Mecanico mecanico) {
        mecanico.setId(id);
        mecanicoService.guardar(mecanico);
        return "redirect:/mecanicos";
    }

    @PostMapping("/apagar/{id}")
    public String apagar(@PathVariable Integer id) {
        mecanicoService.apagar(id);
        return "redirect:/mecanicos";
    }
}