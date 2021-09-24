/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service.interfaz;

import com.sales.market.data.model.ItemInstance;
import com.sales.market.data.model.MovementType;

import java.util.List;

public interface ItemInstanceService extends GenericService<ItemInstance> {

    void registerMovementForItemInstances(List<ItemInstance> itemInstances, MovementType movementType);

    void removeItemsExpired();
}
