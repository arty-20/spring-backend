package com.sales.market.service.impl;

import com.sales.market.data.model.ItemInventoryEntry;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.ItemInventoryEntryRepository;
import com.sales.market.service.interfaz.ItemInventoryEntryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ItemInventoryEntryServiceImpl extends GenericServiceImpl<ItemInventoryEntry> implements ItemInventoryEntryService {
    private final ItemInventoryEntryRepository repository;

    @Override
    protected GenericRepository<ItemInventoryEntry> getRepository() {
        return repository;
    }
}
