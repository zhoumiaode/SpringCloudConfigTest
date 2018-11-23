package com.forezp.configclient.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.configclient.filter
 * @ClassName: CustomerRequestWrapper
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/11/23 11:08
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/11/23 11:08
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class CustomerRequestWrapper extends HttpServletRequestWrapper  {
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public CustomerRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public ServletInputStream getInputStream(){
        byte[] bytes = new byte[0];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        return  new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return byteArrayInputStream.read() == -1 ? true:false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

        };
    }
}
