/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.data.dto;

import com.sales.market.data.model.Sale;

import java.util.Date;

public class SaleDTO extends DTOBase<Sale> {
    private Long employeeId;
    private Date date;
    private String description;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
