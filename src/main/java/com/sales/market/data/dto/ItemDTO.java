package com.sales.market.data.dto;

import com.sales.market.data.model.Item;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ItemDTO extends DTOBase<Item> {
    private String name;
    private String code;
    private Byte[] image;

}
