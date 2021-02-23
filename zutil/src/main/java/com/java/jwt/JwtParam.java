package com.java.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtParam {
    /**
     * 写在http请求或响应头中的名字
     */
    private String tokenHeader;
    /**
     * 加入到token中的
     */
    private String tokenHead;
    /**
     * 密钥
     */
    private String secret;
    /**
     * 超时
     */
    private Long expiration;


}
