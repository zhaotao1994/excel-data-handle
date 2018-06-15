package vip.zhaotao.dto;

import vip.zhaotao.model.StandardProduct;

public class StandardProductDTO extends StandardProduct {

	private static final long serialVersionUID = 1L;

	private String searchTerm;

	/**
	 * 是否合作伙伴有该药品
	 */
	private Boolean is_companyOwn;
	/**
	 * 是否医药公司有该药品
	 */
	private Boolean is_corporationOwn;
	/**
	 * 一级分类名称
	 */
	private String oneLevelName;
	/**
	 * 二级分类名称
	 */
	private String twoLevelName;

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public Boolean getIs_companyOwn() {
		return is_companyOwn;
	}

	public void setIs_companyOwn(Boolean is_companyOwn) {
		this.is_companyOwn = is_companyOwn;
	}

	public Boolean getIs_corporationOwn() {
		return is_corporationOwn;
	}

	public void setIs_corporationOwn(Boolean is_corporationOwn) {
		this.is_corporationOwn = is_corporationOwn;
	}

	public String getOneLevelName() {
		return oneLevelName;
	}

	public void setOneLevelName(String oneLevelName) {
		this.oneLevelName = oneLevelName;
	}

	public String getTwoLevelName() {
		return twoLevelName;
	}

	public void setTwoLevelName(String twoLevelName) {
		this.twoLevelName = twoLevelName;
	}
}
