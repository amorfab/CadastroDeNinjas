package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // GET - Mandar requisicao para mostrar as missoes do BD
    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissoes()
    {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    // POST - Mandar requisicao para criar as missoes no BD
    @PostMapping("/criar")
    public ResponseEntity criarMissao(@RequestBody MissoesDTO missao){
        MissoesDTO novaMissao =  missoesService.criarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missao criada com sucesso: " + novaMissao.getNome() + " (ID): " + novaMissao.getId());
    }

    // PUT - Mandar requisicao para alterar as missoes no BD
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarMissao(@PathVariable Long id, @RequestBody MissoesDTO missao){
        if (missoesService.listarMissoesPorId(id) != null){
            MissoesDTO missaoAtualizada = missoesService.atualizarMissao(id, missao);
            return ResponseEntity.ok(missaoAtualizada);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missao com ID: " + id + " nao encontrada");
        }
    }

    // DELETE - Mandar requisicao para deletar uma missao no BD
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable Long id){
        if (missoesService.listarMissoesPorId(id) != null){
            missoesService.deletarMissao(id);
            return ResponseEntity.ok("Missao com ID: " + id + " deletada com sucesso");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missao com ID: " + id + " nao encontrada");
        }

    }
}
