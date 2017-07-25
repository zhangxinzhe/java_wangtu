/* 
 * @(#)ImportExcel.java    Created on 2010-7-27
 * Copyright (c) 2010 ZDSoft Networks, Inc. All rights reserved.
 * $Id: ExcelImport.java 18918 2010-10-27 06:21:31Z wangzb $
 */
package net.zdsoft.common.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

import net.zdsoft.keel.util.StringUtils;
import net.zdsoft.keel.util.Validators;

/**
 * Excel文件 导入 工具类
 * 
 * @author wangzb
 * @version $Revision: 18918 $, $Date: 2010-10-27 14:21:31 +0800 (星期三, 27 十月 2010) $
 */
public class ExcelImport<T> {

    /**
     * 反射类
     */
    private final Class<T> clazz;

    public ExcelImport(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 
     * @param filePath
     *            导入的Excel文件路径
     * @return 集合类对象
     */
    public Collection<T> importExcelByJXL(String filePath) throws Exception {
        File file = new File(filePath);
        return importExcelByJXL(file);
    }

    /**
     * 
     * @param file
     *            导入的Excel文件对象
     * @return 集合类对象
     */
    public Collection<T> importExcelByJXL(File file) throws Exception {
        Collection<T> lists = new ArrayList<T>();
        // 将传入的File构造为FileInputStream;
        FileInputStream in = null;
        if (!file.exists()) {
            throw new IOException("传入的Excel文件不存在！");
        }
        in = new FileInputStream(file);
        lists = importExcelByJXL(in);

        return lists;
    }

    /**
     * 
     * @param in
     *            文件输入流对象如（fileItem.getInputStream();）
     * @return 集合类对象
     * @throws
     * @throws Exception
     */
    public Collection<T> importExcelByJXL(FileInputStream in) throws Exception {
        Collection<T> lists = new ArrayList<T>();
        // 得到目标类的所有的字段列表
        Field filed[] = clazz.getDeclaredFields();
        // 将所有标有Annotation的字段，也就是允许导入数据的字段,放入到一个map中
        Map<String, Method> fieldMap = new HashMap<String, Method>();
        // 将ExcelAnnotation放入到一个map中。
        Map<String, ExcelAnnotation> excelAnnotationMap = new HashMap<String, ExcelAnnotation>();
        // 循环读取所有字段
        for (int i = 0; i < filed.length; i++) {
            Field f = filed[i];
            // 得到单个字段上的Annotation
            ExcelAnnotation exa = f.getAnnotation(ExcelAnnotation.class);
            // 如果标识了Annotation的话
            if (exa != null) {
                // 构造设置了Annotation的字段的Setter方法
                String fieldName = f.getName();
                String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                // 构造调用的method
                Method setMethod = clazz.getMethod(setMethodName, new Class[] { f.getType() });
                // 将这个method以Annotation的名字为key来存入。
                fieldMap.put(exa.exportTitleName(), setMethod);
                excelAnnotationMap.put(exa.exportTitleName(), exa);
            }
        }
        /**
         * excel的解析开始
         */
        Workbook book = null;
        Sheet sheet = null;
        int columnSizes = 0;
        int rowSizes = 0;
        try {
            book = Workbook.getWorkbook(in);
            sheet = book.getSheet(0);
            columnSizes = sheet.getColumns();
            rowSizes = sheet.getRows();
        }
        catch (Exception e1) {
            e1.printStackTrace();
            throw new IOException("可能原因是模版文件错误，请重新下载模版文件导入。");
        }

        /**
         * 标题解析
         */
        if (columnSizes == 0 && rowSizes == 0) {
            throw new IOException("传入的Excel文件数据不对！");
        }
        // 得到第一行的所有列
        jxl.Cell[] titles = sheet.getRow(0);
        // 将标题的文字内容放入到一个map中。
        Map<Integer, String> titleMap = new HashMap<Integer, String>();
        // 从标题第一列开始
        int i = 0;
        for (jxl.Cell cell : titles) {
            String title = cell.getContents().trim();
            if (!excelAnnotationMap.containsKey(title)) {
                throw new IOException("列[" + title + "]不是模版所需的列，请检查导入模版是否正确！");
            }
            titleMap.put(i, title);
            i++;
        }
        /**
         * 解析内容行
         */
        for (int j = 1; j < rowSizes; j++) {
            T tObject = null;
            jxl.Cell[] rows = sheet.getRow(j);
            if (rows == null || rows.length <= 0) {
                break;
            }
            if (isEmptyRow(rows, columnSizes)) {
                break;
            }
            for (int k = 0; k < columnSizes && k < rows.length; k++) {
                jxl.Cell cell = rows[k];
                if (cell == null || Validators.isBlank(cell.getContents())) {
                    continue;
                }
                if (tObject == null) {
                    tObject = clazz.newInstance();
                }
                // 这里得到此列的对应的标题
                String titleString = titleMap.get(k);
                // 如果这一列的标题和类中的某一列的Annotation相同，那么则调用此类的的set方法，进行设值
                if (fieldMap.containsKey(titleString)) {
                    ExcelAnnotation exa = excelAnnotationMap.get(titleString);
                    if (exa == null) {
                        continue;
                    }
                    String cellContent = null;
                    if (cell.getType() == CellType.DATE) {
                        DateCell dCell = (DateCell) cell;
                        Date date = dCell.getDate();
                        TimeZone zone = TimeZone.getTimeZone("GMT");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        sdf.setTimeZone(zone);
                        cellContent = sdf.format(date);
                    }
                    else {
                        cellContent = cell.getContents();
                    }
                    if (!Validators.isBlank(cellContent) && StringUtils.getRealLength(cellContent) > exa.maxLength()) {
                        throw new Exception("[" + titleString + "]不能超过最大字节" + exa.maxLength());
                    }
                    else {
                        cellContent = cellContent.trim();
                    }

                    Method setMethod = fieldMap.get(titleString);
                    // 得到setter方法的参数
                    Type[] ts = setMethod.getGenericParameterTypes();
                    // 只要一个参数
                    String xclass = ts[0].toString();
                    // 判断参数类型
                    if (xclass.equals("class java.lang.String")) {
                        setMethod.invoke(tObject, cellContent);
                    }
                    else if (xclass.equals("class java.util.Date")) {
                        // 用来格式化日期的DateFormat
                        SimpleDateFormat sf = new SimpleDateFormat(exa.dateTimeFormat());
                        if (cellContent.indexOf("/") > -1) {
                            cellContent = cellContent.replaceAll("/", "-");
                        }
                        try {
                            setMethod.invoke(tObject, sf.parse(cellContent));
                        }
                        catch (Exception e) {
                            throw new Exception("[" + titleString + "]时间格式不正确，请按照提示正确填写");
                        }
                    }
                    else if (xclass.equals("class java.lang.Boolean")) {
                        Boolean boolname = true;
                        if (cellContent.equals("否")) {
                            boolname = false;
                        }
                        setMethod.invoke(tObject, boolname);
                    }
                    else if (xclass.equals("class java.lang.Integer")) {
                        try {
                            setMethod.invoke(tObject, new Integer(cellContent));
                        }
                        catch (Exception e) {
                            throw new Exception("[" + titleString + "]格式不正确，请按照提示正确填写");
                        }
                    }
                    else if (xclass.equals("class java.lang.Long")) {
                        try {
                            setMethod.invoke(tObject, new Long(cellContent));
                        }
                        catch (Exception e) {
                            throw new Exception("[" + titleString + "]格式不正确，请按照提示正确填写");
                        }
                    }
                    else if (xclass.equals("class java.lang.Float")) {
                        try {
                            setMethod.invoke(tObject, new Float(cellContent));
                        }
                        catch (Exception e) {
                            throw new Exception("[" + titleString + "]格式不正确，请按照提示正确填写");
                        }
                    }
                }
            }
            if (tObject != null) {
                lists.add(tObject);
            }
        }
        return lists;
    }

    public static boolean isEmptyRow(Cell[] rows, int columnSizes) {
        if (null == rows) {
            return true;
        }
        for (int i = 0; i < columnSizes; i++) {
            if (rows.length <= i) {
                continue;
            }
            if (null == rows[i]) {
                continue;
            }

            if (!Validators.isBlank(rows[i].getContents())) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // ExcelImport<Testpojo> test = new ExcelImport<Testpojo>(Testpojo.class);
        // List<Testpojo> result;
        // Long start;
        // Long end;
        //
        // // JXL List
        // start = System.currentTimeMillis();
        // result = (ArrayList<Testpojo>) test.importExcelByJXL("D:\\JXLListTestOne.xls");
        // end = System.currentTimeMillis();
        //
        // for (int i = 0; i < result.size(); i++) {
        // Testpojo testpojo = result.get(i);
        // System.out.println("导入的信息为：" + i + testpojo.getLoginname() + "----" + testpojo.getAge() + "---"
        // + testpojo.getMoney() + "-----" + testpojo.getLogintime() + "-----" + testpojo.getCreatetime()
        // + "-----" + testpojo.getModifytime() + "-----" + testpojo.getSex() + "-----"
        // + testpojo.getUsername());
        // }
        // System.out.println("共导入JXL List数据：" + result.size() + "条");
        // System.out.println("JXL List 总共耗时：" + (end - start));
    }
}
