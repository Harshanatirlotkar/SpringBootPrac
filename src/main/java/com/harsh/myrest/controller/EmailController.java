package com.harsh.myrest.controller;

import com.harsh.myrest.entity.EmailRequest;
import com.harsh.myrest.service.DepartmentService;
import com.harsh.myrest.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private DepartmentService departmentService;

    @Value("${mail.table.header}")
    private String emailTableHeader;


    /*
    * {
    "to" : "harshanatirlotkar02@gmail.com",
    "subject" : "Test mail",
    "message" : "Welcome message from department",
    "name" : "user"
    }
    *
    * */
    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        Context context = new Context();
        // Set variables for the template from the POST request data
        context.setVariable("name", emailRequest.getName());
        context.setVariable("message", emailRequest.getMessage());
        context.setVariable("subject", emailRequest.getSubject());
        context.setVariable("departments", departmentService.fetchDepartmentList());
        context.setVariable("headers", emailTableHeader.split(","));

        try {
            emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), "emailTemplate", context);
            return "Email sent successfully!";
        } catch (Exception e) {
            return "Error sending email: " + e.getMessage();
        }
    }
}