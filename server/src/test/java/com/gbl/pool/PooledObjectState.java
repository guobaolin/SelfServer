package com.gbl.pool;

/**
 * Created by guobaolin on 2018/10/25.
 */
public enum PooledObjectState {
    IDLE,
    ALLOCATED,
    EVICTION,
    EVICTION_RETURN_TO_HEAD,
    VALIDATION,
    VALIDATION_PREALLOCATED,
    VALIDATION_RETURN_TO_HEAD,
    INVALIID,
    ABANDONED,
    RETURNING;

    private PooledObjectState(){}
}
