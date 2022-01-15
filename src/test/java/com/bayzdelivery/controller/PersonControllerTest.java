package com.bayzdelivery.controller;

import com.bayzdelivery.model.PersonType;
import com.bayzdelivery.repositories.PersonRepository;
import com.bayzdelivery.model.Person;

import com.bayzdelivery.service.PersonServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {


  MockMvc mockMvc;

  @Mock
  private PersonController personController;

  @Autowired
  private TestRestTemplate template;

  @Autowired
  PersonRepository personRepository;
  private static ObjectMapper mapper = new ObjectMapper();


  @Before
  public void setup() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
  }

  @Test
  public void testUserShouldBeRegistered() throws  Exception{

    PersonType personType = new PersonType();
    personType.setId(1L);

    Person p = new Person();
    p.setEmail("okka@gmail.com");
    p.setId((long) 1);
    p.setName("Ozgur");
    p.setRegistrationNumber("1");
    p.setPersonType(personType);


    Mockito.when(personController.register(p)).thenReturn(ResponseEntity.ok(p));

    String json = mapper.writeValueAsString(p);

    mockMvc.perform(post("/api/person")
          .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
          .content(json).accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andDo(print())
          .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
          .andExpect(jsonPath("$.name", Matchers.equalTo("Ozgur")));

  }

  @Test
  public void testgetALLRecords() throws  Exception{

    List<Person> personList = new ArrayList<>();

    Mockito.when(personController.getAllPersons()).thenReturn(ResponseEntity.ok(personList));
    String json = mapper.writeValueAsString(personList);
    mockMvc.perform(get("/api/person")
            .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
            .content(json).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());
  }


  @Test
  public void testgetById() throws  Exception{

    PersonType personType = new PersonType();
    personType.setId(2L);

    Person p = new Person();
    p.setEmail("faruk@gmail.com");
    p.setId((long) 2);
    p.setName("faruk");
    p.setRegistrationNumber("2");
    p.setPersonType(personType);

    Mockito.when(personController.getPersonById(2L)).thenReturn(ResponseEntity.ok(p));
    String json = mapper.writeValueAsString(p);
    mockMvc.perform(get("/api/person/2")
            .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
            .content(json).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", Matchers.equalTo("faruk")))
            .andDo(print());
  }

}
