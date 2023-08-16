package com.techelevator.dao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Food;
import com.techelevator.model.Meals;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcMealsDao implements MealsDao {
    private final JdbcTemplate jdbcTemplate;
    private final FoodDao foodDao;

    public JdbcMealsDao(JdbcTemplate jdbcTemplate, FoodDao foodDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.foodDao = foodDao;
    }

    @Override
    public List<Meals> findAll(int userId) {
        List<Meals> meals = new ArrayList<>();
        String sql = "SELECT * FROM meals WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            Meals meal = mapRowToMeals(results);
//            if (results.getInt("food_id") != 0) {
//                Food food = foodDao.mapRowToFood(results);
//                meal.addNewFood(food);
//            }
            meals.add(meal);
        }
        return meals;
    }

    @Override
    public Meals createMeal(Meals meals) {
        Meals newMeal = null;
        String sql = "INSERT INTO meals (user_id, meal_type, meal_name, meal_description, log_day, is_quick_meal) VALUES (?, ?, ?, ?, ?, ?) RETURNING meal_id";
        try {
            int mealId = jdbcTemplate.queryForObject(sql, int.class, meals.getUserId(), meals.getMealType(), meals.getMealName(), meals.getMealDescription(), meals.getLogDay(), meals.isQuickMeal());
            newMeal = getMealById(mealId);
//            String fSql = "INSERT INTO meal_food (meal_id, food_id) VALUES (?, ?)";
//            for (Food food : foodList) {
//                jdbcTemplate.update(fSql, mealId, food.getFoodId());
//            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newMeal;
    }

    //add condition that handles foodId of 0; Call method for insert sql to create food entity; then run addToMeal
    @Override
    public void addFoodToMeal(int foodId, String logDay, int meal_id) {
        String sql = "INSERT INTO meal_food (meal_id, food_id, log_day) VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(sql, meal_id, foodId, logDay);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

    }

//    @Override
//    public int getTotalCalories(int mealId){
//        Meals meals = new Meals();
//        String sql = "SELECT SUM(f.calories * f.num_of_servings) AS total_calories " +
//                "FROM meal_food mf " +
//                "JOIN food f ON mf.food_id = f.food_id " +
//                "JOIN meals m ON mf.meal_id = m.meal_id " +
//                "WHERE mf.meal_id = ? " +
//                "GROUP BY mf.meal_id, m.log_day;";
//        try{
//            int calories = jdbcTemplate.queryForObject(sql, int.class, mealId);
//            meals.setTotalCalories(calories);
//        }catch(Exception e){
//            throw new DaoException("Error retrieving total calories", e);
//        } return meals.getTotalCalories();
//    }

    @Override
    public Meals getMealById(int mealId) {
        String sql = "SELECT * FROM meals WHERE meal_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, mealId);
        if (results.next()) {
            Meals meal = mapRowToMeals(results);
//            if (results.getInt("food_id") != 0) {
//                Food food = foodDao.mapRowToFood(results);
//                meal.addNewFood(food);
//            }
            return meal;
        }
        throw new DaoException("meal_id" + mealId + "was not found");
    }

    @Override
    public int deleteMealById(int mealId) {
        int numberOfRows = 0;
        String fSql = "DELETE FROM meal_food WHERE meal_id = ?";
        String mSql = "DELETE FROM meals WHERE meal_id = ?";
        try {
            jdbcTemplate.update(fSql, mealId);
            jdbcTemplate.update(mSql, mealId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }

    @Override
    public Meals updateMealsById(Meals meals, int id) {
        Meals updateMeals = null;
        String sql = "UPDATE meals SET meal_type = ?, meal_name = ?, meal_description = ?, log_day = ?, is_quick_meal = ? WHERE meal_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, meals.getMealType(), meals.getMealName(), meals.getMealDescription(), meals.getLogDay(), meals.isQuickMeal(), id);
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
    public Meals getMealDate(String date) {
        String sql = "SELECT * FROM meals WHERE log_day = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, date);
        if (results.next()) {
            return mapRowToMeals(results);
        }
        throw new DaoException();
    }

    private Meals mapRowToMeals(SqlRowSet sql) {
        Meals meals = new Meals();
        meals.setMealId(sql.getInt("meal_id"));
        meals.setUserId(sql.getInt("user_id"));
        meals.setMealType(sql.getString("meal_type"));
        meals.setMealName(sql.getString("meal_name"));
        meals.setMealDescription(sql.getString("meal_description"));
        meals.setLogDay(sql.getString("log_day"));
        meals.setQuickMeal(sql.getBoolean("is_quick_meal"));
        return meals;
    }
}