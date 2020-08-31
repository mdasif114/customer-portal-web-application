package com.customer.portal.service;

import com.customer.portal.entities.User;
import com.customer.portal.validators.UserRegistrationDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.fail;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRegistrationServiceTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRegistrationService userRegService;

    private MockMvc mockMvc;

    private UserRegistrationDto userRegDto;

    @Before()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCreateUserValidation() {
        try {
            createUserObject();
            userRegService.save(userRegDto);
        } catch (Exception ex) {
            log.info("user already exists " + ex);
        }
    }

    @Test
    public void testEmailAlreadyExistsOrNot() {
        try {
            User user = userRegService.findByEmail("abcdefgh@customer.com");
            Assert.assertEquals("abcdefgh@customer.com", user.getEmail());
        } catch (UsernameNotFoundException unfe) {
            Assert.assertEquals("Invalid username or password.", unfe.getMessage());
        }
    }

    @Test
    public void testInvalidUserName() {
        try {
            userRegService.loadUserByUsername("abcdefghijklmno@customer.com");
            fail("User name is valid: ");
        } catch (UsernameNotFoundException unfe) {
            Assert.assertEquals("Invalid username or password.", unfe.getMessage());
        }
    }

    @Test
    public void testMobileNumberExistsOrNot() {
        try {
            User user = userRegService.findByMobileNumber("+123456789");
            Assert.assertEquals("+123456789", user.getMobileNumber());
        } catch (UsernameNotFoundException unfe) {
            Assert.assertEquals("Invalid username or password.", unfe.getMessage());
        }
    }

    private void createUserObject() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setAccountType("Individual");
        userRegistrationDto.setEmail("abcdefgh@customer.com");
        userRegistrationDto.setShopBasedIn("France");
        userRegistrationDto.setMobileNumber("+123456789");
        userRegistrationDto.setPassword("Abcd!23456");
        this.userRegDto = userRegistrationDto;
    }

}
