package com.kharitonov.spring_jdbc.dao;

import com.kharitonov.spring_jdbc.entity.City;

import java.util.List;

public interface CityDao {

    City findById(int id);

    List<City> findAll();

    /*void insert(City city);

    void update(int id);

    void delete(int id);*/
}
