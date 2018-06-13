package vip.zhaotao.poi;

/**
 * 单元格值
 */
public class CellValue {

    /**
     * 值
     */
    private Object value;

    /**
     * 是否自动列宽
     */
    private Boolean autoSizeColumn;

    /**
     * 模式
     */
    private String pattern;

    private CellValue() {}

    public CellValue(Object value) {
        this(value, null, null);
    }

    public CellValue(Object value, Boolean autoSizeColumn) {
        this(value, autoSizeColumn, null);
    }

    public CellValue(Object value, String pattern) {
        this(value, null, pattern);
    }

    public CellValue(Object value, Boolean autoSizeColumn, String pattern) {
        if (null == value) {
            value = "";
        }
        this.value = value;
        this.autoSizeColumn = autoSizeColumn;
        this.pattern = pattern;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Boolean getAutoSizeColumn() {
        return autoSizeColumn;
    }

    public void setAutoSizeColumn(Boolean autoSizeColumn) {
        this.autoSizeColumn = autoSizeColumn;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
