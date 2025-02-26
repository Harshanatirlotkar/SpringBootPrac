package com.harsh.myrest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testWithoutLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/welcome"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void testWithLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/departments"))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }


    @Test
    @WithMockUser(username = "harsh", roles = "admin")
    public void testWithLoginPassword() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/departments"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    @WithMockUser(username = "test", roles = {"user"})
    public void testWithLoginPasswordUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/department/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
