package com.organization_creative_competition.service;

import java.util.List;

public interface CrudService<T, I> {

    T getById(I id);

    T save(T t);

    T update(T t);

    void delete(I id);

    List<T> findAll();
}