package cn.drizzt.entity;

import java.util.Date;

import javax.persistence.Id;

public class SignalBatch extends BaseEntity {
	@Id
	private String id;

	private Date receiveTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
}