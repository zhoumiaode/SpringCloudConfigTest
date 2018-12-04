package com.forezp.configclient.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.configclient.listener
 * @ClassName: MyListener
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/11/26 9:45
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/11/26 9:45
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@WebListener
public class MyListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("监听器消失");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("监听器开启");
    }
}
