package org.tix.lab4_1.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tix.lab4_1.DTO.CreditCardDTO;
import org.tix.lab4_1.model.mainDB.Cards;

@Mapper
public interface CreditCardMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "goal", target = "goal")
    @Mapping(source = "type", target = "cardType")
    @Mapping(source = "bonus", target = "bonus")
    @Mapping(source = "credit_limit", target = "creditLimit")
    CreditCardDTO toDTO(Cards card);


}
