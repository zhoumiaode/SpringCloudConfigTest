package com.forezp.configserver.controller;

import com.forezp.configserver.domain.Boy;
import com.forezp.configserver.service.HiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.configserver.controller
 * @ClassName: HiController
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/10/30 10:49
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/10/30 10:49
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@RestController
public class HiController {

    @Resource
    public HiService hiService;

    @RequestMapping(value = "/hi")
    public String hi(){
        return hiService.hi();
    }

    @RequestMapping(value = "/getBoy")
    public List<Boy> getBot(){
        List<Boy> list=new ArrayList<Boy>();
        Boy boy=new Boy("1","jack");
        Boy boy1=new Boy("2","love");
        list.add(boy);
        list.add(boy1);
        return list;
    }
}
