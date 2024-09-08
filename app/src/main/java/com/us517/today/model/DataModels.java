package com.us517.today.model;

import java.util.List;

public class DataModels<T> extends BaseModel {
    public List<T> data;


    @Override
    public String toString() {
        return "DataModels{" +
                "data=" + data +
                '}';
    }

}
