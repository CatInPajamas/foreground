package com.crowd.foreground.dao;

import com.crowd.foreground.entity.PriceItem;
import com.crowd.foreground.entity.Project;
import com.crowd.foreground.entity.ProjectInfo;
import com.crowd.foreground.entity.ProjectType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.security.interfaces.DSAPublicKey;
import java.util.List;

@Mapper
public interface ProjectDao {


    /**
     * 根据项目类型id选择项目
     * @param id
     * @return
     */
    @Select("SELECT t_project.id id,t_project.name name,money money,supportmoney supportmoney,supportmoney/money*100 percentage,supporter supporter,head_picture_path head_picture_path " +
            "FROM t_project LEFT JOIN t_project_type ON t_project.id=t_project_type.projectid " +
            "WHERE t_project_type.typeid=#{id} " +
            "ORDER BY t_project.id DESC " +
            "LIMIT 0,4")
    List<ProjectInfo> getProjectInfoByTypeid(Integer id);

    /**
     * 获取全部项目类型
     * @return
     */
    @Select("select * from t_type")
    List<ProjectType> getAllType();


    @Select("select * from t_project where id=#{id}")
    Project getProjectById(Integer id);

    @Select("select name from t_project where id=#{id}")
    String getProjectNameById(Integer id);


    @Select("SELECT t_project.id id,t_project.name name,money money,supportmoney supportmoney,supportmoney/money*100 percentage,supporter supporter,head_picture_path head_picture_path " +
            "FROM t_project")
    List<ProjectInfo> getAllProjects();

    /**
     * 根据项目分类和状态组合筛选
     * @param id
     * @return
     */
    @Select("SELECT t_project.id id,t_project.name name,money money,supportmoney supportmoney,supportmoney/money*100 percentage,supporter supporter,head_picture_path head_picture_path " +
            "FROM t_project LEFT JOIN t_project_type ON t_project.id=t_project_type.projectid " +
            "WHERE name like '%${keyword}%' and (#{id} is null or t_project_type.typeid=#{id}) and (#{status} is null or t_project.status=#{status})"+
            "ORDER BY t_project.id DESC ")
    List<ProjectInfo> getProjectInfoByTypeAndStatus(String keyword,Integer id,Integer status) throws Exception;

    /**
     * 根据项目分类和状态组合筛选 按发布时间排序
     * @param id
     * @return
     */
    @Select("SELECT t_project.id id,t_project.name name,money money,supportmoney supportmoney,supportmoney/money*100 percentage,supporter supporter,head_picture_path head_picture_path " +
            "FROM t_project LEFT JOIN t_project_type ON t_project.id=t_project_type.projectid " +
            "WHERE name like '%${keyword}%' and (#{id} is null or t_project_type.typeid=#{id}) and (#{status} is null or t_project.status=#{status})" +
            "ORDER BY date_format(deploydate,'%Y%m%d') DESC ")
    List<ProjectInfo> getProjectInfoByTypeAndStatus1(String keyword,Integer id,Integer status) throws Exception;

    /**
     * 根据项目分类和状态组合筛选 按项目金额排序
     * @param id
     * @return
     */
    @Select("SELECT t_project.id id,t_project.name name,money money,supportmoney supportmoney,supportmoney/money*100 percentage,supporter supporter,head_picture_path head_picture_path " +
            "FROM t_project LEFT JOIN t_project_type ON t_project.id=t_project_type.projectid " +
            "WHERE name like '%${keyword}%' and (#{id} is null or t_project_type.typeid=#{id}) and (#{status} is null or t_project.status=#{status})" +
            "ORDER BY money DESC ")
    List<ProjectInfo> getProjectInfoByTypeAndStatus2(String keyword,Integer id,Integer status) throws Exception;

    /**
     * 根据项目分类和状态组合筛选 按支持人数排序
     * @param id
     * @return
     */
    @Select("SELECT t_project.id id,t_project.name name,money money,supportmoney supportmoney,supportmoney/money*100 percentage,supporter supporter,head_picture_path head_picture_path " +
            "FROM t_project LEFT JOIN t_project_type ON t_project.id=t_project_type.projectid " +
            "WHERE name like '%${keyword}%' and (#{id} is null or t_project_type.typeid=#{id}) and (#{status} is null or t_project.status=#{status})" +
            "ORDER BY supporter DESC ")
    List<ProjectInfo> getProjectInfoByTypeAndStatus3(String keyword,Integer id,Integer status) throws Exception;

    /**
     * 根据所选项目的支持项id选择支持项
     * @param id
     * @return PriceItem
     */
    @Select("SELECT * from t_project_item WHERE id=#{id} ")
    PriceItem getPriceItemByItemId(Integer id);

    /**
     * 根据项目id选择该项目下的所有支持项
     * @param id
     * @return List<PriceItem>
     */
    @Select("SELECT t_project_item.id,t_project_item.money,delivery,introduce " +
            "from t_project_item LEFT JOIN t_project on t_project_item.projectid=t_project.id " +
            "WHERE t_project.id=#{id} " +
            "ORDER BY t_project_item.money desc")
    List<PriceItem> getPriceItemById(Integer id);

    /**
     * 根据支持项id获取其对应项目的名称
     * @param id
     * @return
     */
    @Select("SELECT t_project.name " +
            "from t_project LEFT JOIN t_project_item on t_project_item.projectid=t_project.id " +
            "WHERE t_project_item.id=#{id}")
    String getProjectNameByItemId(Integer id);

    @Update("update t_project set supportmoney=supportmoney+#{money},supporter=supporter+1 where id=#{id}")
    void updateProject(Integer id, Double money);


}
