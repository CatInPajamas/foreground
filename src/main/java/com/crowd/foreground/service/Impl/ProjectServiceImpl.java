package com.crowd.foreground.service.Impl;


import com.crowd.foreground.dao.ProjectDao;
import com.crowd.foreground.entity.PriceItem;
import com.crowd.foreground.entity.Project;
import com.crowd.foreground.entity.ProjectInfo;
import com.crowd.foreground.entity.ProjectType;
import com.crowd.foreground.service.ProjectService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectDao projectDao;

    /**
     * 获取所有项目类型
     * @return
     */
    @Override
    public List<ProjectType> getAllType() {
        return projectDao.getAllType();
    }

    /**
     * 根据项目id获取项目名
     * @param id
     * @return
     */
    @Override
    public String getProjectNameById(Integer id) {
        return projectDao.getProjectNameById(id);
    }

    /**
     * 根据项目类型id获取这个类型的所有项目
     * @param id
     * @return
     */
    @Override
    public List<ProjectInfo> getProjectInfoByTypeid(Integer id) {
        return projectDao.getProjectInfoByTypeid(id);
    }

    /**
     * 根据项目id获取想要的项目信息，存入map中
     * @param id
     * @return
     */
    @Override
    public Map<String,String> getProjectDetail(Integer id) {
        Project project=projectDao.getProjectById(id);
        Map<String,String> map=new HashMap<>();
        Integer status =project.getStatus();
        switch (status) {
            case 0:
                map.put("status","审核中");
                break;
            case 1:
                map.put("status","众筹中");
                break;
            case 2:
                map.put("status","众筹成功");
                break;
            case 3:
                map.put("status","已关闭");
                break;
            default:
                break;
        }

        // 根据deployeDate 计算lastDay
        String deployDate =project.getDeploydate();
        // 获取当前日期
        Date currentDay = new Date();
        // 把众筹日期解析成Date 类型
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date deployDay = format.parse(deployDate);
            // 获取当前当前日期的时间戳
            long currentTimeStamp = currentDay.getTime();
            // 获取众筹日期的时间戳
            long deployTimeStamp = deployDay.getTime();
            // 两个时间戳相减计算当前已经过去的时间
            long pastDays = (currentTimeStamp - deployTimeStamp) / 1000 / 60 / 60 / 24;
            // 获取总的众筹天数
            Integer totalDays =project.getDay();
            // 使用总的众筹天数减去已经过去的天数得到剩余天数
            Integer lastDay = (int) (totalDays - pastDays);
            map.put("lastday",lastDay.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        double supportmoney=project.getSupportmoney();
        double money=project.getMoney();
        double d=Double.valueOf(supportmoney)/Double.valueOf(money)*100;
        String percentage;
        if(d==0){
            percentage="0";
        }else {
            percentage=String.format("%.1f", d);
        }
        map.put("supportor",String.valueOf(project.getSupporter()));
        map.put("supportmoney",String.valueOf(supportmoney));
        map.put("money",String.valueOf(money));
        map.put("percentage",String.valueOf(percentage));
        map.put("headpic",project.getHead_picture_path());
        map.put("bodypic",project.getBody_picture_path());
        map.put("name",project.getName());
        map.put("remark",project.getRemark());
        return map;
    }

    /**
     * 根据项目id获取这个项目下的所有支持项信息
     * @param id
     * @return
     */
    @Override
    public List<PriceItem> getPriceItemById(Integer id) {
        return projectDao.getPriceItemById(id);
    }

    /**
     * 获取所有项目信息
     * @return
     */
    @Override
    public List<ProjectInfo> getAllProjects() {
        return projectDao.getAllProjects();
    }

    /**
     * 根据条件筛选项目
     * @param keyword
     * @param id
     * @param status
     * @param criteria
     * @return
     * @throws Exception
     */
    @Override
    public List<ProjectInfo> getProjectInfoByTypeAndStatus(String keyword,Integer id, Integer status,Integer criteria) throws Exception {
        List<ProjectInfo> list=new ArrayList<>();
        switch (criteria){
            case 0:
                list=projectDao.getProjectInfoByTypeAndStatus(keyword,id,status);
                break;
            case 1:
                list=projectDao.getProjectInfoByTypeAndStatus1(keyword,id,status);
                break;
            case 2:
                list=projectDao.getProjectInfoByTypeAndStatus2(keyword,id,status);
                break;
            case 3:
                list=projectDao.getProjectInfoByTypeAndStatus3(keyword,id,status);
                break;
        }
        return list;
    }

    /**
     * 根据支持项id获取支持项信息
     * @param id
     * @return
     */
    @Override
    public PriceItem getPriceItemByItemId(Integer id) {
        return projectDao.getPriceItemByItemId(id);
    }

    /**
     * 根据支持项id获取对应项目名
     * @param id
     * @return
     */
    @Override
    public String getProjectNameByItemId(Integer id) {
        return projectDao.getProjectNameByItemId(id);
    }

    /**
     * 跟新项目的已筹集钱数
     * @param id
     * @param money
     */
    @Override
    public void updateProject(Integer id, Double money) {
        projectDao.updateProject(id,money);
    }


}
