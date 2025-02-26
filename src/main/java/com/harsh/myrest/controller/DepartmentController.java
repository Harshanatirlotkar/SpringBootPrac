package com.harsh.myrest.controller;


import com.harsh.myrest.entity.AuthRequest;
import com.harsh.myrest.entity.Department;
import com.harsh.myrest.service.DepartmentService;
import com.harsh.myrest.service.JwtService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/")
public class DepartmentController {

    private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);
    // Annotation
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private JwtService jwtService;


    //http://localhost:8082/v1/welcome
    @GetMapping("/welcome")
    public ResponseEntity<String> test()
    {
        log.info("INFO log using Lombok Slf4j ");
        log.error("ERROR log using Lombok Slf4j ");
        log.warn("WARN log using Lombok Slf4j ");
        return new ResponseEntity<>("Hello from my test app", HttpStatus.OK);
    }

    // Save operation
    @PostMapping("/department")
    public ResponseEntity<Department> saveDepartment(
             @Valid @RequestBody Department department)
    {
        Department depDB = departmentService.saveDepartment(department);
        return new ResponseEntity<>(depDB,HttpStatus.CREATED);
    }

    // http://localhost:8082/v1/departments
    @GetMapping("/departments")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<List<Department>> fetchDepartmentList()
    {
        List<Department> departments = departmentService.fetchDepartmentList();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    //http://localhost:8082/v1/department/1
    @GetMapping("/department/{id}")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Department> fetchDepartmentById(@PathVariable("id") Long departmentId)
    {
        Department department1 = departmentService.getDepById(departmentId);
        return new ResponseEntity<>(department1, HttpStatus.OK);
    }

    // Update operation
    @PutMapping("/department/{id}")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department,
                     @PathVariable("id") Long departmentId)
    {
        Department department1 = departmentService.updateDepartment(
                department, departmentId);

        return new ResponseEntity<>(department1,HttpStatus.OK);
    }

    // Delete operation
    @DeleteMapping("/department/{id}")
    public ResponseEntity<HttpStatus> deleteDepartmentById(@PathVariable("id")
                                               Long departmentId)
    {
        departmentService.deleteDepartmentById(
                departmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // http://localhost:8082/v1/authenticate
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateRequest(@RequestBody AuthRequest authRequest)
    {
        String token = jwtService.generateToken(authRequest.getUsername());
        return new ResponseEntity<>(token,HttpStatus.CREATED);
    }

}
