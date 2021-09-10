package com.cis3368.projectfinal.repositories;

import com.cis3368.projectfinal.models.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepo extends CrudRepository<Employee, String> {
}
