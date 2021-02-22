package com.java.excel;

import java.util.List;

public interface ExcelInterrupt {
    List<String> filter(List<String> rawRow);
    boolean skip(List<String> rawRow);
}
