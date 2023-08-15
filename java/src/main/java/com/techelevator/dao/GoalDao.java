package com.techelevator.dao;

import com.techelevator.model.Goals;

import java.time.LocalDate;
import java.util.List;

public interface GoalDao {

    List<Goals> list(int userId);

    List<Goals> getGoalsByDate(String date);

    List<Goals> getGoalsByTimeframe(int userId, String fromDate, String toDate);

    Goals getGoalByGoalId(int goalId);

    Goals createGoal(Goals goal);

    Goals updateGoal(Goals goal);


}
