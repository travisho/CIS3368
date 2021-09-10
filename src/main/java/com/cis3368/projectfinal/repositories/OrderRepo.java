package com.cis3368.projectfinal.repositories;

import com.cis3368.projectfinal.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order, String> {
}
