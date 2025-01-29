package com.harsh.myrest.controller;


import com.harsh.myrest.entity.Department;
import com.harsh.myrest.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    // Annotation
    @Autowired
    private DepartmentService departmentService;


    @GetMapping("/test")
    public ResponseEntity<String> test()
    {
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

    // Read operation
    @GetMapping("/departments")
    public ResponseEntity<List<Department>> fetchDepartmentList()
    {
        List<Department> departments = departmentService.fetchDepartmentList();
        return new ResponseEntity<>(departments, HttpStatus.OK);
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
}
