package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Food;
import com.techelevator.model.FoodDto;
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
    public Food addNewFood(FoodDto food) {
    Food newFood = null;
    String sql = "INSERT INTO food (food_name, food_type, serving_size, calories) VALUES (?, ?, ?, ?) RETURNING food_id";
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
        String sql = "SELECT food_id, food_name, food_type, serving_size, calories, num_of_servings, is_quick_food FROM food WHERE food_id = ?";
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
        String sql = "SELECT food_id, food_name, food_type, serving_size, calories, num_of_servings, is_quick_food FROM food";
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
    public List<Food> getFoodByName(String foodName, boolean useWildCard){
        List<Food> allFoodName = new ArrayList<>();
        if(useWildCard){
            foodName = "%" + foodName + "%";
        }
        String sql = "SELECT food_id, food_name, food_type, serving_size, calories, num_of_servings, is_quick_food FROM food WHERE food_name ILIKE ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + foodName + "%");
            while (results.next()) {
                Food getFoodName = mapRowToFood(results);
                allFoodName.add(getFoodName);
            }
        }catch (NumberFormatException e){
            System.out.println("Not a number");
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } return allFoodName;
    }
    @Override
    public int deleteFoodById(int foodId) {
            int numberOfRows = 0;
            String sql = "DELETE FROM food WHERE food_id = ?";
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
//    public int deleteFoodByName(String foodName) {
//          int numberOfRows = 0;
//        String sql = "DELETE from food WHERE food_name = ?";
//            try{
//                numberOfRows = jdbcTemplate.update(sql, foodName);
//            } catch (CannotGetJdbcConnectionException e) {
//                throw new DaoException("Unable to connect to server or database");
//            } catch (DataIntegrityViolationException e) {
//                throw new DaoException("Data Integrity violation", e);
//            }
//                return numberOfRows;
//    }

    @Override
    public List<Food> addFoodByName(String foodName){
        List<Food> foodItem = new ArrayList<>();
        String sql = "SELECT food_id, food_type, serving_size, calories, num_of_servings, is_quick_food FROM food WHERE food_name = ?";
            try {
                SqlRowSet results = jdbcTemplate.queryForRowSet(sql, foodName);
                if (results.next()) {
                    foodItem = (List<Food>) mapRowToFood(results);
                }
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            }
        return foodItem;

    }

//    @Override
//    public List<Food> getFoodName(String foodName) {
//        return null;
//    }

    public Food mapRowToFood(SqlRowSet rs) {
        Food fd = new Food();
        fd.setFoodId(rs.getInt("food_id"));
        fd.setFoodName(rs.getString("food_name"));
        fd.setFoodType(rs.getString("food_type"));
        fd.setServingSize(rs.getInt("serving_size"));
        fd.setCalories(rs.getDouble("calories"));
        fd.setNumOfServings(rs.getInt("num_of_servings"));
        fd.setQuickFood(rs.getBoolean("is_quick_food"));
        return fd;
    }
}
