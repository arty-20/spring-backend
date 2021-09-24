/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service.interfaz;


import com.sales.market.data.model.Employee;

public interface EmployeeService extends GenericService<Employee> {

    Employee findByEmail(String email);

    Employee findSystemEmployee();
}
