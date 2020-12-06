package com.kharitonov.spring_jdbc.app;

import com.kharitonov.spring_jdbc.dao.CityDao;
import com.kharitonov.spring_jdbc.entity.City;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CityDao dao = context.getBean("cityDao", CityDao.class);
        City city = dao.findById(5);
        System.out.println(city);
        List<City> cities = dao.findAll();
        cities.forEach(System.out::println);
        context.close();
    }
}
