package cn.drizzt.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.bind.DatatypeConverter;

import net.sf.json.JSONObject;

public class VoiceUtil {

    private static final String serverURL = "http://vop.baidu.com/server_api";
    private static String token = "";
    private static final String apiKey = "B4gRaLaNMXkqZq1fMbNLYmlR";
    private static final String secretKey = "fdb71b6830db633a61b2d14460a19d76";
    private static final String cuid = "ciaVerify";

    public static void getToken() throws Exception {
        String getTokenURL = "https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials" + "&client_id=" + apiKey
                + "&client_secret=" + secretKey;
        HttpURLConnection conn = (HttpURLConnection) new URL(getTokenURL).openConnection();
        InputStream is = conn.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuffer response = new StringBuffer();
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        token = (String) JSONObject.fromObject(response.toString()).get("access_token");
    }

    public static String getTranslation(String fileName) throws Exception {
        File pcmFile = new File(Const.CTI_VOICE_PATH + File.separator + fileName);
        HttpURLConnection conn = (HttpURLConnection) new URL(serverURL).openConnection();

        // construct params
        JSONObject params = new JSONObject();
        params.put("format", "pcm");
        params.put("rate", 8000);
        params.put("channel", "1");
        params.put("token", token);
        params.put("cuid", cuid);
        params.put("len", pcmFile.length());
        params.put("speech", DatatypeConverter.printBase64Binary(loadFile(pcmFile)));

        // add request header
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

        conn.setDoInput(true);
        conn.setDoOutput(true);

        // send request
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(params.toString());
        wr.flush();
        wr.close();

        InputStream is = conn.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuffer response = new StringBuffer();
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        String Translation = "";
        JSONObject jsonObject = JSONObject.fromObject(response.toString());
        if (jsonObject.getString("err_no").equals("0")) {
            Translation = jsonObject.getString("result");
        }else if(jsonObject.getString("err_no").equals("3302")){
        	Translation = "error3302";
        }
        return Translation;
    }

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            is.close();
            throw new IOException("Could not completely read file " + file.getName());
        }

        is.close();
        return bytes;
    }

    public static void main(String[] args) throws Exception {
//        VoiceUtil.getToken();
    	token = "24.bffc5cc38424472197ae587580f5f0c1.2592000.1513994548.282335-7672161";
    	String translation = VoiceUtil.getTranslation("32.wav");
    	if(translation.equals("error3302")){
    		VoiceUtil.getToken();
        	translation = VoiceUtil.getTranslation("32.wav");
    	}
        System.out.println("结果：" + translation);
    }
}
