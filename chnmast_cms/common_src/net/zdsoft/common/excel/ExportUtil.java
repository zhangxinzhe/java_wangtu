package net.zdsoft.common.excel;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import net.zdsoft.keel.action.Printable;
import net.zdsoft.keel.util.FileUtils;
import net.zdsoft.keel.util.ObjectUtils;

public class ExportUtil implements Printable {
    public static final String PROMISSION = "promission";
    public static final short DATE_FORMAT = HSSFDataFormat.getBuiltinFormat("m/d/yy");

    /**
     * Comment for <code>serialVersionUID</code>
     */

    private short dateFormat = DATE_FORMAT; // 默认日期格式
    private boolean printEnabled = false; // 默认为非printable action

    public ExportUtil() {
    }

    public short getDateFormat() {
        return dateFormat;
    }

    @Override
    public String getPrintContent() {
        return null;
    }

    @Override
    public boolean isPrintEnabled() {
        return printEnabled;
    }

    public void setDateFormat(short dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setPrintEnabled(boolean printEnabled) {
        this.printEnabled = printEnabled;
    }

    public void exportXLSFile(String fileName, Map<String, List<String>> fieldTitleMap,
            Map<String, List<Map<String, String>>> sheetName2RecordListMap) {
        printEnabled = true; // 设置为printable

        HSSFWorkbook workbook = new HSSFWorkbook();

        int i = 0;
        for (Iterator iter = sheetName2RecordListMap.entrySet().iterator(); iter.hasNext();) {
            Map.Entry entry = (Entry) iter.next();
            String sheetName = (String) entry.getKey(); // key 工作表名称
            List recordList = (List) entry.getValue(); // value 工作表上的记录

            workbook.createSheet();
            workbook.setSheetName(i++, sheetName, HSSFWorkbook.ENCODING_UTF_16);

            // 创建首行信息栏
            HSSFRow row = workbook.getSheetAt(i - 1).createRow(0);
            List<String> fieldTitleList = fieldTitleMap.get(entry.getKey());
            String[] fieldTitles = fieldTitleList.toArray(new String[0]);
            for (int j = 0; j < fieldTitles.length; j++) {
                HSSFCell cell = row.createCell((short) j);
                cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(fieldTitles[j]);
            }

            // 写入每条记录
            int rowNum = 1; // 行号
            for (int j = 0, m = recordList.size(); j < m; j++) {
                HSSFRow _row = workbook.getSheetAt(i - 1).createRow(rowNum++);
                Map field2ValueMap = (Map) recordList.get(j);

                for (int k = 0; k < fieldTitles.length; k++) {
                    HSSFCell cell = _row.createCell((short) k);
                    cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
                    Object value = field2ValueMap.get(fieldTitles[k]);

                    if (value == null) {
                        _row.getCell((short) k).setCellType(HSSFCell.CELL_TYPE_BLANK);
                    }
                    else if (value instanceof String) {
                        _row.getCell((short) k).setCellType(HSSFCell.CELL_TYPE_STRING);
                        _row.getCell((short) k).setCellValue((String) value);
                    }
                    else if (value instanceof Boolean) {
                        _row.getCell((short) k).setCellType(HSSFCell.CELL_TYPE_BOOLEAN);
                        _row.getCell((short) k).setCellValue(((Boolean) value).booleanValue());
                    }
                    else if (value instanceof Integer) {
                        _row.getCell((short) k).setCellValue(((Integer) value).intValue());
                    }
                    else if (value instanceof Double) {
                        _row.getCell((short) k).setCellValue(((Double) value).doubleValue());
                    }
                    else if (value instanceof Date) {
                        HSSFCellStyle cellDateStyle = workbook.createCellStyle();
                        cellDateStyle.setDataFormat(dateFormat);
                        _row.getCell((short) k).setCellStyle(cellDateStyle);
                        _row.getCell((short) k).setCellValue((Date) value);
                    }
                    else {
                        _row.getCell((short) k).setCellValue(value.toString());
                    }
                }
            }
        }

        outputData(workbook, fileName); // 导出文件
    }

    /**
     * 将记录信息导出为xls格式文件. (未使用reflection来实现的方法)
     * 
     * @param fileName
     *            导出文件的名称
     * @param fieldTitles
     *            工作表的字段标题数组(首行)
     * @param sheetName2RecordListMap
     *            记录集map, 其中key为工作表名称, value为一个list(内含字段标题-字段值的map).
     */
    protected void exportXLSFile(String fileName, String[] fieldTitles, Map<String, List<Map>> sheetName2RecordListMap) {
        printEnabled = true; // 设置为printable
        HSSFWorkbook workbook = getWorkbookForExport(fileName, fieldTitles, sheetName2RecordListMap);
        outputData(workbook, fileName); // 导出文件
    }

    public void exportXLSFile(String fileName, String[] fieldTitles, Map<String, List<Map>> sheetName2RecordListMap,
            OutputStream out) {
        printEnabled = true; // 设置为printable
        HSSFWorkbook workbook = getWorkbookForExport(fileName, fieldTitles, sheetName2RecordListMap);
        try {
            workbook.write(out); // 输出文件
            out.flush();
        }
        catch (IOException e) {
            // ingore
        }
        finally {
            FileUtils.close(out);
        }
    }

    protected HSSFWorkbook getWorkbookForExport(String fileName, String[] fieldTitles,
            Map<String, List<Map>> sheetName2RecordListMap) {
        printEnabled = true; // 设置为printable

        HSSFWorkbook workbook = new HSSFWorkbook();

        int i = 0;
        for (Iterator iter = sheetName2RecordListMap.entrySet().iterator(); iter.hasNext();) {
            Map.Entry entry = (Entry) iter.next();
            String sheetName = (String) entry.getKey(); // key 工作表名称
            List recordList = (List) entry.getValue(); // value 工作表上的记录

            workbook.createSheet();
            workbook.setSheetName(i++, sheetName, HSSFWorkbook.ENCODING_UTF_16);

            // 创建首行信息栏
            HSSFRow row = workbook.getSheetAt(i - 1).createRow(0);
            for (int j = 0; j < fieldTitles.length; j++) {
                HSSFCell cell = row.createCell((short) j);
                cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(fieldTitles[j]);
            }

            // 写入每条记录
            int rowNum = 1; // 行号
            for (int j = 0, m = recordList.size(); j < m; j++) {
                HSSFRow _row = workbook.getSheetAt(i - 1).createRow(rowNum++);
                Map field2ValueMap = (Map) recordList.get(j);

                for (int k = 0; k < fieldTitles.length; k++) {
                    HSSFCell cell = _row.createCell((short) k);
                    cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
                    Object value = field2ValueMap.get(fieldTitles[k]);

                    if (value == null) {
                        _row.getCell((short) k).setCellType(HSSFCell.CELL_TYPE_BLANK);
                    }
                    else if (value instanceof String) {
                        _row.getCell((short) k).setCellType(HSSFCell.CELL_TYPE_STRING);
                        _row.getCell((short) k).setCellValue((String) value);
                    }
                    else if (value instanceof Boolean) {
                        _row.getCell((short) k).setCellType(HSSFCell.CELL_TYPE_BOOLEAN);
                        _row.getCell((short) k).setCellValue(((Boolean) value).booleanValue());
                    }
                    else if (value instanceof Integer) {
                        _row.getCell((short) k).setCellValue(((Integer) value).intValue());
                    }
                    else if (value instanceof Double) {
                        _row.getCell((short) k).setCellValue(((Double) value).doubleValue());
                    }
                    else if (value instanceof Date) {
                        HSSFCellStyle cellDateStyle = workbook.createCellStyle();
                        cellDateStyle.setDataFormat(dateFormat);
                        _row.getCell((short) k).setCellStyle(cellDateStyle);
                        _row.getCell((short) k).setCellValue((Date) value);
                    }
                    else {
                        _row.getCell((short) k).setCellValue(value.toString());
                    }
                }
            }
        }

        return workbook; // 导出文件
    }

    /**
     * 将记录信息导出为xls格式文件. (使用reflection来实现的方法)
     * 
     * @param fieldTitles
     *            工作表的字段标题数组(首行)
     * @param propertyNames
     *            每行记录对应的值对象的属性名称数组
     * @param records
     *            记录集map, 其中key为工作表名称, value为属于该工作表的值对象列表
     * @param fileName
     *            导出文件的名称
     */
    public void exportXLSFile(String[] fieldTitles, String[] propertyNames, Map records, String fileName) {
        printEnabled = true; // 设置为printable

        HSSFWorkbook workbook = getWorkbookForExport(fieldTitles, propertyNames, records, fileName);

        outputData(workbook, fileName); // 导出文件

    }

    public void exportXLSFile(String[] fieldTitles, String[] propertyNames, Map records, String fileName,
            OutputStream out) {
        printEnabled = true; // 设置为printable

        HSSFWorkbook workbook = getWorkbookForExport(fieldTitles, propertyNames, records, fileName);

        try {
            workbook.write(out); // 输出文件
            out.flush();
        }
        catch (IOException e) {
            // ingore
        }
        finally {
            FileUtils.close(out);
        }

    }

    private HSSFWorkbook getWorkbookForExport(String[] fieldTitles, String[] propertyNames, Map records, String fileName) {
        if (fieldTitles.length != propertyNames.length) {
            throw new IllegalArgumentException("工作表的字段标题列数必须和值对象的属性数相等");
        }

        HSSFWorkbook workbook = new HSSFWorkbook();

        int i = 0;
        for (Iterator iter = records.entrySet().iterator(); iter.hasNext();) {
            Map.Entry entry = (Entry) iter.next();
            String sheetName = (String) entry.getKey(); // key 工作表名称
            List sheetRecords = (List) entry.getValue(); // value 工作表上的记录

            workbook.createSheet();
            workbook.setSheetName(i++, sheetName, HSSFWorkbook.ENCODING_UTF_16);

            // 创建首行信息栏
            HSSFRow row = workbook.getSheetAt(i - 1).createRow(0);
            for (int j = 0; j < fieldTitles.length; j++) {
                HSSFCell cell = row.createCell((short) j);
                cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(fieldTitles[j]);
            }

            // 写入每条记录
            int rowNum = 1; // 行号
            for (int j = 0, m = sheetRecords.size(); j < m; j++) {
                HSSFRow _row = workbook.getSheetAt(i - 1).createRow(rowNum++);
                Object obj = sheetRecords.get(j);
                for (int k = 0; k < fieldTitles.length; k++) {
                    HSSFCell cell = _row.createCell((short) k);
                    cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);

                    Object value = ObjectUtils.getNestedProperty(obj, propertyNames[k]);

                    if (value == null) {
                        _row.getCell((short) k).setCellType(HSSFCell.CELL_TYPE_BLANK);
                    }
                    else if (value instanceof String) {
                        _row.getCell((short) k).setCellType(HSSFCell.CELL_TYPE_STRING);
                        _row.getCell((short) k).setCellValue((String) value);
                    }
                    else if (value instanceof Boolean) {
                        _row.getCell((short) k).setCellType(HSSFCell.CELL_TYPE_BOOLEAN);
                        _row.getCell((short) k).setCellValue(((Boolean) value).booleanValue());
                    }
                    else if (value instanceof Integer) {
                        _row.getCell((short) k).setCellValue(((Integer) value).intValue());
                    }
                    else if (value instanceof Double) {
                        _row.getCell((short) k).setCellValue(((Double) value).doubleValue());
                    }
                    else if (value instanceof Date) {
                        HSSFCellStyle cellDateStyle = workbook.createCellStyle();
                        cellDateStyle.setDataFormat(dateFormat);
                        _row.getCell((short) k).setCellStyle(cellDateStyle);
                        _row.getCell((short) k).setCellValue((Date) value);
                    }
                    else {
                        _row.getCell((short) k).setCellValue(value.toString());
                    }
                }
            }
        }
        return workbook;
    }

