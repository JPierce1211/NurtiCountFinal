package com.techelevator.dao;

import com.techelevator.model.Food;
import com.techelevator.model.User;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public interface FoodDao {


    Food getFoodById(int id);

    List<Food> listFood();

    Food deleteFoodById(int id);
    Food mapRowToFood(SqlRowSet rs);

    Food deleteFoodByName();
}
