package vip.zhaotao.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import vip.zhaotao.poi.CellValue;
import vip.zhaotao.poi.ConvertUtils;
import vip.zhaotao.poi.ExcelColumn;
import vip.zhaotao.poi.ExcelUtils;
import vip.zhaotao.util.PathUtil;
import vip.zhaotao.util.PinYinUtils;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChineseMedicineTest {

    /**
     * 字母与中文正则
     */
    private static final String LETTERS_AND_CHINESE_REGEX = "([a-zA-Z_\\u4e00-\\u9fa5]+)";
    /**
     * 首位正整数与正浮点数正则
     */
    private static final String FIRST_POSITIVE_INTEGER_AND_POSITIVE_FLOAT_REGEX = "(^[0-9]\\d*\\.?\\d*)";
    /**
     * 其他位置正整数与正浮点数正则
     */
    private static final String OTHER_POSITIVE_INTEGER_AND_POSITIVE_FLOAT_REGEX = "([0-9]\\d*\\.?\\d*)";
    /**
     * 小括号（圆括号）正则
     */
    private static final String PARENTHESIS_REGEX = "(\\([^\\)]*\\))";
    /**
     * 分隔符正则
     */
    private static final String SEPARATOR_REGEX = "([~|-])";
    /**
     * 1ml, 1g, 10个, 0.15g
     */
    private static Pattern patternOne = Pattern.compile(String.format("%s%s$", FIRST_POSITIVE_INTEGER_AND_POSITIVE_FLOAT_REGEX, LETTERS_AND_CHINESE_REGEX));
    /**
     * 10ml(冲服), 0.3ml(冲服)
     */
    private static Pattern patternTwo = Pattern.compile(String.format("%s%s%s$", FIRST_POSITIVE_INTEGER_AND_POSITIVE_FLOAT_REGEX, LETTERS_AND_CHINESE_REGEX, PARENTHESIS_REGEX));
    /**
     * 1-2ml, 1~2g, 1~2个, 0.12~0.34mg
     */
    private static Pattern patternThree = Pattern.compile(String.format("%s%s%s%s$", FIRST_POSITIVE_INTEGER_AND_POSITIVE_FLOAT_REGEX, SEPARATOR_REGEX, OTHER_POSITIVE_INTEGER_AND_POSITIVE_FLOAT_REGEX, LETTERS_AND_CHINESE_REGEX));
    /**
     * 4.5g~6g
     */
    private static Pattern patternFour = Pattern.compile(String.format("%s%s%s%s%s", FIRST_POSITIVE_INTEGER_AND_POSITIVE_FLOAT_REGEX, LETTERS_AND_CHINESE_REGEX, SEPARATOR_REGEX, OTHER_POSITIVE_INTEGER_AND_POSITIVE_FLOAT_REGEX, LETTERS_AND_CHINESE_REGEX));
    /**
     * 5~10g(另炖，兑服), 0.5~5g(粉碎装胶囊吞)
     */
    private static Pattern patternFive = Pattern.compile(String.format("%s%s%s%s%s$", FIRST_POSITIVE_INTEGER_AND_POSITIVE_FLOAT_REGEX, SEPARATOR_REGEX, OTHER_POSITIVE_INTEGER_AND_POSITIVE_FLOAT_REGEX, LETTERS_AND_CHINESE_REGEX, PARENTHESIS_REGEX));
    /**
     * 0.5~1.0(冲)
     */
    private static Pattern patternSex = Pattern.compile(String.format("%s%s%s%s", FIRST_POSITIVE_INTEGER_AND_POSITIVE_FLOAT_REGEX, SEPARATOR_REGEX, OTHER_POSITIVE_INTEGER_AND_POSITIVE_FLOAT_REGEX, PARENTHESIS_REGEX));
    /**
     * (冲服)
     */
    private static Pattern patternTen = Pattern.compile(String.format("%s$", PARENTHESIS_REGEX));
    /**
     * Model属性名称集合
     */
    private static LinkedHashSet<String> modelPropertyNameSet = Sets.newLinkedHashSet();
    /**
     * Excel列名集合
     */
    private static LinkedHashSet<String> excelColumnNameSet = Sets.newLinkedHashSet();

    static {
        modelPropertyNameSet.add(ExcelColumn.CHINESE_MEDICINE_ID.getPropertyName());
        modelPropertyNameSet.add(ExcelColumn.CHINESE_MEDICINE_NAME.getPropertyName());
        modelPropertyNameSet.add(ExcelColumn.CHINESE_MEDICINE_REMARK.getPropertyName());
        modelPropertyNameSet.add(ExcelColumn.CHINESE_MEDICINE_BASIC_SQUARED_ADD_AND_SUB.getPropertyName());
        modelPropertyNameSet.add(ExcelColumn.CHINESE_MEDICINE_MASTER_ID.getPropertyName());
        modelPropertyNameSet.add(ExcelColumn.CHINESE_MEDICINE_SORT.getPropertyName());
        modelPropertyNameSet.add(ExcelColumn.CHINESE_MEDICINE_DOSAGE.getPropertyName());
        modelPropertyNameSet.add(ExcelColumn.CHINESE_MEDICINE_BOIL_MEDICINE_WAY.getPropertyName());

        excelColumnNameSet.add(ExcelColumn.CHINESE_MEDICINE_ID.getColumnName());
        excelColumnNameSet.add(ExcelColumn.CHINESE_MEDICINE_NAME.getColumnName());
        excelColumnNameSet.add(ExcelColumn.CHINESE_MEDICINE_REMARK.getColumnName());
        excelColumnNameSet.add(ExcelColumn.CHINESE_MEDICINE_BASIC_SQUARED_ADD_AND_SUB.getColumnName());
        excelColumnNameSet.add(ExcelColumn.CHINESE_MEDICINE_MASTER_ID.getColumnName());
        excelColumnNameSet.add(ExcelColumn.CHINESE_MEDICINE_SORT.getColumnName());
        excelColumnNameSet.add(ExcelColumn.CHINESE_MEDICINE_DOSAGE.getColumnName());
        excelColumnNameSet.add(ExcelColumn.CHINESE_MEDICINE_DOSAGE_MIN.getColumnName());
        excelColumnNameSet.add(ExcelColumn.CHINESE_MEDICINE_DOSAGE_MAX.getColumnName());
        excelColumnNameSet.add(ExcelColumn.CHINESE_MEDICINE_DOSAGE_UNIT.getColumnName());
        excelColumnNameSet.add(ExcelColumn.CHINESE_MEDICINE_BOIL_MEDICINE_WAY.getColumnName());
    }

    /**
     * 处理药品名称
     *
     * @throws IllegalAccessException
     * @throws IOException
     * @throws InstantiationException
     */
    @Test
    public void handleMedicineName() throws IllegalAccessException, IOException, InstantiationException, BadHanyuPinyinOutputFormatCombination {
        // TODO Windows系统则将“test.xlsx”文件放置在桌面即可，其他系统放置在当前登录用户的根目录下
        String filename = "test.xlsx";
        String desktopOrUserHomePath = PathUtil.getDesktopOrUserHome();
        File file = new File(String.format("%s%s", desktopOrUserHomePath, filename));
        if (file.exists()) {
            long startTime = System.currentTimeMillis();
            InputStream in = new FileInputStream(file);
            int startRow = 1;
            List<ChineseMedicine> list = ExcelUtils.read(in, startRow, modelPropertyNameSet, ChineseMedicine.class);
            Set<String> set = Sets.newHashSet();
            if (CollectionUtils.isNotEmpty(list)) {
                for (ChineseMedicine medicine : list) {
                    set.add(medicine.getName().trim());
                }
                File sqlFile = new File(String.format("%s%s_%d.sql", desktopOrUserHomePath, "p_standard_product-chinese_medicine-data", System.currentTimeMillis()));
                if (!sqlFile.exists()) {
                    sqlFile.createNewFile();
                }
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(sqlFile));
                String oneLevelSql = " (SELECT * FROM (SELECT id FROM gouyao.mall_category WHERE parent_category_id IS NULL AND name = '中药' AND isShow = 1 LIMIT 1) t)";
                String twoLevelSql = " (SELECT * FROM (SELECT id FROM gouyao.mall_category WHERE parent_category_id IN (SELECT * FROM (SELECT id FROM gouyao.mall_category WHERE parent_category_id IS NULL AND name = '中药' AND isShow = 1 LIMIT 1) t1) AND name = '中药' LIMIT 1) t2)";
                Iterator<String> iterator = set.iterator();
                while (iterator.hasNext()) {
                    StringBuilder builder = new StringBuilder();
                    builder.append("INSERT INTO gouyao.p_standard_product(name, pinyin_name, headchar_name, brand_name, mall_category_level1, mall_category_level2, item_type, status, created, updated) ");
                    builder.append("VALUES(");
                    String name = iterator.next();
                    String nameComplete = PinYinUtils.getComplete(name);
                    String nameInitial = PinYinUtils.getInitial(name);
                    builder.append(String.format("'%s', '%s', '%s'", name, nameComplete, nameInitial)).append(", ");
                    builder.append("'',");
                    builder.append(oneLevelSql).append(", ");
                    builder.append(twoLevelSql).append(", ");
                    builder.append("2, 1, NOW(), NOW());");
                    bufferedWriter.write(builder.toString());
                    bufferedWriter.newLine();
                }
                bufferedWriter.flush();
                bufferedWriter.close();
            }
            System.err.println(String.format("总共%d条，去重后剩余%d条，耗时%.3f秒！", list.size(), set.size(), (System.currentTimeMillis() - startTime) / 1000.0));
        }
    }

    /**
     * 处理所有数据
     *
     * @throws IOException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Test
    public void handleAllData() throws IOException, InstantiationException, IllegalAccessException {
        // TODO Windows系统则将“test.xlsx”文件放置在桌面即可，其他系统放置在当前登录用户的根目录下
        String filename = "test.xlsx";
        ExcelUtils.Type type = ExcelUtils.Type.getByExpandedName(String.format(".%s", FilenameUtils.getExtension(filename)));
        String desktopOrUserHomePath = PathUtil.getDesktopOrUserHome();
        File file = new File(String.format("%s%s", desktopOrUserHomePath, filename));
        if (file.exists()) {
            long startTime = System.currentTimeMillis();
            InputStream in = new FileInputStream(file);
            int startRow = 1;
            List<ChineseMedicine> list = ExcelUtils.read(in, startRow, modelPropertyNameSet, ChineseMedicine.class);
            List<ChineseMedicine> matchingList = Lists.newArrayList();
            List<ChineseMedicine> mismatchingList = Lists.newArrayList();
            List<ChineseMedicine> emptyList = Lists.newArrayList();
            File sqlFile = new File(String.format("%s%s_%d.sql", desktopOrUserHomePath, "p_chinese_medicine-data", System.currentTimeMillis()));
            if (!sqlFile.exists()) {
                sqlFile.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(sqlFile));
            for (ChineseMedicine medicine : list) {
                if (StringUtils.isBlank(medicine.getRemark())) {
                    emptyList.add(medicine);
                } else if (this.remarkValueMatcher(medicine)) {
                    matchingList.add(medicine);
                } else {
                    mismatchingList.add(medicine);
                }
                StringBuilder builder = new StringBuilder();
                builder.append("INSERT INTO gouyao.p_chinese_medicine(id, name, remark, basic_squared_add_and_sub, master_id, sort, dosage, dosage_min, dosage_max, dosage_unit, boil_medicine_way) ");
                builder.append("VALUES(");
                builder.append(medicine.getId()).append(", ");
                builder.append("'").append(medicine.getName().trim()).append("', ");
                builder.append("'").append(medicine.getRemark() != null ? medicine.getRemark().trim() : "").append("', ");
                builder.append(medicine.getBasicSquaredAddAndSub()).append(", ");
                builder.append(medicine.getMasterId()).append(", ");
                builder.append(medicine.getSort()).append(", ");
                builder.append(medicine.getDosage()).append(", ");
                builder.append(medicine.getDosageMin()).append(", ");
                builder.append(medicine.getDosageMax()).append(", ");
                builder.append("'").append(medicine.getDosageUnit() != null ? medicine.getDosageUnit().trim() : "").append("', ");
                builder.append("'").append(medicine.getBoilMedicineWay() != null ? medicine.getBoilMedicineWay().trim() : "").append("'");
                builder.append(");");
                bufferedWriter.write(builder.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            if (CollectionUtils.isNotEmpty(matchingList)) {
                this.write(type, excelColumnNameSet, matchingList, String.format("%s%s_%d%s", desktopOrUserHomePath, "处理成功中药数据", System.currentTimeMillis(), type.getExpandedName()));
            }
            if (CollectionUtils.isNotEmpty(mismatchingList)) {
                this.write(type, excelColumnNameSet, mismatchingList, String.format("%s%s_%d%s", desktopOrUserHomePath, "未处理中药数据", System.currentTimeMillis(), type.getExpandedName()));
            }
            if (CollectionUtils.isNotEmpty(emptyList)) {
                this.write(type, excelColumnNameSet, emptyList, String.format("%s%s_%d%s", desktopOrUserHomePath, "中药备注为空数据", System.currentTimeMillis(), type.getExpandedName()));
            }
            System.err.println(String.format("总共%d条, 成功处理%d条, 未处理%d条, 备注为空%d条，耗时%.3f秒！", list.size(), matchingList.size(), mismatchingList.size(), emptyList.size(), (System.currentTimeMillis() - startTime) / 1000.0));
        }
    }

    /**
     * 写入
     *
     * @param type     Excel文件类型
     * @param head     表头
     * @param list     需要写入Excel的数据
     * @param filePath 文件路径
     * @throws IOException
     */
    private void write(ExcelUtils.Type type, LinkedHashSet<String> head, List<ChineseMedicine> list, String filePath) throws IOException {
        if (CollectionUtils.isNotEmpty(list)) {
            FileOutputStream out = new FileOutputStream(filePath);
            LinkedList<LinkedList<CellValue>> data = Lists.newLinkedList();
            for (ChineseMedicine medicine : list) {
                LinkedList<CellValue> cellValueList = Lists.newLinkedList();
                cellValueList.add(new CellValue(medicine.getId()));
                cellValueList.add(new CellValue(medicine.getName()));
                cellValueList.add(new CellValue(medicine.getRemark()));
                cellValueList.add(new CellValue(medicine.getBasicSquaredAddAndSub()));
                cellValueList.add(new CellValue(medicine.getMasterId()));
                cellValueList.add(new CellValue(medicine.getSort()));
                cellValueList.add(new CellValue(medicine.getDosage()));
                cellValueList.add(new CellValue(medicine.getDosageMin()));
                cellValueList.add(new CellValue(medicine.getDosageMax()));
                cellValueList.add(new CellValue(medicine.getDosageUnit()));
                cellValueList.add(new CellValue(medicine.getBoilMedicineWay()));
                data.add(cellValueList);
            }
            ExcelUtils.write(type, out, head, data);
        }
    }

    /**
     * 备注值正则匹配，如匹配成功则会对值进行相应的分割
     *
     * @param medicine 中药
     * @return
     */
    private boolean remarkValueMatcher(ChineseMedicine medicine) {
        boolean matcher = true;
        String remark = this.chinesePunctuationReplace(medicine);
        if (patternOne.matcher(remark).matches()) {
            this.remarkValueHandle(medicine, RemarkType.TYPE_ONE);
        } else if (patternTwo.matcher(remark).matches()) {
            this.remarkValueHandle(medicine, RemarkType.TYPE_TWO);
        } else if (patternThree.matcher(remark).matches()) {
            this.remarkValueHandle(medicine, RemarkType.TYPE_THREE);
        } else if (patternFour.matcher(remark).matches()) {
            this.remarkValueHandle(medicine, RemarkType.TYPE_FOUR);
        } else if (patternFive.matcher(remark).matches()) {
            this.remarkValueHandle(medicine, RemarkType.TYPE_FIVE);
        } else if (patternSex.matcher(remark).matches()) {
            this.remarkValueHandle(medicine, RemarkType.TYPE_SEX);
        } else if (patternTen.matcher(remark).matches()) {
            this.remarkValueHandle(medicine, RemarkType.TYPE_TEN);
        } else {
            matcher = false;
        }
        return matcher;
    }

    /**
     * 备注值处理
     *
     * @param medicine 中药
     * @param type
     */
    private void remarkValueHandle(ChineseMedicine medicine, RemarkType type) {
        String remark = medicine.getRemark();
        int value = type.getValue();
        if (value == RemarkType.TYPE_ONE.getValue()
                || value == RemarkType.TYPE_TWO.getValue()) {
            this.dosageMinAndMaxValueHandle(medicine, remark, FIRST_POSITIVE_INTEGER_AND_POSITIVE_FLOAT_REGEX);
        }
        if (value == RemarkType.TYPE_THREE.getValue()
                || value == RemarkType.TYPE_FOUR.getValue()) {
            this.dosageMinValueHandle(medicine, remark, FIRST_POSITIVE_INTEGER_AND_POSITIVE_FLOAT_REGEX);
            this.dosageMaxValueHandle(medicine, remark, OTHER_POSITIVE_INTEGER_AND_POSITIVE_FLOAT_REGEX, SEPARATOR_REGEX);
        }
        if (value == RemarkType.TYPE_ONE.getValue()
                || value == RemarkType.TYPE_TWO.getValue()
                || value == RemarkType.TYPE_THREE.getValue()
                || value == RemarkType.TYPE_FOUR.getValue()) {
            this.dosageUnitValueHandle(medicine, remark, LETTERS_AND_CHINESE_REGEX);
        }
        if (value == RemarkType.TYPE_TWO.getValue()
                || value == RemarkType.TYPE_FOUR.getValue()
                || value == RemarkType.TYPE_FIVE.getValue()) {
            this.boilMedicineWayValueHandle(medicine, remark, PARENTHESIS_REGEX);
        }
    }

    /**
     * 中文标点符号替换
     *
     * @param medicine 中药
     * @return
     */
    private String chinesePunctuationReplace(ChineseMedicine medicine) {
        String remark = medicine.getRemark();
        if (StringUtils.isNotBlank(remark)) {
            remark = remark.trim();
            remark = remark.replace("（", "(");
            remark = remark.replace("）", ")");
            remark = remark.replace("～", "~");
        } else {
            remark = "";
        }
        medicine.setRemark(remark);
        return remark;
    }

    /**
     * 用量最小值处理
     *
     * @param medicine                                  中药
     * @param remark                                    备注
     * @param firstPositiveIntegerAndPositiveFloatRegex 首位正整数与正浮点数正则
     */
    private void dosageMinValueHandle(ChineseMedicine medicine, String remark, String firstPositiveIntegerAndPositiveFloatRegex) {
        Matcher matcher = Pattern.compile(firstPositiveIntegerAndPositiveFloatRegex).matcher(remark);
        if (matcher.find()) {
            medicine.setDosageMin(ConvertUtils.toDouble(matcher.group()));
        }
    }

    /**
     * 用量最大值处理
     *
     * @param medicine                                  中药
     * @param remark                                    备注
     * @param otherPositiveIntegerAndPositiveFloatRegex 其他位置正整数与正浮点数正则
     * @param separatorRegex                            分隔符正则
     */
    private void dosageMaxValueHandle(ChineseMedicine medicine, String remark, String otherPositiveIntegerAndPositiveFloatRegex, String separatorRegex) {
        Matcher matcher = Pattern.compile(String.format("%s%s", separatorRegex, otherPositiveIntegerAndPositiveFloatRegex)).matcher(remark);
        if (matcher.find()) {
            Matcher temp = Pattern.compile(otherPositiveIntegerAndPositiveFloatRegex).matcher(matcher.group());
            if (temp.find()) {
                medicine.setDosageMax(ConvertUtils.toDouble(temp.group()));
            }
        }
    }

    /**
     * 用量最小值与最大值处理
     *
     * @param medicine                                  中药
     * @param remark                                    备注
     * @param firstPositiveIntegerAndPositiveFloatRegex 首位正整数与正浮点数正则
     */
    private void dosageMinAndMaxValueHandle(ChineseMedicine medicine, String remark, String firstPositiveIntegerAndPositiveFloatRegex) {
        Matcher matcher = Pattern.compile(firstPositiveIntegerAndPositiveFloatRegex).matcher(remark);
        if (matcher.find()) {
            medicine.setDosageMin(ConvertUtils.toDouble(matcher.group()));
            medicine.setDosageMax(medicine.getDosageMin());
        }
    }

    /**
     * 用量单位值处理
     *
     * @param medicine               中药
     * @param remark                 备注
     * @param lettersAndChineseRegex 字母与中文正则
     */
    private void dosageUnitValueHandle(ChineseMedicine medicine, String remark, String lettersAndChineseRegex) {
        Matcher matcher = Pattern.compile(lettersAndChineseRegex).matcher(remark);
        if (matcher.find()) {
            medicine.setDosageUnit(matcher.group());
        }
    }

    /**
     * 煎药方式值处理
     *
     * @param medicine         中药
     * @param remark           备注
     * @param parenthesisRegex 小括号（圆括号）正则
     */
    private void boilMedicineWayValueHandle(ChineseMedicine medicine, String remark, String parenthesisRegex) {
        Matcher matcher = Pattern.compile(parenthesisRegex).matcher(remark);
        if (matcher.find()) {
            String temp = matcher.group();
            if (StringUtils.isBlank(medicine.getBoilMedicineWay())) {
                medicine.setBoilMedicineWay(temp.substring(1, temp.length() - 1));
            }
        }
    }

    /**
     * 备注类型
     */
    enum RemarkType {
        /**
         * 1ml, 1g, 10个, 0.15g
         */
        TYPE_ONE(1),
        /**
         * 10ml(冲服), 0.3ml(冲服)
         */
        TYPE_TWO(2),
        /**
         * 1-2ml, 1~2g, 1~2个, 0.12~0.34mg
         */
        TYPE_THREE(3),
        /**
         * 4.5g~6g
         */
        TYPE_FOUR(4),
        /**
         * 5~10g(另炖，兑服), 0.5~5g(粉碎装胶囊吞)
         */
        TYPE_FIVE(5),
        /**
         * 0.5~1.0(冲)
         */
        TYPE_SEX(6),
        /**
         * (冲服)
         */
        TYPE_TEN(10);

        private int value;

        RemarkType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}