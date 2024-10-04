package org.tix.lab4_1.DTO;

import lombok.Data;
import org.tix.lab4_1.model.mainDB.Cards;

import java.util.List;

@Data
public class ExpertMessageDTO {
    private String name;
    private String surname;
    private Double salary;
    private Double creditLimit;
    private List<Cards> preferredCards;
}
