package unipar.aluno.financeiro.provider;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import unipar.aluno.financeiro.exception.ExceptionResponse;
import unipar.aluno.financeiro.exception.ValidacaoException;

import java.util.Date;

@ControllerAdvice
public class ValidacaoExceptionProvider {

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<ExceptionResponse> handleValidacaoException(ValidacaoException ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse("Erro de validação", new Date(), request.getDescription(false), HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}