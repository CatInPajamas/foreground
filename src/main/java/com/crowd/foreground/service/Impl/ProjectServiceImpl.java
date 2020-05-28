package com.crowd.foreground.service.Impl;


import com.crowd.foreground.dao.ProjectDao;
import com.crowd.foreground.entity.PortalProject;
import com.crowd.foreground.entity.PortalType;
import com.crowd.foreground.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectDao projectDao;
    @Override
    public void saveProject(PortalProject portalProject, Integer memberId) {

    }

    @Override
    public List<PortalType> getPortalType() {
        return projectDao.getPortalType();
    }


}
