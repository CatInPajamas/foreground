package com.crowd.foreground.service;

import com.crowd.foreground.entity.PriceItem;
import com.crowd.foreground.entity.Project;
import com.crowd.foreground.entity.ProjectInfo;
import com.crowd.foreground.entity.ProjectType;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    void saveProject(Project portalProject, Integer memberId);

    List<ProjectType> getAllType();

    String getProjectNameById(Integer id);

    List<ProjectInfo> getProjectInfoByTypeid(Integer id);

    Map<String,String> getProjectDetail(Integer id);

    List<PriceItem> getPriceItemById(Integer id);

    List<ProjectInfo> getAllProjects();

    List<ProjectInfo> getProjectInfoByTypeAndStatus(String keyword,Integer id,Integer status,Integer criteria) throws Exception;

    PriceItem getPriceItemByItemId(Integer id);

    String getProjectNameByItemId(Integer id);

    void updateProject(Integer id, Double money);

}
