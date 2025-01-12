/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.repository;

import com.sales.market.data.model.Employee;

public interface EmployeeRepository extends GenericRepository<Employee> {
    Employee findByUserEmail(String email);

    Employee findByUserSystemTrue();
}
