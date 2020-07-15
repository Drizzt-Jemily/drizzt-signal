package cn.drizzt.util;

public class Const {

	public static final boolean DEBUG = false; // 控制文件删除

	public static final String CTI_SO_PATH = "/usr/local/lib/shcti/ver5.4.30/out/libshpa3.so.5.4.30";
//	public static final String CTI_INI_PATH = "/home/longtel/CtiLinux5.4.20-2.6.32-504.el6.x86_64-SMP-x86_64/tools/";
	public static final String CTI_INI_PATH = "/home/drizzt/signal/";
	public static final String CTI_VOICE_PATH = "/home/drizzt/signal/voice";
	// public static final String CTI_VOICE_PATH = "C:/workspace";

	public static final String AUTH_TOPIC = "authMessage";

	public static final String AREA_CODE = "010"; // 中继线所在地区区号
//	public static final String[] CODE_POOL = { "56617400", "56617401", "56617402", "56617403", "56617404", "56617405",
//			"56617406", "56617407", "56617408", "56617409", "56617410", "56617411", "56617412", "56617413", "56617414",
//			"56617415", "56617416", "56617417", "56617418", "56617419", "56617420", "56617421", "56617422", "56617423",
//			"56617424", "56617425", "56617426", "56617427", "56617428", "56617429", "56617430", "56617431", "56617432",
//			"56617433", "56617434", "56617435", "56617436", "56617437", "56617438", "56617439", "56617440", "56617441",
//			"56617442", "56617443", "56617444", "56617445", "56617446", "56617447", "56617448", "56617449", "56617450",
//			"56617451", "56617452", "56617453", "56617454", "56617455", "56617456", "56617457", "56617458", "56617459",
//			"56617460", "56617461", "56617462", "56617463", "56617464", "56617465", "56617466", "56617467", "56617468",
//			"56617469", "56617470", "56617471", "56617472", "56617473", "56617474", "56617475", "56617476", "56617477",
//			"56617478", "56617479", "56617480", "56617481", "56617482", "56617483", "56617484", "56617485", "56617486",
//			"56617487", "56617488", "56617489", "56617490", "56617491", "56617492", "56617493", "56617494", "56617495",
//			"56617496", "56617497", "56617498", "56617499", "56617500", "56617501", "56617502", "56617503", "56617504",
//			"56617505", "56617506", "56617507", "56617508", "56617509", "56617510", "56617511", "56617512", "56617513",
//			"56617514", "56617515", "56617516", "56617517", "56617518", "56617519", "56617520", "56617521", "56617522",
//			"56617523", "56617524", "56617525", "56617526", "56617527", "56617528", "56617529", "56617530", "56617531",
//			"56617532", "56617533", "56617534", "56617535", "56617536", "56617537", "56617538", "56617539", "56617540",
//			"56617541", "56617542", "56617543", "56617544", "56617545", "56617546", "56617547", "56617548", "56617549",
//			"56617550", "56617551", "56617552", "56617553", "56617554", "56617555", "56617556", "56617557", "56617558",
//			"56617559", "56617560", "56617561", "56617562", "56617563", "56617564", "56617565", "56617566", "56617567",
//			"56617568", "56617569", "56617570", "56617571", "56617572", "56617573", "56617574", "56617575", "56617576",
//			"56617577", "56617578", "56617579", "56617580", "56617581", "56617582", "56617583", "56617584", "56617585",
//			"56617586", "56617587", "56617588", "56617589", "56617590", "56617591", "56617592", "56617593", "56617594",
//			"56617595", "56617596", "56617597", "56617598", "56617599" };
	
	public static final String[] CODE_POOL = { "9521290202" };
	
	public static final int CARD_SLEEPTIME = 60000; // 板卡启动时间
	// public static final int SIP_NUMBER = 0; // sip话路数量
	public static final int CARD_NUMBER = 60; // 话路数量
	public static final int CHMANAGER_TIMEOUT = 30000; // 线路超时时间
	public static final int DIAL_TIMEOUT = 15000; // 呼叫超时时间
	// public static final int SIP_DIAL_TIMEOUT = 8000; // sip呼叫超时时间
	public static final int RECORD_TIME = 6000; // 录音时间
	public static final int DIAL_DELAY = 5000; // 话路延时释放时间

	public static final int DISPATCHER_SLEEPTIME = 100; // 调度器睡眠时间
	public static final int POOL_KEEPALIVE_SECOND = 5; // 线程存活时间

	// 呼叫状态
	public static final int CALL_RESULT_0 = 0; // 未呼叫
	public static final int CALL_RESULT_99 = 99; // 正在呼叫
	public static final int CALL_RESULT_98 = 98; // 呼叫无应答（呼叫超时）
	public static final int CALL_RESULT_97 = 97; // 呼叫失败
	public static final int CALL_RESULT_1 = 1; // 正常号码
	public static final int CALL_RESULT_2 = 2; // 被接听
	public static final int CALL_RESULT_3 = 3; // 暂时无法接听
	public static final int CALL_RESULT_4 = 4; // 呼叫限制
	public static final int CALL_RESULT_5 = 5; // 关机
	public static final int CALL_RESULT_6 = 6; // 空号
	public static final int CALL_RESULT_7 = 7; // 停机
	public static final int CALL_RESULT_8 = 8; // 来电提醒

}