package unipar.aluno.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unipar.aluno.financeiro.model.Categoria;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria, Long> {
}
