package com.techelevator.dao;

import com.techelevator.model.Food;
import com.techelevator.model.User;

import java.util.List;

public interface FoodDao {


    Food getFoodById(int id);

    List<Food> getFood();
}
