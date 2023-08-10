package com.techelevator.controller;

import com.techelevator.dao.ProfileDao;
import com.techelevator.dao.GoalDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Goals;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@Component
@RestController
@PreAuthorize("isAuthenticated()")
public class GoalsController {

    private GoalDao goalDao;

    private ProfileDao profileDao;

    public GoalsController(GoalDao goalDao, ProfileDao profileDao){
        this.goalDao = goalDao;
        this.profileDao = profileDao;
    }

    @GetMapping("goals")
    public List<Goals> listAllGoals(){
        return goalDao.list();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("createGoals")
    public Goals create(@RequestBody Goals goal){
        try {
            return goalDao.createGoal(goal);
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found.");
        }
    }

    @PutMapping("goals/update")
    public Goals update(@RequestBody Goals goal){
        return goalDao.updateGoal(goal);
    }

    @GetMapping("profile/(id}/goals")
    public Goals getGoalByProfile(@PathVariable int profileId){
        return goalDao.getGoalsByProfileId(profileId);
    }

    @GetMapping("goals/time")
    public List<Goals> getProgressOverTime(@RequestBody String fromDate, @RequestBody String toDate){
        return goalDao.getGoalsByTimeframe(fromDate, toDate);
    }

    @GetMapping("goals/{id}")
    public Goals getGoalById(@PathVariable int goalId){
        Goals progress = goalDao.getGoalByGoalId(goalId);
        return progress;
    }

    @GetMapping("goals/date")
    public List<Goals> getGoalByDate(String date){
        return goalDao.getGoalByDate(date);
    }


}
