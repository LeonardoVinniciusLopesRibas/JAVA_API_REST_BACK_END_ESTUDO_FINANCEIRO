package unipar.aluno.financeiro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import unipar.aluno.financeiro.model.Categoria;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoRequest {

    private double quantia;
    private LocalDate dtTransacao;
    private Long categoria;
    private String descricao;

}
