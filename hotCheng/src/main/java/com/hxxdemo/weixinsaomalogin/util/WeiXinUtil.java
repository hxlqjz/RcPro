package com.hxxdemo.weixinsaomalogin.util;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.json.JSONException;

import net.sf.json.JSONObject;

public class WeiXinUtil {

	public final static String access_token_url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public final static String oauth2_1_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	public final static String oauth2_2_url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	public final static String get_userInfo_url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	public final static String get_hangye_url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
	//上面那五个，不用改。把下边的appid和appsecret改了就行
	public final static String appid="wx8666da66ca071393";
	public final static String appSecret="37aec1ee8da7e3d3434d663b8a74a1f8";
	
	
	
	public static AccessToken getAccessToken(String appid, String appSecret) {
		//替换真实appid和appsecret
		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appSecret);
		System.out.println("requestUrl==>"+requestUrl);
		AccessToken accesstoken=new AccessToken();
		//得到json对象
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		System.out.println("jsonObject==>"+jsonObject.toString());
		
		//将得到的json对象的属性值，存到accesstoken中
		accesstoken.setToken(jsonObject.getString("access_token"));
		accesstoken.setExpiresIn(jsonObject.getInt("expires_in"));
		return accesstoken;
	} 
	
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
 
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
 
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
 
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
 
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
 
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
 
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	/**
	 * 获取jsapi_ticket
	 * 
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	public static String getJsapiTicket(String accessToken) throws JSONException {
		// 获取公众号jsapi_ticket的链接
		String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
		if (accessToken != null) {
			String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKEN", accessToken);
			// String requestUrl = access_token_url.replace("APPID",
			// appid).replace("APPSECRET", appsecret);
			JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
			// 如果请求成功
			if (null != jsonObject) {
				return jsonObject.getString("ticket");
			}
		} else {
			System.out.println("*****token为空 获取ticket失败******");
		}
 
		return null;
	}
	
				
	
/**
 * 网页授权认证	
 * @param appId
 * @param appSecret
 * @param code
 * @return
 */
	public static WeixinOauth2Token getOauth2AccessToken(String appId,String appSecret,String code) {
		String  requestUrl=oauth2_1_url.replace("APPID", "wx8666da66ca071393").replace("SECRET", appSecret).replace("CODE", code);
		//发送请求获取网页授权凭证
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, EnumMethod.GET.name(), null);
		System.out.println("--->"+jsonObject.toString());
		WeixinOauth2Token   wxo=new WeixinOauth2Token();
		wxo.setAccessToken(jsonObject.getString("access_token"));
		wxo.setExpiresIn(jsonObject.getInt("expires_in"));
		wxo.setRefreshToken(jsonObject.getString("refresh_token"));
		wxo.setOpenId(jsonObject.getString("openid"));
		wxo.setScope(jsonObject.getString("scope"));
		
		return wxo;
		
	}
	/**
	 * 获取用户的基本信息
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public static SNSUserInfo getSNSUserInfo(String accessToken,String openId) {
		String requestUrl=oauth2_2_url.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		SNSUserInfo snsuserinfo=new SNSUserInfo();
		//通过网页授权获取用户信息
		JSONObject jsonObject=CommonUtil.httpsRequest(requestUrl, EnumMethod.GET.name(), null);
		
		snsuserinfo.setOpenId(jsonObject.getString("openid"));
		snsuserinfo.setNickname(jsonObject.getString("nickname"));
		snsuserinfo.setSex(jsonObject.getInt("sex"));
		snsuserinfo.setCountry(jsonObject.getString("country"));
		snsuserinfo.setProvince(jsonObject.getString("province"));
		snsuserinfo.setCity(jsonObject.getString("city"));
		snsuserinfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
		//snsuserinfo.setPrivilegeList(JSONArray._fromArray(jsonObject.getString("privilege"),String.class));
		return snsuserinfo; 
	}
	
	/**
	 * 创建网页授权的url
	 * @param redirectUri
	 * @return
	 */
	public static String createUrl(String redirectUri) {
		
		String url =get_userInfo_url.replace("APPID", appid).replace("REDIRECT_URI", CommonUtil.urlEncodeUTF8(redirectUri)).replace("SCOPE", "snsapi_userinfo");
		System.out.println(url);
		return url;
	}	
	
	/**
	 * 长连接转化成短链接，提高扫码速度跟成功率
	 * @param args
	 */
	
	public static String  shortURL(String longURL, String wxAppId, String secret) {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN";
        try {
        	//将更新后的access_token,替换上去
			requestUrl = requestUrl.replace("ACCESS_TOKEN",getAccessToken(wxAppId, secret).getToken());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String jsonMsg = "{\"action\":\"long2short\",\"long_url\":\"%s\"}";
        //格式化url
        String.format(jsonMsg, longURL);
        JSONObject jsonobject = CommonUtil.httpsRequest(requestUrl, "POST",String.format(jsonMsg, longURL));
         System.out.println("--wsy--->"+jsonobject.toString());
        //转换成短连接成功
        return jsonobject.getString("short_url");
    }

	
	public static void main(String[] args) {
		AccessToken a =getAccessToken(appid, appSecret);
		//String s= shortURL(sss, appid, appSecret);
		//System.out.println(s);
		System.out.println(a.getToken());
		System.out.println(a.getExpiresIn());
	}
	
}
