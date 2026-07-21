package com.example.gestoroficina.controller;

import com.example.gestoroficina.service.ConfiguracaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping("/definicoes")
public class DefinicoesController {

    private final ConfiguracaoService configuracaoService;

    public DefinicoesController(ConfiguracaoService configuracaoService) {
        this.configuracaoService = configuracaoService;
    }

    @GetMapping
    public String definicoes(Model model) {
        model.addAttribute("gestaoStock", configuracaoService.isGestaoStockAtiva());
        model.addAttribute("logoOficina", configuracaoService.getValor("logo_oficina"));
        model.addAttribute("nomeOficina", configuracaoService.getValor("nome_oficina"));
        model.addAttribute("moradaOficina", configuracaoService.getValor("morada_oficina"));
        model.addAttribute("telefoneOficina", configuracaoService.getValor("telefone_oficina"));
        return "definicoes";
    }

    @PostMapping("/gestao-stock")
    public String toggleGestaoStock(@RequestParam boolean ativo) {
        configuracaoService.setGestaoStock(ativo);
        return "redirect:/definicoes";
    }

    @PostMapping("/info")
    public String guardarInfo(@RequestParam String nomeOficina,
                              @RequestParam String moradaOficina,
                              @RequestParam String telefoneOficina,
                              RedirectAttributes redirectAttributes) {
        configuracaoService.setValor("nome_oficina", nomeOficina);
        configuracaoService.setValor("morada_oficina", moradaOficina);
        configuracaoService.setValor("telefone_oficina", telefoneOficina);
        redirectAttributes.addFlashAttribute("sucesso", "Informações atualizadas.");
        return "redirect:/definicoes";
    }

    @PostMapping("/logo")
    public String uploadLogo(@RequestParam("logo") MultipartFile file,
                             RedirectAttributes redirectAttributes) throws IOException {
        if (!file.isEmpty()) {
            if (file.getSize() > 500 * 1024) { // 500KB
                redirectAttributes.addFlashAttribute("erro", "Logo demasiado grande. Máximo 500KB.");
                return "redirect:/definicoes";
            }
            String base64 = Base64.getEncoder().encodeToString(file.getBytes());
            String mimeType = file.getContentType();
            configuracaoService.setValor("logo_oficina", "data:" + mimeType + ";base64," + base64);
        }
        redirectAttributes.addFlashAttribute("sucesso", "Logo atualizado com sucesso.");
        return "redirect:/definicoes";
    }
}