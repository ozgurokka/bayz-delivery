package com.bayzdelivery.controller;

import com.bayzdelivery.model.Order;
import com.bayzdelivery.model.Person;
import com.bayzdelivery.model.PersonType;
import com.bayzdelivery.repositories.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
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
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ozgurokka on 1/15/22 5:06 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {
    MockMvc mockMvc;

    @Mock
    private OrderController orderController;

    @Autowired
    private TestRestTemplate template;

    private static ObjectMapper mapper = new ObjectMapper();


    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testGiveOrder() throws Exception {


        Person p = new Person();
        p.setId((long) 1);

        Order order = new Order();

        order.setId(1L);
        order.setPrice(new BigDecimal(10));
        order.setItem("iphone");
        order.setCustomer(p);
        //order.setOrderTime(Instant.now());


        Mockito.when(orderController.giveOrder(order)).thenReturn(ResponseEntity.ok(order));

        String json = mapper.writeValueAsString(order);

        mockMvc.perform(post("/api/order")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.item", Matchers.equalTo("iphone")));
    }

    @Test
    public void testgetALLRecords() throws  Exception{

        List<Order> orderList = new ArrayList<>();

        Mockito.when(orderController.getAllOrders()).thenReturn(ResponseEntity.ok(orderList));
        String json = mapper.writeValueAsString(orderList);
        mockMvc.perform(get("/api/order")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
