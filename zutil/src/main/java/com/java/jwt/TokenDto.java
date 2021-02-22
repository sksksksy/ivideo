package com.java.jwt;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>tokenDta用于传输token中的userId</p>
 *
 * @version 1.1.0
 * @since 1.1.0
 */
@Accessors(chain = true)
@Data
public class TokenDto implements Serializable {
    private final static long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 公司Id
     */
    private String companyId;
}
