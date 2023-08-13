package com.techelevator.controller;

import com.techelevator.dao.PicDao;
import com.techelevator.dao.ProfileDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Profile;
import com.techelevator.model.ProfilePic;
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
    private PicDao picDao;
    private UserDao userDao;

    public ProfileController(ProfileDao profileDao, UserDao userDao, PicDao picDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
        this.picDao = picDao;
    }

    @GetMapping("/profile/{id}")
    public Profile get(@PathVariable int id, Principal principal){
//        User user = userDao.getUserByUsername(principal.getName());
        Profile profile = profileDao.getProfileByUserId(id);
        return profile;
    }

    @GetMapping("/profilePic/{picId}") //Returns pic object
    public ProfilePic getPicByPicId(@PathVariable int picId){
        return picDao.getPicById(picId);
    }

    @GetMapping("/profilePic/url/{picId}") //Should return URL of pic
    public String getPicUrl(@PathVariable int picId){
        return picDao.getPicUrl(picId);
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

//    @PutMapping("/profile/{id}")
//    public Profile update(@RequestBody Profile updatedProfile, @PathVariable int id){
//        updatedProfile.setProfileId(id);
//        Profile updateProfile = profileDao.updateProfile(updatedProfile);
//        return updateProfile;
//    }

    @PutMapping("/profile/edit")
    public Profile update(@RequestBody Profile updatedProfile){
        return profileDao.updateProfile(updatedProfile);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/profile/{profileId}")
    public void delete(@PathVariable int profileId){
        profileDao.deleteProfile(profileId);

    }
}
