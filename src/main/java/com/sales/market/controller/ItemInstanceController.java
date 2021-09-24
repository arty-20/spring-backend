/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.controller;

import com.sales.market.data.dto.ItemInstanceDTO;
import com.sales.market.data.model.ItemInstance;
import com.sales.market.data.model.MovementType;
import com.sales.market.service.interfaz.ItemInstanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/iteminstances")
public class ItemInstanceController extends GenericController<ItemInstance, ItemInstanceDTO> {
    private ItemInstanceService service;

    public ItemInstanceController(ItemInstanceService service) {
        this.service = service;
    }

    @PostMapping("/{movement}")
    public ResponseEntity<?> manageItemsInstaces(@RequestBody List<ItemInstanceDTO> dtos,
                                                 @PathVariable("movement")MovementType movementType){
        List<ItemInstance> itemInstances = toModel(dtos);
        service.registerMovementForItemInstances(itemInstances,movementType);
        return new ResponseEntity<>("successful process", HttpStatus.OK);
    }

    @PostMapping("/expired")
    public ResponseEntity<?> removeItemInstaceExpired(){
        service.removeItemsExpired();
        return new ResponseEntity<>("successful process", HttpStatus.OK);
    }

    @Override
    protected ItemInstanceService getService() {
        return service;
    }
}
