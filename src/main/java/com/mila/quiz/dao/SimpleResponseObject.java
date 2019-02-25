package com.mila.quiz.dao;

import com.fasterxml.jackson.annotation.JsonInclude;

public class SimpleResponseObject {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String sResponse;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean bResponse;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer iResponse;
	
	public SimpleResponseObject(Integer number)
	{
		setiResponse(number);
	}
	
	public SimpleResponseObject(String str)
	{
		setsResponse(str);
	}
	
	public SimpleResponseObject(Boolean bol)
	{
		setbResponse(bol);
	}
	
	public String getsResponse() {
		return sResponse;
	}
	public void setsResponse(String sResponse) {
		this.sResponse = sResponse;
	}
	
	public Boolean isbResponse() {
		return bResponse;
	}
	public void setbResponse(Boolean bResponse) {
		this.bResponse = bResponse;
	}

	public Integer getiResponse() {
		return iResponse;
	}

	public void setiResponse(Integer iResponse) {
		this.iResponse = iResponse;
	}


	
	
}
