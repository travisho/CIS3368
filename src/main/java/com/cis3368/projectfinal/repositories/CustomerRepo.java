package com.cis3368.projectfinal.repositories;

import com.cis3368.projectfinal.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, String> {
}
