package cn.drizzt.model;

public class ChManager {

	private String id;

	private int ch; // 通道号

	private boolean useStatus = false; // 是否被使用

	private long startTime; // 通道使用开始时间

	private String called; // 主叫号码

	private String calling; // 被叫号码

	private boolean callStatus = false; // 是否是呼叫状态

	private boolean voiceStatus = false; // 是否是语音识别状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ChManager(int ch) {
		this.ch = ch;
	}

	public int getCh() {
		return ch;
	}

	public void setCh(int ch) {
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

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public boolean isUseStatus() {
		return useStatus;
	}

	public void setUseStatus(boolean useStatus) {
		this.useStatus = useStatus;
	}

	public boolean isCallStatus() {
		return callStatus;
	}

	public void setCallStatus(boolean callStatus) {
		this.callStatus = callStatus;
	}

	public boolean isVoiceStatus() {
		return voiceStatus;
	}

	public void setVoiceStatus(boolean voiceStatus) {
		this.voiceStatus = voiceStatus;
	}

}
