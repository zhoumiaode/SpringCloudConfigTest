package com.forezp.configclient.domain;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.configserver.domain
 * @ClassName: Boy
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/11/26 11:27
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/11/26 11:27
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Boy {

    private String id;
    private String name;

    public Boy() {
    }

    public Boy(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
