package cn.drizzt.util;

import com.huawei.sis.bean.SisConfig;
import com.huawei.sis.bean.SisConstant;
import com.huawei.sis.bean.request.AsrCustomShortRequest;
import com.huawei.sis.bean.response.AsrCustomShortResponse;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.sis.bean.AuthInfo;
import com.huawei.sis.client.AsrCustomizationClient;
import com.huawei.sis.exception.SisException;
import com.huawei.sis.util.IOUtils;

/**
 * 定制语音识别 demo、包含一句话识别
 *
 * Copyright 2020 Huawei Technologies Co.,Ltd.
 */
public class HWUtil {

	private String ak = "6UC240XMN5MFW4F5OS6I";
	private String sk = "0C5VyWq1spg62ambp6Hlg22ekW4H6XeHTZLtwiU3";
	private String region = "cn-north-4"; // 区域，如cn-north-1、cn-north-4
	private String projectId = "06334a770a0025c72fcbc0078f207334"; // 项目id，在我的凭证查看。
	private String pathAudioFormat = "pcm8k16bit"; // 文件格式，如wav等，支持格式详见api文档
	private String pathProperty = "chinese_8k_common"; // 属性字符串，language_sampleRate_domain, 如chinese_8k_common, 详见api文档
	private static final Logger LOGGER = LoggerFactory.getLogger(HWUtil.class);

	/**
	 * 设置一句话识别参数，所有参数均有默认值，不配置也可使用
	 *
	 * @param request
	 *            一句话识别请求
	 */
	private void setShortParameter(AsrCustomShortRequest request) {

		// 设置是否添加标点，默认是no
		request.setAddPunc("yes");
	}

	/**
	 * 定义config，所有参数可选，设置超时时间等。
	 *
	 * @return SisConfig
	 */
	private SisConfig getConfig() {
		SisConfig config = new SisConfig();
		// 设置连接超时，默认10000ms
		config.setConnectionTimeout(SisConstant.DEFAULT_CONNECTION_TIMEOUT);
		// 设置请求超时，默认10000ms
		config.setRequestTimeout(SisConstant.DEFAULT_CONNECTION_REQUEST_TIMEOUT);
		// 设置socket超时，默认10000ms
		config.setSocketTimeout(SisConstant.DEFAULT_SOCKET_TIMEOUT);
		// 设置代理, 一定要确保代理可用才启动此设置。 代理初始化也可用不加密的代理，new ProxyHostInfo(host, port);
		// ProxyHostInfo proxy = new ProxyHostInfo(host, port, username, password);
		// config.setProxy(proxy);
		return config;
	}

	public String getTranslation(String fileName) {
		try {
			// 1. 初始化AsrCustomizationClient
			// 定义authInfo，根据ak，sk，region，projectId
			AuthInfo authInfo = new AuthInfo(ak, sk, region, projectId);
			// 设置config，主要与超时有关
			SisConfig config = getConfig();
			// 根据authInfo和config，构造AsrCustomizationClient
			AsrCustomizationClient asr = new AsrCustomizationClient(authInfo, config);

			// 2. 配置请求
			String data = IOUtils.getEncodeDataByPath(Const.CTI_VOICE_PATH + File.separator + fileName);
			AsrCustomShortRequest request = new AsrCustomShortRequest(data, pathAudioFormat, pathProperty);
			// 设置请求参数，所有参数均为可选
			setShortParameter(request);

			// 3. 发送请求，获取响应
			AsrCustomShortResponse response = asr.getAsrShortResponse(request);
			// 打印结果
			LOGGER.info("翻译文件:" + Const.CTI_VOICE_PATH + File.separator + fileName + ",翻译结果：" + response.getText());
			return response.getText();

		} catch (SisException e) {
			e.printStackTrace();
			return "error_code:" + e.getErrorCode() + "\nerror_msg:" + e.getErrorMsg();
		}
	}

	public static void main(String[] args) {
		HWUtil demo = new HWUtil();
		System.out.println(demo.getTranslation("D:/3.wav"));

	}

}