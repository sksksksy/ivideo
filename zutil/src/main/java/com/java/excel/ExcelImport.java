package com.java.excel;


import com.google.common.collect.Lists;
import com.java.excel.impl.DefaultInterrupt;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

/**
 * excel import util
 */
public class ExcelImport {
    private InputStream inputStream = null;
    private RawTableData rawTableData = new RawTableData();
    private boolean isXss = true;
    private boolean getHead = true;
    private ExcelInterrupt interrupt = new DefaultInterrupt();

    public void setInput(InputStream input) {
        setInput(input, true);
    }

    public void setInput(InputStream input, boolean isXss) {
        if (input != null) {
            this.inputStream = input;
            excelImport();
        }
        this.isXss = isXss;
    }

    public void setInterrupt(ExcelInterrupt interrupt) {
        this.interrupt = interrupt;
    }

    public void setGetHead(boolean getHead) {
        this.getHead = getHead;
    }

    public RawTableData getRawTableData() {
        return rawTableData;
    }

    /**
     * @param file
     */
    public void setInput(String file) {
        setInput(file, true);
    }

    public void setInput(String file, boolean isXss) {
        try {
            InputStream s = new FileInputStream(new File(file));
            if (s != null) {
                this.inputStream = s;
                excelImport();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void excelImport() {
        parser();
    }

    /**
     * analysis
     *
     * @throws IOException
     */
    private void parser() {
        Workbook workbook = null;

        try {
            if (isXss) {
                System.out.println("尝试XSSFWorkbook");
                workbook = new XSSFWorkbook(inputStream);
            } else {
                System.out.println("尝试HSSFWorkbook");
                workbook = new HSSFWorkbook(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> headers = Lists.newLinkedList();
        List<List<String>> rawRows = Lists.newLinkedList();
        Sheet sheetPage = workbook.getSheetAt(0);
        int lastRowNum = sheetPage.getLastRowNum();
        if (lastRowNum <= 0) return;
        int i = 0;
        int h = 0;
        while (i <= lastRowNum) {
            Row row = sheetPage.getRow(i++);
            if (row == null) continue;
            Iterator<Cell> iterator = row.iterator();
            List<String> rawRow = Lists.newLinkedList();
            while (iterator.hasNext()) {
                Cell cell = iterator.next();
                String s = getCell(cell);
                if (h == 0 && getHead) {
                    headers.add(s);
                } else {
                    rawRow.add(s);
                }
            }
            h++;
            rawRow = interrupt.filter(rawRow);
            if (interrupt.skip(rawRow)) continue;
            rawRows.add(rawRow);
        }
        this.rawTableData.setHeaders(headers);
        this.rawTableData.setRows(rawRows);
    }

    private String suffix(String file) {
        int i = file.lastIndexOf(".");
        return file.substring(i + 1);
    }

    private String getCell(Cell cell) {
        StringBuffer cellStr = new StringBuffer();
        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_NUMERIC:
                DecimalFormat df=new DecimalFormat("0");
                cellStr.append(df.format(cell.getNumericCellValue()));
                break;
            case Cell.CELL_TYPE_STRING:
                cellStr.append(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                cellStr.append(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK:
                cellStr.append(" ");
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                cellStr.append(cell.getBooleanCellValue());
                break;
        }
        return cellStr.toString();
    }
}
