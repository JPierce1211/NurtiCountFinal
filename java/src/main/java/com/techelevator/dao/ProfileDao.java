package com.techelevator.dao;

import com.techelevator.model.Profile;

public interface ProfileDao {

    Profile getProfileById(int id);

    Profile createProfile(Profile profile);

    Profile updateProfile(Profile profile);

    int deleteProfile(int id);

}
