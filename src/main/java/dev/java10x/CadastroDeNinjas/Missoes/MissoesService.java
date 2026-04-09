package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissoesService {
    private MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    // Listar todos os meus ninjas
    public List<MissoesModel> listarMissoes(){
        return missoesRepository.findAll();
    }

    // Criar uma nova missao
    public MissoesModel criarMissao(MissoesModel missao){
        return missoesRepository.save(missao);
    }
}
