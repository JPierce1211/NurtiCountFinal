package com.techelevator.dao;

import com.techelevator.model.Progress;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.time.LocalDate;
import java.util.List;

public interface ProgressDao {

    List<Progress> list();

    List<Progress> getProgressByDate(LocalDate date);

    Progress getProgressByProfileId(int profileId);

    Progress getProgressByProgressId(int progressId);

    Progress createProgress(Progress progress);

    Progress updateProgress(Progress progress);


}
