package com.sales.market.service.interfaz;

import com.sales.market.data.model.Item;
import com.sales.market.data.model.ItemInventory;

public interface ItemInventoryService extends GenericService<ItemInventory> {

    ItemInventory saveItemInventoryDefault(Item item);
}
