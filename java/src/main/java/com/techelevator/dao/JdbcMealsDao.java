package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Meals;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcMealsDao implements MealsDao {
    private final JdbcTemplate jdbcTemplate;
    public JdbcMealsDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Meals findMealById(int mealId){
        String sql = "SELECT * FROM meal_user WHERE meal_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        if (results.next()) {
            return mapRowToMeals(results);
        }
        throw new DaoException("meal_user" + mealId + "was not found");
    }
    @Override
    public List<Meals> findAll(){
        List<Meals> meals = new ArrayList<>();
        String sql = "SELECT * FROM meal_user";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Meals meal = mapRowToMeals(results);
            meals.add(meal);
        }
        return  meals;
    }

    @Override
    public Meals updateMeals(Meals meals){
        Meals updateMeals = null;
        String sql 
    }
//    @Override
//    public Meals createMeal(Meals meals){
//        Meals newMeal = null;
//        String sql = "INSERT INTO meal_user (meal_type, log_day) VALUES (?,?) Returning meal_id";
//        try{
//            int mealId = jdbcTemplate.queryForObject(sql, int.class, meals.getMealType(), meals.getMealDate());
//            newMeal =
//        }

    }
    private Meals mapRowToMeals(SqlRowSet sql){
        Meals meals = new Meals();
        meals.setMealId(sql.getInt("meal_id"));
        meals.getMealDate(sql.getDate("log_day").toLocalDate());
        meals.getUserId(sql.getInt("user_id"));
        meals.getMealType(sql.getString("meal_type"));
        return meals;
    }
}
