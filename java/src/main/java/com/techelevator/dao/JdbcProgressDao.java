package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Profile;
import com.techelevator.model.Progress;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProgressDao implements ProgressDao{

    private ProfileDao profileDao;
    private JdbcTemplate jdbcTemplate;

    public JdbcProgressDao(JdbcTemplate jdbcTemplate, ProfileDao profileDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.profileDao = profileDao;
    }

    @Override
    public List<Progress> list(){ //List all progress in one place
        List<Progress> progressCheckPoints = new ArrayList<>();
        String sql = "SELECT profile_id, current_weight, desired_weight, bmi, log_day FROM progress;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                Progress progress = mapRowToProgress(results);
                progressCheckPoints.add(progress);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return progressCheckPoints;
    }

    @Override
    public List<Progress> getProgressByDate(LocalDate date){ //Retrieve a specific progress log by a certain date
        List<Progress> progressDate = new ArrayList<>();
        String sql = "SELECT profile_id, current_weight, desired_weight, bmi, log_day " +
                "FROM progress " +
                "WHERE log_day = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, date);
            while (results.next()){
                Progress progress = mapRowToProgress(results);
                progressDate.add(progress);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return progressDate;

    }

    @Override
    public List<Progress> getProgressByTimeframe(LocalDate fromDate, LocalDate toDate){ //Viewing trajectories based on timeframes
        List<Progress> progressChart = new ArrayList<>();
        String sql = "SELECT profile_id, current_weight, desired_weight, bmi, log_day " +
                "FROM progress " +
                "WHERE log_day BETWEEN ? AND ? " +
                "ORDER BY log_day;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, fromDate, toDate);
            while (results.next()) {
                progressChart.add(mapRowToProgress(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return progressChart;
    }

    @Override
    public Progress getProgressByProfileId(int profileId){ //Get all progress by the profile to be ready to display
        Progress progress = null;
        String sql = "SELECT progress_id, profile_id, current_weight, desired_weight, bmi, log_day " +
                "FROM progress " +
                "WHERE profile_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, profileId);
            if (results.next()){
                progress = mapRowToProgress(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return progress;
    }

    public Progress getProgressByProgressId(int progressId){ //Specific progress point
        Progress progress = null;
        String sql = "SELECT profile_id, current_weight, desired_weight, bmi, log_day " +
                "FROM progress " +
                "WHERE progress_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, progressId);
            if (results.next()){
                progress = mapRowToProgress(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return progress;
    }

    @Override
    public Progress createProgress(Progress progress){ //Allows user to log a new progress point
        Progress newProgress = null;
        String sql = "INSERT INTO progress (profile_id, current_weight, desired_weight, bmi, log_day) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING progress_id;";
        try{
            //The profileDao.getProfileById(progress.getProfileById) may be redundant
            int newProgressId = jdbcTemplate.queryForObject(sql, int.class, profileDao.getProfileById(progress.getProfileId()),
                    progress.getCurrentWeight(), progress.getDesiredWeight(), progress.getBmi(), progress.getDate());

            newProgress = getProgressByProfileId(newProgressId);
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the server or database", e);
        }catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newProgress;
    }

    @Override
    public Progress updateProgress(Progress progress){ //Allows user to update current progress
        Progress updateProgress = null;
        String sql = "UPDATE progress SET profile_id = ?, current_weight = ?, desired_weight = ?, bmi  = ?, log_day = ? " +
                "WHERE progress_id = ?;";
        try{
            int numberOfRows = jdbcTemplate.update(sql, progress.getProfileId(), progress.getCurrentWeight(), progress.getDesiredWeight(),
                    progress.getBmi(), progress.getDate());
            if(numberOfRows == 0){
                throw new DaoException("Zero rows affected, expected at least one");
            }else{
                updateProgress = getProgressByProgressId(progress.getProgressId());
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }

        return updateProgress;
    }

    private Progress mapRowToProgress(SqlRowSet rs){
        Progress progress = new Progress();
        progress.setProgressId(rs.getInt("progress_id"));
        progress.setProfileId(rs.getInt("profile_id"));
        progress.setCurrentWeight(rs.getDouble("current_weight"));
        progress.setDesiredWeight(rs.getDouble("desired_weight"));
        progress.setBmi(rs.getDouble("bmi"));
        progress.setDate(rs.getDate("log_day").toLocalDate());
        return progress;
    }
}
