package com.java.tool.excel;

import com.java.tool.excel.impl.AbstractDefaultTransfer;
import com.java.exception.BaseRunException;
import com.java.tool.ObjectTools;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * excel export util.but not prefect.
 *
 * @author ZHP
 */
public final class ExcelExport extends AbstractDefaultTransfer {
    private final String PREFIX = "[*]";
    private Workbook workbook = null;
    private ExcelTransfer transfer = null;
    private FExcelTransfer fTransfer = null;

    public enum ExcelType {
        XSSF, HSSF
    }

    private void init() {
        if (workbook == null) {
            workbook = new HSSFWorkbook();
        }
    }

    public void setXSSFOrHSSF(ExcelType type) {
        if (type == ExcelType.XSSF) {
            workbook = new XSSFWorkbook();
        } else if (type == ExcelType.HSSF) {
            workbook = new HSSFWorkbook();
        }
    }

    public void setTransfer(ExcelTransfer excelTransfer) {
        this.transfer = excelTransfer;
    }

    public <T> void setFTransfer(FExcelTransfer<T> fTransfer) {
        this.fTransfer = fTransfer;
    }

    /**
     * get Workbook object
     *
     * @param excelData
     * @return
     */
    public <T> Workbook getXlsData(TableData<T> excelData) {
        List<List<String>> dataList = ObjectToList(excelData);
        return getXlsData(dataList);
    }

    /**
     * map数据结构转为Excel
     *
     * @param list
     * @return
     */
    public Workbook getXlsDataOfMap(List<Map<String, String>> list) {
        if (workbook == null) init();
        if (ObjectTools.collectionIsEmpty(list) && ObjectTools.mapIsEmpty(list.get(0))) {
            BaseRunException.throwException("传入的excel原始数据为空");
        }
        int rows = list.size();
        int cols = list.get(0).size();
        Sheet sheet = workbook.createSheet("sheet1");
        sheet.setDefaultColumnWidth(18);//设置列宽
        AtomicInteger r = new AtomicInteger(0);
        list.parallelStream().forEach(map -> {
            Row row = sheet.createRow(r.get());
            AtomicInteger c = new AtomicInteger(0);
            map.forEach((k, v) -> {
                Cell cell = row.createCell(c.get(), Cell.CELL_TYPE_BLANK);
                RichTextString text = null;
                if (workbook instanceof HSSFWorkbook) {
                    text = new HSSFRichTextString(v);
                } else if (workbook instanceof XSSFWorkbook) {
                    text = new XSSFRichTextString(v);
                }
                cell.setCellValue(text);
                c.incrementAndGet();
            });
            r.incrementAndGet();
        });
        return workbook;
    }

    /**
     * get HSSFWorkbook object
     *
     * @param dataList
     * @return
     */
    public Workbook getXlsData(List<List<String>> dataList) {
        int rows = dataList.size();
        int cols = dataList.get(0).size();
        if (workbook == null) init();
        Sheet sheet = workbook.createSheet("sheet1");
        sheet.setDefaultColumnWidth(18);//设置列宽
        Row row;
        Cell cell;
        for (int r = 0; r < rows; r++) {
            row = sheet.createRow(r);
            for (int c = 0; c < cols; c++) {
                cell = row.createCell(c, Cell.CELL_TYPE_BLANK);
                String s = dataList.get(r).get(c);
                RichTextString text = null;
                if (workbook instanceof HSSFWorkbook) {
                    text = new HSSFRichTextString(s);
                } else if (workbook instanceof XSSFWorkbook) {
                    text = new XSSFRichTextString(s);
                }
                cell.setCellValue(text);
            }
        }
        return workbook;
    }

    /**
     * make tableData to List<List<String>>
     *
     * @param excelData
     * @return
     * @since 2021-1-13 optimization
     */
    public <T> List<List<String>> ObjectToList(TableData<T> excelData) {
        List<List<String>> dataList = null;
        if (null == transfer) {
            if (fTransfer != null) {
                dataList = this.fTransfer.transfer(excelData);
            } else {
                dataList = super.transfer(excelData);
            }
        } else {
            dataList = this.transfer.transfer(excelData);
        }
        return dataList;
    }

    /**
     * make tableData to excel file
     *
     * @param excelData
     * @param filepath  fileName and filePath
     */
    public <T> void export(TableData<T> excelData, String filepath) {
        List<List<String>> dataList = ObjectToList(excelData);
        export(dataList, filepath);
    }

    /**
     * make List<<List<String>>> to excel file
     *
     * @param dataList
     * @param filepath fileName and filePath
     */
    public void export(List<List<String>> dataList, String filepath) {
        int rows = dataList.size();
        int cols = dataList.get(0).size();
        if (workbook == null) init();
        Sheet sheet = workbook.createSheet("sheet1");
        sheet.setDefaultColumnWidth(18);//设置列宽
        Row row;
        Cell cell;
        for (int r = 0; r < rows; r++) {
            row = sheet.createRow(r);
            for (int c = 0; c < cols; c++) {
                cell = row.createCell(c, Cell.CELL_TYPE_BLANK);
                String s = dataList.get(r).get(c);
                RichTextString text = null;
                if (workbook instanceof HSSFWorkbook) {
                    text = new HSSFRichTextString(s);
                } else if (workbook instanceof XSSFWorkbook) {
                    text = new XSSFRichTextString(s);
                }
                cell.setCellValue(text);
            }
        }
        //save file
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(filepath));
            workbook.write(outputStream);
            System.out.println(PREFIX + filepath + "save ok!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    System.out.println(PREFIX + "close outputStream error!");
                    e.printStackTrace();
                }
            }
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    System.out.println(PREFIX + "close workbook error!");
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 按照HSSFWorkbook保存文件
     *
     * @param filepath
     * @param workbook
     */
    @Deprecated
    public void saveXls(String filepath, HSSFWorkbook workbook) {
        saveFile(filepath, workbook.getBytes());
        if (workbook != null) {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * set skip properties list
     *
     * @param sikp
     */
    public void setSkip(List<String> sikp) {
        this.SKIP_LIST = sikp;
    }

    /**
     * whether read parent class
     *
     * @param isread
     */
    public void setIsReadSupper(boolean isread) {
        NotReadSupperClass = !isread;
    }

    /**
     * save the byte data to excel file
     *
     * @param filepath include fileName and filePath
     * @param bytes    byte[]
     */
    public void saveFile(String filepath, byte[] bytes) {
        FileOutputStream out = null;
        FileChannel outChannel = null;
        ByteBuffer buff = ByteBuffer.wrap(bytes);
        try {
            out = new FileOutputStream(new File(filepath));
            outChannel = out.getChannel();
            int length = 0;
            while ((length = outChannel.write(buff)) != 0) {
                System.out.println(PREFIX + "write length：" + length);
            }
            out.flush();
            System.out.println(PREFIX + filepath + "write success.");
        } catch (Exception e) {
            System.out.println(PREFIX + filepath + "write fail.");
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

