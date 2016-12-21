package net.unit8.sigcolle.form;

import enkan.component.doma2.DomaProvider;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.inject.Inject;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

/**
 * @auther takahashi
 */
@Data
public class LoginForm extends FormBase {
    @Inject
    DomaProvider domaProvider;

    @NotBlank
    @Length(max = 50)
    private String email;

    @Length(min = 4, max = 20)
    private String pass;

    @Override
    public boolean hasErrors() {
        return super.hasErrors();
    }


}
