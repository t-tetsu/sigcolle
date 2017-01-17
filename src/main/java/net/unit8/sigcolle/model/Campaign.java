package net.unit8.sigcolle.model;

import lombok.Data;
import org.seasar.doma.*;

import java.io.Serializable;

/**
 * @author kawasima
 */
@Entity
@Data
public class Campaign implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long campaignId;

    private String title;

    // Markdown description
    private String statement;

    private Long goal;

    private Long createdBy;
}
