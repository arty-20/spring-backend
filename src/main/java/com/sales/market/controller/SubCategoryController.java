/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.controller;

import com.sales.market.data.dto.SubCategoryDTO;
import com.sales.market.data.model.SubCategory;
import com.sales.market.service.interfaz.GenericService;
import com.sales.market.service.interfaz.SubCategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subcategories")
public class SubCategoryController extends GenericController<SubCategory, SubCategoryDTO> {
    private SubCategoryService service;

    public SubCategoryController(SubCategoryService service) {
        this.service = service;
    }

    @Override
    protected GenericService getService() {
        return service;
    }
}
