package com.techelevator.controller;

import com.techelevator.dao.ProfileDao;
import com.techelevator.dao.ProgressDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Profile;
import com.techelevator.model.Progress;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@Component
@RestController
@PreAuthorize("isAuthenticated()")
public class ProgressController {

    private ProgressDao progressDao;

    private ProfileDao profileDao;

    public ProgressController(ProgressDao progressDao, ProfileDao profileDao){
        this.progressDao = progressDao;
        this.profileDao = profileDao;
    }

    @GetMapping("profile/progress")
    public List<Progress> listAllProgress(){
        return progressDao.list();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("profile/{id}/create-process")
    public Progress create(@RequestBody Progress progress, @PathVariable int profileId){
        progress.setProfileId(profileId);
        try {
            Progress newProgress = progressDao.createProgress(progress);
            return newProgress;
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found.");
        }

    }

    @PutMapping("profile/{id}/progress/update")
    public Progress update(@RequestBody Progress progress){
        return progressDao.updateProgress(progress);
    }


}
