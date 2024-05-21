package unipar.aluno.financeiro.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoRequest {

    @NotBlank(message = "Quantia não pode ser nula nem vazia")
    @NotNull(message = "Quantia não pode ser nula")
    @DecimalMin(value = "0.0", inclusive = false, message = "Quantia deve ser maior que zero")
    private double quantia;

    @NotBlank(message = "Data não pode ser nula nem vazia")
    @NotNull(message = "Data não pode ser nula")
    private LocalDate dtTransacao;

    @NotNull(message = "Categoria não pode ser nula")
    private Long categoria;

    @Size(max = 255, message = "O tamanho máximo da descrição é 255")
    @Size(min = 3, message = "O tamanho mínimo da descrição é 3")
    @NotNull(message = "Descrição não pode ser nula")
    @NotBlank(message = "Descrição não pode ser em branco")
    @Column(name = "descricao", length = 255)
    private String descricao;

}
