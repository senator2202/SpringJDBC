package com.kharitonov.spring_jdbc.dao;

import com.kharitonov.spring_jdbc.entity.Entity;

import java.util.List;

public interface BaseDao<T extends Entity, K> {

    T findById(K id);

    List<T> findAll();

    int insert(T entity);

    /*void update(int id);

    void delete(int id);*/
}
