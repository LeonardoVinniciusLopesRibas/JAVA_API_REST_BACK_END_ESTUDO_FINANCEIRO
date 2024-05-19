package unipar.aluno.financeiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unipar.aluno.financeiro.dto.TransacaoFindAllResponse;
import unipar.aluno.financeiro.dto.TransacaoRequest;
import unipar.aluno.financeiro.exception.ValidacaoException;
import unipar.aluno.financeiro.model.Categoria;
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
    public Transacao postTransacao(@RequestBody TransacaoRequest transacaoRequest) throws NamingException, ValidacaoException, SQLException, Exception{
        Transacao transacao = new Transacao();
        transacao.setQuantia(transacaoRequest.getQuantia());
        transacao.setDt_transacao(transacaoRequest.getDtTransacao());
        transacao.setDescricao(transacaoRequest.getDescricao());

        // Crie uma instância de Categoria com apenas o ID
        Categoria categoria = new Categoria();
        categoria.setId(transacaoRequest.getCategoriaId());
        transacao.setCategoria(categoria);

        return transacaoService.save(transacao);
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<Transacao> getById(@PathVariable Long id)throws NamingException, ValidacaoException, SQLException, Exception{
        Transacao transacao = transacaoService.findById(id);
        if(transacao == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transacao);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Transacao> put(@PathVariable Long id, @RequestBody Transacao transacao) throws NamingException, ValidacaoException, SQLException, Exception{

        Transacao transacaoExistente = transacaoService.findById(id);
        if (transacaoExistente == null){
            return ResponseEntity.notFound().build();
        }

        transacaoExistente.setQuantia(transacao.getQuantia());
        transacaoExistente.setDt_transacao(transacao.getDt_transacao());
        transacaoExistente.setCategoria(transacao.getCategoria());
        transacaoExistente.setDescricao(transacao.getDescricao());

        Transacao transacaoAtualizada = transacaoService.save(transacaoExistente);

        return ResponseEntity.ok(transacaoAtualizada);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Transacao> delete(@PathVariable Long id) throws NamingException, ValidacaoException, SQLException, Exception{
        Transacao transacao = transacaoService.findById(id);

        if (transacao == null){
            return ResponseEntity.notFound().build();
        }

        transacaoService.delete(id);
        return ResponseEntity.ok(transacao);

    }

}