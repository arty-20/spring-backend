/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.data.model;

import com.sales.market.data.dto.ItemInventoryDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@ToString
@Getter
@Setter
@Entity
public class ItemInventory extends ModelBase<ItemInventoryDTO> {

    @OneToOne(targetEntity = Item.class)
    private Item item;

    @Column(precision = 16, scale = 6)
    private BigDecimal stockQuantity;

    @Column(precision = 16, scale = 6)
    private BigDecimal lowerBoundThreshold;

    @Column(precision = 16, scale = 6)
    private BigDecimal upperBoundThreshold;

    @Column(precision = 16, scale = 6)
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "itemInventory")
    @ToString.Exclude
    private List<ItemInventoryEntry> itemsInventoryEntry = new LinkedList<>();
}
