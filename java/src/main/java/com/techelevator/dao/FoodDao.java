package com.techelevator.dao;

import com.techelevator.model.Food;
import com.techelevator.model.User;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public interface FoodDao {


    Food getFoodById(int foodId);

    List<Food> listFood();

    int deleteFoodById(int foodId);

////    fooName was not added. This is a placeholder for it
//    Food deleteFoodByName();

    Food mapRowToFood(SqlRowSet rs);
}

