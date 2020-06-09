package com.crowd.foreground.service.Impl;

import com.crowd.foreground.ForegroundApplication;
import com.crowd.foreground.dao.ProjectDao;
import com.crowd.foreground.entity.PriceItem;
import com.crowd.foreground.entity.ProjectInfo;
import com.crowd.foreground.entity.ProjectType;
import com.crowd.foreground.service.ProjectService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ForegroundApplication.class)
public class ProjectServiceImplTest {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectDao projectDao;



    @Test
    public void getAllType() {
        List<ProjectType> typeList=projectService.getAllType();
        Assert.assertTrue(!typeList.isEmpty());
    }

    @Test
    public void getProjectNameById() {
        String name=projectService.getProjectNameById(1);
        Assert.assertEquals("小熊挂烫机",name);
    }

    @Test
    public void getProjectInfoByTypeid() {
        List<ProjectInfo> projectInfos=projectService.getProjectInfoByTypeid(1);
        Assert.assertTrue(!projectInfos.isEmpty());
    }

    @Test
    public void getProjectDetail() {
        Map<String,String> map=projectService.getProjectDetail(1);
        Assert.assertTrue(!map.isEmpty());
    }

    @Test
    public void getPriceItemById() {
        List<PriceItem> priceItems=projectService.getPriceItemById(3);
        Assert.assertTrue(priceItems.size()==2);
    }

    @Test
    public void getAllProjects() {
        List<ProjectInfo> projectInfos=projectService.getAllProjects();
        Assert.assertTrue(projectInfos.size()==16);
    }

    @Test
    public void getProjectInfoByTypeAndStatus() throws Exception{
        List<ProjectInfo> list1=projectService.getProjectInfoByTypeAndStatus("机",1,1,1);
        Assert.assertTrue(list1.size()==2);
    }

    @Test
    public void getPriceItemByItemId() {
        PriceItem priceItem=new PriceItem(1,1,159.00,3,"感谢您的支持，您将获得小熊小型手持熨烫机1台");
        PriceItem priceItem1=projectService.getPriceItemByItemId(1);
        Assert.assertEquals(priceItem.getIntroduce(),priceItem1.getIntroduce());
    }

    @Test
    public void getProjectNameByItemId() {
        String name=projectService.getProjectNameByItemId(1);
        Assert.assertEquals("小熊挂烫机",name);
    }

    @Test
    public void updateProject() {
        Map<String,String> map=projectService.getProjectDetail(16);
        int i=Integer.valueOf(map.get("supportor"));
        double j=Double.valueOf(map.get("supportmoney"));
        projectService.updateProject(16,100.0);
        Map<String,String> map1=projectService.getProjectDetail(16);
        int ii=Integer.valueOf(map1.get("supportor"));
        double jj=Double.valueOf(map1.get("supportmoney"));
        Assert.assertEquals(i+1,ii);
        Assert.assertEquals(j+100.0,jj,0.0);
    }
}