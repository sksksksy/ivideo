package com.java.jwt;


import com.java.string.StringCheck;
import com.java.tool.ClassTools;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 * Created by macro on 2018/4/26.
 *
 * @code{getObject,generateTokenFromObject,getObjectFromClaims,getFiledMap,getFields}
 * @see JwtToken#getObject(String, Object) 根据token字符串和传入的空token对象，返回带值的token对象
 * @see JwtToken#getObjectFromClaims(Claims, Object) 内部方法
 * @see JwtToken#getFiledMap(Object) 内部方法
 * @since 2021/01/19 新增根据传的对应生成token和根据token字符串获取token对象
 */
public class JwtToken {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtToken.class);
    private static final String CLAIM_KEY_USERID = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private String tokenHeader;
    private String tokenHead;
    private String secret;
    private Long expiration;

    public JwtToken() {
    }

    public JwtToken(String tokenHeader, String tokenHead, String secret, Long expiration) {
        this.tokenHeader = tokenHeader;
        this.tokenHead = tokenHead;
        this.secret = secret;
        this.expiration = expiration;
    }

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public String getTokenHead() {
        return tokenHead;
    }

    public void setTokenHead(String tokenHead) {
        this.tokenHead = tokenHead;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    /**
     * 根据负责生成JWT的token
     */

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token中获取登录用户名
     */
    @Deprecated
    public String getUserIdFromToken(String token) {
        String userId;
        try {
            Claims claims = getClaimsFromToken(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            userId = null;
        }
        return userId;
    }

    /**
     * 根据token字符串和对象获取值
     *
     * @param token
     * @param t
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T> T getObject(String token, T t) {
        Claims claims = getClaimsFromToken(token);
        return getObjectFromClaims(claims, t);
    }

    /**
     * 验证token是否还有效
     *
     * @param token    客户端传入的token
     * @param tokenDto 从数据库中查询出来的用户信息
     */
    @Deprecated
    public boolean validateToken(String token, TokenDto tokenDto) {
        String userId = getUserIdFromToken(token);
        return userId.equals(tokenDto.getUserId()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     */
    public boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 根据TokenDto生成token
     */
    @Deprecated
    public String generateToken(TokenDto tokenDto) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERID, tokenDto.getUserId());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 当原来的token没过期时是可以刷新的
     *
     * @param oldToken 带tokenHead的token
     */
    public String refreshHeadToken(String oldToken) {
        if (StringCheck.isEmpty(oldToken)) {
            return null;
        }
        String token = oldToken.substring(tokenHead.length());
        if (StringCheck.isEmpty(token)) {
            return null;
        }
        //token校验不通过
        Claims claims = getClaimsFromToken(token);
        if (claims == null) {
            return null;
        }
        //如果token已经过期，不支持刷新
        if (isTokenExpired(token)) {
            return null;
        }
        //如果token在30分钟之内刚刷新过，返回原token
        if (tokenRefreshJustBefore(token, 30 * 60)) {
            return token;
        } else {
            claims.put(CLAIM_KEY_CREATED, new Date());
            return generateToken(claims);
        }
    }

    /**
     * 判断token在指定时间内是否刚刚刷新过
     *
     * @param token 原token
     * @param time  指定时间（秒）
     */
    private boolean tokenRefreshJustBefore(String token, int time) {
        Claims claims = getClaimsFromToken(token);
        Date created = claims.get(CLAIM_KEY_CREATED, Date.class);
        Date refreshDate = new Date();
        //刷新时间在创建时间的指定时间内
        if (refreshDate.after(created) && refreshDate.before(offsetSecond(created, time))) {
            return true;
        }
        return false;
    }

    private Date offsetSecond(Date date, int time) {
        long offsetd = date.getTime() + time * 1000;
        return new Date(offsetd);
    }

    /**
     * 使用对象生成jwtToken
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T extends TokenDto> String generateTokenFromObject(T t) {
        Map<String, Object> claims = getFiledMap(t);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 根据claims组装对象
     *
     * @param claims
     * @param t
     * @param <T>
     * @return
     */
    private <T> T getObjectFromClaims(Claims claims, T t) {
        if (t == null) return null;
        Class<?> clazz = t.getClass();
        List<String> fields = new LinkedList<>();
        ClassTools.getFields(clazz, fields, false);
        int count = 0;
        for (Method method : clazz.getMethods()) {
            String name = method.getName();
            if (!name.contains("set")) continue;
            for (String e : fields) {
                String methodName = "set" + e.substring(0, 1).toUpperCase() + e.substring(1);
                if (!name.equals(methodName)) {
                    continue;
                }
                String a = (String) claims.get(e);
                if (a != null) {
                    try {
                        if (method.getParameterTypes()[0] == Integer.class) {
                            method.invoke(t, Integer.parseInt(a));
                        } else {
                            method.invoke(t, a);
                        }
                        count++;

                    } catch (IllegalAccessException illegalAccessException) {
                        LOGGER.error("非法", illegalAccessException);
                    } catch (InvocationTargetException invocationTargetException) {
                        LOGGER.error("invocation", invocationTargetException);
                    }
                }
            }
        }
        System.out.println("收到的Token对象为:" + t);
        return count > 0 ? t : null;
    }

    private <T> Map<String, Object> getFiledMap(T t) {
        Map<String, Object> claims = new LinkedHashMap<>();
        if (null != t) {
            Class<?> clazz = t.getClass();
            List<String> fieldNames = new LinkedList<>();
            ClassTools.getFields(clazz, fieldNames, false);
            fieldNames.stream().forEach(f -> {
                try {
                    Field field = clazz.getDeclaredField(f);
                    field.setAccessible(true);
                    Object o = field.get(t);
                    if (Objects.isNull(o)) {

                    } else if (!(o instanceof String)) {
                        claims.put(f, String.valueOf(o));
                    } else {
                        claims.put(f, o);
                    }
                } catch (IllegalAccessException | NoSuchFieldException var6) {
                    var6.printStackTrace();
                }
            });
        }
        return claims;
    }
}
