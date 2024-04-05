package br.com.ntconsult.assembleia.application.v1.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfImpl implements ConstraintValidator<Cpf, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(!s.matches("\\d{11}")) return false;
        if (s.matches("(\\d)\\1{10}")) return false;

        int digito1 = getPrimeiroVerificador(s);
        int digito2 = getSegundoVerificador(s);

        return digito1 == Integer.parseInt(s.substring(9, 10)) && digito2 == Integer.parseInt(s.substring(10));
    }

    private static int getPrimeiroVerificador(String s) {
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Integer.parseInt(s.substring(i, i + 1)) * (10 - i);
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) digito1 = 0;

        return digito1;
    }

    private static int getSegundoVerificador(String s) {
        int soma;
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Integer.parseInt(s.substring(i, i + 1)) * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) digito2 = 0;

        return digito2;
    }
}
