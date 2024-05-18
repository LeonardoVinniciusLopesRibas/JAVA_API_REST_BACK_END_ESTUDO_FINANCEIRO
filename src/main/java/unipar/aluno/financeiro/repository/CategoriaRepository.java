package unipar.aluno.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unipar.aluno.financeiro.model.Categoria;

public interface CategoriaRepository  extends JpaRepository<Categoria, Long> {
}
