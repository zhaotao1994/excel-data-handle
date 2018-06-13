package vip.zhaotao.poi;

/**
 * Excel列
 */
public enum ExcelColumn {

    /**
     * 字符
     */
    CHARACTER_VALUE("字符", "characterValue"),
    /**
     * 字符串
     */
    STRING_VALUE("字符串", "stringValue"),
    /**
     * 字节
     */
    BYTE_VALUE("字节", "byteValue"),
    /**
     * 短整型
     */
    SHORT_VALUE("短整型", "shortValue"),
    /**
     * 整型
     */
    INTEGER_VALUE("整型", "integerValue"),
    /**
     * 长整型
     */
    LONG_VALUE("长整型", "longValue"),
    /**
     * 单精度
     */
    FLOAT_VALUE("单精度", "floatValue"),
    /**
     * 双精度
     */
    DOUBLE_VALUE("双精度", "doubleValue"),
    /**
     * 日期
     */
    DATE_VALUE("日期", "dateValue"),
    /**
     * 布尔
     */
    BOOLEAN_VALUE("布尔", "booleanValue"),
    /**
     * BigDecimal
     */
    BIG_DECIMAL_VALUE("BigDecimal", "bigDecimalValue"),

    // 中药（ChineseMedicine）开始
    /**
     * id
     */
    CHINESE_MEDICINE_ID("ID", "id"),
    /**
     * 药品名称
     */
    CHINESE_MEDICINE_NAME("药品名称", "name"),
    /**
     * 备注
     */
    CHINESE_MEDICINE_REMARK("备注", "remark"),
    /**
     * 基本方/加减法
     */
    CHINESE_MEDICINE_BASIC_SQUARED_ADD_AND_SUB("基本方/加减法", "basicSquaredAddAndSub"),
    /**
     * 主id
     */
    CHINESE_MEDICINE_MASTER_ID("主id", "masterId"),
    /**
     * 排序
     */
    CHINESE_MEDICINE_SORT("排序", "sort"),
    /**
     * 用量
     */
    CHINESE_MEDICINE_DOSAGE("用量", "dosage"),
    /**
     * 用量最小值
     */
    CHINESE_MEDICINE_DOSAGE_MIN("用量最小值", "dosageMin"),
    /**
     * 用量最大值
     */
    CHINESE_MEDICINE_DOSAGE_MAX("用量最大值", "dosageMax"),
    /**
     * 用量单位
     */
    CHINESE_MEDICINE_DOSAGE_UNIT("用量单位", "dosageUnit"),
    /**
     * 煎药方式
     */
    CHINESE_MEDICINE_BOIL_MEDICINE_WAY("煎药方式", "boilMedicineWay");
    // 中药（ChineseMedicine）结束

    /**
     * 列名，Excel文件中的列名
     */
    private String columnName;

    /**
     * 属性名，与JavaBean中一一对应
     */
    private String propertyName;

    ExcelColumn(String columnName, String propertyName) {
        this.columnName = columnName;
        this.propertyName = propertyName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
