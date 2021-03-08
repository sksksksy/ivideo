package com.java.three.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * mybatis-plus创建
 *
 * @author zhoup
 */
public class MybatisPlusCreate {
    String author = "";
    String dbUrl = "";
    String driverName = "";
    String dbUserName = "";
    String dbUserPassword = "";
    String filePath = "";
    String moduleName = "";//模块名
    String packageName = "";//包名
    String templateFilePath = "";//模版名
    String tableNames = "";//多表时用逗号隔开

    public void start() {
        //代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        //全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        if (filePath == null || "".equals(filePath)) {
            filePath = projectPath + "/src/main/java";
        }
        gc.setOutputDir(filePath);
        gc.setAuthor(author);
        gc.setOpen(false);

        autoGenerator.setGlobalConfig(gc);
        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dbUrl);//数据库连接
        dsc.setDriverName(driverName);//数据库驱动
        dsc.setUsername(dbUserName);//用户名
        dsc.setPassword(dbUserPassword);//用户密码
        dsc.setDbType(DbType.ORACLE);
        autoGenerator.setDataSource(dsc);
        //包配置
        PackageConfig pack = new PackageConfig();
        pack.setModuleName(moduleName);//模块名
        pack.setParent(packageName);//包名
        autoGenerator.setPackageInfo(pack);

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                //TODO
            }
        };
        String templatePath;
        // 如果模板引擎是 freemarker
        if (templateFilePath == null || "".equals(templateFilePath)) {
            templatePath = "/templates/mapper.xml.ftl";
        } else {
            templatePath = templateFilePath;
        }
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pack.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        autoGenerator.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        strategy.setInclude(tableNames);//表名，多个表使用,隔开
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pack.getModuleName() + "_");
        autoGenerator.setStrategy(strategy);
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator.execute();
    }
}
