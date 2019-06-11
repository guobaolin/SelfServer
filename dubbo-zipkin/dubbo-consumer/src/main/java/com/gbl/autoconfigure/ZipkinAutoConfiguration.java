package com.gbl.autoconfigure;

/**
 * Created by guobaolin on 2019/1/29.
 */
//@Configuration
//@EnableConfigurationProperties(ZipkinProperties.class)
public class ZipkinAutoConfiguration {

//    @Autowired
//    private ZipkinProperties zipkinProperties;

//    @Bean
//    public SpanCollector spanCollector(){
//        HttpSpanCollector.Config config = HttpSpanCollector.Config.builder()
//                .compressionEnabled(zipkinProperties.isCompressionEnabled())
//                .connectTimeout(zipkinProperties.getConnectTimeout())
//                .flushInterval(zipkinProperties.getFlushInterval())
//                .readTimeout(zipkinProperties.getReadTimeout())
//                .build();
//        return HttpSpanCollector.create(zipkinProperties.getUrl(), config, new EmptySpanCollectorMetricsHandler());
//    }
//
//    @Bean
//    public Brave brave(SpanCollector spanCollector){
//        Brave.Builder builder = new Brave.Builder(zipkinProperties.getServiceName());
//        builder.spanCollector(spanCollector);
//        builder.traceSampler(Sampler.ALWAYS_SAMPLE);
//        return builder.build();
//    }
//
//    @Bean
//    public BraveServletFilter braveServletFilter(Brave brave){
//        BraveServletFilter filter = new BraveServletFilter(brave.serverRequestInterceptor(),
//                brave.serverResponseInterceptor(), new DefaultSpanNameProvider());
//        return filter;
//    }
//
//    @Bean
//    public OkHttpClient okHttpClient(Brave brave) {
//        OkHttpClient httpClient = new OkHttpClient().newBuilder()
//                .addInterceptor(new BraveOkHttpRequestResponseInterceptor(
//                        brave.clientRequestInterceptor(),
//                        brave.clientResponseInterceptor(),
//                        new DefaultSpanNameProvider()
//                )).build();
//        return httpClient;
//    }
}
