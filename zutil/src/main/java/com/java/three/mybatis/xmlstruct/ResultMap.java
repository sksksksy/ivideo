package com.java.three.mybatis.xmlstruct;

import lombok.Data;

import java.util.List;

@Data
public class ResultMap {
    private String id;
    private String type;
    List<Result> results;
}
