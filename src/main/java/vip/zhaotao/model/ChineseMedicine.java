package vip.zhaotao.model;

/**
 * 中药
 */
public class ChineseMedicine {

    /**
     * id
     */
    private Long id;
    /**
     * 药品名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 基本方/加减法
     */
    private Integer basicSquaredAddAndSub;
    /**
     * 主id
     */
    private Long masterId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 用量
     */
    private Double dosage;
    /**
     * 用量最小值
     */
    private Double dosageMin;
    /**
     * 用量最大值
     */
    private Double dosageMax;
    /**
     * 用量单位
     */
    private String dosageUnit;
    /**
     * 煎药方式
     */
    private String boilMedicineWay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getBasicSquaredAddAndSub() {
        return basicSquaredAddAndSub;
    }

    public void setBasicSquaredAddAndSub(Integer basicSquaredAddAndSub) {
        this.basicSquaredAddAndSub = basicSquaredAddAndSub;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Double getDosage() {
        return dosage;
    }

    public void setDosage(Double dosage) {
        this.dosage = dosage;
    }

    public Double getDosageMin() {
        return dosageMin;
    }

    public void setDosageMin(Double dosageMin) {
        this.dosageMin = dosageMin;
    }

    public Double getDosageMax() {
        return dosageMax;
    }

    public void setDosageMax(Double dosageMax) {
        this.dosageMax = dosageMax;
    }

    public String getDosageUnit() {
        return dosageUnit;
    }

    public void setDosageUnit(String dosageUnit) {
        this.dosageUnit = dosageUnit;
    }

    public String getBoilMedicineWay() {
        return boilMedicineWay;
    }

    public void setBoilMedicineWay(String boilMedicineWay) {
        this.boilMedicineWay = boilMedicineWay;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ChineseMedicine{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", remark='").append(remark).append('\'');
        sb.append(", basicSquaredAddAndSub=").append(basicSquaredAddAndSub);
        sb.append(", masterId=").append(masterId);
        sb.append(", sort=").append(sort);
        sb.append(", dosage=").append(dosage);
        sb.append(", dosageMin=").append(dosageMin);
        sb.append(", dosageMax=").append(dosageMax);
        sb.append(", dosageUnit='").append(dosageUnit).append('\'');
        sb.append(", boilMedicineWay='").append(boilMedicineWay).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
