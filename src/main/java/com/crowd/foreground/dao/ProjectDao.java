package com.crowd.foreground.dao;

import com.crowd.foreground.entity.PortalProject;
import com.crowd.foreground.entity.PortalType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectDao {

    @Select("select * from t_type")
    List<PortalType> getPortalType();

    @Select("SELECT t_project.id projectId,name projectName,money,deploydate deployDate," +
            "supportmoney/money*100 percentage,supporter supporter,picture_path picturePath" +
            "FROM t_project LEFT JOIN t_project_type ON t_project.id=t_project_type.projectid" +
            "WHERE t_project_type.typeid=#{id}" +
            "ORDER BY t_project.id DESC" +
            "LIMIT 0,4")
    List<PortalProject> getProjectByTyprid();
}
