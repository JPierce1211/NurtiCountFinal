package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Progress;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcProgressDao implements ProgressDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcProgressDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
                "VALUES (?, ?, ?, ?, ?) RETURNING profile_id;";

    }

    @Override
    public Progress updateProgress(Progress progress){

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
