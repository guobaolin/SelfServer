package com.gbl.propertiesconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by guobaolin on 2019/1/29.
 */
@ConfigurationProperties(prefix = TraceProperties.TRACE_PREFIX)
public class TraceProperties {
    public static final String TRACE_PREFIX = "trace.brave";

    private String serviceName;
    private String zipkin;
    private float rate;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getZipkin() {
        return zipkin;
    }

    public void setZipkin(String zipkin) {
        this.zipkin = zipkin;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
