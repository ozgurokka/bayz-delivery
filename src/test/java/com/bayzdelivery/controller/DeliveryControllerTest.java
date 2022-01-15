package com.bayzdelivery.controller;

import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.model.Person;
import com.bayzdelivery.model.PersonType;
import com.bayzdelivery.repositories.DeliveryRepository;
import com.bayzdelivery.repositories.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.models.auth.In;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ozgurokka on 1/15/22 1:14 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeliveryControllerTest {

    MockMvc mockMvc;

    @Mock
    private DeliveryController deliveryController;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    DeliveryRepository deliveryRepository;

    private static ObjectMapper mapper = new ObjectMapper();


    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(deliveryController).build();
    }

    @Test
    public void testSaveDelivery() throws Exception {


        PersonType personCustomer = new PersonType();
        personCustomer.setId(1L);
        personCustomer.setName("1");

        PersonType deliverMan = new PersonType();
        deliverMan.setId(1L);
        deliverMan.setName("1");

        Person p1 = new Person();
        p1.setEmail("okka@gmail.com");
        p1.setId((long) 1);
        p1.setName("Ozgur");
        p1.setRegistrationNumber("1");
        p1.setPersonType(personCustomer);

        Person p2 = new Person();
        p2.setEmail("josh@gmail.com");
        p2.setId((long) 1);
        p2.setName("josh");
        p2.setRegistrationNumber("2");
        p2.setPersonType(deliverMan);


        Delivery delivery = new Delivery();
        delivery.setComission(BigDecimal.ONE);
        delivery.setStartTime(null);
        delivery.setEndTime(null);
        delivery.setDistance(150L);
        delivery.setId(1L);
        delivery.setCustomer(p1);
        delivery.setDeliveryMan(p2);
        delivery.setOrder(null);


        Mockito.when(deliveryController.createNewDelivery(delivery)).thenReturn(ResponseEntity.ok(delivery));

        String json = mapper.writeValueAsString(delivery);

        mockMvc.perform(post("/api/delivery")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.distance", Matchers.equalTo(150)))
                .andExpect(jsonPath("$.deliveryMan.email", Matchers.equalTo("josh@gmail.com")));
    }

    @Test
    public void testgetALLRecords() throws  Exception{

        Delivery delivery = new Delivery();

        Mockito.when(deliveryController.pickDelivery(-1L)).thenReturn(ResponseEntity.ok(delivery));
        String json = mapper.writeValueAsString(delivery);
        mockMvc.perform(get("/api/delivery")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

}