package br.ufscar.dc.dsw.AA1Veiculos.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = UniquePlacaValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniquePlaca {
    String message() default "{br.ufscar.dc.dsw.AA1Veiculos.validation.UniquePlaca.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
