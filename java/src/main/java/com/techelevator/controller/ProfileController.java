package com.techelevator.controller;

import com.techelevator.dao.ProfileDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Profile;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@Component
@RestController
@PreAuthorize("isAuthenticated()")
public class ProfileController {

    private ProfileDao profileDao;
    private UserDao userDao;

    public ProfileController(ProfileDao profileDao, UserDao userDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    @GetMapping("/profile/{id}")
    public Profile get(@PathVariable int profileId, Principal principal){
//        User user = userDao.getUserByUsername(principal.getName());
        Profile profile = profileDao.getProfileById(profileId);
        return profile;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-profile/{id}")
    public Profile create(@RequestBody Profile profile, @PathVariable int userId, Principal principal){
        Profile newProfile = profileDao.createProfile(profile);
        return newProfile;
    }

    @PutMapping("/profile/{id}/edit")
    public Profile update(@RequestBody Profile profile, @PathVariable int profileId, Principal principal){
        Profile updateProfile = profileDao.updateProfile(profile);
        return updateProfile;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/profile/{id}")
    public void delete(@PathVariable int id, Principal principal){
        int deleteProfile = profileDao.deleteProfile(id);
    }
}