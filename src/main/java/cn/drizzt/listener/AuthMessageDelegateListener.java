package cn.drizzt.listener;

public class AuthMessageDelegateListener {

	// 监听Redis消息
	public void handleMessage(String message) {
		System.out.println("我收到消息了:"+message);
	}
}