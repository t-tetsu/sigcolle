package net.unit8.sigcolle.form;

import enkan.component.doma2.DomaProvider;
import lombok.Data;
import net.unit8.sigcolle.dao.UserDao;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.inject.Inject;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

/**
 * @auther takahashi
 */
@Data
public class UserForm extends FormBase {
    @Inject
    DomaProvider domaProvider;

    @DecimalMin("1")
    @DecimalMax("9999")
    private String userId;

    @NotBlank
    @Length(max = 20)
    private String firstName;

    @NotBlank
    @Length(max = 20)
    private String lastName;

    @NotBlank
    @Length(max = 50)
    private String email;

    @Length(min = 4, max = 20)
    private String pass;

    public Long getUserIdLong() {
        return Long.parseLong(userId);
    }

    @Override
    public boolean hasErrors() {
        return super.hasErrors();
    }


}
