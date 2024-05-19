package unipar.aluno.financeiro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unipar.aluno.financeiro.dto.TransacaoFindAllResponse;
import unipar.aluno.financeiro.exception.ValidacaoException;
import unipar.aluno.financeiro.model.Categoria;
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

    public void validateInsertUpdate(Transacao transacao) throws ValidacaoException {
        if (transacao.getDt_transacao() == null) {
            throw new ValidacaoException("Data não pode ser nula");
        }
        if (transacao.getDescricao().trim().length() > 255) {
            throw new ValidacaoException("Descrição deverá ter no máximo 255 caracteres");
        }
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
        validateInsertUpdate(transacao);
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
}
