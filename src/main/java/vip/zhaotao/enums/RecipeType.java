package vip.zhaotao.enums;

/**
 * 处方分类
 */
public enum RecipeType {

    CategoryA(1, "甲类非处方药"),

    CategoryB(2, "乙类非处方药"),

    Recipe(3, "处方药"),

    Other(4, "其他");

    private int value;
    private String remark;

    RecipeType(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public static RecipeType getByValue(int value) {
        for (RecipeType o : RecipeType.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }
}