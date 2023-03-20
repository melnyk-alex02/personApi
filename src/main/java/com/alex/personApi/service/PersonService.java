package com.alex.personApi.service;

import com.alex.personApi.dto.PersonDTO;
import com.alex.personApi.exception.DataNotFoundException;
import com.alex.personApi.mapper.PersonMapper;
import com.alex.personApi.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
@Transactional
public class PersonService {
    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper){
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public PersonDTO getPerson(Long id){
        PersonDTO personDTO = personMapper.toDto(personRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("There is no person with id " + id)));

        personDTO.setAge(Period.between(personDTO.getDateOfBirth(), LocalDate.now()).getYears());

        return personDTO;
    }
}
