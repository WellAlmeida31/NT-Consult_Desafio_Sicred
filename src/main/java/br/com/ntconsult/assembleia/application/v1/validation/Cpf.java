package br.com.ntconsult.assembleia.application.v1.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {CpfImpl.class})
public @interface Cpf {
    String message() default "CPF inválido, digite somente os numeros de um CPF válido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

