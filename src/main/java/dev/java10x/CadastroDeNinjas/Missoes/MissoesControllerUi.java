package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/missoes/ui")
public class MissoesControllerUi {
    private final MissoesService missoesService;

    public MissoesControllerUi(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/listar")
    public String listarMissoes(Model model){
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        model.addAttribute("missoes", missoes);
        return "listarMissoes"; //Tem que retornar o nome da pagina de renderiza
    }

    @GetMapping("/deletar/{id}")
    public String deletarMissao(@PathVariable Long id){
        missoesService.deletarMissao(id);
        return "redirect:/missoes/ui/listar"; //Tem que retornar o nome da pagina de renderiza
    }

    @GetMapping("/listar/{id}")
    public String listarMissaoPorId(@PathVariable Long id, Model model) {
        MissoesDTO missoes = missoesService.listarMissoesPorId(id);
        if (missoes != null) {
            model.addAttribute("missoes", missoes);
            return "detalhesMissao";
        } else {
            model.addAttribute("mensagem", "Missão não encontrada");
            return "listarMissoes";
        }
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarMissao(Model model) {
        model.addAttribute("missoes", new MissoesDTO());
        return "adicionarMissao";
    }

    @PostMapping("/salvar")
    public String salvarMissao(@ModelAttribute MissoesDTO missao, RedirectAttributes redirectAttributes) {
        missoesService.criarMissao(missao);
        redirectAttributes.addFlashAttribute("mensagem", "Missão cadastrada com sucesso!");
        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/alterar/{id}")
    public String mostrarFormularioAlterarMissao(@PathVariable Long id, Model model) {
        MissoesDTO missoes = missoesService.listarMissoesPorId(id);
        if (missoes != null) {
            model.addAttribute("missoes", missoes);
            return "alterarMissao";
        } else {
            return "listarMissoes";
        }
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarMissao(@PathVariable Long id, @ModelAttribute MissoesDTO missoes, RedirectAttributes redirectAttributes){
        if (missoes != null) {
            missoesService.atualizarMissao(id,missoes);
            redirectAttributes.addFlashAttribute("mensagem", "Missão atualizada com sucesso!");
            return "redirect:/missoes/ui/listar";
        }else{
            redirectAttributes.addFlashAttribute("mensagem", "Missão não encontrada");
            return "redirect:/missoes/ui/listar";
        }
    }
}
