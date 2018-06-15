package vip.zhaotao.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 标准库
 */
public class StandardProduct implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public StandardProduct() {
	}
	
	public StandardProduct(Long id) {
		this.id = id;
	}
	
	/**
	 * id
	 */
	private Long id;
	/**
	 * 标准编码
	 */	
	private String standardCode;
	/**
	 * 药品名称
	 */
	private String name;
	/**
	 * 商品名称（品牌）
	 */
	private String brandName;
	/**
	 * 规格
	 */
	private String specification;
	/**
	 * 条形码
	 */
	private String barCode;
	/**
	 * 状态，1正常
	 */
	private Integer status;
	/**
	 * 批准文号
	 */
	private String approvalNumber;
	/**
	 * 生产厂家
	 */
	private String manufacturer;
	/**
	 * 成分（组方）
	 */
	private String ingredients;
	/**
	 * 性状
	 */
	private String character;
	/**
	 * 一级分类
	 */
	private MallCategory mallCategoryLevel1;
	/**
	 * 二级分类
	 */
	private MallCategory mallCategoryLevel2;
	/**
	 * 处方分类
	 */
	private String recipeType;
	/**
	 * 功能主治
	 */
	private String function;
	/**
	 * 适应症
	 */
	private String indication;
	/**
	 * 用法用量
	 */
	private String usageAndDosage;
	/**
	 * 不良反应
	 */
	private String adverseReaction;
	/**
	 * 禁忌
	 */
	private String taboo;
	/**
	 * 孕妇及哺乳期妇女用药
	 */
	private String pregnantAndLactantWomen;
	/**
	 * 儿童用药
	 */
	private String children;
	/**
	 * 老年患者用药
	 */
	private String senilePatient;
	/**
	 * 注意事项
	 */
	private String warning;
	/**
	 * 药物过量
	 */
	private String overdose;
	/**
	 * 药物相互作用
	 */
	private String drugInteractions;
	/**
	 * 药物毒理作用
	 */
	private String drugToxicology;
	/**
	 * 药代动力学
	 */
	private String pharmacokinetic;
	/**
	 * 贮藏
	 */
	private String storage;
	/**
	 * 包装
	 */
	private String packaging;
	/**
	 * 有效期
	 */
	private String validityPeriod;
	/**
	 * 整卖单位
	 */
	private String wholeSaleUnit;
	/**
	 * 换算值
	 */
	private Double conversion;
	/**
	 * 散卖单位
	 */
	private String retailSaleUnit;
	/**
	 * 项目类别，1西药，2中药
	 */
	private String itemType;
	/**
	 * 创建时间
	 */
	private Date created;
	/**
	 * 更新时间
	 */
	private Date updated;

	private Long fromMyProductId;
	
	private Long uploadCorporationId;
	
	private String image;
	
	/** id */
	public Long getId() {
    	return id;
    }
    /** id */
    public void setId(Long id) {
    	this.id = id;
    }
	/** 标准编码 */
	public String getStandardCode() {
    	return standardCode;
    }
    /** 标准编码 */
    public void setStandardCode(String standardCode) {
    	this.standardCode = standardCode;
    }
	/** 药品名称 */
	public String getName() {
    	return name;
    }
    /** 药品名称 */
    public void setName(String name) {
    	this.name = name;
    }
	/** 商品名称（品牌） */
	public String getBrandName() {
    	return brandName;
    }
    /** 商品名称（品牌） */
    public void setBrandName(String brandName) {
    	this.brandName = brandName;
    }
	/** 状态，1正常 */
	public Integer getStatus() {
    	return status;
    }
    /** 状态，1正常 */
    public void setStatus(Integer status) {
    	this.status = status;
    }
	/** 批准文号 */
	public String getApprovalNumber() {
    	return approvalNumber;
    }
    /** 批准文号 */
    public void setApprovalNumber(String approvalNumber) {
    	this.approvalNumber = approvalNumber;
    }
	/** 生产厂家 */
	public String getManufacturer() {
    	return manufacturer;
    }
    /** 生产厂家 */
    public void setManufacturer(String manufacturer) {
    	this.manufacturer = manufacturer;
    }
	/** 成分（组方） */
	public String getIngredients() {
    	return ingredients;
    }
    /** 成分（组方） */
    public void setIngredients(String ingredients) {
    	this.ingredients = ingredients;
    }
	/** 性状 */
	public String getCharacter() {
    	return character;
    }
    /** 性状 */
    public void setCharacter(String character) {
    	this.character = character;
    }

	public MallCategory getMallCategoryLevel1() {
		return mallCategoryLevel1;
	}

	public void setMallCategoryLevel1(MallCategory mallCategoryLevel1) {
		this.mallCategoryLevel1 = mallCategoryLevel1;
	}

	public MallCategory getMallCategoryLevel2() {
		return mallCategoryLevel2;
	}

	public void setMallCategoryLevel2(MallCategory mallCategoryLevel2) {
		this.mallCategoryLevel2 = mallCategoryLevel2;
	}

	/** 处方分类 */
	public String getRecipeType() {
    	return recipeType;
    }
    /** 处方分类 */
    public void setRecipeType(String recipeType) {
    	this.recipeType = recipeType;
    }
	/** 功能主治 */
	public String getFunction() {
    	return function;
    }
    /** 功能主治 */
    public void setFunction(String function) {
    	this.function = function;
    }
	/** 适应症 */
	public String getIndication() {
    	return indication;
    }
    /** 适应症 */
    public void setIndication(String indication) {
    	this.indication = indication;
    }

	/** 用法用量 */
	public String getUsageAndDosage() {
    	return usageAndDosage;
    }
    /** 用法用量 */
    public void setUsageAndDosage(String usageAndDosage) {
    	this.usageAndDosage = usageAndDosage;
    }
	/** 不良反应 */
	public String getAdverseReaction() {
    	return adverseReaction;
    }
    /** 不良反应 */
    public void setAdverseReaction(String adverseReaction) {
    	this.adverseReaction = adverseReaction;
    }
	/** 禁忌 */
	public String getTaboo() {
    	return taboo;
    }
    /** 禁忌 */
    public void setTaboo(String taboo) {
    	this.taboo = taboo;
    }
	/** 孕妇及哺乳期妇女用药 */
	public String getPregnantAndLactantWomen() {
    	return pregnantAndLactantWomen;
    }
    /** 孕妇及哺乳期妇女用药 */
    public void setPregnantAndLactantWomen(String pregnantAndLactantWomen) {
    	this.pregnantAndLactantWomen = pregnantAndLactantWomen;
    }
	/** 儿童用药 */
	public String getChildren() {
    	return children;
    }
    /** 儿童用药 */
    public void setChildren(String children) {
    	this.children = children;
    }
	/** 老年患者用药 */
	public String getSenilePatient() {
    	return senilePatient;
    }
    /** 老年患者用药 */
    public void setSenilePatient(String senilePatient) {
    	this.senilePatient = senilePatient;
    }
	/** 注意事项 */
	public String getWarning() {
    	return warning;
    }
    /** 注意事项 */
    public void setWarning(String warning) {
    	this.warning = warning;
    }
	/** 药物过量 */
	public String getOverdose() {
    	return overdose;
    }
    /** 药物过量 */
    public void setOverdose(String overdose) {
    	this.overdose = overdose;
    }
	/** 药物相互作用 */
	public String getDrugInteractions() {
    	return drugInteractions;
    }
    /** 药物相互作用 */
    public void setDrugInteractions(String drugInteractions) {
    	this.drugInteractions = drugInteractions;
    }
	/** 药物毒理作用 */
	public String getDrugToxicology() {
    	return drugToxicology;
    }
    /** 药物毒理作用 */
    public void setDrugToxicology(String drugToxicology) {
    	this.drugToxicology = drugToxicology;
    }
	/** 药代动力学 */
	public String getPharmacokinetic() {
    	return pharmacokinetic;
    }
    /** 药代动力学 */
    public void setPharmacokinetic(String pharmacokinetic) {
    	this.pharmacokinetic = pharmacokinetic;
    }
	/** 贮藏 */
	public String getStorage() {
    	return storage;
    }
    /** 贮藏 */
    public void setStorage(String storage) {
    	this.storage = storage;
    }
	/** 包装 */
	public String getPackaging() {
    	return packaging;
    }
    /** 包装 */
    public void setPackaging(String packaging) {
    	this.packaging = packaging;
    }
	/** 有效期 */
	public String getValidityPeriod() {
    	return validityPeriod;
    }
    /** 有效期 */
    public void setValidityPeriod(String validityPeriod) {
    	this.validityPeriod = validityPeriod;
    }
	/** 整卖单位 */
	public String getWholeSaleUnit() {
    	return wholeSaleUnit;
    }
    /** 整卖单位 */
    public void setWholeSaleUnit(String wholeSaleUnit) {
    	this.wholeSaleUnit = wholeSaleUnit;
    }
	/** 换算值 */
	public Double getConversion() {
    	return conversion;
    }
    /** 换算值 */
    public void setConversion(Double conversion) {
    	this.conversion = conversion;
    }
	/** 散卖单位 */
	public String getRetailSaleUnit() {
    	return retailSaleUnit;
    }
    /** 散卖单位 */
    public void setRetailSaleUnit(String retailSaleUnit) {
    	this.retailSaleUnit = retailSaleUnit;
    }
	/** 项目类别，1西药，2中药 */
	public String getItemType() {
    	return itemType;
    }
    /** 项目类别，1西药，2中药 */
    public void setItemType(String itemType) {
    	this.itemType = itemType;
    }
	/** 创建时间 */
	public Date getCreated() {
    	return created;
    }
    /** 创建时间 */
    public void setCreated(Date created) {
    	this.created = created;
    }
	/** 更新时间 */
	public Date getUpdated() {
    	return updated;
    }
    /** 更新时间 */
    public void setUpdated(Date updated) {
    	this.updated = updated;
    }

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Long getFromMyProductId() {
		return fromMyProductId;
	}

	public void setFromMyProductId(Long fromMyProductId) {
		this.fromMyProductId = fromMyProductId;
	}

	public Long getUploadCorporationId() {
		return uploadCorporationId;
	}

	public void setUploadCorporationId(Long uploadCorporationId) {
		this.uploadCorporationId = uploadCorporationId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
