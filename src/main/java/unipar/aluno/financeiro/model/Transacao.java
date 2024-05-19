package unipar.aluno.financeiro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double quantia;
    private Date dt_transacao;

    @OneToOne
    private Categoria categoria;

    @Column(name = "descricao", length = 255)
    private String descricao;

}
