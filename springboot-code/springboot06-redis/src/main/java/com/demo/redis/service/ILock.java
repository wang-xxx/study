package com.demo.redis.service;

public interface ILock {

    boolean tryLock(long timeoutSec);

    void unlock();

}
