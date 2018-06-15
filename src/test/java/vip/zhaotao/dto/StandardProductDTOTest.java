package vip.zhaotao.dto;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;
import vip.zhaotao.poi.ExcelColumn;
import vip.zhaotao.poi.ExcelUtils;
import vip.zhaotao.util.PathUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class StandardProductDTOTest {

    /**
     * Model属性名称集合
     */
    private static LinkedHashSet<String> modelPropertyNameSet = Sets.newLinkedHashSet();

    static {
        // 标准编码
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_STANDARD_CODE.getPropertyName());
        // 条形码
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_BAR_CODE.getPropertyName());
        // 药品名称
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_NAME.getPropertyName());
        // 商品名称
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_BRAND_NAME.getPropertyName());
        // 批准文号
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_APPROVAL_NUMBER.getPropertyName());
        // 生产厂家
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_MANUFACTURER.getPropertyName());
        // 成分（组方）
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_INGREDIENTS.getPropertyName());
        // 性状
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_CHARACTER.getPropertyName());
        // 一级分类
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_DTO_ONE_LEVEL_NAME.getPropertyName());
        // 二级分类
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_DTO_TWO_LEVEL_NAME.getPropertyName());
        // 处方分类
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_RECIPE_TYPE.getPropertyName());
        // 功能主治
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_FUNCTION.getPropertyName());
        // 适应症
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_INDICATION.getPropertyName());
        // 规格
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_SPECIFICATION.getPropertyName());
        // 用法用量
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_USAGE_AND_DOSAGE.getPropertyName());
        // 不良反应
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_ADVERSE_REACTION.getPropertyName());
        // 禁忌
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_TABOO.getPropertyName());
        // 孕妇及哺乳期妇女用药
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_PREGNANT_AND_LACTANT_WOMEN.getPropertyName());
        // 儿童用药
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_CHILDREN.getPropertyName());
        // 老年患者用药
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_SENILE_PATIENT.getPropertyName());
        // 注意事项
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_WARNING.getPropertyName());
        // 药物过量
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_OVERDOSE.getPropertyName());
        // 药物相互作用
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_DRUG_INTERACTIONS.getPropertyName());
        // 药物毒理作用
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_DRUG_TOXICOLOGY.getPropertyName());
        // 药代动力学
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_PHARMACOKINETIC.getPropertyName());
        // 贮藏
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_STORAGE.getPropertyName());
        // 包装
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_PACKAGING.getPropertyName());
        // 有效期
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_VALIDITY_PERIOD.getPropertyName());
        // 项目类别
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_ITEM_TYPE.getPropertyName());
        // 整卖单位
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_WHOLE_SALE_UNIT.getPropertyName());
        // 换算值
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_CONVERSION.getPropertyName());
        // 散卖单位
        modelPropertyNameSet.add(ExcelColumn.STANDARD_PRODUCT_RETAIL_SALE_UNIT.getPropertyName());
    }

    /**
     * 标准库数据读取
     *
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Test
    public void read() throws IOException, IllegalAccessException, InstantiationException {
        String filename = "test.xlsx";
        String desktopOrUserHomePath = PathUtil.getDesktopOrUserHome();
        File file = new File(String.format("%s%s", desktopOrUserHomePath, filename));
        if (file.exists()) {
            long startTime = System.currentTimeMillis();
            InputStream in = new FileInputStream(file);
            int startRow = 1;
            List<Map<String, Object>> handelFairlyList = Lists.newArrayList();
            List<StandardProductDTO> list = ExcelUtils.read(in, startRow, modelPropertyNameSet, StandardProductDTO.class, handelFairlyList);
            System.err.println(String.format("总共%d条，耗时%.3f秒！", list.size(), (System.currentTimeMillis() - startTime) / 1000.0));
        }
    }
}