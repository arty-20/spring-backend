/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service.interfaz;

import com.sales.market.data.model.Item;

public interface ItemService extends GenericService<Item> {

    int countItemsById(long id);
}
