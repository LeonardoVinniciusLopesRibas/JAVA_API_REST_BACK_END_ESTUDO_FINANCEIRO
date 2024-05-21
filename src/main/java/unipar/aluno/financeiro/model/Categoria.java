package unipar.aluno.financeiro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 3, message = "O tamanho mínimo do nome da Categoria é 3")
    @Size(max = 100, message = "O tamanho máximo do nome da Categoria é 100")
    @NotNull(message = "Nome da categoria não pode ser nula")
    @NotBlank(message = "Nome da categoria não pode ser em branco")
    @Column(name = "categoria", length = 100)
    private String categoria;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O tipo da categoria não pode ser nulo")
    private TipoCategoriaEnum tipo;

}
