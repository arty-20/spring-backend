/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.data.model;

import com.sales.market.data.dto.ItemDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@ToString
@Getter
@Setter
@Entity
public class Item extends ModelBase<ItemDTO> {

    private String name;

    private String code;

    private Byte[] image;

    @OneToOne(targetEntity = SubCategory.class)
    private SubCategory subCategory;



}
