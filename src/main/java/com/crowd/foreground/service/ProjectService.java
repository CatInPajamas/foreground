package com.crowd.foreground.service;

import com.crowd.foreground.entity.PortalProject;
import com.crowd.foreground.entity.PortalType;

import java.util.List;

public interface ProjectService {
    void saveProject(PortalProject portalProject,Integer memberId);

    List<PortalType> getPortalType();
}
