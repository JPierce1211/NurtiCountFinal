package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Profile;
import com.techelevator.model.ProfilePic;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcPicDao implements PicDao {

    JdbcTemplate jdbcTemplate;

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

//    @Override
//    public String getPicByUrl(String url){
//        String sql = "SELECT url " +
//                "FROM profile_img " +
//                "WHERE url = ?;";
//        try {
//            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, url);
//            if
//        }
//    }

    private ProfilePic mapRowToProfilePic(SqlRowSet rs){
        ProfilePic profilePic = new ProfilePic();
        profilePic.setProfilePicId(rs.getInt("profile_pic_id"));
        profilePic.setUrl(rs.getNString("url"));
        return profilePic;
    }

}
