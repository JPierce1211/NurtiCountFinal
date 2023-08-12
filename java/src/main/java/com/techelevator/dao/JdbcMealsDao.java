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
        String sql = "SELECT meals.meal_id, meals.user_id, meals.meal_type, meals.log_day, meals.is_quick_meal, food.food_id, food.food_name, food.food_type, food.serving_size, food.calories, food.num_of_servings " +
                        "FROM meals " +
                        "JOIN meal_food ON meals.meal_id = meal_food.meal_id " +
                        "JOIN food ON meal_food.food_id = food.food_id " +
                        "WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()){
            Meals meal = mapRowToMeals(results);
            if(results.getInt("food_id") != 0){
                Food food = foodDao.mapRowToFood(results);
                meal.addNewFood(food);
            }
            meals.add(meal);
        }
        return meals;
    }

    @Override
    public Meals createMeal(Meals meals, List<Food> foodList) {
        Meals newMeal = null;
        String sql = "INSERT INTO meals (user_id, meal_type, log_day, is_quick_meal) VALUES (?, ?, ?, ?) RETURNING meal_id";
        try {
            int mealId = jdbcTemplate.queryForObject(sql, int.class, meals.getUserId(), meals.getMealType(), meals.getLogDay(), meals.isQuickMeal());
            newMeal = getMealById(mealId);

            String fSql = "INSERT INTO meal_food (meal_id, food_id) VALUES (?, ?)";
            for(Food food : foodList){
                jdbcTemplate.update(fSql, mealId, food.getFoodId());
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
        String sql = "SELECT meals.meal_id, meals.user_id, meals.meal_type, meals.log_day, meals.is_quick_meal, food.food_id, food.food_name, food.food_type, food.serving_size, food.calories, food.num_of_servings " +
                "FROM meals " +
                "JOIN meal_food ON meals.meal_id = meal_food.meal_id " +
                "JOIN food ON meal_food.food_id = food.food_id " +
                "WHERE meals.meal_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, mealId);
        if (results.next()) {
            Meals meal = mapRowToMeals(results);

            if (results.getInt("food_id") != 0) {
                Food food = foodDao.mapRowToFood(results);
                meal.addNewFood(food);
        }
            return meal;
        }

        throw new DaoException("Meal " + mealId + " was not found");
    }
    @Override
    public int deleteMealById(int mealId){
        int numberOfRows = 0;
        String fSql = "DELETE FROM meal_food WHERE meal_id = ?";
        String mSql = "DELETE FROM meals WHERE meal_id = ?";
        try{
            jdbcTemplate.update(fSql, mealId);
            jdbcTemplate.update(mSql, mealId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }
    @Override
    public Meals updateMealsById(Meals meals, int id){
        Meals updateMeals = null;
        String sql = "UPDATE meals SET meal_type = ?, log_day = ?, is_quick_meal = ? WHERE meal_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, meals.getMealType(), meals.getLogDay(), meals.isQuickMeal(), id);
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
        meals.setMealId(sql.getInt("meal_id"));
        meals.setUserId(sql.getInt("user_id"));
        meals.setMealType(sql.getString("meal_type"));
        meals.setLogDay(sql.getString("log_day"));
        meals.setQuickMeal(sql.getBoolean("is_quick_meal"));
        return meals;
    }

//    public Food mapRowToFood(SqlRowSet rs) {
//        Food food = new Food();
//        food.setFoodId(rs.getInt("id"));
//        food.setMealId(rs.getInt("meal_id"));
//        food.setFoodName(rs.getString("food_name"));
//        food.setFoodType(rs.getString("food_type"));
//        food.setServingSize(rs.getInt("serving_size"));
//        food.setCalories(rs.getDouble("calories"));
//        food.setNumOfServings(rs.getInt("number_of_servings"));
//        return food;
//    }
}
