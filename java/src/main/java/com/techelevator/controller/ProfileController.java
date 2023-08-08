package com.techelevator.controller;

import com.techelevator.dao.ProfileDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@PreAuthorize("isAuthenticated()")
public class ProfileController {

    private ProfileDao profileDao;
    private UserDao userDao;

    public ProfileController(ProfileDao profileDao, UserDao userDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    @GetMapping("/profile")
    public Profile get(int profileId){
        Profile profile = profileDao.getProfileById(profileId);
        return profile;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-profile")
    public Profile create(@RequestBody Profile profile){
        Profile newProfile = profileDao.createProfile(profile);
        return newProfile;
    }

    @PutMapping("/profile/update")
    public Profile update(@RequestBody Profile profile){
        Profile updateProfile = profileDao.updateProfile(profile);
        return updateProfile;
    }

    @DeleteMapping("/profile/{id}")
    public void delete(@PathVariable int id){
        int deleteProfile = profileDao.deleteProfile(id);
    }
}
