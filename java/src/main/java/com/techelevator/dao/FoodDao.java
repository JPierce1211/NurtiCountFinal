package com.techelevator.dao;

import com.techelevator.model.Food;
import com.techelevator.model.FoodDto;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public interface FoodDao {


    Food addNewFood(FoodDto food);

    Food getFoodById(int foodId);

    List<Food> listFood();

    int deleteFoodById(int foodId);

    List<Food> addFoodByName(String foodName);

    Food mapRowToFood(SqlRowSet rs);
}

