package dev.java10x.CadastroDeNinjas.Ninjas;
import dev.java10x.CadastroDeNinjas.Missoes.MissoesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {
    private final NinjaRepository ninjaRepository;
    private final NinjaMapper ninjaMapper;
    private final MissoesRepository missoesRepository;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper, MissoesRepository missoesRepository) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
        this.missoesRepository = missoesRepository;
    }

    // Listar todos os meus ninjas
    public List<NinjaDTO> listarNinjas(){
        List<NinjaModel> ninjas = ninjaRepository.findAll();
        return ninjas.stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());
    }

    // Listar todos os meus ninjas por ID
    public NinjaDTO listarNinjasPorId(Long id){
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.map(ninjaMapper::map).orElse(null);
    }

    // Criar um novo ninja
    public NinjaDTO criarNinja(NinjaDTO ninjaDTO){
        NinjaModel ninja = ninjaMapper.map(ninjaDTO);
        if (ninjaDTO.getMissaoId() != null) {
            missoesRepository.findById(ninjaDTO.getMissaoId())
                    .ifPresent(ninja::setMissoes);
        }
        ninja = ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }

    // Deletar um ninjas por ID - Tem que ser um metodo VOID
    public void deletarNinja(Long id){
        ninjaRepository.deleteById(id);
    }

    // Atualizar ninja
    public NinjaDTO alterarNinja (Long id, NinjaDTO ninjaDTO){
        Optional<NinjaModel> ninjaExistente = ninjaRepository.findById(id);
        if (ninjaExistente.isPresent()){
            NinjaModel ninjaAtualizado = ninjaMapper.map(ninjaDTO);
            ninjaAtualizado.setId(id);
            if (ninjaDTO.getMissaoId() != null) {
                missoesRepository.findById(ninjaDTO.getMissaoId())
                        .ifPresent(ninjaAtualizado::setMissoes);
            }
            NinjaModel ninjaSalvo = ninjaRepository.save(ninjaAtualizado);
            return ninjaMapper.map(ninjaSalvo);
        }
        return null;
    }
}
