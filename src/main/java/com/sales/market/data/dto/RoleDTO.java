package com.sales.market.data.dto;


import com.sales.market.data.model.Role;

public class RoleDTO extends DTOBase<Role> {

    private String name;

    public RoleDTO() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
