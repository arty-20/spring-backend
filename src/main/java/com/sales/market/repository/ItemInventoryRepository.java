package com.sales.market.repository;

import com.sales.market.data.model.Item;
import com.sales.market.data.model.ItemInventory;
import org.springframework.data.jpa.repository.Query;

public interface ItemInventoryRepository extends GenericRepository<ItemInventory> {

    @Query("select inv from ItemInventory inv where inv.item = ?1")
    ItemInventory findByItem(Item item);
}