    /**
     * 将记录信息导出为xls格式文件.
     * 
     * @param fieldTitles
     *            工作表的字段标题数组(首行)
     * @param sheetNames
     *            工作表名
     * @param fileName
     *            导出文件的名称
     */
    public void exportXLSTemplet(String[] fieldTitles, String[] sheetNames, String fileName) {
        printEnabled = true; // 设置为printable

        HSSFWorkbook workbook = new HSSFWorkbook();

        for (int i = 0, length = sheetNames.length; i < length; i++) {

            workbook.createSheet();
            workbook.setSheetName(i, sheetNames[i], HSSFWorkbook.ENCODING_UTF_16);

            // 创建首行信息栏
            HSSFRow row = workbook.getSheetAt(i).createRow(0);
            for (int j = 0; j < fieldTitles.length; j++) {
                HSSFCell cell = row.createCell((short) j);
                cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(fieldTitles[j]);
            }

        }

        outputData(workbook, fileName); // 导出文件
    }

    /**
     * 导出多条xls文档
     * 
     * @param fileName
     *            文件名
     * @param sheet2fieldName
     *            每个sheet的字段名，首行
     * @param sheet2Record
     *            每个sheet对应数据记录
     * 
     *            sheet2fieldName和sheet2Record中数组长度必须相等,
     */
    public void exportMultXLSFile(String fileName, Map<String, String[]> sheet2fieldName,
            Map<String, List<Object[]>> sheet2Record) {
        printEnabled = true; // 设置为printable

        HSSFWorkbook workbook = new HSSFWorkbook();

        int i = 0;
        for (Map.Entry<String, String[]> entry : sheet2fieldName.entrySet()) {
            String sheetName = entry.getKey();
            String[] fileNames = entry.getValue();
            workbook.createSheet();
            workbook.setSheetName(i++, sheetName, HSSFWorkbook.ENCODING_UTF_16);
            HSSFRow row = workbook.getSheetAt(i - 1).createRow(0);
            for (int j = 0; j < fileNames.length; j++) {// 创建首行字段
                HSSFCell cell = row.createCell((short) j);
                cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(fileNames[j]);
            }
            List<Object[]> records = sheet2Record.get(sheetName);
            int rowNum = 1; // 行号
            for (Object[] record : records) {
                HSSFRow _row = workbook.getSheetAt(i - 1).createRow(rowNum++);
                for (int k = 0; k < record.length; k++) {
                    HSSFCell cell = _row.createCell((short) k);
                    cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
                    Object value = record[k];

                    if (value == null) {
                        _row.getCell((short) k).setCellType(HSSFCell.CELL_TYPE_BLANK);
                    }
                    else if (value instanceof String) {
                        _row.getCell((short) k).setCellType(HSSFCell.CELL_TYPE_STRING);
                        _row.getCell((short) k).setCellValue((String) value);
                    }
                    else if (value instanceof Boolean) {
                        _row.getCell((short) k).setCellType(HSSFCell.CELL_TYPE_BOOLEAN);
                        _row.getCell((short) k).setCellValue(((Boolean) value).booleanValue());
                    }
                    else if (value instanceof Integer) {
                        _row.getCell((short) k).setCellValue(((Integer) value).intValue());
                    }
                    else if (value instanceof Double) {
                        _row.getCell((short) k).setCellValue(((Double) value).doubleValue());
                    }
                    else if (value instanceof Date) {
                        HSSFCellStyle cellDateStyle = workbook.createCellStyle();
                        cellDateStyle.setDataFormat(dateFormat);
                        _row.getCell((short) k).setCellStyle(cellDateStyle);
                        _row.getCell((short) k).setCellValue((Date) value);
                    }
                    else {
                        _row.getCell((short) k).setCellValue(value.toString());
                    }
                }
            }
        }
        outputData(workbook, fileName);
    }

    private void outputData(HSSFWorkbook workbook, String fileName) {
        // fileName = StringUtils.encode(fileName + ".xls", "utf-8");
        fileName = fileName + ".xls";
        ServletActionContext.getResponse().setHeader("Cache-Control", "");
        ServletActionContext.getResponse().setContentType("application/data;charset=GBK");
        ServletActionContext.getResponse().setHeader("Content-Disposition", "attachment; filename=" + fileName);
        OutputStream out = null;
        try {
            out = new BufferedOutputStream(ServletActionContext.getResponse().getOutputStream());

            workbook.write(out); // 输出文件
            out.flush();
        }
        catch (IOException e) {
            // ingore
        }
        finally {
            FileUtils.close(out);
        }
    }

}
