package com.techelevator.dao;

import com.techelevator.model.Food;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public class JdbcFoodDao implements FoodDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcFoodDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Food getFoodById(int foodId) {
        return null;
    }

    public List<Food> getFood() {
        return null;
    }

    private Food mapRowToFood(SqlRowSet rs){
        return null;
    }
}
