package com.techelevator.dao;

import com.techelevator.model.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcProfileDao implements ProfileDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcProfileDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    Profile getProfileById(int id){

    }

    Profile createProfile(Profile profile){

    }

    Profile updateProfile(Profile profile){

    }

    Profile deleteProfile(int id){

    }

    private Profile mapRowToProfile(SqlRowSet rs){
        Profile profile = new Profile();
        profile.setProfileId(rs.getInt("profile_id"));
        profile.setHeight(rs.getDouble("height"));
        profile.setDisplayName(rs.getString("display_name"));
        profile.setBirthday(rs.getDate("birthday").toLocalDate());
        profile.setProfilePicture(rs.getString("profile_picture"));
        profile.setStartingWeight(rs.getDouble("starting_weight"));
        return profile;
    }
}
