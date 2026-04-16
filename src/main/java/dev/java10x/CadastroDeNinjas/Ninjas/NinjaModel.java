package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table (name = "tb_ninjas")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data //getters e setters
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
    @Column (name = "nome")
    private String nome;
    @Column (name = "idade")
    private int idade;
    @Column(unique = true)
    private String email;
    @Column (name = "rank")
    private String rank;
    // @ManyToOne - um ninja tem uma unica missao
    @ManyToOne
    @JoinColumn(name = "missao_id") //Foreing key ou chave estrangeira
    private MissoesModel missoes;

}
