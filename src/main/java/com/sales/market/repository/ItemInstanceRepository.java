/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.repository;


import com.sales.market.data.model.ItemInstance;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
public interface ItemInstanceRepository extends GenericRepository<ItemInstance> {

    @Modifying
    @Query("update ItemInstance i set i.itemInstanceStatus = 'SOLD' where i = ?1")
    void updateItemInstancetoSOLD(ItemInstance itemInstance);

    @Modifying
    @Query("update ItemInstance i set i.itemInstanceStatus = 'AVAILABLE' where i = ?1")
    void updateItemInstancetoAVAILABLE(ItemInstance itemInstance);

    @Modifying
    @Query("update ItemInstance i set i.itemInstanceStatus = 'SCREWED' where i = ?1")
    void updateItemInstancetoSCREWED(ItemInstance itemInstance);

    @Query("select i from ItemInstance i where i.itemInstanceStatus <> 'SCREWED' and i.dateExpiry < ?1")
    List<ItemInstance> getItemInstanceExpired(LocalDate date);

    @Query("select i from ItemInstance i where i.itemInstanceStatus = 'AVAILABLE'")
    List<ItemInstance> getItemInstanceAVAILABLE();

    @Query("select i from ItemInstance i where i.itemInstanceStatus = 'SOLD'")
    List<ItemInstance> getItemInstanceSOLD();

    @Query("select i from ItemInstance i where i.itemInstanceStatus = 'SCREWED'")
    List<ItemInstance> getItemInstanceSCREWED();
}
