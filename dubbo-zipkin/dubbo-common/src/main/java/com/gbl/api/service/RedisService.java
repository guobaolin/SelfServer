package com.gbl.api.service;

import java.io.Serializable;

public interface RedisService {

    Serializable get(final String key);

    boolean set(final String key, Serializable value);

    void remove(final String key);

    boolean exists(final String key);

}
