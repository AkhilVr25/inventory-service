package com.microserviceprouduct.inventoryservice.service;

import com.microserviceprouduct.inventoryservice.dto.InventoryResponse;
import com.microserviceprouduct.inventoryservice.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j



public class InventoryService {
    private final InventoryRepository inventoryRepository;


    public boolean isInStock(String skuCode) {

        return inventoryRepository.findBySkuCodeIn(skuCode).isPresent();

    }

    public List<InventoryResponse> getInventoryAvailability(List<String> skuCodes) {

       List<InventoryResponse> result = new ArrayList<>();
       for (String skuCode : skuCodes) {
            InventoryResponse inventoryResponse = new InventoryResponse();
          log.info("skuCode :{}",skuCode);
          inventoryResponse.setSkuCode(skuCode);           inventoryResponse.setInStock(inventoryRepository.findBySkuCodeIn(skuCode).isPresent());
            result.add(inventoryResponse);
        }      return result;

  }
     public List<InventoryResponse>isInStock(List<String>skuCode){
        log.info("Checking Inventory");
        return inventoryRepository.findBySkuCodeIn(skuCode.toString())
                .stream().map(inventory->
                        InventoryResponse
                                .builder()
                                .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity()> 0 )
                                .build())
                .toList();
       }


}
