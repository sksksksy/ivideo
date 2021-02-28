package com.java.tool.excel.impl;



import com.java.tool.excel.ExcelTransfer;
import com.java.tool.excel.TableData;

import java.lang.reflect.Field;
import java.util.*;

public abstract class AbstractDefaultTransfer implements ExcelTransfer {
    protected List<String> SKIP_LIST = null;
    protected boolean NotReadSupperClass = true;


    @Override
    public <T> List<List<String>> transfer(TableData<T> excelData) {
        List<List<String>> dataList = Collections.synchronizedList(new LinkedList<>());
        List<String> headers = excelData.getHeaders();
        if (headers != null) {
            dataList.add(headers);
        }
        List<T> objectList = excelData.getDataList();
        List<String> fieldNames = new LinkedList<>();
        if (null != excelData.getDataList() && excelData.getDataList().size() > 0) {
            T td = excelData.getDataList().get(0);
            Class<?> aClass = td.getClass();
            getFields(aClass, fieldNames);
            objectList.parallelStream().forEach(t -> {
                Class<?> clazz = t.getClass();
                List<String> rows = new LinkedList<String>();
                fieldNames.stream().forEach(f -> {
                    try {
                        Field field = clazz.getDeclaredField(f);
                        field.setAccessible(true);
                        Object o = field.get(t);
                        if (Objects.isNull(o)) {
                            rows.add(" ");
                        } else if (!(o instanceof String)) {
                            rows.add(String.valueOf(o));
                        } else {
                            rows.add(o.toString());
                        }
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
                dataList.add(rows);
            });
        }
        return dataList;
    }

    private List<String> getFields(Class<?> clazz, List<String> result) {
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (IsNotInclude(SKIP_LIST, field.getName())) continue;
            result.add(field.getName());
        }
        if (!hasSuperClass(clazz) || NotReadSupperClass) {
            return result;
        }
        return getFields(clazz.getSuperclass(), result);
    }

    private <T> boolean IsInclude(Collection<T> objects, T o) {
        if (objects == null || objects.size() == 0) return true;
        if (objects.contains(o)) return true;
        return false;
    }

    private <T> boolean IsNotInclude(Collection<T> objects, T o) {
        return !IsInclude(objects, o);
    }

    private boolean hasSuperClass(Class<?> clazz) {
        if (clazz.getSuperclass() == Object.class) {
            return false;
        }
        return true;
    }
}
