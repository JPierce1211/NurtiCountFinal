package com.techelevator.dao;

import com.techelevator.model.ProfilePic;

public interface PicDao {

    ProfilePic getPicById(int picId);

    String getPicUrl(int picId);
}
