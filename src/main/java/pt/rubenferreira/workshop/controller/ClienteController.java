package pt.rubenferreira.workshop.controller;

import pt.rubenferreira.workshop.entity.Cliente;
import pt.rubenferreira.workshop.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String nome,
                         @RequestParam(required = false) String nif,
                         Model model) {
        model.addAttribute("clientes", clienteService.filtrar(nome, nif));
        model.addAttribute("nome", nome);
        model.addAttribute("nif", nif);
        model.addAttribute("activePage", "clientes");
        return "clientes/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("activePage", "clientes");
        return "clientes/form";
    }

    @PostMapping("/novo")
    public String novoGuardar(@ModelAttribute Cliente cliente) {
        clienteService.guardar(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        model.addAttribute("cliente", clienteService.buscarPorId(id));
        model.addAttribute("activePage", "clientes");
        return "clientes/form";
    }

    @PostMapping("/editar/{id}")
    public String editarGuardar(@PathVariable Integer id,
                                @ModelAttribute Cliente cliente) {
        cliente.setId(id);
        clienteService.guardar(cliente);
        return "redirect:/clientes";
    }

    @PostMapping("/apagar/{id}")
    public String apagar(@PathVariable Integer id) {
        clienteService.apagar(id);
        return "redirect:/clientes";
    }
}