/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service.interfaz;

import com.sales.market.data.model.SubCategory;

import java.util.Set;

public interface SubCategoryService extends GenericService<SubCategory> {

    Set<SubCategory> findAllByCategoryId(Long id);
}
