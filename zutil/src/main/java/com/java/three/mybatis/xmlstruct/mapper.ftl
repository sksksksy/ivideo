<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${xml.mapperNamespace!""}">
    <#if xml.resultMaps??>
        <#list xml.resultMaps as rm>
            <resultMap id="${rm.id}" type="${rm.type!""}">
                <#if rm.results??>
                    <#list rm.results as r>
                        <result column="" property="${r.property}"/>
                    </#list></#if>
            </resultMap>
        </#list>
    </#if>
    <#if xml.selectList??>
        <#list xml.selectList as sl>
            <select id="${sl.id}" resultMap="${sl.resultMap}"></select>
        </#list>
    </#if>
</mapper>