package org.tix.lab4_1.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tix.lab4_1.DTO.ExpertMessageDTO;
import org.tix.lab4_1.model.mainDB.ExpertMessage;

@Mapper
public interface ExpertMessageMapper {
    @Mapping(source = "candidateName", target = "name")
    @Mapping(source = "candidateSurname", target = "surname")
    @Mapping(source = "candidateSalary", target = "salary")
    @Mapping(source = "candidateCreditLimit", target = "creditLimit")
    @Mapping(source = "preferredCards", target = "preferredCards")
    ExpertMessageDTO toDTO(ExpertMessage expertMessage);
}
