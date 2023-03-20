package com.alex.personApi.mapperTest;


import com.alex.personApi.dto.PersonDTO;
import com.alex.personApi.entity.Person;
import com.alex.personApi.mapper.PersonMapper;
import com.alex.personApi.mapper.PersonMapperImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PersonMapperTest {
	private static PersonMapper personMapper;

    @BeforeAll
    public static void setUp(){
        personMapper = new PersonMapperImpl();
    }

    @Test
    void testUserMapper(){
        Person person = new Person(1L,
                "Gru",
                "Salt",
                LocalDate.of(1989, 5, 21));

        PersonDTO personDTO = personMapper.toDto(person);

        assertAll(()->{
            assertEquals(person.getName(), personDTO.getName());
            assertEquals(person.getSurname(), personDTO.getSurname());
            assertEquals(person.getDateOfBirth(), personDTO.getDateOfBirth());
        });
    }
}