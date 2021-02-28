package com.java.tool.excel;

import java.util.List;

public interface ExcelTransfer {
    <T> List<List<String>> transfer(TableData<T> data);
}
