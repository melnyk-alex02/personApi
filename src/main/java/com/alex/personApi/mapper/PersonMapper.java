package com.alex.personApi.mapper;

import com.alex.personApi.dto.PersonDTO;
import com.alex.personApi.entity.Person;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {

    PersonDTO toDto(Person person);
}
