package unipar.aluno.financeiro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoFindAllResponse {

    private double quantia;
    private LocalDate dtTransacao;
    private String categoriaNome;
    private String descricao;

}
