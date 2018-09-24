package cn.drizzt.util;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface ShUtil extends Library {

	ShUtil INSTANCE = (ShUtil) Native.loadLibrary(Const.CTI_SO_PATH, ShUtil.class);

	public int SsmStartCti(String shconfig, String shindex);

	public int SsmCloseCti();

	public String SsmGetLastErrMsgA();

	public int SsmGetMaxCh();

	public int SsmGetChState(int ch);

	public int SsmSetTxCallerId(int ch, String phNum);

	public int SsmSipSetTxUserName(int ch, String phNum);

	public int SsmAutoDial(int ch, String phNum);

	public int SsmChkAutoDial(int ch);

	public int SsmHangup(int ch);

	public int SsmRecToFile(int ch, String fileName, int nFormat, int dwStartPos, int dwBytes, int dwTime, int nMask);

	public int SsmStopRecToFile(int ch);

	public int SsmChkRecToFile(int ch);

	public int SsmGetToneAnalyzeResult(int ch);

	public int SsmDetectBargeIn(int ch);

	public int SsmSearchIdleCallOutCh(int wSearchMode, int dwPrecedence);

	public int SsmCloseToneAnalyze(int ch);

	public int SsmStartToneAnalyze(int ch);

}
