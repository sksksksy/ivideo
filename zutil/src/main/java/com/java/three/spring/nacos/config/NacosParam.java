package com.java.three.spring.nacos.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.stereotype.Component;

@Component
@NacosPropertySource(dataId = "", groupId = "", autoRefreshed = true)
public class NacosParam {
    @NacosValue(value = "${name:xxx}")
    public String name;
}
