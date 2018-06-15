package vip.zhaotao.poi;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.math.BigDecimal;
import java.util.*;

public class ExcelUtils {

    public static final String ISO_DATETIME = String.format("%s %s", DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern(), DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.getPattern());

    public static final String RMB = "¥#,##0.00;¥-#,##0.00";

    /**
     * 获取工作薄
     *
     * @param type
     * @return
     */
    private static Workbook getWorkBook(Type type) {
        Workbook workbook = null;
        switch (type) {
            case XLS:
                workbook = new HSSFWorkbook();
                break;
            case XLSX:
                workbook = new XSSFWorkbook();
                break;
        }
        return workbook;
    }

    private static Workbook getWorkBook(InputStream in) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        if (!in.markSupported()) {
            in = new PushbackInputStream(in, 8);
        }
        // xls
        if (POIFSFileSystem.hasPOIFSHeader(in)) {
            workbook = new HSSFWorkbook(in);
        }
        // xlsx
        else if (POIXMLDocument.hasOOXMLHeader(in)) {
            workbook = new XSSFWorkbook(in);
        }
        return workbook;
    }

    /**
     * 创建头
     *
     * @param sheet
     * @param head
     */
    private static void createHead(Sheet sheet, Set<String> head) {
        if (CollectionUtils.isNotEmpty(head)) {
            // 获取最后行数并创建行
            Row row = sheet.createRow(sheet.getLastRowNum());
            // 列数，因getLastCellNum()对于空行返回-1，所以此处不能使用该方法
            int cellNum = 0;
            Iterator<String> iterator = head.iterator();
            while (iterator.hasNext()) {
                // 创建单元格
                Cell cell = row.createCell(cellNum++);
                // 为单元格赋值
                cell.setCellValue(iterator.next());
            }
        }
    }


    /**
     * 写入
     *
     * @param type
     * @param out
     * @param head
     * @param data
     * @throws IOException
     */
    public static void write(Type type, OutputStream out, LinkedHashSet<String> head, LinkedList<LinkedList<CellValue>> data) throws IOException {
        if (type == null || out == null || CollectionUtils.isEmpty(data)) {
            return;
        }

        // 获取工作薄、创建工作表
        Workbook workBook = getWorkBook(type);
        if (workBook == null) {
            return;
        }

        CreationHelper creationHelper = workBook.getCreationHelper();
        CellStyle cellStyle = workBook.createCellStyle();
        Sheet sheet = workBook.createSheet();

        // 创建头
        if (CollectionUtils.isNotEmpty(head)) {
            createHead(sheet, head);
        }

        // 获取最后的行数
        int rowNum = sheet.getLastRowNum();
        // 添加数据
        if (CollectionUtils.isNotEmpty(data)) {
            // 行
            Row row;
            // 列
            Cell cell;
            // 单元格值
            CellValue cellValue;
            Object value;
            // 是否自动列宽
            Boolean autoSizeColumn;

            Iterator<LinkedList<CellValue>> listIterator = data.iterator();
            while (listIterator.hasNext()) {
                // 列数，因getLastCellNum()对于空行返回-1，所以此处不能使用该方法
                int cellNum = 0;
                row = sheet.createRow(++rowNum);
                Iterator<CellValue> iterator = listIterator.next().iterator();
                while (iterator.hasNext()) {
                    cellValue = iterator.next();
                    cell = row.createCell(cellNum);
                    value = cellValue.getValue();
                    if (value instanceof Number) {
                        cell.setCellValue(((Number) value).doubleValue());
                    } else if (value instanceof Date) {
                        // 当值为日期时，需要设置样式，否则日期会转化为浮点数
                        cell.setCellValue((Date) value);
                    } else if (value instanceof Boolean) {
                        cell.setCellValue((Boolean) value);
                    } else {
                        cell.setCellValue(value.toString());
                    }

                    if (StringUtils.isNotBlank(cellValue.getPattern())) {
                        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat(cellValue.getPattern()));
                        cell.setCellStyle(cellStyle);
                    }

                    // 自动列宽
                    autoSizeColumn = cellValue.getAutoSizeColumn();
                    if (null != autoSizeColumn && autoSizeColumn) {
                        sheet.autoSizeColumn(cellNum);
                    }

                    cellNum++;
                }
            }

            // 写入
            workBook.write(out);
            // 关闭输出流
            out.close();
        }
    }

    /**
     * 读取
     *
     * @param in
     * @param startRow         起始行，例如：表头占用1行，则该值为1，excel行号起始值为0。
     * @param property         JavaBean属性，必须与表头一一对应。
     * @param clazz
     * @param <T>
     * @param handleFairlyList 处理失败集合
     * @return
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> List<T> read(InputStream in, int startRow, LinkedHashSet<String> property, Class<T> clazz, List<Map<String, Object>> handleFairlyList) throws IOException, IllegalAccessException, InstantiationException {
        String[] propertyArray = property.toArray(new String[]{});

        List<T> list = Lists.newArrayList();
        if (handleFairlyList == null) {
            handleFairlyList = Lists.newArrayList();
        }
        Workbook workBook = getWorkBook(in);
        // 默认读取第1个工作表
        Sheet sheet = workBook.getSheetAt(0);
        // 获取最后的行号
        int lastRowNum = sheet.getLastRowNum();
        while (startRow <= lastRowNum) {
            // 行
            Row row = sheet.getRow(startRow);
            short firstCellNum = row.getFirstCellNum();
            short lastCellNum = row.getLastCellNum();
            Map<String, Object> map = Maps.newHashMap();
            Map<String, Object> handleFairlyMap = Maps.newHashMap();
            int index = 0;
            T bean = clazz.newInstance();
            while (firstCellNum < lastCellNum) {
                Cell cell = row.getCell(firstCellNum);
                if (cell == null) {
                    map.put(propertyArray[index], null);
                } else {
                    // 处理前的值
                    Object handleBefore = null;
                    // 处理后的值
                    Object handleAfter = null;
                    switch (cell.getCellTypeEnum()) {
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                handleBefore = handleAfter = cell.getDateCellValue();
                            } else {
                                handleBefore = NumberToTextConverter.toText(cell.getNumericCellValue());
                                handleAfter = valueHandle(bean, propertyArray[index], handleBefore);
                            }
                            break;
                        case STRING:
                            handleBefore = cell.getStringCellValue();
                            handleAfter = valueHandle(bean, propertyArray[index], handleBefore);
                            break;
                        case BOOLEAN:
                            handleBefore = handleAfter = cell.getBooleanCellValue();
                            break;
                    }
                    map.put(propertyArray[index], handleAfter);
                    // 说明列内容类型与对象属性类型不匹配
                    if (handleBefore != null && handleAfter == null) {
                        handleFairlyMap.put(propertyArray[index], handleBefore);
                    }
                }
                index++;
                firstCellNum++;
            }
            if (!handleFairlyMap.isEmpty()) {
                handleFairlyList.add(handleFairlyMap);
            }
            list.add(mapToBean(bean, map));
            startRow++;
        }
        return list;
    }

    /**
     * 值处理
     *
     * @param bean
     * @param property
     * @param value
     * @return
     */
    private static Object valueHandle(Object bean, String property, Object value) {
        Object result;
        BeanMap beanMap = new BeanMap(bean);
        Class<?> propertyType = beanMap.getType(property);
        if (propertyType.equals(Byte.class)) {
            result = ConvertUtils.toByte(value);
        } else if (propertyType.equals(Short.class)) {
            result = ConvertUtils.toShort(value);
        } else if (propertyType.equals(Integer.class)) {
            result = ConvertUtils.toInteger(value);
        } else if (propertyType.equals(Long.class)) {
            result = ConvertUtils.toLong(value);
        } else if (propertyType.equals(Float.class)) {
            result = ConvertUtils.toFloat(value);
        } else if (propertyType.equals(Double.class)) {
            result = ConvertUtils.toDouble(value);
        } else if (propertyType.equals(BigDecimal.class)) {
            result = ConvertUtils.toBigDecimal(value);
        } else if (propertyType.equals(Character.class)) {
            result = ConvertUtils.toCharacter(value);
        } else {
            result = value;
        }
        return result;
    }

    private static <T> T mapToBean(T bean, Map<String, Object> map) {
        BeanMap beanMap = new BeanMap(bean);
        beanMap.putAll(map);
        return bean;
    }

    public enum Type {

        XLS(".xls"),
        XLSX(".xlsx");

        private String expandedName;

        Type(String expandedName) {
            this.expandedName = expandedName;
        }

        public String getExpandedName() {
            return expandedName;
        }

        public static Type getByExpandedName(String expandedName) {
            Type type = null;
            if (StringUtils.isNotBlank(expandedName)) {
                for (Type t : Type.values()) {
                    if (StringUtils.equals(t.getExpandedName(), expandedName)) {
                        type = t;
                        break;
                    }
                }
            }
            return type;
        }
    }
}
