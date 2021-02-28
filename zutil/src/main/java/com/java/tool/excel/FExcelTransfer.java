package com.java.tool.excel;

import java.util.List;

/**
 * 函数式转换接口
 */
@FunctionalInterface
public interface FExcelTransfer<T> {
    List<List<String>> transfer(TableData<T> tableData);
}
