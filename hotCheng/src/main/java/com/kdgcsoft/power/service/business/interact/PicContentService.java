package com.kdgcsoft.power.service.business.interact;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.dao.interact.PicContentDao;
import com.kdgcsoft.power.entity.business.interact.PicContent;
import com.kdgcsoft.power.service.fw.base.BaseService;

import net.coobird.thumbnailator.Thumbnails;
import sun.misc.BASE64Encoder;

/**   
 * @Title: Service
 * @Description: 图片保存成字符串
 * @date 2017-07-08
 * @version V1.0   
 *
 */
@Component
@Transactional
public class PicContentService extends BaseService{
	
	@Autowired
	private BeetlSQLHelper bsh;
	@Autowired
	private PicContentDao picDao;
	
	public Object saveMySanpPic(HttpServletRequest request,String tabName,String tabKey,int hight,int width) throws IOException{
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> ite = multiRequest.getFileNames();
			while (ite.hasNext()) {		
				PicContent en=new PicContent();
				en.setCreateTime(new Date());
				en.setIsUse("Y");
				en.setTableName(tabName);
				en.setTabKey(tabKey);
				MultipartFile file = multiRequest.getFile(ite.next());
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
	            String img="data:"+ctype+";base64,";
			      img+=encoder.encodeBuffer(bytes).trim();
			     en.setContent(img);
			     savepic(en);
			}
		}
		return null;
	};
	
	
	public Object saveLogoPic(HttpServletRequest request,String tabName,String tabKey,int hight,int width) throws IOException{
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> ite = multiRequest.getFileNames();
			while (ite.hasNext()) {		
				PicContent en=new PicContent();
			    List<PicContent> pics=findByTableNameAndTabKey(tabName,tabKey);
			    if(pics.size()>0){
			    	en=pics.get(0);
			    }else{
			    	en.setCreateTime(new Date());
					en.setIsUse("Y");
					en.setTableName(tabName);
					en.setTabKey(tabKey);	
			    }
			
				MultipartFile file = multiRequest.getFile(ite.next());
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
	            String img="data:"+ctype+";base64,";
			      img+=encoder.encodeBuffer(bytes).trim();
			     en.setContent(img);
			     savepic(en);
			}
		}
		return null;
	};
	
	public PicContent savepic(PicContent e){
		e = picDao.save(e);
		return e;
	}
	
	public List<PicContent> findByTableNameAndTabKey(String tableName,String tabKey){		
		return picDao.findByTableNameAndTabKey(tableName, tabKey);
	}
	
	public  List<Map<String,Object>> findPicList(String tableName,String tabKey){
		Map<String,Object> para = new HashMap<String,Object>();	
		para.put("tableName", tableName);
		para.put("tabKey", tabKey);
	    List<Map<String,Object>> maps=bsh.getMapList("business.interact.picContentSql.findPicListBytabNameAndKey", para);	
	    return maps;
	}

}
