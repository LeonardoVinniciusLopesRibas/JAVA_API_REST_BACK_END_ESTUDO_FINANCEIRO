package unipar.aluno.financeiro.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ValidacaoException extends Exception{

    private List<String> errorList;

    public ValidacaoException(String message) {
        errorList = Arrays.asList(message);
    }

    public ValidacaoException(List<String> errorList) {
        this.errorList = errorList;
    }


}
