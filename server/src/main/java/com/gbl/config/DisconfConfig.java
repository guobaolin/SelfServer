package com.gbl.config;

import com.baidu.disconf.client.DisconfMgrBean;
import com.baidu.disconf.client.DisconfMgrBeanSecond;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by guobaolin on 2018/10/31.
 */
@Configuration
//@EnableAspectJAutoProxy
public class DisconfConfig {

    public static final String scanPackage = "com.gbl";

    @Bean(destroyMethod = "destroy")
    public DisconfMgrBean disconfMgrBean(){
        DisconfMgrBean disconfMgr = new DisconfMgrBean();
        disconfMgr.setScanPackage(scanPackage);
        return disconfMgr;
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public DisconfMgrBeanSecond disconfMgrBean2(){
        return new DisconfMgrBeanSecond();
    }

}
