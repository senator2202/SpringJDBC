package com.kharitonov.spring_jdbc.dao.impl;

import com.kharitonov.spring_jdbc.dao.CityDao;
import com.kharitonov.spring_jdbc.entity.City;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

class CityRowMapper implements RowMapper<City> {

    @Override
    public City mapRow(ResultSet resultSet, int i) throws SQLException {
        City city = new City();
        city.setId(resultSet.getInt("ID"));
        city.setName(resultSet.getString("Name"));
        city.setCountryCode(resultSet.getString("CountryCode"));
        city.setDistrict(resultSet.getString("District"));
        city.setPopulation(resultSet.getInt("Population"));
        return city;
    }
}

public class JdbcCityDao implements CityDao {
    private static final String SQL_SELECT_CITIES = "SELECT ID, Name, CountryCode, District, Population FROM city";
    private static final String SQL_SELECT_CITY_BY_ID = SQL_SELECT_CITIES + " WHERE ID = ?";
    private static final String SQL_INSERT_CITY =
            "INSERT INTO city (Name, CountryCode, District, Population) VALUES (?, ?, ?, ?)";
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public City findById(Integer id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_CITY_BY_ID, new Object[]{id}, new CityRowMapper());
    }

    @Override
    public List<City> findAll() {
        /*List<City> cities = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL_SELECT_CITIES);
        for (Map<String, Object> row : rows) {
            City city = new City();
            city.setId((int) row.get("ID"));
            city.setName((String) row.get("Name"));
            city.setCountryCode((String) row.get("CountryCode"));
            city.setDistrict((String) row.get("District"));
            city.setPopulation((int) row.get("Population"));
            cities.add(city);
        }
        return cities;*/

        return jdbcTemplate.query(SQL_SELECT_CITIES, new BeanPropertyRowMapper<>(City.class));
    }

    @Override
    public int insert(City entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        /*jdbcTemplate.update(SQL_INSERT_CITY, new Object[]{entity.getName(), entity.getCountryCode(),
                entity.getDistrict(), entity.getPopulation()});*/

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_CITY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getCountryCode());
            ps.setString(3, entity.getDistrict());
            ps.setInt(4, entity.getPopulation());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }
}
