package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Goals;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGoalDao implements GoalDao {

    private ProfileDao profileDao;
    private JdbcTemplate jdbcTemplate;

    public JdbcGoalDao(JdbcTemplate jdbcTemplate, ProfileDao profileDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.profileDao = profileDao;
    }

    @Override
    public List<Goals> list(int userId){ //List all goals in one place
        List<Goals> goalCheckPoints = new ArrayList<>();
        String sql = "SELECT goal_id, user_id, desired_weight, bmi, log_day FROM goals " +
                "WHERE user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()){
                Goals goal = mapRowToGoals(results);
                goalCheckPoints.add(goal);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return goalCheckPoints;
    }

    @Override
    public List<Goals> getGoalsByDate(String date){ //Retrieve a specific progress log by a certain date
        List<Goals> goalDate = new ArrayList<>();
        String sql = "SELECT goal_id, user_id, desired_weight, bmi, log_day " +
                "FROM goals " +
                "WHERE log_day = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, date);
            while (results.next()){
                Goals goal = mapRowToGoals(results);
                goalDate.add(goal);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return goalDate;

    }

    @Override
    public List<Goals> getGoalsByTimeframe(String fromDate, String toDate){ //Viewing trajectories based on timeframes
        List<Goals> progressChart = new ArrayList<>();
        String sql = "SELECT goal_id, user_id, desired_weight, bmi, log_day " +
                "FROM goals " +
                "WHERE log_day BETWEEN ? AND ? " +
                "ORDER BY log_day;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, fromDate, toDate);
            while (results.next()) {
                progressChart.add(mapRowToGoals(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return progressChart;
    }

    public Goals getGoalByGoalId(int goalId){ //Specific progress point
        Goals goals = null;
        String sql = "SELECT goal_id, user_id, desired_weight, bmi, log_day " +
                "FROM goals " +
                "WHERE goal_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, goalId);
            if (results.next()){
                goals = mapRowToGoals(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return goals;
    }

    @Override
    public Goals createGoal(Goals goal){ //Allows user to log a new progress point
        Goals newGoal = null;
        String sql = "INSERT INTO goals (user_id, desired_weight, bmi, log_day) " +
                "VALUES (?, ?, ?, ?) RETURNING goal_id;";
        try{
            int newGoalId = jdbcTemplate.queryForObject(sql, int.class, goal.getUserId(), goal.getDesiredWeight(), goal.getBmi(), goal.getLogDay());

            newGoal = getGoalByGoalId(newGoalId);
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the server or database", e);
        }catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newGoal;
    }

    @Override
    public Goals updateGoal(Goals goal){ //Allows user to update current progress
        Goals updateGoal = null;
        String sql = "UPDATE goals SET user_id = ?, desired_weight = ?, bmi = ?, log_day = ? " +
                "WHERE goal_id = ?;";
        try{
            int numberOfRows = jdbcTemplate.update(sql, goal.getUserId(), goal.getDesiredWeight(), goal.getBmi(), goal.getLogDay(), goal.getGoalId());
            if(numberOfRows == 0){
                throw new DaoException("Zero rows affected, expected at least one");
            }else{
                updateGoal = getGoalByGoalId(goal.getGoalId());
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }

        return updateGoal;
    }

    private Goals mapRowToGoals(SqlRowSet rs){
        Goals goals = new Goals();
        goals.setGoalId(rs.getInt("goal_id"));
        goals.setUserId(rs.getInt("user_id"));
        goals.setDesiredWeight(rs.getDouble("desired_weight"));
        goals.setBmi(rs.getDouble("bmi"));
        goals.setLogDay(rs.getString("log_day"));
        return goals;
    }
}
