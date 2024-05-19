package unipar.aluno.financeiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unipar.aluno.financeiro.dto.TransacaoFindAllResponse;
import unipar.aluno.financeiro.dto.TransacaoRequest;
import unipar.aluno.financeiro.exception.ValidacaoException;
import unipar.aluno.financeiro.model.Categoria;
import unipar.aluno.financeiro.model.Transacao;
import unipar.aluno.financeiro.services.CategoriaService;
import unipar.aluno.financeiro.services.TransacaoService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/financeiro/transacao")
public class TransacaoController {

    private TransacaoService transacaoService;
    private CategoriaService categoriaService;

    @Autowired
    public TransacaoController(TransacaoService transacaoService, CategoriaService categoriaService) {
        this.transacaoService = transacaoService;
        this.categoriaService = categoriaService;
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

        Categoria categoria = new Categoria();
        Long id = transacaoRequest.getCategoria();
        categoria = categoriaService.findById(id);

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
    public ResponseEntity<Transacao> putTransacao(@PathVariable Long id, @RequestBody TransacaoRequest transacaoRequest) throws NamingException, ValidacaoException, SQLException, Exception {
        Transacao transacaoExistente = transacaoService.findById(id);
        if (transacaoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        transacaoExistente.setQuantia(transacaoRequest.getQuantia());
        transacaoExistente.setDt_transacao(transacaoRequest.getDtTransacao());
        transacaoExistente.setDescricao(transacaoRequest.getDescricao());
        Categoria categoria = categoriaService.findById(transacaoRequest.getCategoria());
        if (categoria == null) {
            return ResponseEntity.badRequest().build();
        }
        transacaoExistente.setCategoria(categoria);
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