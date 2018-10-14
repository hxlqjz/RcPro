package com.kdgcsoft.power.controller.business.interact;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hp.hpl.sparta.ParseException;
import com.kdgcsoft.power.common.bean.JsonMsg;
import com.kdgcsoft.power.common.bean.Principal;
import com.kdgcsoft.power.common.util.StringUtil;
import com.kdgcsoft.power.controller.fw.base.BaseController;
import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.entity.business.interact.AvatarEntity;
import com.kdgcsoft.power.entity.business.interact.MsgLog;
import com.kdgcsoft.power.entity.business.interact.PortalComments;
import com.kdgcsoft.power.entity.business.interact.PortalCommentsForUser;
import com.kdgcsoft.power.entity.fw.system.SysCUser;
import com.kdgcsoft.power.service.business.interact.InteractService;
import com.kdgcsoft.power.service.business.interact.MsgLogService;
import com.kdgcsoft.power.service.business.interact.PortalCommentsService;
import com.kdgcsoft.power.service.fw.system.SysCUserService;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**   
 * @Title: Controller
 * @Description: 交流互动的后台代码
 * @date 2017-08-21
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/interact")
public class InteractController extends BaseController {
	@Autowired
	private BeetlSQLHelper bsh;
	@Autowired
	private InteractService interactService;
	@Autowired
	private PortalCommentsService portalCommentsService;
	@Autowired
    private SysCUserService sysCUserService;
	
	@Autowired
	private MsgLogService msgLogService;
	
 	/**
 	 * @author shywang
 	 * @title: 获取新闻列表里的数据
 	 * @param dictCatCode
 	 * @return
 	 * @throws ParseException 
 	 */
 	@RequestMapping(value = "/getImportantNewList")
 	@ResponseBody
 	public List<Map<String,Object>> getImportantNewList(HttpServletRequest request,HttpServletResponse response) throws ParseException{	
 		Principal principal = getEmpSession(); // 当前登陆人员信息
 		//Long userId=principal.getUserId();
 		Long ui=41587L;
 		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();		
		list=interactService.getImportantNewList(ui);
		response.addHeader("Access-Control-Allow-Origin", "*");
 		return list;
 	}
 	
 	/**
 	 * @author shywang
 	 * @title: 获取新闻列表里的明细数据
 	 * @param dictCatCode
 	 * @return
 	 * @throws ParseException 
 	 */
 	@RequestMapping(value = "/info_findPublishInfoById")
 	@ResponseBody
 	public Map<String,Object>  findPublishInfoById(HttpServletRequest request) throws ParseException{	
		String newsId = request.getParameter("newsId");
         Map<String,Object> map=new HashMap<String,Object>();		
         map=interactService.findPublishInfoById(newsId);	 		 
 		return map;
 	}
 	
 	/**
 	 * @author shywang
 	 * @title: 获取新闻明细里所有评论列表
 	 * @param dictCatCode
 	 * @return
 	 * @throws ParseException 
 	 * @throws IOException 
 	 */
 	@RequestMapping(value = "/getAllPortalCommentsByNewsId")
 	@ResponseBody
 	public List<Map<String,Object>>  getAllPortalCommentsByNewsId(HttpServletRequest request) throws ParseException, IOException{	
 		String newsId = request.getParameter("newsId");
		String empId = request.getParameter("empId");
		List<Map<String,Object>>maps=new ArrayList<Map<String,Object>>();
		maps=interactService.getAllPortalCommentsByNewsId(newsId,"46901");	
		for(Map<String,Object> map:maps){		 
         Object hpob=map.get("CREATEBY");
         Object ctob=map.get("COMMENTSTEXT");
         if(hpob!=null){
        	String headpic=getHeadPic(hpob.toString()); 
           map.put("headPic", (Object)headpic);
         } 
         if(ctob!=null){
        	 String text=ctob.toString();
        	 text=text.replace("[微笑]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t013c8163b2e3a79074.png\" alt=\"\"/>");
         	text=text.replace("[笑哭]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t01f70d1377e90c5ff7.png\" alt=\"\"/>");
         	text=text.replace("[得意地笑]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t010bdbad3411945a5e.png\" alt=\"\"/>");
         	text=text.replace("[心心眼]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t012e2c0f3b2f92e4cb.png\" alt=\"\"/>");
         	text=text.replace("[亲吻]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t010b310385748e358c.png\" alt=\"\"/>");
         	text=text.replace("[酷]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t01acb442b3dbc09920.png\" alt=\"\"/>");
         	text=text.replace("[天真]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t010ee885f1a8e37b59.png\" alt=\"\"/>");
         	text=text.replace("[脸红]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t01e85b7a669af8c900.png\" alt=\"\"/>");
         	text=text.replace("[吃惊]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t01a26c7f38db379c22.png\" alt=\"\"/>");
         	text=text.replace("[愤怒]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t017c371a977fa17bb0.png\" alt=\"\"/>");
         	text=text.replace("[哭泣]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t01e15c4daf22f81122.png\" alt=\"\"/>");
         	text=text.replace("[尖叫]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t01bc863d51761c6fb1.png\" alt=\"\"/>");
         	text=text.replace("[+1]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t01952da9fe852bd75a.png\" alt=\"\"/>");
         	text=text.replace("[肌肉]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t015ceaabb65d47ede6.png\" alt=\"\"/>");
         	text=text.replace("[祈祷]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t01866ace262aff3f72.png\" alt=\"\"/>");
         	text=text.replace("[幽灵]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t01ca096ef69b020627.png\" alt=\"\"/>");
		    map.put("COMMENTSTEXT", text);
		    
         }
         map.put("list",(Object)getCommentsList(map.get("COMMENTSID").toString()));
		}
 		return maps;
 	}
 	
