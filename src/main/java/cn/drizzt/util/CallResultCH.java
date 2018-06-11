package cn.drizzt.util;

public class CallResultCH {

	public static String getCH(int callResult) {
		String cr = "";
		if (callResult == Const.CALL_RESULT_0) {
			cr = "处理中，请间隔10秒后再试";
		} else if (callResult == Const.CALL_RESULT_99) {
			cr = "正在呼叫，请间隔10秒后再试";
		} else if (callResult == Const.CALL_RESULT_98) {
			cr = "呼叫无应答";
		} else if (callResult == Const.CALL_RESULT_97) {
			cr = "呼叫失败";
		} else if (callResult == Const.CALL_RESULT_1) {
			cr = "振铃";
		} else if (callResult == Const.CALL_RESULT_2) {
			cr = "接听";
		} else if (callResult == Const.CALL_RESULT_3) {
			cr = "暂时无法接通";
		} else if (callResult == Const.CALL_RESULT_4) {
			cr = "呼叫限制";
		} else if (callResult == Const.CALL_RESULT_5) {
			cr = "关机";
		} else if (callResult == Const.CALL_RESULT_6) {
			cr = "空号";
		} else if (callResult == Const.CALL_RESULT_7) {
			cr = "停机";
		} else if (callResult == Const.CALL_RESULT_8) {
			cr = "来电提醒";
		} else {
			cr = "未知状态";
		}
		return cr;
	}
}
