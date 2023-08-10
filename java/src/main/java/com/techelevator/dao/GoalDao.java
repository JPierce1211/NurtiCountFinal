package com.techelevator.dao;

import com.techelevator.model.Goals;

import java.time.LocalDate;
import java.util.List;

public interface GoalDao {

    List<Goals> list();

    List<Goals> getGoalByDate(String date);

    List<Goals> getGoalsByTimeframe(String fromDate, String toDate);

    Goals getGoalsByProfileId(int profileId);

    Goals getGoalByGoalId(int goalId);

    Goals createGoal(Goals goal);

    Goals updateGoal(Goals goal);


}
