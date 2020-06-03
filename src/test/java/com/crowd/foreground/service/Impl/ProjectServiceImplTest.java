package com.crowd.foreground.service.Impl;

import com.crowd.foreground.ForegroundApplication;
import com.crowd.foreground.dao.ProjectDao;
import com.crowd.foreground.entity.Order;
import com.crowd.foreground.entity.PriceItem;
import com.crowd.foreground.entity.Project;
import com.crowd.foreground.entity.ProjectInfo;
import com.crowd.foreground.service.ProjectService;
import com.crowd.foreground.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ForegroundApplication.class)
@EnableAutoConfiguration
@EnableWebMvc
class ProjectServiceImplTest {

    @Autowired
    private ProjectService projectService;

    @Autowired
    UserService userService;

    @Autowired
    private ProjectDao projectDao;

    @Test
    public void test(){
        //List<ProjectInfo> list=projectService.getProjectInfoByTypeAndStatus(1,1,3);
        List<ProjectInfo> list=projectDao.getProjectInfoByTypeAndStatus(1,1,"supporter");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getSupporter());
        }
    }

    @Test
    public void test1(){
        PriceItem priceItem=projectService.getPriceItemByItemId(4);
        System.out.println(priceItem.toString());
    }

    @Test
    void test2() throws Exception{
        Date date = new Date();
        System.out.println("date:"+date);
        String nowtime = String.valueOf(System.currentTimeMillis());
        System.out.println("nowtime"+nowtime);

    }
}