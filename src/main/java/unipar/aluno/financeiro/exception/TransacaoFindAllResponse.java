package unipar.aluno.financeiro.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoFindAllResponse {

    private double quantia;
    private Date dtTransacao;
    private String categoriaNome;
    private String descricao;

}
