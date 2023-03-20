package com.alex.personApi.controllerTest;

import com.alex.personApi.controller.PersonController;
import com.alex.personApi.dto.PersonDTO;
import com.alex.personApi.mapper.PersonMapperImpl;
import com.alex.personApi.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    public PersonController personController;
    @Autowired
    public PersonMapperImpl personMapper;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    PersonService personService;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    public void getPerson() throws Exception {
        PersonDTO personDTO = new PersonDTO(10L, "Semuel", "Jackson", LocalDate.of(1970, 1, 24));


        when(personService.getPerson(any()))
                .thenReturn(personDTO);

        mockMvc.perform(get("/api/person/{id}", 10L))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Semuel"));
    }
}
