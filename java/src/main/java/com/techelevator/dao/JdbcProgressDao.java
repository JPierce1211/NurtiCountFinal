package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Profile;
import com.techelevator.model.Progress;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcProgressDao implements ProgressDao{

    private ProfileDao profileDao;
    private JdbcTemplate jdbcTemplate;

    public JdbcProgressDao(JdbcTemplate jdbcTemplate, ProfileDao profileDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.profileDao = profileDao;
    }

    @Override
    public Progress getProgressById(int profileId){
        Progress progress = null;
        String sql = "SELECT profile_id, current_weight, desired_weight, bmi, log_day " +
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

    @Override
    public Progress createProgress(Progress progress){
        Progress newProgress = null;
        String sql = "INSERT INTO progress (profile_id, current_weight, desired_weight, bmi, log_day) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING progress_id;";
        try{
            //The profileDao.getProfileById(progress.getProfileById) may be redundant
            int newProgressId = jdbcTemplate.queryForObject(sql, int.class, profileDao.getProfileById(progress.getProfileId()),
                    progress.getCurrentWeight(), progress.getDesiredWeight(), progress.getBmi(), progress.getDate());

            newProgress = getProgressById(newProgressId);
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the server or database", e);
        }catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newProgress;
    }

    @Override
    public Progress updateProgress(Progress progress){
        Progress updateProgress = null;
        String sql = "UPDATE progress SET profile_id = ?, current_weight = ?, desired_weight = ?, bmi  = ?, log_day = ? " +
                "WHERE progress_id = ?;";
        try{
            int numberOfRows = jdbcTemplate.update(sql, progress.getProfileId(), progress.getCurrentWeight(), progress.getDesiredWeight(),
                    progress.getBmi(), progress.getDate());
            if(numberOfRows == 0){
                throw new DaoException("Zero rows affected, expected at least one");
            }else{
                updateProgress = getProgressById(progress.getProfileId());
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
        progress.setProfileId(rs.getInt("profile_id"));
        progress.setCurrentWeight(rs.getDouble("current_weight"));
        progress.setDesiredWeight(rs.getDouble("desired_weight"));
        progress.setBmi(rs.getDouble("bmi"));
        progress.setDate(rs.getDate("log_day").toLocalDate());
        return progress;
    }
}
