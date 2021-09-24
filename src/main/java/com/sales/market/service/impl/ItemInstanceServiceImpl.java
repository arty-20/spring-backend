package com.sales.market.service.impl;

import com.sales.market.data.model.*;
import com.sales.market.exception.OperationNotPermitException;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.ItemInstanceRepository;
import com.sales.market.service.interfaz.ItemInstanceService;
import com.sales.market.service.interfaz.ItemInventoryEntryService;
import com.sales.market.service.interfaz.ItemInventoryService;
import com.sales.market.service.interfaz.ItemService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemInstanceServiceImpl extends GenericServiceImpl<ItemInstance> implements ItemInstanceService {
    private final ItemInstanceRepository repository;
    private final ItemService itemService;
    private final ItemInventoryService itemInventoryService;
    private final ItemInventoryEntryService itemInventoryEntryService;

    @Override
    protected GenericRepository<ItemInstance> getRepository() {
        return repository;
    }

    @Override
    public ItemInstance bunchSave(ItemInstance itemInstance) {
        if (itemInstance.getItem() != null) {
            if (itemService.countItemsById(itemInstance.getItem().getId()) == 0){
                itemService.save(itemInstance.getItem());
            }
        }
        return super.bunchSave(itemInstance);
    }


    @Override
    public void registerMovementForItemInstances(List<ItemInstance> itemInstances, MovementType movementType) {
        if (movementType.equals(MovementType.BUY))
            itemInstances = itemInstances.stream().map(this::bunchSave).collect(Collectors.toList());
        Map<Long, ItemInstanceAux> itemInstanceAuxMap = convertForInventory(itemInstances);
        itemInstanceAuxMap.forEach( (k, v) -> registerMovement(movementType, v));
    }

    @Override
    public void removeItemsExpired() {
        registerMovementForItemInstances(repository.getItemInstanceExpired(LocalDate.now()), MovementType.REMOVED);
    }

    private void registerMovement(MovementType movementType, ItemInstanceAux aux){
        ItemInventory itemInventory = itemInventoryService.saveItemInventoryDefault(aux.getItem());
        System.out.println(movementType);
        switch (movementType){
            case BUY: {
                itemInventory.setStockQuantity(itemInventory.getStockQuantity().add(aux.getStock()));
                itemInventory.setTotalPrice(itemInventory.getTotalPrice().subtract(aux.getPrices()));
                repository.updateItemInstancetoAVAILABLE(aux.itemInstance);
                break;
            }
            case SALE:{
                BigDecimal rest = itemInventory.getStockQuantity().subtract(aux.getStock());
                if (rest.compareTo(BigDecimal.ZERO) >= 0){
                    itemInventory.setStockQuantity(itemInventory.getStockQuantity().subtract(aux.getStock()));
                    itemInventory.setTotalPrice(itemInventory.getTotalPrice().add(aux.getPrices()));
                    repository.updateItemInstancetoSOLD(aux.itemInstance);
                }else {
                    throw new OperationNotPermitException("operation not permit");
                }
                break;
            }
            case REMOVED:{
                BigDecimal rest = itemInventory.getStockQuantity().subtract(aux.getStock());
                if (rest.compareTo(BigDecimal.ZERO) >= 0){
                    itemInventory.setStockQuantity(itemInventory.getStockQuantity().subtract(aux.getStock()));
                    repository.updateItemInstancetoSCREWED(aux.itemInstance);
                }else {
                    throw new OperationNotPermitException("operation not permit");
                }
                break;
            }
        }
        ItemInventoryEntry entry = new ItemInventoryEntry();
        entry.setItemInstanceSkus(aux.getIdentifiers());
        entry.setMovementType(movementType);
        entry.setQuantity(aux.getStock());
        entry.setPrice(aux.getPrices());
        entry.setItemInventory(itemInventory);
        itemInventoryEntryService.save(entry);
    }

    private Map<Long, ItemInstanceAux> convertForInventory(List<ItemInstance> itemInstances){
        Map<Long, ItemInstanceAux> res = new HashMap<>();
        for (ItemInstance itemInstance : itemInstances) {
            long key = itemInstance.getItem().getId();
            if (res.containsKey(key)){
                res.put(key, new ItemInstanceAux(res.get(key), itemInstance));
            }
            res.put(key, new ItemInstanceAux(itemInstance));
        }
        return res;
    }

    @Getter
    static class ItemInstanceAux{
        private final String identifiers;
        private final BigDecimal prices;
        private final BigDecimal stock;
        private Item item;
        private ItemInstance itemInstance;

        public ItemInstanceAux(ItemInstance itemInstance) {
            this.identifiers = itemInstance.getIdentifier();
            this.prices = itemInstance.getPrice();
            stock = BigDecimal.ONE;
            item = itemInstance.getItem();
            this.itemInstance = itemInstance;
        }

        public ItemInstanceAux(ItemInstanceAux aux, ItemInstance itemInstance){
            identifiers = aux.getIdentifiers() +  " - " + itemInstance.getIdentifier();
            prices = aux.getPrices().add(itemInstance.getPrice());
            stock = aux.getStock().add(BigDecimal.ONE);
        }
    }
}
