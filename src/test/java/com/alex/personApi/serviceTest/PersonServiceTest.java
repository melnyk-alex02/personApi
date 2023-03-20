package com.alex.personApi.serviceTest;

import com.alex.personApi.dto.PersonDTO;
import com.alex.personApi.entity.Person;
import com.alex.personApi.repository.PersonRepository;
import com.alex.personApi.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Test
    void testPersonData() {
        Optional<Person> person = Optional.of(new Person(10L,
                "Paul",
                "Porter",
                LocalDate.of(1987, 4, 14))
        );

        Mockito.when(personRepository.findById(10L)).thenReturn(person);

        PersonDTO personDTO = personService.getPerson(10L);

        verify(personRepository).findById(10L);
        assertAll(() -> {
            assertEquals("Paul", personDTO.getName());
            assertEquals("Porter", personDTO.getSurname());
        });
    }
}
