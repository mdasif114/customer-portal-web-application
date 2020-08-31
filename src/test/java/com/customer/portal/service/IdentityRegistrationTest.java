package com.customer.portal.service;

import com.customer.portal.validators.IdentityRegistrationDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IdentityRegistrationTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private IdentityRegistrationService identityRegService;

    private MockMvc mockMvc;

    MockHttpSession session;

    @Before()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        session = new MockHttpSession();
    }

    @Test
    public void testAddIdentityDetails() {
        IdentityRegistrationDto identityDto = new IdentityRegistrationDto();
        identityDto.setDocumentType("Passport");
        identityDto.setDocumentNumber("123456");
        identityRegService.saveIdentityDetails(identityDto);
    }

    @Test
    @WithMockUser(username = "abcdefgh@customer.com")
    public void testGetIdentityDetails() throws Exception {
        session.setAttribute("userId", 123L);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/identityRegistration").session(session);
        this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("identityRegistration"))
                .andExpect(model().attributeExists("identity"));
    }
}
