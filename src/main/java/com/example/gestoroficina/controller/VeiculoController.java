package com.example.gestoroficina.controller;

import com.example.gestoroficina.entity.Veiculo;
import com.example.gestoroficina.service.ClienteService;
import com.example.gestoroficina.service.VeiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;
    private final ClienteService clienteService;

    public VeiculoController(VeiculoService veiculoService,
                             ClienteService clienteService) {
        this.veiculoService = veiculoService;
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String matricula,
                         @RequestParam(required = false) String marca,
                         @RequestParam(required = false) String cliente,
                         Model model) {
        model.addAttribute("veiculos", veiculoService.filtrar(matricula, marca, cliente));
        model.addAttribute("matricula", matricula);
        model.addAttribute("marca", marca);
        model.addAttribute("cliente", cliente);
        model.addAttribute("activePage", "veiculos");
        return "veiculos/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("veiculo", new Veiculo());
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("activePage", "veiculos");
        return "veiculos/form";
    }

    @PostMapping("/novo")
    public String novoGuardar(@ModelAttribute Veiculo veiculo) {
        veiculoService.guardar(veiculo);
        return "redirect:/veiculos";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        model.addAttribute("veiculo", veiculoService.buscarPorId(id));
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("activePage", "veiculos");
        return "veiculos/form";
    }

    @PostMapping("/editar/{id}")
    public String editarGuardar(@PathVariable Integer id,
                                @ModelAttribute Veiculo veiculo) {
        veiculo.setId(id);
        veiculoService.guardar(veiculo);
        return "redirect:/veiculos";
    }

    @PostMapping("/apagar/{id}")
    public String apagar(@PathVariable Integer id) {
        veiculoService.apagar(id);
        return "redirect:/veiculos";
    }
}