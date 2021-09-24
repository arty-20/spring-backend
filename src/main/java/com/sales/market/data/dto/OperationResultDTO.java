package com.sales.market.data.dto;

import java.util.ResourceBundle;

public class OperationResultDTO<T> {

    private String message;
    private T dataTransferObject;
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("application");

    public OperationResultDTO() {
        super();
    }

    public OperationResultDTO(String key) {
        super();
        setMessage(key);
    }

    public OperationResultDTO(String key, T dataTransferObject) {
        super();
        setMessage(key);
        this.dataTransferObject = dataTransferObject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String key) {
        this.message = RESOURCE_BUNDLE.getString(key);
    }

    public T getDataTransferObject() {
        return dataTransferObject;
    }

    public void setDataTransferObject(T dataTransferObject) {
        this.dataTransferObject = dataTransferObject;
    }

}
