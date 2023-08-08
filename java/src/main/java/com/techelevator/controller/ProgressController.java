package com.techelevator.controller;

import com.techelevator.dao.ProfileDao;
import com.techelevator.dao.ProgressDao;
import com.techelevator.model.Profile;
import com.techelevator.model.Progress;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

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
    public Progress create(Progress progress, @PathVariable int profileId){
        Profile profile = profileDao.getProfileById(profileId);
        Progress newProgress = progressDao.createProgress(progress);

        return null;
    }

    @PutMapping("profile/{id}/progress/update")
    public Progress update(Progress progress){
        return progressDao.updateProgress(progress);
    }


}
