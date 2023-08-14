package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Profile;
import com.techelevator.model.ProfilePic;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class JdbcPicDao implements PicDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcPicDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ProfilePic getPicById(int picId){
        ProfilePic pic = null;
        String sql = "SELECT profile_pic_id, url " +
                "FROM profile_img " +
                "WHERE profile_pic_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, picId);
            if (results.next()){
                pic = mapRowToProfilePic(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return pic;
    }

    @Override
    public String getPicUrl(int picId)
    {
        String url = null;
        String sql = "SELECT url FROM profile_img WHERE profile_pic_id = ?;";
        try
        {
            url = jdbcTemplate.queryForObject(sql, String.class, picId);
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return url;
    }

    private ProfilePic mapRowToProfilePic(SqlRowSet rs){
        ProfilePic profilePic = new ProfilePic();
        profilePic.setProfilePicId(rs.getInt("profile_pic_id"));
        profilePic.setUrl(rs.getString("url"));
        return profilePic;
    }

}
