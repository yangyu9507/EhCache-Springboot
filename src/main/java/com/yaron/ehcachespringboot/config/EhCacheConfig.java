package com.yaron.ehcachespringboot.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author Yaron
 * @version 1.0
 * @date 2023/02/08
 * @description
 */
@Slf4j
@Configuration
public class EhCacheConfig extends CachingConfigurerSupport {


    /**
     * 自定义缓存数据 key 生成策略
     * target: 类
     * method: 方法
     * params: 参数
     *
     * @return KeyGenerator
     * 注意: 该方法只是声明了key的生成策略,还未被使用,需在@Cacheable注解中指定keyGenerator
     * 如: @Cacheable(value = "key", keyGenerator = "keyGenerator")
     */
    @Override
    @Primary
    @Bean
    public KeyGenerator keyGenerator() {
        //new了一个KeyGenerator对象,采用lambda表达式写法
        //类名+方法名+参数列表的类型+参数值，然后再做md5转16进制作为key
        //使用冒号(:)进行分割，可以很多显示出层级关系
        return (target, method, params) -> {
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append(target.getClass().getName());
            strBuilder.append(":");
            strBuilder.append(method.getName());
            for (Object obj : params) {
                if (obj != null) {
                    strBuilder.append(":");
                    strBuilder.append(obj.getClass().getName());
                    strBuilder.append(":");
                    strBuilder.append(JSON.toJSONString(obj));
                }
            }
            //log.info("ehcache key str: " + strBuilder.toString());
            String md5DigestAsHex = DigestUtils.md5DigestAsHex(strBuilder.toString().getBytes(StandardCharsets.UTF_8));
            log.info("ehcache key md5DigestAsHex: " + md5DigestAsHex);
            return md5DigestAsHex;
        };
    }
}
