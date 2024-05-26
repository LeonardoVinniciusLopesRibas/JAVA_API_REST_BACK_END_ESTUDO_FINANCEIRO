package unipar.aluno.financeiro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unipar.aluno.financeiro.dto.TransacaoFindAllResponse;
import unipar.aluno.financeiro.exception.ValidacaoException;
import unipar.aluno.financeiro.model.Categoria;
import unipar.aluno.financeiro.model.TipoCategoriaEnum;
import unipar.aluno.financeiro.model.Transacao;
import unipar.aluno.financeiro.repository.CategoriaRepository;
import unipar.aluno.financeiro.repository.TransacaoRepository;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    private TransacaoRepository transacaoRepository;
    private CategoriaRepository categoriaRepository;

    @Autowired
    public TransacaoService(TransacaoRepository transacaoRepository, CategoriaRepository categoriaRepository) {
        this.transacaoRepository = transacaoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<TransacaoFindAllResponse> findAll() {

        List<Transacao> transacoes = transacaoRepository.findAll();

        return transacoes.stream().map(transacao -> new TransacaoFindAllResponse(
                transacao.getQuantia(),
                transacao.getDt_transacao(),
                transacao.getCategoria().getCategoria(),
                transacao.getDescricao())).collect(Collectors.toList());

    }

    public Transacao save(Transacao transacao) throws NamingException, ValidacaoException, SQLException, Exception {
        try {
            return transacaoRepository.save(transacao);
        } catch (Exception e) {
            System.out.println("Erro ao salvar a transação: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public Transacao findById(Long id) throws NamingException, ValidacaoException, SQLException, Exception {

        if (id == null) {
            throw new ValidacaoException("Id da transação não pode ser nulo");
        }

        Optional<Transacao> transacaoOptional = transacaoRepository.findById(id);
        return transacaoOptional.orElse(null);

    }

    public void delete(Long id) throws NamingException, ValidacaoException, SQLException, Exception {
        if (id == null) {
            throw new ValidacaoException("ID da transação não pode ser nulo");
        }
        transacaoRepository.deleteById(id);
    }

    public List<Transacao> obterTransacoesPorTipo(TipoCategoriaEnum tipoCategoriaEnum) {
        
        return transacaoRepository.findAllByCategoriaTipo(tipoCategoriaEnum);
        
    }
}
