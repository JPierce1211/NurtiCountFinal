package com.techelevator.service;

import com.techelevator.model.Food;
import org.springframework.stereotype.Component;

@Component
public interface FoodFactService {
    Food getFacts();

}
