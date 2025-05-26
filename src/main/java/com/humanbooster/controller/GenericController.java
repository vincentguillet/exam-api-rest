package com.humanbooster.controller;

import java.util.List;

public interface GenericController<T, ID> {
    void create(T entity);
    T read(ID id);
    void update(ID id);
    void delete(ID id);
    List<T> getAll();
}