package com.java.tool.excel;

import java.util.List;

public interface ExcelInterrupt {
    List<String> filter(List<String> rawRow);
    boolean skip(List<String> rawRow);
}
