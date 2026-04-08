package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota";
    }

    // Adicionar Ninja (CREATE)
    @PostMapping("/criar")
    public String criar(){
        return "Ninja criado com sucesso";
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public String listarTodosOsNinja(){
        return "Todos os Ninja";
    }

    // Mostrar ninja por id (READ)
    @GetMapping("/listarID")
    public String listarTodosOsNinjaPorId(){
        return "Todos os Ninja por ID";
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterar")
    public String alterar(){
        return "Ninja alterado com sucesso";
    }

    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletar")
    public String deletar(){
        return "Ninja deletado com sucesso";
    }
}
