package com.gbl.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by guobaolin on 2019/3/14.
 */
public class TraceUtil {

    private static final ThreadLocal<Map<String, String>> TRACE = new ThreadLocal<>();

    public static final String TRACE_ID = "traceId";

    public static final String PARENT_ID = "parentId";

    /**
     * 获取全局链路Id
     *
     * @return
     */
    public static String getTraceId() {
        return getTrace().get(TRACE_ID);
    }

    public static void setTraceId(String traceId) {
        getTrace().put(TRACE_ID, traceId);
    }

    /**
     * 获取上一级链路的spanId
     *
     * @return
     */
    public static String getParentId() {
        return getTrace().get(PARENT_ID);
    }

    public static void setParentId(String parentId) {
        if (parentId != null) {
            getTrace().put(PARENT_ID, parentId);
        }
    }

    public static Map<String, String> getTrace() {
        if (TRACE.get() == null) {
            Map<String, String> traceMap = new HashMap<>();
            traceMap.put(TRACE_ID, UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
            traceMap.put(PARENT_ID, null);
            TRACE.set(traceMap);
        }
        return TRACE.get();
    }

    public static void setTrace(Map<String, String> traceMap) {
        TRACE.set(traceMap);
    }
}
