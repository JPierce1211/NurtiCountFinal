package com.techelevator.dao;

import com.techelevator.model.Progress;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface ProgressDao {

    Progress getProgressById(int profileId);

    Progress createProgress(Progress progress);

    Progress updateProgress(Progress progress);


}
