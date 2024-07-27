package com.softulp.appgmaldonado.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Wrapper<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @SerializedName("$id")
    private String id;

    @SerializedName("$values")
    private List<T> values;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }
}
