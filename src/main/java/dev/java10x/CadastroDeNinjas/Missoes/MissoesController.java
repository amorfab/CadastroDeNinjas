package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // GET - Mandar requisicao para mostrar as missoes do BD
    @GetMapping("/listar")
    public List<MissoesModel> listarMissoes() {
        return missoesService.listarMissoes();
    }

    // POST - Mandar requisicao para criar as missoes no BD
    @PostMapping("/criar")
    public MissoesModel criarMissao(@RequestBody MissoesModel missao){
        return missoesService.criarMissao(missao);
    }

    // PUT - Mandar requisicao para alterar as missoes no BD
    @PutMapping("/alterar")
    public String alterarMissao(){
        return "Missao alterada com sucesso";
    }

    // DELETE - Mandar requisicao para deletar uma missao no BD
    @DeleteMapping("/deletar/{id}")
    public void deletarMissao(@PathVariable Long id){
        missoesService.deletarMissao(id);
    }



}
