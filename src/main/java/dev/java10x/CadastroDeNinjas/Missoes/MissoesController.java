package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Lista todas as missoes", description = "Rota lista todas as missoes cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missoes listadas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma missao foi encontrada")
    } )
    public ResponseEntity<List<MissoesDTO>> listarMissoes()
    {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    // POST - Mandar requisicao para criar as missoes no BD
    @PostMapping("/criar")
    @Operation(summary = "Cria uma nova missao", description = "Rota cria uma nova missao e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missao criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criacao da missao")
    } )
    public ResponseEntity criarMissao(
            @Parameter(description = "Usuario manda os dados da missao a ser criada no caminho da requisicao")
            @RequestBody MissoesDTO missao){
        MissoesDTO novaMissao =  missoesService.criarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missao criada com sucesso: " + novaMissao.getNome() + " (ID): " + novaMissao.getId());
    }

    // PUT - Mandar requisicao para alterar as missoes no BD
    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera a missao por Id", description = "Rota altera uma missao pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missao alterada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missao nao encontrada")
    } )
    public ResponseEntity<?> alterarMissao(
            @Parameter(description = "Usuario manda o id no caminho da requisicao")
            @PathVariable Long id,
            @Parameter(description = "Usuario manda os dados da missao a ser atualizada no caminho da requisicao")
            @RequestBody MissoesDTO missao){
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
    @Operation(summary = "Deleta a missao por Id", description = "Rota deleta uma missao pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missao deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missao nao encontrada, nao foi possivel deletar")
    })
    public ResponseEntity<String> deletarMissao(
            @Parameter(description = "Usuario manda o id no caminho da requisicao")
            @PathVariable Long id){
        if (missoesService.listarMissoesPorId(id) != null){
            missoesService.deletarMissao(id);
            return ResponseEntity.ok("Missao com ID: " + id + " deletada com sucesso");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missao com ID: " + id + " nao encontrada");
        }

    }
}
