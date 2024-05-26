package unipar.aluno.financeiro.provider;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import unipar.aluno.financeiro.exception.ExceptionResponse;
import unipar.aluno.financeiro.exception.ValidacaoException;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ExceptionResponse> handleSQLException(SQLException ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                "Erro interno do servidor. Por favor, entre em contato com o suporte.",
                new Date(),
                request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR.toString()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidacaoException> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {

        List<String> errors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.add(fieldError.getField()+ " + " + fieldError.getDefaultMessage());
        }

        ValidacaoException validacaoException = new ValidacaoException(errors);

        return ResponseEntity.badRequest().body(validacaoException);
    }

    @ExceptionHandler(NamingException.class)
    public ResponseEntity<ExceptionResponse> handleNamingException(NamingException ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                "Erro interno do servidor. Por favor, entre em contato com o suporte.",
                new Date(),
                request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR.toString()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<ExceptionResponse> handleValidacaoException(ValidacaoException ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                "Erro de validação: " + ex.getMessage(),
                new Date(),
                request.getDescription(false),
                HttpStatus.BAD_REQUEST.toString()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGlobalException(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                "Erro de validação: "+ex.getMessage(),
                new Date(),
                request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR.toString()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}