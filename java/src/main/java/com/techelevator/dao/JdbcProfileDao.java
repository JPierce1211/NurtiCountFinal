package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcProfileDao implements ProfileDao{

    private UserDao userDao;
    private JdbcTemplate jdbcTemplate;

    public JdbcProfileDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }
    @Override
    public Profile getProfileById(int id){
        Profile profile = null;
        String sql = "SELECT user_id, profile_id, birthday, height, starting_weight, " +
                "display_name, profile_pic_id, goals " +
                "FROM profile " +
                "WHERE profile_id = ?";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()){
                profile = mapRowToProfile(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return profile;
    }

    @Override
    public Profile getProfileByUserId(int userId){
        Profile profile = null;
        String sql = "SELECT user_id, profile_id, birthday, height, current_weight, " +
                "display_name, profile_pic_id, goals " +
                "FROM profile " +
                "WHERE user_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            if (results.next()){
                profile = mapRowToProfile(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return profile;
    }

    @Override
    public Profile createProfile(Profile profile){
        Profile newProfile = null;
        String sql = "INSERT INTO profile (user_id, birthday, height, starting_weight, display_name, profile_pic_id, goals) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING profile_id;";
        try {
            int newProfileId = jdbcTemplate.queryForObject(sql, int.class, profile.getUserId(), profile.getBirthday(),
                    profile.getHeight(), profile.getCurrentWeight(), profile.getDisplayName(), profile.getProfilePicId(), profile.getGoals());

            newProfile = getProfileById(newProfileId);
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the server or database", e);
        }catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newProfile;
    }

    @Override
    public Profile updateProfile(Profile profile){
        Profile updateProfile = null;
        String sql = "UPDATE profile SET user_id = ?, birthday = ?, height = ?, current_weight = ?, display_name = ?, profile_pic_id = ?, goals = ? " +
                "WHERE profile_id = ?;";
        try{
            int numberOfRows = jdbcTemplate.update(sql, profile.getUserId(), profile.getBirthday(), profile.getHeight(),
                    profile.getCurrentWeight(), profile.getDisplayName(), profile.getProfilePicId(), profile.getProfileId(), profile.getGoals());
            if(numberOfRows == 0){
                throw new DaoException("Zero rows affected, expected at least one");
            }else{
                updateProfile = getProfileById(profile.getProfileId());
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }

        return updateProfile;
    }

    @Override
    public int deleteProfile(int id){
        int numberOfRows = 0;
        String userProfileSql = "DELETE FROM profile WHERE profile_id = ?;";

        try{
            numberOfRows = jdbcTemplate.update(userProfileSql, id);
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }

    private Profile mapRowToProfile(SqlRowSet rs){
        Profile profile = new Profile();
        profile.setUserId(rs.getInt("user_id"));
        profile.setProfileId(rs.getInt("profile_id"));
        profile.setBirthday(rs.getString("birthday"));
        profile.setHeight(rs.getDouble("height"));
        profile.setCurrentWeight(rs.getDouble("current_weight"));
        profile.setDisplayName(rs.getString("display_name"));
        profile.setProfilePicId(rs.getInt("profile_pic_id"));
        profile.setGoals(rs.getInt("goals"));
        return profile;
    }
}
