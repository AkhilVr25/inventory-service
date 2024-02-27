package com.microserviceprouduct.inventoryservice.repository;

import com.microserviceprouduct.inventoryservice.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
 //   Optional<Inventory> findBySkuCode(String skuCode);

    Optional<Inventory> findBySkuCodeIn(String skuCode);

  //   List<Object> findBySkuCodeIn(List<String> skuCode);
}
