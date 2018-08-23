package com.oechsler.diancan.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class RedisUtil {
@Autowired
private RedisTemplate redisTemplate;
//加锁

    /**
     *
     * @param key
     * @param value 当前时间+超时时间
     * @return
     */
    public boolean lock(String key,String value){
        redisTemplate.opsForValue().set("222","222");
    if (redisTemplate.opsForValue().setIfAbsent(key,value)){
        return true;
    }
    //锁过期
    String currentValue= (String) redisTemplate.opsForValue().get(key);
    if (!StringUtils.isEmpty(currentValue)&&Long.parseLong(currentValue)<System.currentTimeMillis()){

        String oldvalue= (String) redisTemplate.opsForValue().getAndSet(key,value);
        if (!StringUtils.isEmpty(oldvalue)&&oldvalue.equals(currentValue)){
            return true;
        }

    }
    return false;

}

public void unlock(String key,String value){

        try {
            String currentValue= (String) redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue)&&currentValue.equals(value)){

                redisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e){
            log.error("redis解锁异常");
        }


}


}
