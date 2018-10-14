package com.kdgcsoft.power.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.kdgcsoft.power.common.bean.SystemConfig;


/**
 * 文件 上传工具类
 * @author lshua
 *
 */
public class FileUtil {

	
	
	/**
	 * 获取文件后缀名
	 * @param fileName
	 * @return
	 */
	public static String getSuffix(String fileName){
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	
	
	/**
	 * 获取字节数
	 * @param fileSizeStr
	 * @return
	 */
	public static long getFileSize(String fileSizeStr){
		if(StringUtil.isEmpty(fileSizeStr)){
			return 0;
		}
		long fileSizeVal = 0;
		int fileSize = StringUtil.str2Int(fileSizeStr.substring(0, fileSizeStr.length() - 1));
		String fileSizeUnit = fileSizeStr.substring(fileSizeStr.length() - 1);
		if("K".equalsIgnoreCase(fileSizeUnit)){
			fileSizeVal = fileSize * 1024L;
		}else if("M".equalsIgnoreCase(fileSizeUnit)){
			fileSizeVal = fileSize * 1024L * 1024L;
		}else if("G".equalsIgnoreCase(fileSizeUnit)){
			fileSizeVal = fileSize * 1024L * 1024L * 1024L;
		}
		
		return fileSizeVal;
	}
	
	
	
	/**
	 *上传文件返回字节数组 
	 *一般用于大字段上传,图片上传
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static byte[] getByteData(HttpServletRequest request) throws IOException{
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//判断request是否有文件上传
		if(multipartResolver.isMultipart(request)){ 
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			Iterator<String> ite = multiRequest.getFileNames();
			while(ite.hasNext()){
				MultipartFile file = multiRequest.getFile(ite.next());
				InputStream is =  file.getInputStream();
				byte[] data = new byte[(int) is.available()];
				while ((is.read(data)) > 0) {
				   is.read(data);
				}
				
				is.close();
				return data;
			}
		}
		return null;
	}
	
	/**
	 *上传文件返回输入流
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static InputStream getInputStream(HttpServletRequest request) throws IOException{
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//判断request是否有文件上传
		if(multipartResolver.isMultipart(request)){ 
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			Iterator<String> ite = multiRequest.getFileNames();
			while(ite.hasNext()){
				MultipartFile file = multiRequest.getFile(ite.next());
				InputStream is =  file.getInputStream();
				return is;
			}
		}
		return null;
	}
	
	
	/**
	 * 显示图片
	 * @param response
	 * @param data  字节数据
	 * @throws IOException 
	 */
	public static void showImage(HttpServletResponse response,byte[] data) throws IOException{
		if(data != null){
			response.setContentType("image/jpeg");
			OutputStream outs = response.getOutputStream();
			outs.write(data);
			outs.flush();
			outs.close();
		}
	}
	
	
	/**
	 * 功能 : 下载文件
	 * 开发：wwzhang 2017-8-14
	 * @param fileName 文件名称
	 * @param filePath 文件路径
	 * @param response
	 * @throws IOException
	 */
	public static void downLoadFile(String fileName, String filePath, HttpServletResponse response) throws IOException {
		fileName =  java.net.URLEncoder.encode(fileName, "UTF-8");
		fileName = fileName.replace("+", "%20");   //encode后替换  解决空格问题
		File file = new File(SystemConfig.UPLOAD_DIR_REAL + filePath);
		response.setContentType("MediaType.APPLICATION_OCTET_STREAM;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
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
	}
	
}
