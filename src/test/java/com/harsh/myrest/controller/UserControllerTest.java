package com.harsh.myrest.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harsh.myrest.entity.UserInfo;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateUser() throws Exception {
        var user = new UserInfo();
        user.setName("alisha");
        user.setPassword("pwd");
        user.setRoles("user");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("alishna"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value(Matchers.not("13112")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles").exists());
    }

    @Test
    public void testCreateUserWithoutPassword() throws Exception {
        var user = new UserInfo();
        user.setName("alisha");
        user.setPassword("");
        user.setRoles("user");

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/user/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
}
