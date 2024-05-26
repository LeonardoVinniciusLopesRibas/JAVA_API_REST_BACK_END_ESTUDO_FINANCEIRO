package unipar.aluno.financeiro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unipar.aluno.financeiro.exception.ValidacaoException;
import unipar.aluno.financeiro.model.Categoria;
import unipar.aluno.financeiro.model.TipoCategoriaEnum;
import unipar.aluno.financeiro.repository.CategoriaRepository;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> findAll() throws NamingException, ValidacaoException, SQLException {
        return categoriaRepository.findAll();
    }

    public Categoria save(Categoria categoria) throws NamingException, ValidacaoException, SQLException {
        return categoriaRepository.save(categoria);
    }

    public Categoria findById(Long id) throws NamingException, ValidacaoException, SQLException {
        if (id == null) {
            throw new ValidacaoException("ID da categoria não pode ser nulo");
        }

        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        return categoriaOptional.orElse(null);
    }

    public void delete(Long id) throws NamingException, ValidacaoException, SQLException{
        if (id == null) {
            throw new ValidacaoException("ID da categoria não pode ser nulo");
        }
        categoriaRepository.deleteById(id);
    }
}
