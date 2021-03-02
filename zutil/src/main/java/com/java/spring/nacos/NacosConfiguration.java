package com.java.spring.nacos;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.annotation.NacosProperty;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("")
@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "172.16.20.150:8848"))
public class NacosConfiguration {
}
