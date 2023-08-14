package com.techelevator.dao;

import com.techelevator.model.Goals;

import java.time.LocalDate;
import java.util.List;

public interface GoalDao {

    List<Goals> list();

    List<Goals> getGoalsByDate(String date);

    List<Goals> getGoalsByTimeframe(String fromDate, String toDate);

    Goals getGoalsByUserId(int userId);

    Goals getGoalByGoalId(int goalId);

    Goals createGoal(Goals goal);

    Goals updateGoal(Goals goal);


}
