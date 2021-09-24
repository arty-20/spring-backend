package com.sales.market.data.dto;

import com.sales.market.data.model.Expense;
import com.sales.market.data.model.ExpenseType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ExpenseDTO extends DTOBase<Expense> {
    private ExpenseType expenseType;
    private Long value;
    private String description;

}
