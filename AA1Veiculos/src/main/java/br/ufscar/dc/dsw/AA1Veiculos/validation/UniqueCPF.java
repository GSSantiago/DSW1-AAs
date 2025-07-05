package br.ufscar.dc.dsw.AA1Veiculos.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueCPFValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCPF {
    String message() default "CPF já cadastrado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}