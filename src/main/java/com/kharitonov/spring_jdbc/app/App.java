package com.kharitonov.spring_jdbc.app;

import com.kharitonov.spring_jdbc.dao.CityDao;
import com.kharitonov.spring_jdbc.dao.CountryDao;
import com.kharitonov.spring_jdbc.entity.City;
import com.kharitonov.spring_jdbc.entity.Country;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        insertCity(context);
        context.close();
    }

    static void printCities(ClassPathXmlApplicationContext context) {
        CityDao dao = context.getBean(CityDao.class);
        City city = dao.findById(5);
        System.out.println(city);
        List<City> cities = dao.findAll();
        cities.forEach(System.out::println);
    }

    static void printCountries(ClassPathXmlApplicationContext context) {
        CountryDao dao = context.getBean(CountryDao.class);
        Country country = dao.findById("USA");
        System.out.println(country);
        List<Country> countries = dao.findAll();
        countries.forEach(System.out::println);
        context.close();
    }

    static void insertCity(ClassPathXmlApplicationContext context) {
        City city = new City();
        city.setName("Novogrudok");
        city.setCountryCode("BLR");
        city.setDistrict("Grodno");
        city.setPopulation(122000);
        CityDao dao =context.getBean(CityDao.class);
        int key = dao.insert(city);
        System.out.println(key);
    }
}
