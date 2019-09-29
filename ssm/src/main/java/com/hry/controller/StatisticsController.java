package com.hry.controller;

import com.hry.bean.StatBean;
import com.hry.service.StatisticsService;
import com.hry.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class StatisticsController {

    @Resource
    private StatisticsService statisticsService;

    @RequestMapping("/selectPassengerRegisted")
    @ResponseBody
    public void selectPassengerRegisted(){
        StatBean statBean = null;
        List<Integer> integers = statisticsService.selectPassengerRegisted();
        for (Integer integer : integers) {
            statBean = new StatBean();
            if (integer == 1) {
                statBean.setName("待审核");
                statBean.setValue(1);
            }else if (integer == 2){
                statBean.setName("审核通过");
                statBean.setValue(2);
            }else if (integer == -1){
                statBean.setName("未通过审核");
                statBean.setValue(-1);
            }
        }
    }
}
