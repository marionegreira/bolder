package com.bolder.server.models.dto;

import java.io.Serializable;
import java.util.List;

public class PageRequestDto implements Serializable{
	private static final long serialVersionUID = 346766886223167690L;
	
	public PageRequestDto() {
	}
	

	private int pageNumber;
	private int pageSize;
	private Object data;

	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