 	private String getHeadPic(String userId) throws IOException {
	     InputStream is = null;
	 	Map<String,Object> para = new HashMap<String,Object>();	
		para.put("userId", userId);
	     Map<String,Object> map=bsh.getMap("business.interact.interactSql.findUserHeadpic", para);	
        StringBuffer sb = new StringBuffer();
	     if(map!=null){
	    	 Map row =map ;
          AvatarEntity p = new AvatarEntity();          
          if(StringUtil.isNotEmpty(row.get("HEADPORTRAIT"))){ 
        	  byte[] k=(byte[]) row.get("HEADPORTRAIT");       	
              InputStream sbs = new ByteArrayInputStream(k); 
				BufferedReader br = null;
				br = new BufferedReader(new InputStreamReader(sbs));
				String s = null;
				try {
					s = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
				    sb.append(s);
				    try {
						s = br.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}			
			}
	     }         			
		return sb.toString();
	}
 	 	
	private  List<Map<String,Object>>  getCommentsList(String commentsId) {
		Map<String,Object> para = new HashMap<String,Object>();	
		para.put("commentsId", commentsId);
		  List<Map<String,Object>> Maps=bsh.getMapList("business.interact.interactSql.getCommentsList", para);	
		List<PortalCommentsForUser> list1=new ArrayList<PortalCommentsForUser>();
		for(Map<String,Object> map:Maps){								
			if(StringUtil.isNotEmpty(map.get("COMMENTSTEXT"))){
				String text=map.get("COMMENTS_TEXT").toString();
				text=text.replace("[微笑]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t013c8163b2e3a79074.png\" alt=\"\"/>");
            	text=text.replace("[笑哭]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t01f70d1377e90c5ff7.png\" alt=\"\"/>");
            	text=text.replace("[得意地笑]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t010bdbad3411945a5e.png\" alt=\"\"/>");
            	text=text.replace("[心心眼]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t012e2c0f3b2f92e4cb.png\" alt=\"\"/>");
            	text=text.replace("[亲吻]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t010b310385748e358c.png\" alt=\"\"/>");
            	text=text.replace("[酷]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t01acb442b3dbc09920.png\" alt=\"\"/>");
            	text=text.replace("[天真]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t010ee885f1a8e37b59.png\" alt=\"\"/>");
            	text=text.replace("[脸红]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t01e85b7a669af8c900.png\" alt=\"\"/>");
            	text=text.replace("[吃惊]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t01a26c7f38db379c22.png\" alt=\"\"/>");
            	text=text.replace("[愤怒]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t017c371a977fa17bb0.png\" alt=\"\"/>");
            	text=text.replace("[哭泣]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t01e15c4daf22f81122.png\" alt=\"\"/>");
            	text=text.replace("[尖叫]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t01bc863d51761c6fb1.png\" alt=\"\"/>");
            	text=text.replace("[ 1]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t01952da9fe852bd75a.png\" alt=\"\"/>");
            	text=text.replace("[肌肉]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t015ceaabb65d47ede6.png\" alt=\"\"/>");
            	text=text.replace("[祈祷]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t01866ace262aff3f72.png\" alt=\"\"/>");
            	text=text.replace("[幽灵]","<img class=\"comment-emoji\" src=\"/src/main/webapp/static/images/pl_images/t01ca096ef69b020627.png\" alt=\"\"/>");
				map.put("commentsText", text);
			}			
		}
		return Maps;
	}
 	
	/**
	 * 添加评论
	 * @param request
	 * @param portalComments
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/addCommentsText")
	@ResponseBody
	public Object addCommentsText(HttpServletRequest request) throws UnsupportedEncodingException{
		PortalComments portalComments=new PortalComments();
		String newsId=request.getParameter("newsId");
		String contentText=request.getParameter("content_text_");
		if(StringUtil.isNotEmpty(contentText)){			
			contentText = java.net.URLDecoder.decode(contentText, "UTF-8");	
		}
		portalComments.setNewsId(Long.parseLong(newsId));
		portalComments.setIsDisplayName("N");

		//portalComments.setCreateBy(this.getLoginUser(request).getEmpId().toString());
		portalComments.setCreateBy("213294");
		portalComments.setCommentsText(contentText);
		portalComments.setCreateTime(new Date());
		portalCommentsService.addCommentsText(portalComments);
		JsonMsg jsonmsg = new JsonMsg(true,"success");	
		return jsonmsg;
	}
	
	/**
	 * 判断当前用户是否登录了
	 * @param request
	 * @param portalComments
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/findUserIshadLogin")
	@ResponseBody
	public JsonMsg findUserIshadLogin(HttpServletRequest request) throws UnsupportedEncodingException{
		Principal principal = getEmpSession();
		JsonMsg msg=new JsonMsg();
		Map<String,Object> data=new HashMap<String,Object>();
		if(principal!=null && principal.getUserId()!=null){
			 msg.setSuccess(true);
			 SysCUser sysCUser = sysCUserService.findSysCUser(principal.getUserId());
			 data.put("userCode", sysCUser.getUserCode());
			 data.put("userName", sysCUser.getUserName());
			 data.put("userTyp", sysCUser.getUserTyp());	
		}else{
			 msg.setSuccess(false);
		}
		msg.setData(data);
		return msg;
	}
	
	/**
	 * 认证当前用户真实信息
	 * @param request
	 * @param portalComments
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/authUserinfo")
	@ResponseBody
	public JsonMsg authUserinfo(HttpServletRequest request) throws UnsupportedEncodingException{
		Principal principal = getEmpSession();
		String userTyp=request.getParameter("userTyp");
		String rmk=request.getParameter("rmk");
		JsonMsg msg=new JsonMsg();
		Map<String,Object> data=new HashMap<String,Object>();
		if(principal!=null && principal.getUserId()!=null){
			 msg.setSuccess(true);
			 SysCUser sysCUser = sysCUserService.findSysCUser(principal.getUserId());
			String tel=sysCUser.getTel();
			String userCode=principal.getUserCode();
			if(userCode.equals(tel)){
				 sysCUser.setUserTyp(userTyp);
				 sysCUser.setRmk(rmk);
				 sysCUser.setUserName(userTyp);
				 sysCUserService.saveUser(sysCUser);
				 saveMsgLogInfo(userTyp);
				 msg.setMsg("认证成功！");
			}else{
				 msg.setMsg("手机号与登录账号不一致！");
				 msg.setSuccess(false);
			}				
		}else{
			 msg.setSuccess(false);
		}
		msg.setData(data);
		return msg;
	}
	
	private void saveMsgLogInfo(String userCode){
		 String msg=userCode+"用户您好！您的实名认证信息已审核通过，即刻起您可畅想更多实名认证服务！";
		 String type="1";//注册类型
		 MsgLog en=new MsgLog();
		 en.setCreateTime(new Date());
		 en.setIsUse("Y");
		 en.setUserCode(userCode);
		 en.setType(type);
		 en.setMsg(msg);
		 msgLogService.saveMsgLog(en);
		 
	};
 	
 	

}
