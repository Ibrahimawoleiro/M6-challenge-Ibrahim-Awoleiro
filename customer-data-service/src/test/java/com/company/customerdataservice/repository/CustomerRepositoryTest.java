package com.company.customerdataservice.repository;

import com.company.customerdataservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
 @SpringBootTest
public class CustomerRepositoryTest {
    @Autowired
    CustomerRepository CustomTestrepo;
    Customer customer;

    @Before
    public void setUp() throws Exception {
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
        customer.setState("Best");
        CustomTestrepo.save(customer);
    }

    @Test
    public void shouldAddCustomer(){
        Customer custom = new Customer();
        custom.setFirstName("Mes");
        custom.setLastName("Ronald");
        custom.setAddress1("Football ");
        custom.setAddress2("street");
        custom.setCity("Football City");
        custom.setCountry("bamboom");
        custom.setPhone("0930");
        custom.setEmail("ronaldmes@football.com");
        custom.setPostalCode("234423");
        custom.setCompany("Goa");
        custom.setState("Bes");

        custom=CustomTestrepo.save(custom);

        Optional<Customer> custommer = CustomTestrepo.findById(custom.getCustomerid());

        assertEquals(custommer.get().getCustomerid(), custom.getCustomerid());
    }

    @Test
    public void shouldUpdateCustomer(){
        customer.setFirstName("Updated");
        CustomTestrepo.save(customer);
        Optional<Customer> customer3 = CustomTestrepo.findById(customer.getCustomerid());
        assertEquals(customer3.get().getFirstName(),"Updated");
    }

    @Test
    public void shouldDeleteCustomer(){
        CustomTestrepo.deleteById(customer.getCustomerid());
        Optional<Customer> customer2 = CustomTestrepo.findById(customer.getCustomerid());
        assertFalse(customer2.isPresent());
    }

    @Test
    public void shouldGetCustomerByID(){
        Optional<Customer> customer1=CustomTestrepo.findById(customer.getCustomerid());
        assertEquals(customer1.get().getCustomerid(),customer.getCustomerid());
    }

    @Test
    public void shouldGetCustomerByState(){
        List<Customer> customer1=CustomTestrepo.findByState("Best");
        assertEquals(customer1.get(0).getState(),customer.getState());
    }
}