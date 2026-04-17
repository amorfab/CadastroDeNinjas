package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")

public class NinjaControllerUi {
    private final NinjaService ninjaService;

    public NinjaControllerUi(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public String listarNinjas(Model model){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas"; //Tem que retornar o nome da pagina de renderiza
    }

    // Deletar Ninja (DELETE)
    @GetMapping("/deletar/{id}")
    public String deletarNinja(@PathVariable Long id){
        ninjaService.deletarNinja(id);
        return "redirect:/ninjas/ui/listar"; //Tem que retornar o nome da pagina de renderiza
    }

    // Mostrar ninja por id (READ)
    @GetMapping("/listar/{id}")
    public String listarNinjasPorId(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if (ninja != null) {
            model.addAttribute("ninja", ninja);
            return "detalhesNinja";
        } else {
            model.addAttribute("mensagem", "Ninja não encontrado");
            return "listarNinjas";
        }
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarNinja(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        return "adicionarNinja";
    }

    @PostMapping("/salvar")
    public String salvarNinja(@ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes) {
        ninjaService.criarNinja(ninja);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja cadastrado com sucesso!");
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/alterar/{id}")
    public String mostrarFormularioAlterarNinja(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if (ninja != null) {
            model.addAttribute("ninja", ninja);
            return "alterarNinja";
        } else {
            return "listarNinjas";
        }
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarNinja(@PathVariable Long id, @ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes){
        if (ninja != null) {
            ninjaService.alterarNinja(id, ninja);
            redirectAttributes.addFlashAttribute("mensagem", "Ninja atualizado com sucesso!");
            return "redirect:/ninjas/ui/listar";
        }else{
            redirectAttributes.addFlashAttribute("mensagem", "Ninja não encontrado");
            return "redirect:/ninjas/ui/listar";
        }
    }

}
