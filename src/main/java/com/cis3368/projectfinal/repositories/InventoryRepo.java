package com.cis3368.projectfinal.repositories;

import com.cis3368.projectfinal.models.Inventory;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepo extends CrudRepository<Inventory, String> {
}
