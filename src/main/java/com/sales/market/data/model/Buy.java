/**
 * @author: Edson A. Terceros T.
 * 17
 */

package com.sales.market.data.model;

import com.sales.market.data.dto.BuyDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Buy extends ModelBase<BuyDTO> {

    @Column(precision = 10, scale = 5)
    private BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
