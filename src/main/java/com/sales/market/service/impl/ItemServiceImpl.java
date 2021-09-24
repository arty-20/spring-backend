/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service.impl;

import com.sales.market.data.model.Item;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.ItemRepository;
import com.sales.market.service.interfaz.ItemService;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl extends GenericServiceImpl<Item> implements ItemService {
    private final ItemRepository repository;

    public ItemServiceImpl(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<Item> getRepository() {
        return repository;
    }

    @Override
    public int countItemsById(long id) {
        return repository.countItemsById(id);
    }
}
