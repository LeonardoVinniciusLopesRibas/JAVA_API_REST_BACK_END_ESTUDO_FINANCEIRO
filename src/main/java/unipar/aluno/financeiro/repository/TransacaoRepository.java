package unipar.aluno.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unipar.aluno.financeiro.model.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
