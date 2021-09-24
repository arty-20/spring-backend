/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.data.model;

import com.sales.market.data.dto.ItemInstanceDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@Getter
@Setter
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "UK_ITEMINSTANCE_IDENTIFIER", columnNames = {"identifier"})})
public class ItemInstance extends ModelBase<ItemInstanceDTO> {

    @OneToOne
    private Item item;
    private String identifier;// sku

    private Boolean featured = Boolean.FALSE;

    @Column(precision = 16, scale = 6)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ItemInstanceStatus itemInstanceStatus;

    @Column(nullable = false)
    private LocalDate dateExpiry;

    /*@Override
    public ModelBase toDomain(ItemInstanceDto element, ModelMapper mapper) {
        super.toDomain(element, mapper);
        setItem((Item) new Item().toDomain(element.getItemDto(), mapper));
        return this;
    }*/
}
