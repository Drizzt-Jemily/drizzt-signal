package cn.drizzt.entity;

import javax.persistence.Id;

public class SignalAuth {
	@Id
	private String id;

	private Integer ch;

	private String called;

	private String calling;

	private Long duration;

	private Long voiceDuration;

	private Long startTime;

	private Long startRecordDur;

	private String translation;

	private Integer autoDial;

	private Integer toneAnalyze;

	private Integer recordStatus;

	private Integer callResult;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCh() {
		return ch;
	}

	public void setCh(Integer ch) {
		this.ch = ch;
	}

	public String getCalled() {
		return called;
	}

	public void setCalled(String called) {
		this.called = called;
	}

	public String getCalling() {
		return calling;
	}

	public void setCalling(String calling) {
		this.calling = calling;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Long getVoiceDuration() {
		return voiceDuration;
	}

	public void setVoiceDuration(Long voiceDuration) {
		this.voiceDuration = voiceDuration;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getStartRecordDur() {
		return startRecordDur;
	}

	public void setStartRecordDur(Long startRecordDur) {
		this.startRecordDur = startRecordDur;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public Integer getAutoDial() {
		return autoDial;
	}

	public void setAutoDial(Integer autoDial) {
		this.autoDial = autoDial;
	}

	public Integer getToneAnalyze() {
		return toneAnalyze;
	}

	public void setToneAnalyze(Integer toneAnalyze) {
		this.toneAnalyze = toneAnalyze;
	}

	public Integer getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

	public Integer getCallResult() {
		return callResult;
	}

	public void setCallResult(Integer callResult) {
		this.callResult = callResult;
	}
}