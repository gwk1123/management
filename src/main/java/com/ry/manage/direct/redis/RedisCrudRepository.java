package com.ry.manage.direct.redis;

public interface RedisCrudRepository<T> {

    T saveOrUpdate(T item);
}
