package com.sales.market.data.model;

import com.sales.market.data.dto.ItemInventoryEntryDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class ItemInventoryEntry extends ModelBase<ItemInventoryEntryDTO> {

    @ManyToOne
    private ItemInventory itemInventory;

    @Enumerated(EnumType.STRING)
    private MovementType movementType;

    @Column(precision = 16, scale = 6)
    private BigDecimal quantity; // represent sale or buy instances quantity

    @Column(precision = 16, scale = 6)
    private BigDecimal price;

    @Lob
    private String itemInstanceSkus; //represents a list of the sku of the involved item instances

}
