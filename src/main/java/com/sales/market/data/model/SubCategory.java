/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.data.model;

import com.sales.market.data.dto.SubCategoryDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class SubCategory extends ModelBase<SubCategoryDTO> {
    private String name;
    private String code;
    @ManyToOne
    private Category category;

    private Long cateId;  /// DTO


}
