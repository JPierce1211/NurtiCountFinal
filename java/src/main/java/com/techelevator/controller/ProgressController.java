package com.techelevator.controller;

import com.techelevator.dao.ProfileDao;
import com.techelevator.dao.ProgressDao;
import com.techelevator.model.Progress;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("progress")
    public List<Progress> listAllProgress(){
        return progressDao.list();
    }

}
