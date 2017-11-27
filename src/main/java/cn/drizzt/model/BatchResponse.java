package cn.drizzt.model;

public class BatchResponse {

	private String calling; // 被叫号码
	private String callResult;

	public String getCalling() {
		return calling;
	}

	public void setCalling(String calling) {
		this.calling = calling;
	}

	public String getCallResult() {
		return callResult;
	}

	public void setCallResult(String callResult) {
		this.callResult = callResult;
	}

}
