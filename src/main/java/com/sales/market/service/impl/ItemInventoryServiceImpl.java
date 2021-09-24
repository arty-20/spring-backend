package com.sales.market.service.impl;

import com.sales.market.data.model.Item;
import com.sales.market.data.model.ItemInventory;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.ItemInventoryRepository;
import com.sales.market.service.interfaz.ItemInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class ItemInventoryServiceImpl extends GenericServiceImpl<ItemInventory> implements ItemInventoryService {
    private final ItemInventoryRepository repository;

    @Value("${inventory.lowerBound}")
    private String lowerBound;

    @Value("${inventory.upperBound}")
    private String upperBound;

    @Override
    protected GenericRepository<ItemInventory> getRepository() {
        return repository;
    }

    @Override
    public ItemInventory saveItemInventoryDefault(Item item){
        ItemInventory itemInventory = repository.findByItem(item);
        if (itemInventory == null){

            itemInventory = new ItemInventory();
            itemInventory.setItem(item);
            itemInventory.setStockQuantity(BigDecimal.ZERO);
            itemInventory.setLowerBoundThreshold(new BigDecimal(lowerBound));
            itemInventory.setUpperBoundThreshold(new BigDecimal(upperBound));
            itemInventory.setTotalPrice(BigDecimal.ZERO);
            return save(itemInventory);
        }
        return itemInventory;
    }
}
