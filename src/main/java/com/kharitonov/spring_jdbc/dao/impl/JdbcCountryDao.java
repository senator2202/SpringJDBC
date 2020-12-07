package com.kharitonov.spring_jdbc.dao.impl;

import com.kharitonov.spring_jdbc.dao.CountryDao;
import com.kharitonov.spring_jdbc.entity.Country;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class CountryRowMapper implements RowMapper<Country> {

    @Override
    public Country mapRow(ResultSet resultSet, int i) throws SQLException {
        Country country = new Country();
        country.setCode(resultSet.getString("Code"));
        country.setName(resultSet.getString("Name"));
        country.setContinent(resultSet.getString("Continent"));
        country.setRegion(resultSet.getString("Region"));
        country.setSurfaceArea(resultSet.getFloat("SurfaceArea"));
        country.setIndepYear(resultSet.getShort("IndepYear"));
        country.setPopulation(resultSet.getInt("Population"));
        country.setLifeExpectancy(resultSet.getFloat("LifeExpectancy"));
        country.setGnp(resultSet.getFloat("GNP"));
        country.setGnpOld(resultSet.getFloat("GNPOld"));
        country.setLocalName(resultSet.getString("LocalName"));
        country.setGovernmentFrom(resultSet.getString("GovernmentForm"));
        country.setHeadOfState(resultSet.getString("HeadOfState"));
        country.setCapital(resultSet.getInt("Capital"));
        country.setCode2(resultSet.getString("Code2"));
        return country;
    }
}

public class JdbcCountryDao implements CountryDao {
    private static final String SQL_SELECT_COUNTRIES = "SELECT * FROM country";
    private static final String SQL_SELECT_COUNTRY_BY_ID = SQL_SELECT_COUNTRIES + " WHERE CODE = ?";
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Country findById(String id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_COUNTRY_BY_ID, new Object[]{id}, new CountryRowMapper());
    }

    @Override
    public List<Country> findAll() {
        return jdbcTemplate.query(SQL_SELECT_COUNTRIES, new BeanPropertyRowMapper<>(Country.class));
    }

    @Override
    public int insert(Country entity) {
        return -1;
    }
}
