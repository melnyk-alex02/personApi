package com.alex.personApi.repositoryTest;

import com.alex.personApi.entity.Person;
import com.alex.personApi.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    public PersonRepository personRepository;

    @Test
    @SqlGroup({
            @Sql(value = "classpath:/sqlScripts/reset.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(value = "classpath:/sqlScripts/person.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    }
    )
    void getPersonById(){
        Long givenId = 1L;

        Optional<Person> person = personRepository.findById(givenId);
        assertAll(() -> {
            assertTrue(person.isPresent());
            assertEquals(person.get().getId(), givenId);
        });

    }
}