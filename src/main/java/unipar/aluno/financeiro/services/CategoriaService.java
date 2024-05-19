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

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public void validateInsertUpdate(Categoria categoria) throws ValidacaoException {
        if (categoria == null) {
            throw new ValidacaoException("Categoria não pode ser nula");
        }

        String categoriaName = categoria.getCategoria();
        if (categoriaName == null || categoriaName.trim().isEmpty()) {
            throw new ValidacaoException("Categoria não pode ser nula");
        }

        if (categoriaName.trim().length() > 100) {
            throw new ValidacaoException("Categoria deverá ter no máximo 100 caracteres");
        }

        if (categoria.getTipo() == null) {
            throw new ValidacaoException("O tipo da categoria não pode ser nulo.");
        }

        if (!EnumSet.allOf(TipoCategoriaEnum.class).contains(categoria.getTipo())) {
            throw new ValidacaoException("O tipo da categoria é inválido.");
        }
    }

    public List<Categoria> findAll()  throws NamingException, ValidacaoException, SQLException, Exception {
        return categoriaRepository.findAll();
    }

    public Categoria save(Categoria categoria)  throws NamingException, ValidacaoException, SQLException, Exception {
        validateInsertUpdate(categoria);
        return categoriaRepository.save(categoria);
    }
}
