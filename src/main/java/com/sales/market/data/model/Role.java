package com.sales.market.data.model;

import com.sales.market.data.dto.RoleDTO;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role extends ModelBase<RoleDTO> {

    @Column(nullable = false, length = 25)
    private String name;
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "role_id", nullable = false, updatable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "user_id", nullable = false, updatable = false)})
    private Set<User> users;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
