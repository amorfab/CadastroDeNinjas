package dev.java10x.CadastroDeNinjas.Missoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table (name = "tb_missoes")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "missoes")
@Data //getters e setters

public class MissoesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String dificuldade;
    //@OneToMany - Uma missao pode ter varios ninjas
    @OneToMany(mappedBy = "missoes")
    @JsonIgnore //Erro de serializacao
    private List<NinjaModel> ninjas;


}

