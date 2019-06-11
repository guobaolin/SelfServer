package com.gbl.autoconfigure;

import brave.Tracing;
import brave.propagation.B3Propagation;
import brave.propagation.ExtraFieldPropagation;
import brave.sampler.Sampler;
import com.gbl.propertiesconfig.TraceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

import java.util.concurrent.TimeUnit;

/**
 * Created by guobaolin on 2019/1/29.
 */
@Configuration
@EnableConfigurationProperties({TraceProperties.class})
public class TraceAutoConfiguration {

    @Autowired
    private TraceProperties traceProperties;

    @Bean
    public Sender sender() {
        return OkHttpSender.create(traceProperties.getZipkin());
    }

    @Bean
    public AsyncReporter<zipkin2.Span> spanReporter() {
        return AsyncReporter.builder(sender())
                .closeTimeout(6000, TimeUnit.MILLISECONDS)
                .messageTimeout(6000, TimeUnit.MILLISECONDS)
                .build();
    }

    @Bean
    public Tracing tracing() {
        return Tracing.newBuilder()
                .localServiceName(traceProperties.getServiceName())
                .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, "shiliew"))
                .spanReporter(spanReporter())
                .sampler(Sampler.create(traceProperties.getRate()))
                .build();
    }

//    @Bean
//    public HttpTracing httpTracing(Tracing tracing) {
//        HttpTracing httpTracing = HttpTracing.create(tracing);
//        return httpTracing.toBuilder()
//                .serverParser(new HttpServerParser() {
//                    @Override
//                    protected <Req> String spanName(HttpAdapter<Req, ?> adapter, Req req) {
//                        return adapter.path(req);
//                    }
//                }).clientParser(new HttpClientParser() {
//                    @Override
//                    protected <Req> String spanName(HttpAdapter<Req, ?> adapter, Req req) {
//                        return adapter.path(req);
//                    }
//                }).build();
//    }

}
