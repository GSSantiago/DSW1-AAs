package br.ufscar.dc.dsw.AA2Veiculos.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = UniqueChassiValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueChassi {
    String message() default "{br.ufscar.dc.dsw.AA2Veiculos.validation.UniqueChassi.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
