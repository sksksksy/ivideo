package com.java.three.mybatis.xmlstruct;

import lombok.Data;

import java.util.List;

@Data
public class MapperXml {
    private String mapperName;
    private String mapperNamespace;
    List<ResultMap> resultMaps;
    List<Select> selectList;
}
