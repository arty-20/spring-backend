/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.data.model;

import com.sales.market.data.dto.CategoryDTO;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category extends ModelBase<CategoryDTO> {
    private String name;
    private String code;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Set<SubCategory> subCategories = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

}
