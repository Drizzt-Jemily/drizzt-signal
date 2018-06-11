package cn.drizzt.util;

public class Const {

	public static final boolean DEBUG = false; // 控制文件删除

	public static final String CTI_SO_PATH = "/usr/local/lib/shcti/ver5.4.20/out/libshpa3.so.5.4.20";
	// public static final String CTI_INI_PATH =
	// "/home/longtel/CtiLinux5.4.20-2.6.32-504.el6.x86_64-SMP-x86_64/tools/";
	public static final String CTI_INI_PATH = "/home/drizzt/signal/";
	public static final String CTI_VOICE_PATH = "/home/drizzt/signal/voice";
	// public static final String CTI_VOICE_PATH = "C:/workspace";

	public static final String AUTH_TOPIC = "authMessage";

	public static final String AREA_CODE = "0437"; // 中继线所在地区区号
	public static final String[] CODE_POOL = { "5214001", "5214002", "5214003", "5214004", "5214005", "5214006",
			"5214007", "5214008", "5214009", "5214010", "5214011", "5214012", "5214013", "5214014", "5214015",
			"5214016", "5214017", "5214018", "5214019", "5214020", "5214021", "5214022", "5214023", "5214024",
			"5214025", "5214026", "5214027", "5214028", "5214029", "5214030", "5214031", "5214032", "5214033",
			"5214034", "5214035", "5214036", "5214037", "5214038", "5214039", "5214040", "5214041", "5214042",
			"5214043", "5214044", "5214045", "5214046", "5214047", "5214048", "5214049", "5214050", "5214051",
			"5214052", "5214053", "5214054", "5214055", "5214056", "5214057", "5214058", "5214059", "5214060",
			"5214061", "5214062", "5214063", "5214064", "5214065", "5214066", "5214067", "5214068", "5214069",
			"5214070", "5214071", "5214072", "5214073", "5214074", "5214075", "5214076", "5214077", "5214078",
			"5214079", "5214080", "5214081", "5214082", "5214083", "5214084", "5214085", "5214086", "5214087",
			"5214088", "5214089", "5214090", "5214091", "5214092", "5214093", "5214094", "5214095", "5214096",
			"5214097", "5214098", "5214099", "5214100", "5214101", "5214102", "5214103", "5214104", "5214105",
			"5214106", "5214107", "5214108", "5214109", "5214110", "5214111", "5214112", "5214113", "5214114",
			"5214115", "5214116", "5214117", "5214118", "5214119" };

	// public static final String[] CODE_POOL = { "9521290202" };

	public static final int CARD_SLEEPTIME = 60000; // 板卡启动时间
	// public static final int SIP_NUMBER = 0; // sip话路数量
	public static final int CARD_NUMBER = 120; // 话路数量
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