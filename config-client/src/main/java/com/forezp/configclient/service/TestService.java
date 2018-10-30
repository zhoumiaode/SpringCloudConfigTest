package com.forezp.configclient.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.configclient.service
 * @ClassName: TestService
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/10/29 14:23
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/10/29 14:23
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Service
public class TestService {

    @Resource
    public RestTemplate restTemplate;

    public String test2(){
        return restTemplate.getForObject("http://config-server/test2",String.class);
    }
}
