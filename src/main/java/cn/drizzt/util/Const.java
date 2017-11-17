package cn.drizzt.util;
public class Const {

	public static final String CTI_SO_PATH = "/usr/local/lib/shcti/ver5.3.40/out/libshpa3.so.5.3.40";
	public static final String CTI_INI_PATH = "/home/longtel/CtiLinux5.3.40-2.6.32-504.el6.x86_64-SMP-x86_64/tools/";
	public static final String CTI_VOICE_PATH = "/home/drizzt/signal/voice";

	public static final String AUTH_TOPIC = "authMessage";
	
	public static final int DISPATCHER_SLEEPTIME = 200; // 调度器睡眠时间
    public static final int DISPATCHER_TIMEOUT = 30000; // 调度器超时时间
    public static final int DISPATCHER_DB_TIMEOUT = 60000; // 数据库调度器超时时间
    public static final int SIGNAL_THREADNUM = 200; // 通讯端线程数量
    public static final int POOL_KEEPALIVE_SECOND = 5; // 线程存活时间

}