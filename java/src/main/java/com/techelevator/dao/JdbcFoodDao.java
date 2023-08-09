package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Food;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFoodDao implements FoodDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcFoodDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Food addNewFood(Food food) {
    Food newFood = null;
    String sql = "INSERT INTO food (food_name, food_type, serving_size, calories) VALUES (?, ?, ?,?) RETURNING food_id";
		try {
        int foodId = jdbcTemplate.queryForObject(sql, int.class, food.getFoodName(), food.getFoodType(), food.getServingSize(), food.getCalories());
       newFood = getFoodById(foodId);
    } catch (CannotGetJdbcConnectionException e) {
        throw new DaoException("Unable to connect to server or database", e);
    } catch (DataIntegrityViolationException e) {
        throw new DaoException("Data integrity violation", e);
    }
		return newFood;

}
    @Override
    public Food getFoodById(int foodId) {
        Food food = null;
        String sql = "SELECT id, meal_id, food_name, food_type, serving_size, calories, number_of_servings FROM food WHERE id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, foodId);
            if (results.next()) {
                food = mapRowToFood(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return food;
    }

    @Override
    public List<Food> listFood() {
        List<Food> foodList = new ArrayList<>();
        String sql = "SELECT id, meal_id, food_name, food_type, serving_size, calories, number_of_servings";
            try {
                SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
                while (results.next()) {
                    Food item = mapRowToFood(results);
                    foodList.add(item);
                }
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            }
                return foodList;
    }
    @Override
    public int deleteFoodById(int foodId) {
            int numberOfRows = 0;
            String sql = "DELETE FROM food WHERE id = ?";
            try {
                numberOfRows = jdbcTemplate.update(sql, foodId);
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            }
            return numberOfRows;
    }

//    // We did not add foodName so this is a placeholder for it
//    @Override
//    public Food deleteFoodByName() {
//        return null;
//    }

    public Food mapRowToFood(SqlRowSet rs) {
        Food fd = new Food();
        fd.setFoodId(rs.getInt("id"));
        fd.setMealId(rs.getInt("meal_id"));
        fd.setFoodName(rs.getString("food_name"));
        fd.setFoodType(rs.getString("food_type"));
        fd.setServingSize(rs.getInt("serving_size"));
        fd.setCalories(rs.getDouble("calories"));
        fd.setNumOfServings(rs.getInt("number_of_servings"));
        return fd;
    }
}
