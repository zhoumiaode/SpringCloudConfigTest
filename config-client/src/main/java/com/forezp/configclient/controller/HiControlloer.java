package com.forezp.configclient.controller;

import com.forezp.configclient.domain.Boy;
import com.forezp.configclient.service.TestService;
import io.swagger.annotations.*;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.configclient.controller
 * @ClassName: HiControlloer
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/10/12 14:42
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/10/12 14:42
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@RestController
@RefreshScope
@CrossOrigin
@Api(value = "boy的控制器")
public class HiControlloer {

    @Value("${foo.name}")
    String foo;

    @Resource
    public TestService testService;

    @RequestMapping(value = "/hi")
    public String hi(){
        return foo;
    }

    @GetMapping(value = "/test2")
    public String test2(){
        return testService.test2();
    }

    @RequestMapping(value = "boy",method = RequestMethod.GET)
    public List<Boy> getBoy(){
        return testService.getBoy();
    }

    @ApiOperation(value = "获取boy对象集合的接口",httpMethod = "POST",notes = "获取boy对象集合的接口")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Pet not found") })
    @ApiImplicitParam(name = "boy", value = "boy", required = true, dataType = "Boy")
    @RequestMapping(value = "boy",method = RequestMethod.POST)
    public List<Boy> addBoy(){
        return testService.getBoy();
    }
}
