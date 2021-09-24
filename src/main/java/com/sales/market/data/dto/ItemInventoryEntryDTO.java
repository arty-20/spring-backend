package com.sales.market.data.dto;

import com.sales.market.data.model.ItemInventoryEntry;
import com.sales.market.data.model.MovementType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author arturo
 */
@Getter
@Setter
public class ItemInventoryEntryDTO extends DTOBase<ItemInventoryEntry> {

    private MovementType movementType;

    private BigDecimal quantity;

    private String itemInstanceSkus;
}
