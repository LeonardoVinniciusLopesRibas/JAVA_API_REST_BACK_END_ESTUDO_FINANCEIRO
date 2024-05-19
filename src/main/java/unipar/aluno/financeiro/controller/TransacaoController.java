package unipar.aluno.financeiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import unipar.aluno.financeiro.exception.TransacaoFindAllResponse;
import unipar.aluno.financeiro.exception.ValidacaoException;
import unipar.aluno.financeiro.model.Transacao;
import unipar.aluno.financeiro.services.TransacaoService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/financeiro/transacao")
public class TransacaoController {

    private TransacaoService transacaoService;

    @Autowired
    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping("/getAll")
    public List<TransacaoFindAllResponse> getAllTransacao() throws NamingException, ValidacaoException, SQLException, Exception{
        return transacaoService.findAll();
    }

    @PostMapping("/post")
    public Transacao postTransacao(@RequestBody Transacao transacao) throws NamingException, ValidacaoException, SQLException, Exception{
        return transacaoService.save(transacao);
    }

}