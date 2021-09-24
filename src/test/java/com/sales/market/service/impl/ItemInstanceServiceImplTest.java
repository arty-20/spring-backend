package com.sales.market.service.impl;

import com.sales.market.data.model.Item;
import com.sales.market.data.model.ItemInstance;
import com.sales.market.data.model.MovementType;
import com.sales.market.repository.ItemInstanceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class ItemInstanceServiceImplTest {

    @Autowired
    private ItemInstanceServiceImpl itemInstanceService;

    @Autowired
    private ItemInstanceRepository itemInstanceRepository;

    @Test
    public void purchaseItems(){
        Item item1 = new Item();
        item1.setId(1L);
        Item item2 = new Item();
        item2.setCode("item2");
        item2.setId(2L);

        ItemInstance i1 = crerItemInstance(item1, "identi-1", new BigDecimal("3"), LocalDate.now().plusDays(1));
        ItemInstance i2 = crerItemInstance(item2, "identi-101", new BigDecimal("3"), LocalDate.now().minusMonths(1));
        List<ItemInstance> items =  new LinkedList<>(Arrays.asList(i1, i2));
        itemInstanceService.registerMovementForItemInstances(items, MovementType.BUY);

        List<ItemInstance> itemResult1 = itemInstanceRepository.getItemInstanceAVAILABLE();

        assertEquals(itemResult1.size(), 2);
    }

    @Test
    public void SaleItems(){
        Item item1 = new Item();
        item1.setId(1L);
        Item item2 = new Item();
        item2.setCode("item2");
        item2.setId(2L);

        ItemInstance i1 = crerItemInstance(item1, "identi-1", new BigDecimal("3"), LocalDate.now().plusDays(1));
        ItemInstance i2 = crerItemInstance(item2, "identi-101", new BigDecimal("3"), LocalDate.now().minusMonths(1));
        List<ItemInstance> items =  new LinkedList<>(Arrays.asList(i1, i2));
        itemInstanceService.registerMovementForItemInstances(items, MovementType.BUY);

        List<ItemInstance> itemResult1 = itemInstanceRepository.getItemInstanceAVAILABLE();

        i1.setId(5L);
        i2.setId(6L);

        boolean sinError = true;
        try {
            itemInstanceService.registerMovementForItemInstances(items, MovementType.SALE);
        }catch (Exception e){
            sinError = false;
        }

        assertEquals(itemResult1.size(), 2);
        assertFalse(sinError);
    }

    private ItemInstance crerItemInstance(Item item, String sku, BigDecimal price, LocalDate dateExpiry) {
        ItemInstance itemInstance = new ItemInstance();
        itemInstance.setItem(item);
        itemInstance.setFeatured(true);
        itemInstance.setPrice(price);
        itemInstance.setIdentifier(sku);
        itemInstance.setDateExpiry(dateExpiry);
        return itemInstance;
    }
}