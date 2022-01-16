package com.bayzdelivery.service;

import com.bayzdelivery.model.Person;
import com.bayzdelivery.repositories.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by ozgurokka on 1/16/22 1:22 PM
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonServiceTest {

    MockMvc mockMvc;

    @InjectMocks
    PersonServiceImpl personService;

    @Mock
    PersonRepository personRepository;

    @Test
    public void testgetAllRecords() throws  Exception{

        List<Person> personList = new ArrayList<>();
        List<Person> result = new ArrayList<>();
        Person p = new Person();
        p.setName("ozgur");
        p.setId(1L);
        personList.add(p);


        Mockito.when(personRepository.findAll()).thenReturn(personList);

        result = personService.getAll();
        assertEquals("ozgur",result.get(0).getName());
        assertEquals(Long.valueOf(1),result.get(0).getId());

}

}

