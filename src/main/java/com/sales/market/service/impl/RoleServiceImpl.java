package com.sales.market.service.impl;

import com.sales.market.data.model.Role;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.RoleRepository;
import com.sales.market.service.interfaz.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role> implements RoleService {

    private RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<Role> getRepository() {
        return repository;
    }

}
