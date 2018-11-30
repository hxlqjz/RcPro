package com.kdgcsoft.power.controller.business;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.xpath.DefaultXPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.InputSource;

import com.baidu.aip.ocr.AipOcr;
import com.hp.hpl.sparta.ParseException;
import com.kdgcsoft.power.common.bean.JsonMsg;
import com.kdgcsoft.power.controller.fw.base.BaseController;
import com.kdgcsoft.power.service.business.interact.CarStyleService;

import net.sf.ehcache.CacheManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;  
/**   
 * @TitlC: Controller
 * @Description: 通用交流的后台代码
 * @date 2018年5月25日10:40:17
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/vinCode")
public class VinCodeController extends BaseController{
	   private static CacheManager cacheManager= CacheManager.create();  
	    static String       cacheName = "imgStr";  
	
		@Autowired
		private CarStyleService carStyleService;
 	/**
 	 * @author shywang
 	 * @titlC: 根据vin码查询车型数据
 	 * @param dictCatCode
 	 * @return
 	 * @throws ParseException 
 	 */
 	@RequestMapping(value = "/findVehicleInfoByVinCode")
 	@ResponseBody
    public  JsonMsg findVehicleInfoByVinCode(String  vinCode){
 		JsonMsg msgs=new JsonMsg();
 		Map data=new HashMap();
 		//String vinCode=request.getParameter("vinCode");		
		String xmlInput = "<root><appkey>cac3a8c229c48a24</appkey><appsecret>c38446546e844c0ca77d9255a777c95e</appsecret><method>level.vehicle.vin.get</method><requestformat>json</requestformat><vin>"+vinCode+"</vin></root>";// 传参是XML格式的字符串，xmlInput中的特殊字符需要转义，比如<>
		xmlInput = xmlInput.replace("<", "&lt;");
		xmlInput = xmlInput.replace(">", "&gt;");
		String soapRequestData = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"
				+ "<soap12:Body>"
				+ " <LevelData xmlns=\"http://www.dat881.com/\">" 
				+  "<xmlInput>" + xmlInput + "</xmlInput>" + "</LevelData>" // 接口传入参数
				+ "  </soap12:Body>" + "</soap12:Envelope>";
				    
				PostMethod postMethod = null;
				postMethod = new PostMethod("http://service.vin114.net/req"); // 接口地址
			    				    
				byte[] b = null;
				try {
				b = soapRequestData.getBytes("utf-8");
				} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				}
				InputStream is = new ByteArrayInputStream(b, 0, b.length);
				RequestEntity re = new InputStreamRequestEntity(is, b.length,
				"application/soap+xml; charset=utf-8");
				postMethod.setRequestEntity(re);
				    
				HttpClient httpClient = new HttpClient();
				try {
				httpClient.executeMethod(postMethod);
				soapRequestData = postMethod.getResponseBodyAsString(); // 获取接口的全部返回内容
				System.out.println(soapRequestData);
			    InputSource source = new InputSource(new StringReader(soapRequestData));
			       SAXReader reader = new SAXReader();
			       Document  doc = reader.read(source); 
			       DefaultXPath xpath = new DefaultXPath("/soap:Envelope/soap:Body");  
			       List list = xpath.selectNodes(doc);  
			       Iterator iterator2 = list.iterator();    
			       JSONObject ob=new JSONObject();
			       int i = 1;  
			       while (iterator2.hasNext()) {    
			           Element node = (Element) iterator2.next();  
			           String content= node.getStringValue();
			           ob=JSONObject.fromObject(content);
			       }
			       JSONArray rec=new JSONArray();
			       JSONObject info=new JSONObject();
			       JSONObject AddJson=new JSONObject();
			       if(ob!=null && ob.get("Result")!=null){
			    	    rec=(JSONArray) ob.get("Result"); 
			    	    data.put("result", rec);
			       }
			       if(ob!=null && ob.get("Info")!=null){
			    	    info=(JSONObject) ob.get("Info");
			       }
			       if(ob!=null && ob.get("Additional")!=null){
			    	   AddJson=(JSONObject) ob.get("Additional");
			    	   data.put("Vin", AddJson.get("Vin"));
			    	   data.put("VinYear", AddJson.get("VinYear"));
			       }
			       if(info.get("Success").toString().equals("true")){
			    	  Object o=rec.get(0);		
			       }else{
			    
			    	   System.out.println("err..");
			       }
			       

				} catch (Exception e) {
				       System.out.println("errTack"+e);
				}
				msgs.setData(data);
 		return msgs;
 	}
 	
 	/**
 	 * @author shywang
 	 * @titlC: 根据vin码查询车型数据
 	 * @param dictCatCode
 	 * @return
 	 * @throws ParseException 
 	 */
 	@RequestMapping(value = "/findCarStyleList")
 	@ResponseBody
 	public Object findCarStyleList(HttpServletRequest request){
 		String vinCode=request.getParameter("vinCode");
 		JsonMsg msg=findVehicleInfoByVinCode(vinCode);
 		Map data=(Map) msg.getData();	
 		JSONArray arr=(JSONArray) data.get("result");
 		int size=arr.size();
 		String[] ids=new String[size];
 		Date cd=new Date();
 		String date=DateUtils.formatDate(cd, "yyyy-MM-dd");
 		for(int i=0;i<size;i++){
 			JSONObject ob=arr.getJSONObject(i);
 			ids[i]=ob.get("LevelId").toString();
 			String shiftMemo=ob.get("TransmissionModel").toString();
 			if(!StringUtils.isEmpty(shiftMemo)){
 				List<Map<String,Object>> ls=carStyleService.findVinRecordList(ids[i]);
 				if(ls.size()==0){
 					carStyleService.insertVinRecordList(ids[i],shiftMemo,vinCode,date);
 				}
 				carStyleService.updateCarStyledata(ids[i],shiftMemo);
 			} 			
 		}
 		
 		List<Map<String,Object>> ls=carStyleService.queryCarGoodByids(ids);
 		JsonMsg msgs=new JsonMsg();
 		msgs.setData(ls);
 		return msgs;
 	};
 	
 	
	/**
 	 * @author shywang
 	 * @titlC: 根据lyId查询车型数据
 	 * @param dictCatCode
 	 * @return
 	 * @throws ParseException 
 	 */
 	@RequestMapping(value = "/findCarStyleListByLyId")
 	@ResponseBody
 	public Object findCarStyleListByLyId(HttpServletRequest request){
 		String lyIds=request.getParameter("lyIds");
 		String[] ids=lyIds.split(",");	
 		List<Map<String,Object>> ls=carStyleService.queryCarGoodByids(ids);
 		JsonMsg msgs=new JsonMsg();
 		msgs.setData(ls);
 		return msgs;
 	};
 	
 	
 	/**
 	 * @author shywang
 	 * @titlC: 根据图片扫描的vin码查询车型数据
 	 * @param dictCatCode
 	 * @return
 	 * @throws IOException 
 	 * @throws ParseException 
 	 */
 	@RequestMapping(value = "/findVehicleInfoByImgData")
 	@ResponseBody
    public  Object findVehicleInfoByImgData(HttpServletRequest request,HttpServletResponse response) throws IOException{
 		String vinCode="";
 		String imgData=""; 
 		String imgStr=request.getParameter("textT2");
 		System.out.println("imgStr=="+imgStr);
 		String str = imgStr.substring(imgStr.indexOf(",")+1);
 		 imgData=imgStr;
 		// GenerateImage(str, "D:/PAS/"+System.currentTimeMillis()+".jpg");
 		 System.out.println("imgdata=="+imgData);
 		GenerateImage(str, "C:/PAS/v1.jpg");
 		String xmlInput = "<root><appkey>cac3a8c229c48a24</appkey><appsecret>c38446546e844c0ca77d9255a777c95e</appsecret><method>level.vehicle.vin.ocr</method><requestformat>json</requestformat><imgbase64>"+imgData+"</imgbase64></root>";// 传参是XML格式的字符串，xmlInput中的特殊字符需要转义，比如<>
 		xmlInput = xmlInput.replace("<", "&lt;");
 		xmlInput = xmlInput.replace(">", "&gt;");
 		    
 		String soapRequestData = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
 		+ "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"
 		+ "<soap12:Body>"
 		+ " <LevelData xmlns=\"http://www.dat881.com/\">" 
 		+  "<xmlInput>" + xmlInput + "</xmlInput>" + "</LevelData>" // 接口传入参数
 		+ "  </soap12:Body>" + "</soap12:Envelope>";
 		    
 		PostMethod postMethod = null;
 		postMethod = new PostMethod("http://service.vin114.net/req"); // 接口地址
 		System.out.println(soapRequestData);   
 		byte[] b = null;
 		try {
 		b = soapRequestData.getBytes("utf-8");
 		} catch (UnsupportedEncodingException e) {
 		e.printStackTrace();
 		}
 		InputStream is = new ByteArrayInputStream(b, 0, b.length);
 		RequestEntity re = new InputStreamRequestEntity(is, b.length,
 		"application/soap+xml; charset=utf-8");
 		postMethod.setRequestEntity(re);
 		    
 		HttpClient httpClient = new HttpClient();
 		try {
 		httpClient.executeMethod(postMethod);
 		soapRequestData = postMethod.getResponseBodyAsString(); // 获取接口的全部返回内容
 	   System.out.println(soapRequestData);
  	    InputSource source = new InputSource(new StringReader(soapRequestData));
       SAXReader reader = new SAXReader();
       Document  doc = reader.read(source); 
       DefaultXPath xpath = new DefaultXPath("/soap:Envelope/soap:Body");  
    //   xpath.setNamespaceURIs(Collections.singletonMap("LevelDataResponse","http://www.dat881.com/")); 
       List list = xpath.selectNodes(doc);  
       Iterator iterator2 = list.iterator();    
       JSONObject ob=new JSONObject();
       int i = 1;  
       while (iterator2.hasNext()) {    
           Element node = (Element) iterator2.next();  
           String content= node.getStringValue();
           ob=JSONObject.fromObject(content);
       }
       JSONArray rec=new JSONArray();
       JSONObject info=new JSONObject();
       if(ob!=null && ob.get("Result")!=null){
    	    rec=(JSONArray) ob.get("Result");    	   
       }
       if(ob!=null && ob.get("Info")!=null){
    	    info=(JSONObject) ob.get("Info");
       }
       if(info.get("Success").toString().equals("true")){
    	   JSONObject o= rec.getJSONObject(0);
    	   vinCode=o.get("VinCode").toString();
    	   System.out.println("正常:"+o.get("VinCode"));
       }else{
    	  // String s=comOCRInfo().toString();
    	//   vinCode=s;
    	//   System.out.println("err.."+s);
       }
 		} catch (Exception e) { 		    
 		}
 		JsonMsg msgs=new JsonMsg();
 		msgs.setData(vinCode);
 	//  Object rec= findVehicleInfoByVinCode(vinCode);
 		return msgs;
 	}
 	
 	  //base64字符串转化成图片  
    public static boolean GenerateImage(String imgStr,String imageName)  
    {   
    	 File file = new File("C:\\PAS\\");
    		if (!file.exists() && !file.isDirectory()) {
    			file.mkdir();
    		}
    	//对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null) //图像数据为空  
            return false;  
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            String imgFilePath = imageName;//新生成的图片  
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        }   
        catch (Exception e)   
        {  
            return false;  
        }  
    }  

 	
    public String comOCRInfo(){
    	String ret="";
    	//设置APPID/AK/SK
        String APP_ID = "11260587";
    	 String API_KEY = "w8ZcnuLCLeoXpjVnL70Q4NDP";
    	 String SECRET_KEY = "GhRAsVMi6ygn7dQzcznT5b5faHg31E60";
    	
    	// 初始化一个OcrClient
    	AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
    	// 可�?�：设置网络连接参数
    	client .setConnectionTimeoutInMillis(2000);
    	client .setSocketTimeoutInMillis(60000);
    	// 调用身份证识别接�?
    	String idFilePath = "C:\\PAS\\v1.jpg";
    	String idFilePath1 = "C:\\PAS\\test1.png";
    	 // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");
        
    	// 调用接口
        String path = "C:\\PAS\\v1.jpg";
        org.json.JSONObject res = client.basicGeneral(path, options);
       Object ob=res.get("words_result");
       org.json.JSONObject word=new  org.json.JSONObject();
       if(ob!=null){
    	   org.json.JSONArray sult= (org.json.JSONArray) ob;
    	   word=( org.json.JSONObject) sult.get(0);   
    	   ret=word.get("words").toString();
    	   
       }
        System.out.println(word.toString());
        return ret;
    
    }
    
	/**
 	 * @author shywang
 	 * @title: 查找图片数据
 	 * @param dictCatCode
 	 * @return
 	 * @throws IOException 
 	 * @throws ParseException 
 	 */
 	@RequestMapping(value = "/findThePic")
 	@ResponseBody
    public void findThePic(HttpServletResponse response) throws Exception{
		File file = new File("C:\\PAS\\v1.jpg");
		response.setContentType("MediaType.APPLICATION_OCTET_STREAM;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=vinCode");
        InputStream in = null;
		ServletOutputStream servletOutputStream = null;
        try {
        	in = new FileInputStream(file);
 			servletOutputStream = response.getOutputStream();
 			final int size = 1024;
 			byte[] buffer = new byte[size];
 			int length;
 			while ((length = in.read(buffer)) > 0) {
 				servletOutputStream.write(buffer, 0, length);
 			}
 		} finally {
 			try {
 				if(servletOutputStream != null) {
 					servletOutputStream.flush();
 	 				servletOutputStream.close();
 				}
 			} finally {
 				if(in != null) {
 					in.close();
 				}
 			}
 		}
    	
    };
 	
}
