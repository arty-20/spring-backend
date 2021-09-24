/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.controller;

import com.sales.market.data.dto.ItemDTO;
import com.sales.market.data.model.Item;
import com.sales.market.service.interfaz.GenericService;
import com.sales.market.service.interfaz.ItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController extends GenericController<Item, ItemDTO> {
    private ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @Override
    protected GenericService getService() {
        return service;
    }
}
