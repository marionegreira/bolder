package com.bolder.server.models.dto;

import java.io.Serializable;
import java.util.List;

public class PageDto implements Serializable{
	private static final long serialVersionUID = 346766886223167690L;
	
	public PageDto() {
	}

	private Long recordsTotal;
	private Long recordsFiltered;
	private List<?> data;

	public Long getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public Long getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	
	
}
