package com.cams.blaze.request;

import javax.persistence.*;
@Entity
public class InstalmentKeys {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long id;
	@Column
	private String instalmentType;
	@Column
	private String key;
	public String getInstalmentType() {
		return instalmentType;
	}
	public void setInstalmentType(String instalmentType) {
		this.instalmentType = instalmentType;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column
	private Long parent_id;
	public Long getParent_id() {
		return parent_id;
	}
	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}
}
