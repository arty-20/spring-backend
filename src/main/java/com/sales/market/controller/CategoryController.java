/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.controller;

import com.sales.market.data.dto.CategoryDTO;
import com.sales.market.data.model.Category;
import com.sales.market.service.interfaz.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController extends GenericController<Category, CategoryDTO> {
    private CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @Override
    protected CategoryService getService() {
        return service;
    }
}
