package com.sales.market.data.dto;

import com.sales.market.data.model.Employee;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmployeeDTO extends DTOBase<Employee> {
    private String firstName;
    private String lastName;
    private Byte[] image;

}
