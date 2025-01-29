package com.harsh.myrest.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;

    @NotNull
    @NotBlank
    @NotEmpty
    private String departmentName;

    @NotBlank(message = "Department address is mandatory")
    private String departmentAddress;
    private String departmentCode;

    @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number")
    private String depMobile;

    @Email(message = "Invalid email id")
    private String depEmail;
}
