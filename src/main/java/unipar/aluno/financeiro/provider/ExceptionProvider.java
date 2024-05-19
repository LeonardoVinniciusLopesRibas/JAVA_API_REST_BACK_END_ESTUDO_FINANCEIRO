package unipar.aluno.financeiro.provider;

import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.resource.transaction.backend.jta.internal.synchronization.ExceptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import unipar.aluno.financeiro.exception.ExceptionResponse;

import java.util.Date;

@ControllerAdvice
public class ExceptionProvider {

    @ExceptionHandler(Exception.class)
    public void handleException(Exception ex, WebRequest request) throws Exception {
        throw ex;
    }
}
