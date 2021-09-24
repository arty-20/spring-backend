package com.sales.market.data.dto;

import com.sales.market.data.model.ItemInventory;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author arturo
 */

@Getter
@Setter
public class ItemInventoryDTO extends DTOBase<ItemInventory> {

    private ItemDTO item;
    private BigDecimal stockQuantity;
    private BigDecimal lowerBoundThreshold;
    private BigDecimal upperBoundThreshold;
    private BigDecimal totalPrice;
    private List<ItemInventoryEntryDTO> itemsInventoryEntry;
}
