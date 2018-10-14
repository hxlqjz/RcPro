package com.hxxdemo.weixinsaomalogin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hxxdemo.weixinsaomalogin.util.SNSUserInfo;
import com.hxxdemo.weixinsaomalogin.util.WeiXinUtil;
import com.hxxdemo.weixinsaomalogin.util.WeixinOauth2Token;
import com.kdgcsoft.power.entity.business.interact.RpUser;
import com.kdgcsoft.power.service.business.interact.RpUserService;
import com.hxxdemo.weixinsaomalogin.util.JsSignUtil;

@Controller
@RequestMapping(value = "/index")
public class loginController{
	@Autowired
	private RpUserService rpUserService;
	
    //返回微信二维码，可供扫描登录
    @RequestMapping(value = "/weixin")
    @ResponseBody
    public Map<String,Object> weixin(HttpServletRequest request) throws IOException {
        Map<String,Object> map = new HashMap<String,Object>();
        WeiXinUtil wxU = new WeiXinUtil();
        //http://www.shike.com.cn/index/WeiXinTest是手机用户扫码后会执行的后台方法
        //www.shike.com.cn此域名要与微信公共平台配置的一致
        String url="https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=wx8666da66ca071393&" +
                "redirect_uri=http://lancev.com/hotCheng/index/WeiXinText.do&" +
                "response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
        //将url转换成短链接，提高扫码速度跟成功率
        String shorturl=wxU.shortURL(url, wxU.appid, wxU.appSecret);
        map.put("short_url", shorturl);
        return map;
    }

    int type = 0;
    String type2 = "";
    //判断用户是否扫码登录成功，以便于前台页面跳转
    @RequestMapping(value = "/successDL")
    public Map<String,Object> successDL(String a,HttpServletRequest req){
        Map<String,Object> map = new HashMap<String,Object>();
        try
        {
            synchronized (this) {
                map.put("type", type);
                type=0;
            }
            Thread.sleep(500);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        return map;
    }

    @RequestMapping(value = "/type")
    public void type(int a){
        type=a;
    }

    //微信获取用户信息
    @RequestMapping(value = "/WeiXinTest")
    public ModelAndView WeiXinTest(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws IOException{
        ModelAndView mav = new ModelAndView();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String code = request.getParameter("code");
        // 通过code获取access_token
        WeixinOauth2Token oauth2Token =WeiXinUtil.getOauth2AccessToken(WeiXinUtil.appid, WeiXinUtil.appSecret, code);
        System.out.println(oauth2Token.toString());
        String accessToken=oauth2Token.getAccessToken();
        String openId=oauth2Token.getOpenId();
        //获取到用户的基本信息
        SNSUserInfo snsUserInfo=null;
       /* if(type==0){
            snsUserInfo = WeiXinUtil.getSNSUserInfo(accessToken, openId);
        }*/
        snsUserInfo = WeiXinUtil.getSNSUserInfo(accessToken, openId);
        if(snsUserInfo!=null){
            //证明获取到了微信用户基本信息
            type=1;
            String id = snsUserInfo.getOpenId();
            type2=id;//可用作用户id，此值是微信用户的唯一识别
            System.out.println(" ----- this is weixintest---"); 
            System.out.println("user openId--->"+snsUserInfo.getOpenId());   
            System.out.println("user nickname--->"+snsUserInfo.getNickname());   
            System.out.println("user sex--->"+snsUserInfo.getSex());              
            RpUser en=rpUserService.getInfoByWechat(snsUserInfo.getOpenId());
            //给手机端返回页面，成功，此处无法给pc端进行页面跳转
            mav.addObject("openId", snsUserInfo.getOpenId());
            mav.addObject("nickname", snsUserInfo.getNickname());  
            if(en==null){
            	  mav.setViewName("view/wechat/login1.jsp");	
            }else if(en.getIsBlack().equals(0L)){
            	 mav.setViewName("redirect:/view/wechat/chaxun.html?openId="+snsUserInfo.getOpenId()); 
            }else{
            	 mav.setViewName("view/wechat/login3.html");
            }
  
        }else{
            //给手机端返回页面，失败，此处无法给pc端进行页面跳转
        	 mav.setViewName("view/wechat/login3.html");
        }
        return mav;
    }
    
    //微信获取用户信息
    @RequestMapping(value = "/WeiXinLogin")
    @ResponseBody
    public Map<String, Object> WeiXinLogin(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String, Object> result = new HashMap<String, Object>();
      /*  request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");*/
        String code = request.getParameter("code");
        // 通过code获取access_token
        WeixinOauth2Token oauth2Token =WeiXinUtil.getOauth2AccessToken(WeiXinUtil.appid, WeiXinUtil.appSecret, code);
        System.out.println(oauth2Token.toString());
        String accessToken=oauth2Token.getAccessToken();
        String openId=oauth2Token.getOpenId();
        //获取到用户的基本信息
        SNSUserInfo snsUserInfo=null;
        snsUserInfo = WeiXinUtil.getSNSUserInfo(accessToken, openId);
        if(snsUserInfo!=null){
            //证明获取到了微信用户基本信息
            type=1;
            String id = snsUserInfo.getOpenId();
            type2=id;//可用作用户id，此值是微信用户的唯一识别
            System.out.println("this is  weixinlogin -----"); 
            System.out.println("user openId--->"+snsUserInfo.getOpenId());   
            System.out.println("user nickname--->"+snsUserInfo.getNickname());   
            System.out.println("user sex--->"+snsUserInfo.getSex());              
            RpUser en=rpUserService.getInfoByWechat(snsUserInfo.getOpenId());
            //给手机端返回页面，成功，此处无法给pc端进行页面跳转
            result.put("openId", snsUserInfo.getOpenId());
            result.put("nickName", snsUserInfo.getNickname());
            Long isBlack = 0L;
            if(en == null){
            	isBlack = 2L;
            }else{
            	isBlack = en.getIsBlack();
            }
            result.put("isBlack", isBlack);
            //如为2则去login1界面，0为查询页面，1为login3联系管理员
        }else{
        	result.put("isBlack", 1L);
        }
        return result;
    }
    @RequestMapping(value = "/getSignature")
    @ResponseBody
    public Map<String,String> getSignature(String url){
    	return JsSignUtil.sign(url);  	
    }



}
