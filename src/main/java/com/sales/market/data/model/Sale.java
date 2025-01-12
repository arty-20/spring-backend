/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.data.model;


import com.sales.market.data.dto.SaleDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Sale extends ModelBase<SaleDTO> {
    @OneToOne(optional = false)
    private Employee employee;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    private String description;
    private String image;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
