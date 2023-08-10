package com.techelevator.controller;

import com.techelevator.dao.ProfileDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Profile;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    @PostMapping("/createProfile")
    public Profile create(@RequestBody Profile profile, Principal principal){
        Profile newProfile = null;
        User user = userDao.getUserByUsername(principal.getName());
       try {
           newProfile = profileDao.createProfile(profile);
       } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to create profile.");
        }
        return newProfile;
    }

    @PutMapping("/profile/{id}/edit")
    public Profile update(@RequestBody Profile updatedProfile, @PathVariable int profileId){
        updatedProfile.setProfileId(profileId);
        Profile updateProfile = profileDao.updateProfile(updatedProfile);
        return updateProfile;
    }

    @PutMapping("/profile/edit")//Secondary endpoint & method to update the profile if the above does not work or just needs to be simplified.
    public Profile update(@RequestBody Profile updatedProfile){
        return profileDao.updateProfile(updatedProfile);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/profile/{id}")
    public void delete(@PathVariable int id){
        profileDao.deleteProfile(id);

    }
}
