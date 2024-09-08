package com.us517.today.model;

public class DataModel<T> extends BaseModel {
    public T data;
    @Override
    public String toString() {
        return "SingleModel{" +
                "data=" + data +
                '}';
    }
}

