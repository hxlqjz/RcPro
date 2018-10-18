package com.hxxdemo.weixinsaomalogin.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxxdemo.weixinsaomalogin.entity.MD5;

@Controller
@RequestMapping(value = "/smhb")
public class SmhbController {
	
	 //返回微信二维码，可供扫描登录
    @RequestMapping(value = "/sendHb")
    @ResponseBody
	public Map<String,String> sendHb(HttpServletRequest request, HttpServletResponse response) throws KeyManagementException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, DocumentException{
    	Map<String,String> map =new HashMap<String,String>();
    	  MD5 md=new MD5();
    	  String oi=request.getParameter("opendid");
    	  String opendid="od1hc1ezHucolmlIPfcFiMFvKDy8"; //openid
    	  String totalAmount="100";//红包金额，单位时候分
    	  String hbname="电池扫描返现";//红包名称
    	  String zfy="恭喜发财";
    	  map=  md.sendRedPack(opendid,totalAmount,hbname,zfy);
    	return map;
    } 

}
