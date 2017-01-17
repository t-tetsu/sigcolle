package net.unit8.sigcolle.form;

import enkan.component.doma2.DomaProvider;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.inject.Inject;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.util.List;

/**
 * @auther takahashi
 */
@Data
public class CreateCampaignForm extends FormBase {
    @Inject
    DomaProvider domaProvider;

    @DecimalMin("1")
    @DecimalMax("9999")
    private String campaignId;

    @NotBlank
    @Length(max = 30)
    private String title;

    @NotBlank
    private String statement;

    @NotBlank
    @DecimalMin("100")
    private String goal;

    public Long getGoalLong() {
        return Long.parseLong(goal);
    }

    private String createdBy;

    public Long getCreatedByLong() {
        return Long.parseLong(createdBy);
    }

    @Override
    public boolean hasErrors() {
        return super.hasErrors();
    }

    @Override
    public boolean hasErrors(String name) {
        return super.hasErrors(name);
    }

    @Override
    public List<String> getErrors(String name) {
        return super.getErrors(name);
    }

}
