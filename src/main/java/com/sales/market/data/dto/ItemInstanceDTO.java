package com.sales.market.data.dto;

import com.sales.market.data.model.ItemInstance;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class ItemInstanceDTO extends DTOBase<ItemInstance> {

    private String identifier;

    private Boolean featured;

    private Double price;

    private LocalDate dateExpiry;

    private ItemDTO item;
}
