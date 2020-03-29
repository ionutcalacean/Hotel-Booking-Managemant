package com.example.demo_initializer.components;

public interface Observer  {
    /**
     * interface for ubservers to update all objects when observable object notify all
     * @param news the news to be updated
     */
    public void update(String news);
}
