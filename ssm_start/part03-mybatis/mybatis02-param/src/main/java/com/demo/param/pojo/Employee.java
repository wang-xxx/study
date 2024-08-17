package com.demo.param.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Integer empId;
    private String empName;
    private Double empSalary;
    private String empGender;

}
