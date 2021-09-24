package com.sales.market.data.dto;

import com.sales.market.data.model.User;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class UserDTO extends DTOBase<User> {

    private String firstName;
    private String lastName;
    @Email(message = "{messages.validation.emailFormatError}")
    @NotBlank(message = "{messages.validation.emptyEmail}")
    private String email;
    private String password;
    private List<RoleDTO> roles;
    private EmployeeDTO employee;

    public UserDTO() {
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    @SuppressWarnings("unchecked")
    @Override
    public DTOBase<User> toDto(User user, ModelMapper mapper) {
        super.toDto(user, mapper);
        this.setPassword("");
        setEmployee(new EmployeeDTO());
        mapper.map(user.getEmployee(), getEmployee());
        return this;
    }
}
