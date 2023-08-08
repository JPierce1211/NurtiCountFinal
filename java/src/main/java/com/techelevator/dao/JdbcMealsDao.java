package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Meals;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMealsDao implements MealsDao{
    private final JdbcTemplate jdbcTemplate;
    public JdbcMealsDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Meals> findAllById(){
        List<Meals> meals = new ArrayList<>();
        String sql = "SELECT * FROM meal_user WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Meals meal = mapRowToMeals(results);
            meals.add(meal);
        }
        return  meals;
    }

    @Override
    public Meals createMeal(Meals meals) {
        Meals newMeal = null;
        String sql = "INSERT INTO meal_user (meal_type, log_day) VALUES (?,?) WHERE user_id = ? Returning meal_id";
        try {
            int mealId = jdbcTemplate.queryForObject(sql, int.class, meals.getMealType(), meals.getMealDate());
            newMeal = getMealByProfile(mealId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newMeal;
    }

    @Override
    public Meals getMealByProfile(int profileId){
        String sql = "SELECT * FROM meal_user WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        if (results.next()) {
            return mapRowToMeals(results);
        }
        throw new DaoException("meal_user" + profileId + "was not found");
    }
    @Override
    public int deleteMealById(int mealId){
        int numberOfRows = 0;
        String sql = "DELETE FROM meal_user WHERE meal_id = ?";
        try{
            numberOfRows = jdbcTemplate.update(sql, mealId);
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
        String sql = "UPDATE meal_user m JOIN food f ON m.meal_type SET log_day = ?, meal_type = ? WHERE meal_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, meals.getMealId(), meals.getMealType(), meals.getMealDate());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            updateMeals = getMealByProfile(meals.getMealId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updateMeals;
    }

    private Meals mapRowToMeals(SqlRowSet sql){
        Meals meals = new Meals();
        meals.setMealId(sql.getInt("meal_id"));
        meals.setProfileId(sql.getInt("user_id"));
        meals.setMealType(sql.getString("meal_type"));
        meals.setMealDate(sql.getDate("log_day").toLocalDate());
        return meals;
    }
}
