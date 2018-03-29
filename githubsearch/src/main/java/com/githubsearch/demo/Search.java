package com.githubsearch.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name="orgfileextsearchresults")
public class Search 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String orgFileExt;
	private String fileRefSource;
	@Transient
	private String org;
	@Transient
	private String fileExt;
	
	public Search()
	{
		
	}	
	public Search(Long id, String fileRefSource)
	{
		this.id = id;		
		this.fileRefSource = fileRefSource;
	}
	public String getOrgFileExt() {
		return orgFileExt;
	}
	public void setOrgFileExt(String orgFileExt) {
		this.orgFileExt = orgFileExt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFileRefSource() {
		return fileRefSource;
	}
	public void setFileRefSource(String fileRefSource) {
		this.fileRefSource = fileRefSource;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
}
