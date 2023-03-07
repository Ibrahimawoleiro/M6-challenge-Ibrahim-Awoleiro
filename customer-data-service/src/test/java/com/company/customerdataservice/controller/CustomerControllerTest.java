package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CustomerController Repo;

    Customer customer;

    private ObjectMapper mapper = new ObjectMapper();
    String input;

    @Before
    public void setUp() throws Exception{
        customer = new Customer();
        customer.setFirstName("Messi");
        customer.setLastName("Ronaldo");
        customer.setAddress1("Football Street");
        customer.setAddress2("Ballon d'or Street");
        customer.setCity("Football City");
        customer.setCountry("bamboom");
        customer.setPhone("03084930");
        customer.setEmail("ronaldomessi@football.com");
        customer.setPostalCode("234423");
        customer.setCompany("Goal");
        customer.setState("CA");
        input = mapper.writeValueAsString(customer);
    }

    @Test
    public void createCustomer() throws Exception {
        mockMvc.perform(post("/Customer")
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void updateCustomer() throws Exception {
        customer.setFirstName("Updated");
        customer.setCompany("Leg Over");
        input=mapper.writeValueAsString(customer);
        mockMvc.perform(
                        put("/Customer")
                                .content(input)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/Customer/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getCustomer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/Customer/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getByState() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/Customer/getByState/CA")).andDo(print()).andExpect(status().isOk());
    }
}