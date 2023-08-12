package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Food;
import com.techelevator.model.Meals;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMealsDao implements MealsDao{
    private final JdbcTemplate jdbcTemplate;
    private final FoodDao foodDao;
    public JdbcMealsDao(JdbcTemplate jdbcTemplate, FoodDao foodDao){
        this.jdbcTemplate = jdbcTemplate;
        this.foodDao = foodDao;
    }

    @Override
    public List<Meals> findAll(int userId){
        List<Meals> meals = new ArrayList<>();
        String sql = "SELECT m.meal_id, m.meal_type, m.log_day, m.is_quick_meal, f.food_id, f.food_name, f.food_type, f.serving_size, f.calories, f.num_of_servings " +
                        "FROM meal_user mu " +
                        "JOIN meals m ON mu.meal_id = m.meal_id " +
                        "JOIN meal_foods mf ON m.meal_id = mf.meal_id " +
                        "JOIN food f ON mf.food_id = f.food_id " +
                        "WHERE mu.user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()){
            Meals meal = mapRowToMeals(results);
            if(results.getInt("food_id") != 0){
                Food food = mapRowToFood(results);
                meal.addNewFood(food);
            }
            meals.add(meal);
        }
        return meals;
    }

    @Override
    public Meals createMeal(Meals meals, List<Food> foodList) {
        Meals newMeal = null;
        String sql = "INSERT INTO meal_user (user_id) VALUES (?) Returning meal_id";
        try {
            int mealId = jdbcTemplate.queryForObject(sql, int.class, meals.getMealId());
            newMeal = getMealById(mealId);

            String fSql = "INSERT INTO meal_foods (meal_id, food_id) VALUES (?, ?)";
            for(Food food : foodList){
                jdbcTemplate.update(sql, mealId, food.getFoodId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newMeal;
    }

    @Override
    public Meals getMealById(int mealId) {
        String sql = "SELECT m.meal_id, m.meal_type, m.log_day, m.is_quick_meal, f.food_id, f.food_name, f.food_type, f.serving_size, f.calories, f.num_of_servings " +
                "FROM meals m " +
                "JOIN meal_foods mf ON m.meal_id = mf.meal_id " +
                "JOIN food f ON mf.food_id = f.food_id " +
                "WHERE m.meal_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, mealId);
        if (results.next()) {
            Meals meal = mapRowToMeals(results);

            if (results.getInt("food_id") != 0) {
                Food food = mapRowToFood(results);
                meal.addNewFood(food);
        }
            return meal;
        }

        throw new DaoException("meal_user" + mealId + "was not found");
    }
    @Override
    public int deleteMealById(int mealId){
        int numberOfRows = 0;
        String fSql = "DELETE FROM meal_foods WHERE meal_id = ?";
        String mSql = "DELETE FROM meals WHERE meal_id = ?";
        String muSql = "DELETE FROM meal_user WHERE meal_id = ?";
        try{
            jdbcTemplate.update(fSql, mealId);
            jdbcTemplate.update(mSql, mealId);
            jdbcTemplate.update(muSql, mealId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }
    @Override
    public Meals updateMealsById(Meals meals){
        Meals updateMeals = null;
        String sql = "UPDATE meals SET meal_type = ?, log_day = ? WHERE meal_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql,meals.getMealType(), meals.getMealDate());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            updateMeals = getMealById(meals.getMealId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updateMeals;
    }
    @Override
    public Meals getMealDate(String date){
    String sql = "SELECT * FROM meals WHERE log_day = ?";
    SqlRowSet results = jdbcTemplate.queryForRowSet(sql, date);
    if(results.next()){
        return mapRowToMeals(results);
    }
    throw new DaoException();
    }

    private Meals mapRowToMeals(SqlRowSet sql){
        Meals meals = new Meals();
        meals.setProfileId(sql.getInt("user_id"));
        meals.setMealType(sql.getString("meal_type"));
        meals.setMealDate(sql.getString("log_day"));
        return meals;
    }

    public Food mapRowToFood(SqlRowSet rs) {
        Food food = new Food();
        food.setFoodId(rs.getInt("id"));
        food.setFoodName(rs.getString("food_name"));
        food.setFoodType(rs.getString("food_type"));
        food.setServingSize(rs.getInt("serving_size"));
        food.setCalories(rs.getDouble("calories"));
        food.setNumOfServings(rs.getInt("number_of_servings"));
        return food;
    }
}
