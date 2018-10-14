package com.kdgcsoft.power.controller.business.interact;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.kdgcsoft.power.common.bean.ConflictException;
import com.kdgcsoft.power.common.bean.JsonMsg;
import com.kdgcsoft.power.common.bean.PageModel;
import com.kdgcsoft.power.common.bean.PageObject;
import com.kdgcsoft.power.common.bean.SystemConstants;
import com.kdgcsoft.power.common.util.StringUtil;
import com.kdgcsoft.power.controller.fw.base.BaseController;
import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.entity.business.interact.CarSysNation;
import com.kdgcsoft.power.entity.fw.base.ComCAtt;
import com.kdgcsoft.power.service.business.interact.CarSysNationService;
import com.kdgcsoft.power.service.business.interact.PicContentService;
import com.xiaoleilu.hutool.lang.Console;
import com.xiaoleilu.hutool.util.CollectionUtil;

import net.coobird.thumbnailator.Thumbnails;
import sun.misc.BASE64Encoder;
/**   
 * @Title: Controller
 * @Description: 车型图片上传的后台代码
 * @date 2017-08-21
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/CarSysNation")
public class CarSysNationController extends BaseController {
	@Autowired
	private BeetlSQLHelper bsh;
	@Autowired
	private CarSysNationService carSysNationService;
	
	@Autowired
	private PicContentService picContentService;
	
	/**
	 * 分页查询数据,带分页和模糊搜索
	 */
	@RequestMapping(value="/searchPageMainList",method = RequestMethod.POST)
	@ResponseBody
	public PageModel searchPageMainList(HttpServletRequest request, Integer page, Integer rows){
		if(page==null){
			page=1;	
		}
		if(rows==null){
			rows=20;	
		}
		PageRequest pageRequest = new PageRequest(page , rows);
		PageObject<Map<String,Object>> result=new PageObject<Map<String,Object>> ();			
		List<Map<String,Object>> ls = carSysNationService.searchMainPageList(pageRequest);		
		for(Map<String,Object> map:ls){
			Object c=Tobase64( map.get("content"));			
		   map.put("content", c);		
		}
		
		List<Map<String,Object>>rs= CollectionUtil.sub(ls, (page-1)*rows, page*rows);
		result.setTotalCount((long)ls.size());	
		result.setList(rs);
		return getPageModel(result);
	}
	
	private Object Tobase64(Object imgData){
		  StringBuffer sb = new StringBuffer();
		  if(StringUtil.isNotEmpty(imgData)){ 
			  byte[] k=(byte[])  imgData;
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
		return  sb;
	};
	
	        // 新增，修改弹出框
			@RequestMapping(value = "/loadCarSysNation")
			public ModelAndView loadCarSysNation(String id, HttpServletRequest request){
				ModelAndView model = new ModelAndView();
				CarSysNation entity = new CarSysNation();
				String orgCode = null;
				String deptCode = null;
				if(!StringUtil.isEmpty(id)){//修改
					entity = carSysNationService.findCarSysNationDao(Long.parseLong(id));	
					String no=entity.getOrderNo()+"";
					if(StringUtils.isEmpty(no)){
						entity.setOrderNo(0);	
					}
				}
				model.addObject("orgCode", orgCode);
				model.addObject("deptCode", deptCode);
				model.addObject("entity", entity);
				model.setViewName("view/business/backStageForm");
				return model;
			}
			
			
			/**
			 * 保存主页面的数据
			 * @throws ConflictException 
			 * @throws IOException 
			 * @throws Exception 
			 */
			@RequestMapping(value = "/savePageMain")
			@ResponseBody
			public JsonMsg saveSysCUser(HttpServletRequest request,CarSysNation entity) throws ConflictException, IOException{
				Map<String,String[]> map = request.getParameterMap();
				String imgStr=request.getParameter("imgStr");
				int hight=70;
		 		int width=70;
		 		if(entity.getWidth()>0){
		 			width=entity.getWidth();
		 		}
		 		if(entity.getHeight()>0){
		 			hight=entity.getHeight();
		 		}
			 Object picData= savePic(request,hight,width);
		     if(picData.equals("")){
		    	 picData=imgStr;
		     }
			    entity.setContent(picData.toString());
				entity= carSysNationService.saveMainPage(entity);
				Map<String,Object> maps=new HashMap<String,Object>();
				maps.put("sysId", entity.getSysId());
				return new JsonMsg(true,SystemConstants.MSG_SAVE_SUCCESS,maps);
			}
			
			
			/**
			 * 保存附件
			 * 
			 * @throws IOException
			 * @throws FileUploadException
			 */
			@RequestMapping(value = "savePicContentData")
			@ResponseBody
			public JsonMsg savePicContentData(HttpServletRequest request, ComCAtt entity,String varity)
					throws IOException, FileUploadException {
				String tabName="car_sys_nation";
				String tabKey=entity.getTblKey().toString();
				int hight=160;
		 		int width=160;
			    picContentService.saveLogoPic(request, tabName, tabKey,hight,width);
				Map<String,Object> maps=new HashMap<String,Object>();
				maps.put("pageId", "2");
				JsonMsg msg=new JsonMsg(true, "保存成功！",maps);
				return msg;
			}
			
			public Object savePic(HttpServletRequest request,int hight,int width) throws IOException{
				 String img="";
				CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
						request.getSession().getServletContext());
				if (multipartResolver.isMultipart(request)) {
					MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
					Iterator<String> ite = multiRequest.getFileNames();
					while (ite.hasNext()) {											
						MultipartFile file = multiRequest.getFile(ite.next());
						if(file.getSize()>0){
							InputStream in=file.getInputStream();									
							ByteArrayOutputStream baos=new ByteArrayOutputStream();
						    String oriFname=file.getOriginalFilename();
						    String ctype=file.getContentType();
							String imgSufix = oriFname.substring(oriFname.lastIndexOf(".") + 1);
							if ("JPG".equals(imgSufix.toUpperCase())
									|| "GIF".equals(imgSufix.toUpperCase())
									|| "JPEG".equals(imgSufix.toUpperCase())
									|| "PNG".equals(imgSufix.toUpperCase())
									|| "BMP".equals(imgSufix.toUpperCase())) {
								//图片的缩小的大小为1920*1080
								Thumbnails.of(in).size(hight, width).toOutputStream(baos);				
							}
				            byte[] bytes = baos.toByteArray();
				            BASE64Encoder encoder = new BASE64Encoder();
				              img="data:"+ctype+";base64,";
						      img+=encoder.encodeBuffer(bytes).trim();
						}
									
					}
				}
				return img;
			}
			

			/**
			 * 删除记录
			 */
			@RequestMapping(value="/deleCarSysNation",method = RequestMethod.POST)
			@ResponseBody
			public JsonMsg deleCarSysNation(Long id){
				if(id != null){
					CarSysNation en=	carSysNationService.findCarSysNationDao(id);
					carSysNationService.delete(en);
				}			
				Map<String,Object> maps=new HashMap<String,Object>();
				JsonMsg msg=new JsonMsg(true, "保存成功！",maps);
				return msg;			
			};
			
			/**
			 * 查找所有品牌的数据记录
			 */
			@RequestMapping(value="/findAllCarSysNation",method = RequestMethod.POST)
			@ResponseBody
			public JsonMsg findAllCarSysNation(){
				List<Map<String, Object>> ls=carSysNationService.findAllCarSysNation();	
				Map<String,Object> maps=new HashMap<String,Object>();
				List<Map<String, Object>> comLs=new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> brands=new ArrayList<Map<String, Object>>();
				
				List<Map<String, Object>> bLs=new ArrayList<Map<String, Object>>();		
				Map<String, Object> bMap=new HashMap<String, Object>();//每个品牌对象					
				for(int i=0;i<ls.size();i++){					
					Map<String, Object> en=ls.get(i);
					Object c=Tobase64( en.get("content"));			
					   en.put("content", c);		
					if(en.get("alphabet").equals("#")){
						comLs.add(en);
					}else{		
						if(!bMap.containsKey("brandType")){
							bMap.put("brandType", en.get("alphabet"));								
						}												
						if(bMap.get("brandType").equals(en.get("alphabet"))){ //如果是同一个字母							
							bLs.add(en);
							bMap.put("brandLs", bLs);
						}else{
							 brands.add(bMap);//当字母不等于的时候，说明第一个字母类型，数据已经迭代完了
							 bMap=new HashMap<String, Object>();//每个品牌对象	
							 bLs=new ArrayList<Map<String, Object>>();	
							 bLs.add(en);
						     bMap.put("brandLs", bLs);
							 bMap.put("brandType", en.get("alphabet"));	
						}
						if(i==ls.size()-1){
							 brands.add(bMap);//把最后一个品牌数据给brands
						}
					}
				}
			//	Console.log(brands);
				maps.put("brands", brands);
				maps.put("comLs", comLs);
				JsonMsg msg=new JsonMsg(true, "保存成功！",maps);
				return msg;			
			};
			
			

}
