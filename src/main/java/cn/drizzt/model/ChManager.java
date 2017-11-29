package cn.drizzt.model;

import cn.drizzt.util.Const;

public class ChManager {

	private String id;

	private int ch; // 通道号

	private boolean useStatus = false; // 是否被使用

	private long startTime; // 通道使用开始时间

	private String called; // 主叫号码

	private String calling; // 被叫号码

	private boolean callStatus = false; // 是否是呼叫状态

	private boolean voiceStatus = false; // 是否是语音识别状态

	private int autoDial = 0; // 通道的ssmChkAutoDial状态

	private int toneAnalyze = 0; // 通道的SsmGetToneAnalyzeResult状态

	private int recordStatus = 0; // 0:无录音；1：启动录音；2：录音进行中；3：录音完成；

	private long startRecordDur; // 间隔多久开始录音

	private int callResult = Const.CALL_RESULT_99; // 呼叫结果

	private String translation; // 翻译结果

	private Long voiceDuration; // 语音识别持续时间

	private Long duration; // 验证持续时间

	private boolean hangup = false;

	public boolean isHangup() {
		return hangup;
	}

	public void setHangup(boolean hangup) {
		this.hangup = hangup;
	}

	public Long getVoiceDuration() {
		return voiceDuration;
	}

	public void setVoiceDuration(Long voiceDuration) {
		this.voiceDuration = voiceDuration;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public int getCallResult() {
		return callResult;
	}

	public void setCallResult(int callResult) {
		this.callResult = callResult;
	}

	public int getToneAnalyze() {
		return toneAnalyze;
	}

	public void setToneAnalyze(int toneAnalyze) {
		this.toneAnalyze = toneAnalyze;
	}

	public long getStartRecordDur() {
		return startRecordDur;
	}

	public void setStartRecordDur(long startRecordDur) {
		this.startRecordDur = startRecordDur;
	}

	public int getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(int recordStatus) {
		this.recordStatus = recordStatus;
	}

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

	public int getAutoDial() {
		return autoDial;
	}

	public void setAutoDial(int autoDial) {
		this.autoDial = autoDial;
	}

}
