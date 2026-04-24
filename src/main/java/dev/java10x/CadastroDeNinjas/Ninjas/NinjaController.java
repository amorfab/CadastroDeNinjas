package dev.java10x.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Esssa rota da uma mensagem de boas vindas para quem a acessa")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota";
    }

    // Adicionar Ninja (CREATE)
    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja", description = "Rota cria um novo ninja e insere no banco de dados")
    @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
          @ApiResponse(responseCode = "400", description = "Erro na criacao do ninja")
    } )
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO novoNinja =  ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID): " + novoNinja.getId());
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    @Operation(summary = "Lista todos os ninjas", description = "Rota lista todos os ninjas cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninjas listados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum ninja foi encontrado")
    } )
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    // Mostrar ninja por id (READ)
    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista o ninja por Id", description = "Rota lista um ninja pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja nao encontrado")
    } )
    public ResponseEntity<?> listarNinjasPorId(
            @Parameter(description = "Usuario manda o id no caminho da requisicao")
            @PathVariable Long id){
        if (ninjaService.listarNinjasPorId(id) != null) {
            NinjaDTO ninjaEncontrado = ninjaService.listarNinjasPorId(id);
            return ResponseEntity.ok(ninjaEncontrado);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID: " + id + " nao encontrado");
        }
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera o ninja por Id", description = "Rota altera um ninja pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja nao encontrado, nao foi possivel alterar")
    } )
    public ResponseEntity<?> alterarNinja(
            @Parameter(description = "Usuario manda o id no caminho da requisicao")
            @PathVariable Long id,
            @Parameter(description = "Usuario manda os dados do ninja a ser atualizado no caminho da requisicao")
            @RequestBody NinjaDTO ninja){
        if (ninjaService.listarNinjasPorId(id) != null) {
            NinjaDTO ninjaAtualizado = ninjaService.alterarNinja(id, ninja);
            return ResponseEntity.ok(ninjaAtualizado);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID: " + id + " nao encontrado");
        }
    }

    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta o ninja por Id", description = "Rota deleta um ninja pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja nao encontrado, nao foi possivel deletar")
    })
    public ResponseEntity<String> deletarNinja(
            @Parameter(description = "Usuario manda o id no caminho da requisicao")
            @PathVariable Long id){
        if (ninjaService.listarNinjasPorId(id) != null) {
            ninjaService.deletarNinja(id);
            return ResponseEntity.ok("Ninja com ID: " + id + " deletado com sucesso");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID: " + id + " nao encontrado");
        }
    }
}
