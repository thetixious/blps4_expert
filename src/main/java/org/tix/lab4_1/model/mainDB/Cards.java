package org.tix.lab4_1.model.mainDB;

import jakarta.persistence.Embeddable;
import lombok.Data;
import org.tix.lab4_1.model.util.Bonus;
import org.tix.lab4_1.model.util.CardType;
import org.tix.lab4_1.model.util.Goal;

@Data
@Embeddable
public class Cards {
    private Long id;
    private CardType type;
    private Double credit_limit;
    private String name;
    private Goal goal;
    private Bonus bonus;



}
