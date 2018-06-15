package vip.zhaotao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 产品类别
 */
public class MallCategory implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public MallCategory() {
	}
	
	public MallCategory(Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 */
	private Long id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 上级分类
	 */
	private MallCategory parent;
	/**
	 * 是否显示
	 */
	private Boolean isShow;
	
	private Integer seq;

	/**
	 * 子分类
	 */
	private List<MallCategory> childCategory = new ArrayList<>();
	/**
	 * 是否显示erp默认分类
	 */
	private Boolean isErpClassify;
	
	/**  */
	public Long getId() {
    	return id;
    }
    /**  */
    public void setId(Long id) {
    	this.id = id;
    }
	/** 名称 */
	public String getName() {
    	return name;
    }
    /** 名称 */
    public void setName(String name) {
    	this.name = name;
    }
	/** 上级分类 */
	public MallCategory getParent() {
    	return parent;
    }
    /** 上级分类 */
    public void setParent(MallCategory parent) {
    	this.parent = parent;
    }
	/** 是否显示 */
	public Boolean getIsShow() {
    	return isShow;
    }
    /** 是否显示 */
    public void setIsShow(Boolean isShow) {
    	this.isShow = isShow;
    }

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public List<MallCategory> getChildCategory() {
		return childCategory;
	}

	public void setChildCategory(List<MallCategory> childCategory) {
		this.childCategory = childCategory;
	}

	public Boolean getErpClassify() {
		return isErpClassify;
	}

	public void setErpClassify(Boolean erpClassify) {
		isErpClassify = erpClassify;
	}
}
