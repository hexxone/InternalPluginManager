/*
 * This file is part of RedstoneWorldManager
 * 
 */
package me.lobnews.util;

/**
 * 
 * @author Blockhaus2000
 */
public class Tag<T> {
    private T data;

    public Tag(final T data) {
        this.data = data;
    }

    public Tag() {
        this(null);
    }

    public T getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }
}
