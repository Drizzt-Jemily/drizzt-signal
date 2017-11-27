package cn.drizzt.util;

public class Const {

	public static final String CTI_SO_PATH = "/usr/local/lib/shcti/ver5.3.40/out/libshpa3.so.5.3.40";
	public static final String CTI_INI_PATH = "/home/longtel/CtiLinux5.4.20-2.6.32-504.el6.x86_64-SMP-x86_64/tools/";
	public static final String CTI_VOICE_PATH = "/home/drizzt/signal/voice";
	// public static final String CTI_VOICE_PATH = "C:/workspace";

	public static final String AUTH_TOPIC = "authMessage";

	public static final String AREA_CODE = "010"; // 中继线所在地区区号
	public static final String[] CODE_POOL = { "56617550", "56617551", "56617552", "56617553", "56617554", "56617555",
			"56617556", "56617557", "56617558", "56617559", "56617560", "56617561", "56617562", "56617563", "56617564",
			"56617565", "56617566", "56617567", "56617568", "56617569", "56617570", "56617571", "56617572", "56617573",
			"56617574", "56617575", "56617576", "56617577", "56617578", "56617579", "56617580", "56617581", "56617582",
			"56617583", "56617584", "56617585", "56617586", "56617587", "56617588", "56617589", "56617590", "56617591",
			"56617592", "56617593", "56617594", "56617595", "56617596", "56617597", "56617598", "56617599" };

	public static final int CARD_SLEEPTIME = 15000; // 板卡启动时间
	public static final int CARD_NUMBER = 30; // 话路数量
	public static final int CHMANAGER_TIMEOUT = 20000; // 线路超时时间
	public static final int DIAL_TIMEOUT = 10000; // 呼叫超时时间
	public static final int RECORD_TIME = 4000; // 录音时间
	public static final int DISPATCHER_SLEEPTIME = 200; // 调度器睡眠时间
	public static final int POOL_KEEPALIVE_SECOND = 5; // 线程存活时间

	// 呼叫状态
	public static final int CALL_RESULT_0 = 0; // 未呼叫
	public static final int CALL_RESULT_99 = 99; // 正在呼叫
	public static final int CALL_RESULT_98 = 98; // 呼叫超时
	public static final int CALL_RESULT_97 = 97; // 呼叫异常
	public static final int CALL_RESULT_1 = 1; // 正常号码
	public static final int CALL_RESULT_2 = 2; // 被接听
	public static final int CALL_RESULT_3 = 3; // 暂时无法接听
	public static final int CALL_RESULT_4 = 4; // 呼叫限制
	public static final int CALL_RESULT_5 = 5; // 关机
	public static final int CALL_RESULT_6 = 6; // 空号
	public static final int CALL_RESULT_7 = 7; // 停机
	public static final int CALL_RESULT_8 = 8; // 来电提醒

}