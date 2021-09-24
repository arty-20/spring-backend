package com.sales.market.data.dto;

import com.sales.market.data.model.Category;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CategoryDTO extends DTOBase<Category> {
    private String name;
    private String code;
    private Set<SubCategoryDTO> subCategories = new HashSet<>();


    @Override
    public CategoryDTO toDto(Category category, ModelMapper mapper) {
        CategoryDTO categoryDto = super.toDto(category, mapper);
        categoryDto.setSubCategories(new SubCategoryDTO().toSetDto(category.getSubCategories(), mapper));
        return categoryDto;
    }
}
