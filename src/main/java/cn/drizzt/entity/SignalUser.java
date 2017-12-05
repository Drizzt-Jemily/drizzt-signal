package cn.drizzt.entity;

import java.util.Date;

import javax.persistence.Id;

public class SignalUser extends BaseEntity {
	@Id
    private String id;

    private Integer number;

    private Date registerTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}