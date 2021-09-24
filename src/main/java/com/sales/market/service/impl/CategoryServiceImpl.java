/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service.impl;

import com.sales.market.data.model.Category;
import com.sales.market.repository.CategoryRepository;
import com.sales.market.repository.GenericRepository;
import com.sales.market.service.interfaz.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category> implements CategoryService {
    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<Category> getRepository() {
        return repository;
    }
}
