/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service.impl;

import com.sales.market.data.model.Employee;
import com.sales.market.repository.EmployeeRepository;
import com.sales.market.repository.GenericRepository;
import com.sales.market.service.interfaz.EmployeeService;
import org.springframework.stereotype.Service;

@Service("EmployeeService")
public class EmployeeServiceImpl extends GenericServiceImpl<Employee> implements EmployeeService {

    private EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<Employee> getRepository() {
        return repository;
    }

    @Override
    public Employee findByEmail(String email) {
        return repository.findByUserEmail(email);
    }

    @Override
    public Employee findSystemEmployee() {
        return repository.findByUserSystemTrue();
    }

}
