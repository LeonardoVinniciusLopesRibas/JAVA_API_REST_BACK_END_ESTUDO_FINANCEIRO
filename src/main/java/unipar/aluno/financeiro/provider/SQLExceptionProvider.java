package unipar.aluno.financeiro.provider;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import unipar.aluno.financeiro.exception.ExceptionResponse;

import java.sql.SQLException;
import java.util.Date;

@ControllerAdvice
public class SQLExceptionProvider {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ExceptionResponse> handleSQLException(SQLException ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse("Erro SQL", new Date(), request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}