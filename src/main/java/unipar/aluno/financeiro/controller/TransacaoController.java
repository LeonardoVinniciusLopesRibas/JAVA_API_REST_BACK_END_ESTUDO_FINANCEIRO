package unipar.aluno.financeiro.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unipar.aluno.financeiro.dto.TransacaoFindAllResponse;
import unipar.aluno.financeiro.dto.TransacaoRequest;
import unipar.aluno.financeiro.exception.ValidacaoException;
import unipar.aluno.financeiro.model.Categoria;
import unipar.aluno.financeiro.model.TipoCategoriaEnum;
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

    private final TransacaoService transacaoService;
    private final CategoriaService categoriaService;

    @Autowired
    public TransacaoController(TransacaoService transacaoService, CategoriaService categoriaService) {
        this.transacaoService = transacaoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/getAll")
    public List<TransacaoFindAllResponse> getAllTransacao() throws NamingException, ValidacaoException, SQLException, Exception {
        return transacaoService.findAll();
    }

    @PostMapping("/post")
    public ResponseEntity<Transacao> postTransacao(@Valid @RequestBody TransacaoRequest transacaoRequest) throws NamingException, ValidacaoException, SQLException, Exception {
        Categoria categoria = categoriaService.findById(transacaoRequest.getCategoria());
        if (categoria == null) {
            throw new ValidacaoException("Categoria não encontrada");
        }

        Transacao transacao = new Transacao();
        transacao.setQuantia(transacaoRequest.getQuantia());
        transacao.setDt_transacao(transacaoRequest.getDtTransacao());
        transacao.setDescricao(transacaoRequest.getDescricao());
        transacao.setCategoria(categoria);

        return ResponseEntity.ok(transacaoService.save(transacao));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Transacao> getById(@PathVariable Long id) throws NamingException, ValidacaoException, SQLException, Exception {
        Transacao transacao = transacaoService.findById(id);
        if (transacao == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transacao);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Transacao> putTransacao(@PathVariable Long id, @Valid @RequestBody TransacaoRequest transacaoRequest) throws NamingException, ValidacaoException, SQLException, Exception {
        Transacao transacaoExistente = transacaoService.findById(id);
        if (transacaoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        Categoria categoria = categoriaService.findById(transacaoRequest.getCategoria());
        if (categoria == null) {
            throw new ValidacaoException("Categoria não encontrada");
        }

        transacaoExistente.setQuantia(transacaoRequest.getQuantia());
        transacaoExistente.setDt_transacao(transacaoRequest.getDtTransacao());
        transacaoExistente.setDescricao(transacaoRequest.getDescricao());
        transacaoExistente.setCategoria(categoria);

        Transacao transacaoAtualizada = transacaoService.save(transacaoExistente);
        return ResponseEntity.ok(transacaoAtualizada);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Transacao> delete(@PathVariable Long id) throws NamingException, ValidacaoException, SQLException, Exception {
        Transacao transacao = transacaoService.findById(id);
        if (transacao == null) {
            return ResponseEntity.notFound().build();
        }

        transacaoService.delete(id);
        return ResponseEntity.ok(transacao);
    }

    @GetMapping("/transacoes/receitas")
    public double getTotalReceitas() {
        List<Transacao> receitas = transacaoService.obterTransacoesPorTipo(TipoCategoriaEnum.RECEITA);
        return receitas.stream().mapToDouble(Transacao::getQuantia).sum();
    }

    @GetMapping("/transacoes/despesas")
    public double getTotalDespesas() {
        List<Transacao> despesas = transacaoService.obterTransacoesPorTipo(TipoCategoriaEnum.DESPESA);
        return despesas.stream().mapToDouble(Transacao::getQuantia).sum();
    }

    @GetMapping("/transacoes/saldo")
    public double getSaldo() {
        double totalReceitas = getTotalReceitas();
        double totalDespesas = getTotalDespesas();
        return totalReceitas - totalDespesas;
    }
}