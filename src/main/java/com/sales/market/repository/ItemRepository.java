/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.repository;


import com.sales.market.data.model.Item;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends GenericRepository<Item> {
    @Query("select count(i) from Item i where i.id = ?1")
    int countItemsById(long id);
}
