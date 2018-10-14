package com.kdgcsoft.power.controller.business.comcont;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.hp.hpl.sparta.ParseException;
import com.kdgcsoft.power.controller.fw.base.BaseController;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**   
 * @Title: Controller
 * @Description: 通用交流的后台代码
 * @date 2018年5月25日10:40:17
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/comcont")
public class ComcontController  extends BaseController{

	   private static CacheManager cacheManager= CacheManager.create();  
	    static String       cacheName = "cache1";  
	   
	    @RequestMapping(value = "/testEhcacheWrite")
	 	@ResponseBody
	 	public  static  Object testEhcacheWrite(HttpServletRequest request,HttpServletResponse response){ 
	    	if(cacheManager.getCache(cacheName)==null){
	    		cacheManager.addCache(cacheName);  
	    	}
		        
	        Cache cache1 = cacheManager.getCache(cacheName);  
	        String key = "key1";  
	        String value = "value1";  
	        cache1.put(new Element(key, value));  
	        if(cache1.get(key).getObjectValue().equals("value1")){
	        	System.out.println(cache1.get(key) ); 	
	        }
	        System.out.println(cache1.get(key) ); 
	        
	 		return null;
	 	};
	 	
	 	 @RequestMapping(value = "/testEhcacheRead")
		 	@ResponseBody
		 	public  static  Object testEhcacheRead(HttpServletRequest request,HttpServletResponse response){
	 		 Cache cache1 = cacheManager.getCache(cacheName);  
		        System.out.println(cache1.get("key1").getObjectValue() ); 
		        
		 		return null;
		 	};
	    
	// 短信应用SDK AppID
	int appid = 1400096389; // 1400开头

	// 短信应用SDK AppKey
	String appkey = "3dad967c329892ad882409c5168499be";

	// 需要发送短信的手机号码
	String[] phoneNumbers = {"18256921878"};

	// 短信模板ID，需要在短信应用中申请
	int templateId = 7839; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请

	// 签名
	String smsSign = "腾讯云"; // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`
	
	static String requestUrl="http://api.feige.ee/SmsService/Send";
	/**
 	 * @author shywang
 	 * @title: 获取新闻列表里的数据
 	 * @param dictCatCode
 	 * @return
 	 * @throws ParseException 
	 * @throws java.io.IOException 
	 * @throws org.json.JSONException 
 	 */
 	@RequestMapping(value = "/sendSmsValidator")
 	@ResponseBody
 	public List<Map<String,Object>> sendSmsValidator(HttpServletRequest request,HttpServletResponse response) throws ParseException, org.json.JSONException, java.io.IOException{	
 	
 		try {
 		    SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
 		    SmsSingleSenderResult result = ssender.send(0, "86", phoneNumbers[0],
 		        "【腾讯云】您的验证码是: 5678", "", "");
 		    System.out.print(result);
 		} catch (HTTPException e) {
 		    // HTTP响应码错误
 		    e.printStackTrace();
 		} catch (JSONException e) {
 		    // json解析错误
 		    e.printStackTrace();
 		}
 		return null;
 	}
 	
 
 	
 	/**
 	 * @author shywang
 	 * @title: 获取新闻列表里的数据
 	 * @param dictCatCode
 	 * @return
 	 * @throws ParseException 
	 * @throws java.io.IOException 
	 * @throws org.json.JSONException 
 	 */
 	@RequestMapping(value = "/sendFeigeSms")
 	@ResponseBody
 	public List<Map<String,Object>> sendFeigeSms(HttpServletRequest request,HttpServletResponse response) throws ParseException, org.json.JSONException, java.io.IOException{	
 	
 		try {
			  List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			   formparams.add(new BasicNameValuePair("Account","18256921878"));
			   formparams.add(new BasicNameValuePair("Pwd","eea6a95bde810430b82344ea6"));
			   formparams.add(new BasicNameValuePair("Content","您的验证码是1234"));
			   formparams.add(new BasicNameValuePair("Mobile","18256921878"));
			   formparams.add(new BasicNameValuePair("SignId","43300"));
			   Post(formparams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		return null;
 	}
 	
 	public static void Post( List<NameValuePair> formparams) throws Exception {
		CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();

	 	httpClient.start();
	 
	   HttpPost requestPost=new HttpPost(requestUrl);
	 
       requestPost.setEntity(new UrlEncodedFormEntity(formparams,"utf-8"));

	   httpClient.execute(requestPost, new FutureCallback<HttpResponse>() {
		
		public void failed(Exception arg0) {
			
			 System.out.println("Exception: " + arg0.getMessage());
		}
		
		public void completed(HttpResponse arg0) {
			  System.out.println("Response: " + arg0.getStatusLine());
		try {
			
			InputStream stram= arg0.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(stram));
			System.out.println(	reader.readLine());
			
		} catch (UnsupportedOperationException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

			
		}
		
		public void cancelled() {
			// TODO Auto-generated method stub
			
		}
	}).get();
	 
	 
	 
	  System.out.println("Done");
	 }
 	
}
