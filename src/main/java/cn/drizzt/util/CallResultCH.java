package cn.drizzt.util;

public class CallResultCH {

	public static String getCH(int callResult) {
		String cr = "";
		if (callResult == Const.CALL_RESULT_0) {
			cr = "未呼叫";
		} else if (callResult == Const.CALL_RESULT_99) {
			cr = "正在呼叫";
		} else if (callResult == Const.CALL_RESULT_98) {
			cr = "呼叫超时";
		} else if (callResult == Const.CALL_RESULT_97) {
			cr = "呼叫失败";
		} else if (callResult == Const.CALL_RESULT_1) {
			cr = "正常号码";
		} else if (callResult == Const.CALL_RESULT_2) {
			cr = "被接听";
		} else if (callResult == Const.CALL_RESULT_3) {
			cr = "暂时无法接听";
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
