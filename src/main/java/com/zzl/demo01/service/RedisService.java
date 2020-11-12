package com.zzl.demo01.service;

public interface RedisService {
    void set(String key,String value);
    String get(String key);
    //设置超时时间
    boolean expire(String key,long expire);
    void remove(String key);

    /**
     * 自增操作
     * @param key
     * @param delta 自增步长
     * @return
     */
    Long increment(String key,long delta);
}
