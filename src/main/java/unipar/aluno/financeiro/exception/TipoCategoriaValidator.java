package unipar.aluno.financeiro.exception;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import unipar.aluno.financeiro.model.TipoCategoriaEnum;

public class TipoCategoriaValidator implements ConstraintValidator<ValidTipoCategoria, TipoCategoriaEnum> {
    @Override
    public boolean isValid(TipoCategoriaEnum tipoCategoria, ConstraintValidatorContext constraintValidatorContext) {
        return tipoCategoria == TipoCategoriaEnum.RECEITA || tipoCategoria == TipoCategoriaEnum.DESPESA;
    }
}
