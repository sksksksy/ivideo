package com.java.three.mybatis;

import com.java.three.mybatis.xmlstruct.MapperXml;
import com.java.three.mybatis.xmlstruct.Result;
import com.java.three.mybatis.xmlstruct.ResultMap;
import com.java.three.mybatis.xmlstruct.Select;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.*;

/**
 * 根据mapper生成xml
 */
public class GenerateXmlFormMapper {
    JavaProjectBuilder builder = new JavaProjectBuilder();
    Configuration configuration = new Configuration();
    Writer out = null;
    //模版根路径
    private String TEMPLATE_PATH = "D:\\workplace\\wdd\\parent\\generate\\src\\main\\resources\\template";
    //需要加载文件的路径
    private String TREE_FILE_PATH = "D:\\workplace\\wdd\\parent\\services\\src\\main\\java\\cq\\sgcc\\wd\\services\\domain";
    //额外加载的文件路径
    private List<String> FILE_PATHS;
    //生成的目的目录
    private String target_path = "D:\\workplace\\wdd\\package\\parent\\services\\src\\main\\resources\\mapper\\modify\\";

    public void setTEMPLATE_PATH(String TEMPLATE_PATH) {
        this.TEMPLATE_PATH = TEMPLATE_PATH;
    }

    public void setTREE_FILE_PATH(String TREE_FILE_PATH) {
        this.TREE_FILE_PATH = TREE_FILE_PATH;
    }

    public void setFILE_PATHS(List<String> FILE_PATHS) {
        this.FILE_PATHS = FILE_PATHS;
    }

    public void setTarget_path(String target_path) {
        this.target_path = target_path;
    }

    public void init() {
//        builder.addSourceTree(new File("D:\\workplace\\wdd\\parent\\services\\src\\main\\java\\cq\\sgcc\\wd\\services\\mapper\\modify"));
        builder.addSourceTree(new File(TREE_FILE_PATH));
        try {
            for (String f : FILE_PATHS) {
                builder.addSource(new File(f));
            }
            builder.addSource(new File("D:\\workplace\\wdd\\package\\parent\\services\\src\\main\\java\\cq\\sgcc\\wd\\services\\mapper\\modify\\SearchMapper.java"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<MapperXml> parser() {
        init();
        Collection<JavaClass> classes = builder.getClasses();
        List<MapperXml> mapperXmls = new LinkedList<>();
        System.out.println(classes.size());
        System.out.println(classes.size());
        classes.stream().filter(e -> {
            if (e.getName().endsWith("Mapper")) {
                return true;
            }
            return false;
        }).forEach(
                j -> {
                    MapperXml resultMaps = new MapperXml();
                    resultMaps.setMapperName(j.getName());
                    resultMaps.setMapperNamespace(j.getFullyQualifiedName());
                    List<ResultMap> resultMapList = new LinkedList<>();
                    List<Select> selectList = new LinkedList<>();
                    System.out.println(j.getName());
                    j.getMethods().forEach(m -> {
                        ResultMap resultMap = new ResultMap();
                        Select select = new Select();
                        String name = m.getName();
                        select.setId(name);
                        resultMap.setId(name + "_map");
                        select.setResultMap(name + "_map");
                        List<Result> results = new LinkedList<>();
                        String see = getSeeTagValue(m);
                        if (null != see) {
                            JavaClass javaClass = builder.getClassByName(see);
                            resultMap.setType(javaClass.getFullyQualifiedName());
                            addSuperField(javaClass, results);
                            javaClass.getFields().forEach(f -> {
                                Result result = new Result();
                                result.setProperty(f.getName());
                                results.add(result);
                            });
                        }
                        resultMap.setResults(results);
                        resultMapList.add(resultMap);
                        selectList.add(select);
                    });
                    resultMaps.setResultMaps(resultMapList);
                    resultMaps.setSelectList(selectList);
                    System.out.println(resultMaps);
                    mapperXmls.add(resultMaps);
                }
        );
        return mapperXmls;
    }

    /**
     * 尾递归
     *
     * @param javaClass
     * @param results
     */
    private void addSuperField(JavaClass javaClass, List<Result> results) {
        JavaClass superClass = javaClass.getSuperJavaClass();
        /**
         * 防止死循环
         */
        if (Objects.isNull(superClass)) {
            return;
        }
        superClass.getFields().forEach(sf -> {
            Result result = new Result();
            result.setProperty(sf.getName());
            results.add(result);
        });
        addSuperField(superClass, results);
    }

    public void generateTemplate() throws IOException {
        try {
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String prefix = "D:\\workplace\\wdd\\package\\parent\\services\\src\\main\\resources\\mapper\\modify\\";
        String prefix = target_path;
        Template template = configuration.getTemplate("mapper.ftl");
        parser().parallelStream().forEach(
                xml -> {

                    File outFile = new File(prefix + xml.getMapperName() + ".xml");
                    try {
                        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        Map<String, Object> map = new HashMap<>();
                        map.put("xml", xml);
                        template.process(map, out);
                        System.out.println(outFile.getAbsoluteFile() + "生成完成");
                    } catch (TemplateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        System.out.println("============>xml创建完成");

    }

    public String getSeeTagValue(JavaMethod method) {
        DocletTag see = method.getTagByName("see");
        if (null != see) {
//            System.out.println("tag is " + see.getName());
            return see.getValue();
        }
        return null;
    }

    public void start() throws IOException {
        generateTemplate();
    }

    public static void main(String[] args) throws IOException {
        GenerateXmlFormMapper generateFile = new GenerateXmlFormMapper();
//        generateFile.parser();
        generateFile.generateTemplate();
    }
}
