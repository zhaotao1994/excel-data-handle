package vip.zhaotao.poi;

/**
 * Excel列
 */
public enum ExcelColumn {

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
    CHINESE_MEDICINE_BOIL_MEDICINE_WAY("煎药方式", "boilMedicineWay"),
    // 中药（ChineseMedicine）结束

    // 标准库（StandardProduct）开始
    /**
     * 标准编码
     */
    STANDARD_PRODUCT_STANDARD_CODE("标准编码", "standardCode"),
    /**
     * 药品名称
     */
    STANDARD_PRODUCT_NAME("药品名称", "name"),
    /**
     * 商品名称（品牌）
     */
    STANDARD_PRODUCT_BRAND_NAME("商品名称（品牌）", "brandName"),
    /**
     * 规格
     */
    STANDARD_PRODUCT_SPECIFICATION("规格", "specification"),
    /**
     * 条形码
     */
    STANDARD_PRODUCT_BAR_CODE("条形码", "barCode"),
    /**
     * 批准文号
     */
    STANDARD_PRODUCT_APPROVAL_NUMBER("批准文号", "approvalNumber"),
    /**
     * 生产厂家
     */
    STANDARD_PRODUCT_MANUFACTURER("生产厂家", "manufacturer"),
    /**
     * 成分（组方）
     */
    STANDARD_PRODUCT_INGREDIENTS("成分（组方）", "ingredients"),
    /**
     * 性状
     */
    STANDARD_PRODUCT_CHARACTER("性状", "character"),
    /**
     * 处方分类
     */
    STANDARD_PRODUCT_RECIPE_TYPE("处方分类", "recipeType"),
    /**
     * 功能主治
     */
    STANDARD_PRODUCT_FUNCTION("功能主治", "function"),
    /**
     * 适应症
     */
    STANDARD_PRODUCT_INDICATION("适应症", "indication"),
    /**
     * 用法用量
     */
    STANDARD_PRODUCT_USAGE_AND_DOSAGE("用法用量", "usageAndDosage"),
    /**
     * 不良反应
     */
    STANDARD_PRODUCT_ADVERSE_REACTION("不良反应", "adverseReaction"),
    /**
     * 禁忌
     */
    STANDARD_PRODUCT_TABOO("禁忌", "taboo"),
    /**
     * 孕妇及哺乳期妇女用药
     */
    STANDARD_PRODUCT_PREGNANT_AND_LACTANT_WOMEN("孕妇及哺乳期妇女用药", "pregnantAndLactantWomen"),
    /**
     * 儿童用药
     */
    STANDARD_PRODUCT_CHILDREN("儿童用药", "children"),
    /**
     * 老年患者用药
     */
    STANDARD_PRODUCT_SENILE_PATIENT("老年患者用药", "senilePatient"),
    /**
     * 注意事项
     */
    STANDARD_PRODUCT_WARNING("注意事项", "warning"),
    /**
     * 药物过量
     */
    STANDARD_PRODUCT_OVERDOSE("药物过量", "overdose"),
    /**
     * 药物相互作用
     */
    STANDARD_PRODUCT_DRUG_INTERACTIONS("药物相互作用", "drugInteractions"),
    /**
     * 药物毒理作用
     */
    STANDARD_PRODUCT_DRUG_TOXICOLOGY("药物毒理作用", "drugToxicology"),
    /**
     * 药代动力学
     */
    STANDARD_PRODUCT_PHARMACOKINETIC("药代动力学", "pharmacokinetic"),
    /**
     * 贮藏
     */
    STANDARD_PRODUCT_STORAGE("贮藏", "storage"),
    /**
     * 包装
     */
    STANDARD_PRODUCT_PACKAGING("包装", "packaging"),
    /**
     * 有效期
     */
    STANDARD_PRODUCT_VALIDITY_PERIOD("有效期", "validityPeriod"),
    /**
     * 项目类别
     */
    STANDARD_PRODUCT_ITEM_TYPE("整卖单位", "itemType"),
    /**
     * 整卖单位
     */
    STANDARD_PRODUCT_WHOLE_SALE_UNIT("整卖单位", "wholeSaleUnit"),
    /**
     * 换算值
     */
    STANDARD_PRODUCT_CONVERSION("换算值", "conversion"),
    /**
     * 散卖单位
     */
    STANDARD_PRODUCT_RETAIL_SALE_UNIT("散卖单位", "retailSaleUnit"),
    // 标准库（StandardProduct）结束

    // 标准库DTO（StandardProductDTO）开始
    /**
     * 一级分类名称
     */
    STANDARD_PRODUCT_DTO_ONE_LEVEL_NAME("一级分类", "oneLevelName"),
    /**
     * 二级分类名称
     */
    STANDARD_PRODUCT_DTO_TWO_LEVEL_NAME("二级分类", "twoLevelName");
    // 标准库DTO（StandardProductDTO）结束

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
