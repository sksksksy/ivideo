package com.java.tool.excel.impl;



import com.java.tool.excel.ExcelInterrupt;

import java.util.List;

public class DefaultInterrupt implements ExcelInterrupt {
    @Override
    public List<String> filter(List<String> rawRow) {
        return rawRow;
    }
    @Override
    public boolean skip(List<String> rawRow) {
        if(null==rawRow||rawRow.size()==0){
            return true;
        }
        return false;
    }
}
