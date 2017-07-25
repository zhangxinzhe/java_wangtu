package net.zdsoft.common.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.struts2.ServletActionContext;

import net.zdsoft.common.alipay.enums.AlipayFormState;
import net.zdsoft.common.entity.ValueName;
import net.zdsoft.common.enums.CardStateType;
import net.zdsoft.common.enums.ChargeType;
import net.zdsoft.common.enums.ReceiveStatusEnum;
import net.zdsoft.common.enums.RecordDetailType;
import net.zdsoft.common.enums.SexType;
import net.zdsoft.common.enums.YesNoType;
import net.zdsoft.keel.util.Validators;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public final class ExportUtils {

    private WritableSheet sheet;
    private WritableWorkbook workBook;
    private OutputStream os;
    private final static int sheetTotalRow = 65536;

    public ExportUtils(String fileName) {
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            os = response.getOutputStream();
            response.setHeader("Content-disposition",
                    "attachment; filename=" + new String((fileName).getBytes("gbk"), "iso8859-1") + ".xls");
            response.setContentType("application/msexcel");// 定义输出类型
            workBook = Workbook.createWorkbook(os);
            sheet = workBook.createSheet("sheet1", 0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int setTitle(String titleName, int row, int nums) {
        try {
            WritableFont font = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
                    UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
            WritableCellFormat format = new WritableCellFormat(font);
            format.setAlignment(Alignment.CENTRE);
            format.setBackground(Colour.WHITE);
            format.setBorder(Border.NONE, BorderLineStyle.THIN);
            addCell(sheet, 0, 0, titleName, format);
            sheet.mergeCells(0, row, nums - 1, 0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return row + 1;
    }

    public int addContent(String str, int row, int col) {
        int resultRow = row + 2;
        try {
            WritableFont font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false,
                    UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
            WritableCellFormat format = new WritableCellFormat(font);
            format.setAlignment(Alignment.LEFT);
            addCell(sheet, col, row, str, format);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return resultRow;
    }

    public int addContent(List<?> list, String[] fields, String[] properties, int row) {
        int resultRow = row + 2;
        if (!Validators.isEmpty(list)) {
            resultRow += list.size();
        }
        try {
            WritableFont font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,
                    UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
            WritableCellFormat format = new WritableCellFormat(font);
            format.setAlignment(Alignment.CENTRE);
            format.setBackground(Colour.GRAY_50);
            format.setBorder(Border.ALL, BorderLineStyle.THIN);
            int col = 0;
            for (String field : fields) {
                addCell(sheet, col, row, field, format);
                col++;
            }
            row++;
            col = 0;
            int sheetNum = 0;
            for (Object object : list) {
                if (row == sheetTotalRow) {
                    sheet = workBook.createSheet("sheet" + (sheetNum + 1), sheetNum);
                    sheetNum++;
                    row = 0;
                    for (String field : fields) {
                        addCell(sheet, col, row, field, format);
                        col++;
                    }
                    row = 1;
                    col = 0;
                }
                for (String property : properties) {
                    Object value = getKeyValue(property, object);
                    addCell(sheet, col, row, value, null);
                    col++;
                }
                col = 0;
                row++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return resultRow;
    }

    public void print() {
        try {
            workBook.write();
            os.flush();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (workBook != null) {
                    workBook.close();
                }
                if (os != null) {
                    os.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 导出信息
     *
     * @param fileName
     *            文件名
     * @param list
     *            数据
     * @param fields
     *            头标题
     * @param properties
     *            对于的属性
     * @throws WriteException
     * @throws IOException
     */
    public static void exportInfo(String fileName, List<?> list, String[] fields, String[] properties) {
        exportInfo(fileName, list, fields, properties, null);
    }

    /**
     * 导出信息
     *
     * @param fileName
     *            文件名
     * @param list
     *            数据
     * @param fields
     *            头标题
     * @param properties
     *            对于的属性
     * @param count
     *            合计
     * @throws WriteException
     * @throws IOException
     */
    public static void exportInfo(String fileName, List<?> list, String[] fields, String[] properties, Object count) {
        exportInfo(fileName, list, fields, properties, null, count);
    }

    /**
     * 导出信息 <br>
     * 按照导出信息中的key分sheet <br>
     * 一个key一个sheet sheet名字为 key+_+fileName
     *
     * @param fileName
     *            文件名称
     * @param listMap
     *            所需导出的信息
     * @param fields
     *            列表
     * @param properties
     *            没列对应的属性
     */
    public static void exportInfoForMap(String fileName, Map<String, List> listMap, String[] fields,
            String[] properties) {
        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = null;
        WritableWorkbook book = null;
        Map<Integer, Integer> maxWidthMap = new HashMap<Integer, Integer>();
        try {
            os = response.getOutputStream();
            response.setHeader("Content-disposition",
                    "attachment; filename=" + new String((fileName).getBytes("gbk"), "iso8859-1") + ".xls");
            response.setContentType("application/vnd.ms-excel");// 定义输出类型
            book = Workbook.createWorkbook(os);
            int colNum = fields.length;
            int sheetNum = 0;
            for (String title : listMap.keySet()) {
                int titleSheetNum = 1;
                // sheet名字
                List<?> list = listMap.get(title);
                WritableSheet sheet = book.createSheet(getSheetName(title), sheetNum);
                sheetNum++;
                // 标题
                WritableFont font = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
                        UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
                WritableCellFormat format = new WritableCellFormat(font);
                format.setAlignment(Alignment.CENTRE);
                format.setBackground(Colour.WHITE);
                format.setBorder(Border.NONE, BorderLineStyle.THIN);
                addCell(sheet, 0, 0, title, format);
                sheet.mergeCells(0, 0, colNum - 1, 0);

                // 小标题
                font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                        Colour.BLACK);
                format = new WritableCellFormat(font);
                format.setAlignment(Alignment.CENTRE);
                format.setBackground(Colour.GRAY_50);
                format.setBorder(Border.ALL, BorderLineStyle.THIN);
                int col = 0;
                int row = 1;
                for (String field : fields) {
                    addCell(sheet, col, row, field, format, maxWidthMap);
                    col++;
                }
                row++;
                col = 0;
                if (CollectionUtils.isEmpty(list)) {
                    // 设置最大宽度
                    setColumnWidth(sheet, maxWidthMap);
                    book.write();
                    os.flush();
                    return;
                }

                for (Object object : list) {
                    if (row == sheetTotalRow) {
                        // 设置最大宽度
                        setColumnWidth(sheet, maxWidthMap);
                        maxWidthMap = new HashMap<Integer, Integer>();
                        sheet = book.createSheet(getSheetName(titleSheetNum + "-" + title), sheetNum);
                        titleSheetNum++;
                        sheetNum++;
                        row = 0;
                        for (String field : fields) {
                            addCell(sheet, col, row, field, format, maxWidthMap);
                            col++;
                        }
                        row = 1;
                        col = 0;
                    }
                    for (String property : properties) {
                        Object value = getKeyValue(property, object);
                        addCell(sheet, col, row, value, null, maxWidthMap);
                        col++;

                    }
                    col = 0;
                    row++;
                }
                // 设置最大宽度
                setColumnWidth(sheet, maxWidthMap);
            }

            book.write();
            os.flush();
        }
        catch (Exception e) {
        }
        finally {
            try {
                if (book != null) {
                    book.close();
                }
                if (os != null) {
                    os.close();
                }
            }
            catch (Exception e) {
            }
        }
    }

    /**
     * 导出信息
     *
     * @param fileName
     *            文件名
     * @param list
     *            数据
     * @param fields
     *            头标题
     * @param properties
     *            对于的属性
     * @param itemPros
     *            子列的属性
     * @param count
     *            合计
     * @throws WriteException
     * @throws IOException
     */
    public static void exportInfo(String fileName, List<?> list, String[] fields, String[] properties,
            String[] itemPros, Object count) {
        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = null;
        WritableWorkbook book = null;
        Map<Integer, Integer> maxWidthMap = new HashMap<Integer, Integer>();
        try {
            os = response.getOutputStream();
            response.setHeader("Content-disposition",
                    "attachment; filename=" + new String((fileName).getBytes("gbk"), "iso8859-1") + ".xls");
            response.setContentType("application/vnd.ms-excel");// 定义输出类型
            book = Workbook.createWorkbook(os);
            int colNum = fields.length;
            int sheetNum = 0;
            WritableSheet sheet = book.createSheet("sheet" + (sheetNum + 1), sheetNum);
            sheetNum++;
            // 标题
            WritableFont font = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
                    UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
            WritableCellFormat format = new WritableCellFormat(font);
            format.setAlignment(Alignment.CENTRE);
            format.setBackground(Colour.WHITE);
            format.setBorder(Border.NONE, BorderLineStyle.THIN);
            addCell(sheet, 0, 0, fileName, format);
            sheet.mergeCells(0, 0, colNum - 1, 0);

            // 小标题
            font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                    Colour.BLACK);
            format = new WritableCellFormat(font);
            format.setAlignment(Alignment.CENTRE);
            format.setBackground(Colour.GRAY_50);
            format.setBorder(Border.ALL, BorderLineStyle.THIN);
            int col = 0;
            int row = 1;
            for (String field : fields) {
                addCell(sheet, col, row, field, format, maxWidthMap);
                col++;
            }
            row++;
            col = 0;
            if (CollectionUtils.isEmpty(list)) {
                // 设置最大宽度
                setColumnWidth(sheet, maxWidthMap);
                book.write();
                os.flush();
                return;
            }

            // 合并开始行列数据
            int startCol = 0, startRow = 0;
            // 是否需要合并
            boolean isMerge = false;
            // 合并的行数
            int mergeRow = 0;
            for (Object object : list) {
                if (row == sheetTotalRow) {
                    // 设置最大宽度
                    setColumnWidth(sheet, maxWidthMap);
                    maxWidthMap = new HashMap<Integer, Integer>();
                    sheet = book.createSheet("sheet" + (sheetNum + 1), sheetNum);
                    sheetNum++;
                    row = 0;
                    for (String field : fields) {
                        addCell(sheet, col, row, field, format, maxWidthMap);
                        col++;
                    }
                    row = 1;
                    col = 0;
                }
                for (String property : properties) {
                    Object value = getKeyValue(property, object);
                    // 判断是否是列表
                    if (ArrayUtils.isNotEmpty(itemPros) && value instanceof ArrayList<?>) {
                        List<?> items = (List<?>) value;
                        startRow = row;
                        // 判断之前是否有合并列
                        mergeRow = items.size() - 1;
                        if (!isMerge) {
                            if (mergeRow > 0) {
                                isMerge = true;
                                for (int i = startCol; i < col; i++) {
                                    sheet.mergeCells(i, startRow, i, startRow + mergeRow);
                                }
                            }
                        }
                        for (Object item : items) {
                            for (String itemPro : itemPros) {
                                addCell(sheet, col, row, getKeyValue(itemPro, item), null, maxWidthMap);
                                col++;
                            }
                            col = col - itemPros.length;
                            row++;
                        }
                        row = row - items.size();
                        col += itemPros.length;
                        startCol = col + 1;

                    }
                    else {
                        addCell(sheet, col, row, value, null, maxWidthMap);
                        if (isMerge && mergeRow > 0) {
                            sheet.mergeCells(col, row, col, row + mergeRow);
                        }
                        col++;
                    }

                }
                col = 0;
                startCol = col;
                if (isMerge && mergeRow > 0) {
                    row += mergeRow;
                }
                isMerge = false;
                row++;
            }
            // 合计
            if (count != null) {
                if (row == sheetTotalRow) {
                    sheet = book.createSheet("sheet" + (sheetNum + 1), sheetNum);
                    sheetNum++;
                    row = 0;
                    for (String field : fields) {
                        addCell(sheet, col, row, field, format, maxWidthMap);
                        col++;
                    }
                    row = 1;
                    col = 0;
                }
                font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                        Colour.BLACK);
                format = new WritableCellFormat(font);
                format.setBackground(Colour.GRAY_25);
                format.setBorder(Border.NONE, BorderLineStyle.NONE);
                for (String property : properties) {
                    addCell(sheet, col, row, getKeyValue(property, count), format, maxWidthMap);
                    col++;
                }
            }
            // 设置最大宽度
            setColumnWidth(sheet, maxWidthMap);
            book.write();
            os.flush();
        }
        catch (Exception e) {
        }
        finally {
            try {
                if (book != null) {
                    book.close();
                }
                if (os != null) {
                    os.close();
                }
            }
            catch (Exception e) {
            }
        }
    }

    /**
     * 导出excel信息 适合列名部分不确定的信息
     *
     * @param fileName
     *            文件名称
     * @param list
     *            列表数据
     * @param fields
     *            主列表小标题
     * @param properties
     *            主列表属性信息
     * @param itemFields
     *            子列表 小标题
     * @param itemPro
     *            子列表属性
     */
    public static void exportMergeInfo(String fileName, List<?> list, String[] fields, String[] properties,
            String[] itemFields, String itemPro) {
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            OutputStream os = null;
            WritableWorkbook book = null;
            Map<Integer, Integer> maxWidthMap = new HashMap<Integer, Integer>();
            try {
                os = response.getOutputStream();
                response.setHeader("Content-disposition",
                        "attachment; filename=" + new String((fileName).getBytes("gbk"), "iso8859-1") + ".xls");
                response.setContentType("application/vnd.ms-excel");// 定义输出类型
                book = Workbook.createWorkbook(os);
                int colNum = fields.length;
                if (ArrayUtils.isNotEmpty(itemFields)) {
                    colNum += itemFields.length;
                }
                int sheetNum = 0;
                WritableSheet sheet = book.createSheet("sheet" + (sheetNum + 1), sheetNum);
                sheetNum++;
                // 标题
                WritableFont font = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
                        UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
                WritableCellFormat format = new WritableCellFormat(font);
                format.setAlignment(Alignment.CENTRE);
                format.setBackground(Colour.WHITE);
                format.setBorder(Border.NONE, BorderLineStyle.THIN);
                addCell(sheet, 0, 0, fileName, format);
                sheet.mergeCells(0, 0, colNum - 1, 0);

                // 小标题
                font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                        Colour.BLACK);
                format = new WritableCellFormat(font);
                format.setAlignment(Alignment.CENTRE);
                format.setBackground(Colour.GRAY_50);
                format.setBorder(Border.ALL, BorderLineStyle.THIN);
                int col = 0;
                int row = 1;
                // 添加主列标题
                for (String field : fields) {
                    addCell(sheet, col, row, field, format, maxWidthMap);
                    col++;
                }
                // 添加子列标题
                for (String field : itemFields) {
                    addCell(sheet, col, row, field, format, maxWidthMap);
                    col++;
                }
                row++;
                col = 0;
                if (CollectionUtils.isEmpty(list)) {
                    // 设置最大宽度
                    setColumnWidth(sheet, maxWidthMap);
                    book.write();
                    os.flush();
                    return;
                }

                for (Object object : list) {
                    if (row == sheetTotalRow) {
                        // 设置当前页的单元格宽度
                        setColumnWidth(sheet, maxWidthMap);
                        maxWidthMap = new HashMap<Integer, Integer>();
                        sheet = book.createSheet("sheet" + (sheetNum + 1), sheetNum);
                        sheetNum++;
                        row = 0;
                        // 添加主列标题
                        for (String field : fields) {
                            addCell(sheet, col, row, field, format, maxWidthMap);
                            col++;
                        }
                        // 添加子列标题
                        for (String field : itemFields) {
                            addCell(sheet, col, row, field, format, maxWidthMap);
                            col++;
                        }
                        row = 1;
                        col = 0;
                    }
                    for (String property : properties) {
                        Object value = getKeyValue(property, object);
                        addCell(sheet, col, row, value, null, maxWidthMap);
                        col++;
                    }
                    // 添加子列信息
                    if (StringUtils.isNotBlank(itemPro)) {
                        Object value = getKeyValue(itemPro, object);
                        if (value != null) {
                            if (value instanceof ArrayList<?>) {
                                List<?> items = (List<?>) value;
                                for (Object item : items) {
                                    addCell(sheet, col, row, item, null, maxWidthMap);
                                    col++;
                                }
                            }
                            else if (value.getClass().isArray()) {
                                for (int i = 0; i < Array.getLength(value); i++) {
                                    addCell(sheet, col, row, Array.get(value, i), null, maxWidthMap);
                                    col++;
                                }
                            }
                        }
                    }

                    col = 0;
                    row++;
                }
                // 设置最大宽度
                setColumnWidth(sheet, maxWidthMap);
                book.write();
                os.flush();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (book != null) {
                    book.close();
                }
                if (os != null) {
                    os.close();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addCell(WritableSheet sheet, int col, int row, Object value, WritableCellFormat format)
            throws RowsExceededException, WriteException {
        addCell(sheet, col, row, value, format, null);
    }

    /**
     * 添加cell
     *
     * @param sheet
     * @param col
     * @param row
     * @param value
     * @param format
     * @throws RowsExceededException
     * @throws WriteException
     */
    private static void addCell(WritableSheet sheet, int col, int row, Object value, WritableCellFormat format,
            Map<Integer, Integer> maxWidthMap) throws RowsExceededException, WriteException {
        String cellVal = null;
        if (value == null) {
            cellVal = "";
        }
        else if (value instanceof Date) {
            cellVal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) value);
        }
        else if (value instanceof Long) {
            if (Long.MIN_VALUE == (Long) value) {
                cellVal = "";
            }
            else {
                cellVal = Long.toString(((Long) value));
            }
        }
        else if (value instanceof Integer) {
            if (Integer.MIN_VALUE == (Integer) value) {
                cellVal = "";
            }
            else {
                cellVal = Integer.toString((Integer) value);
            }
        }
        else if (value instanceof Short) {
            if (Short.MIN_VALUE == (Short) value) {
                cellVal = "";
            }
            else {
                cellVal = Short.toString((Short) value);
            }
        }
        else if (value instanceof Double) {
            if (Double.MIN_VALUE == (Double) value) {
                cellVal = "";
            }
            else {
                cellVal = Double.toString((Double) value);
            }
        }
        else if (value instanceof Float) {
            if (Float.MIN_VALUE == (Float) value) {
                cellVal = "";
            }
            else {
                cellVal = Float.toString(((Float) value));
            }
        }
        else if (value instanceof Boolean) {
            Boolean item = (Boolean) value;
            cellVal = item == null ? "" : (item == true ? "是" : "否");
        }
        else if (value instanceof YesNoType) {
            cellVal = ((YesNoType) value).getNameValue();
        }
        else if (value instanceof CardStateType) {
            cellVal = ((CardStateType) value).getNameValue();
        }
        else if (value instanceof ValueName) {
            cellVal = ((ValueName<?, ?>) value).getNameValue().toString();
        }
        else if (value instanceof RecordDetailType) {
            cellVal = ((RecordDetailType) value).getNameValue();
        }
        else if (value instanceof ChargeType) {
            cellVal = ((ChargeType) value).getNameValue();
        }
        else if (value instanceof SexType) {
            cellVal = ((SexType) value).getNameValue();
        }
        else if (value instanceof ReceiveStatusEnum) {
            cellVal = ((ReceiveStatusEnum) value).getNameValue();
        }
        else if (value instanceof AlipayFormState) {
            cellVal = ((AlipayFormState) value).getNameValue();
        }
        else {
            cellVal = (String) value;
        }
        // 计算字符所暂用宽度
        if (maxWidthMap != null) {
            int pointSize = 0;
            if (format != null) {
                pointSize = format.getFont().getPointSize();
            }
            int width = StringUtil.getRealLength(cellVal);
            if (pointSize > 10) {
                width = width * 3 / 2;
            }
            Integer nowWidth = maxWidthMap.get(col);
            if (nowWidth == null || nowWidth < width) {
                maxWidthMap.put(col, width);
            }
        }
        sheet.addCell(addLabel(col, row, cellVal, format));
    }

    private static Label addLabel(int col, int row, String value, WritableCellFormat format) {
        if (format == null) {
            return new Label(col, row, value);
        }
        return new Label(col, row, value, format);
    }

    /**
     * 获取值
     *
     * @param key
     * @param o
     * @return
     */
    private static Object getKeyValue(String key, Object o) {
        Object value = null;
        try {
            String firstLetter = key.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + key.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            value = method.invoke(o, new Object[] {});
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 设置最大宽度
     *
     * @param sheet
     * @param maxLen
     */
    private static void setColumnWidth(WritableSheet sheet, Map<Integer, Integer> maxLen) {
        if (sheet == null || maxLen == null) {
            return;
        }
        for (Integer col : maxLen.keySet()) {
            sheet.setColumnView(col, maxLen.get(col));
        }
    }

    /**
     * 获取sheet最终名称
     *
     * @param title
     * @return
     */
    private static String getSheetName(String title) {
        String sheetName = title;
        if (sheetName.length() > 31) {
            sheetName = sheetName.substring(0, 31);
        }
        return sheetName;
    }
}
