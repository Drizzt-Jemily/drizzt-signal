package cn.drizzt.util;

public class Const {

	public static final String CTI_SO_PATH = "/usr/local/lib/shcti/ver5.3.40/out/libshpa3.so.5.3.40";
	public static final String CTI_INI_PATH = "/home/longtel/CtiLinux5.3.40-2.6.32-504.el6.x86_64-SMP-x86_64/tools/";
	public static final String CTI_VOICE_PATH = "/home/drizzt/signal/voice";

	public static final String AUTH_TOPIC = "authMessage";

	public static final int CARD_SLEEPTIME = 15000; // 板卡启动时间
	public static final int CARD_NUMBER = 30; // 话路数量
	public static final int CHMANAGER_TIMEOUT = 15000; // 线路超时时间
	public static final int DISPATCHER_SLEEPTIME = 200; // 调度器睡眠时间

	// 呼叫状态
	public static final int CALL_STATUS_0 = 0; // 未呼叫
	public static final int CALL_STATUS_99 = 99; // 正在呼叫
}