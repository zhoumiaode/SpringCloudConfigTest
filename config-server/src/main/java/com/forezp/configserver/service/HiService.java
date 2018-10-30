package com.forezp.configserver.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.configserver.service
 * @ClassName: HiService
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/10/30 10:48
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/10/30 10:48
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Service
public class HiService {

    @Resource
    public RestTemplate restTemplate;

    public String hi(){
        return restTemplate.getForObject("http://config-clients/hi",String.class);
    }
}
