package com.kdgcsoft.power.service.fw.base;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.kdgcsoft.power.common.bean.PageObject;
import com.kdgcsoft.power.common.bean.Principal;
import com.kdgcsoft.power.common.bean.SystemConfig;
import com.kdgcsoft.power.common.util.StringUtil;
import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.dao.fw.base.ComCAttDao;
import com.kdgcsoft.power.entity.fw.base.ComCAtt;

import net.sf.json.JSONArray;
import sun.misc.BASE64Encoder;

@Service("ComCAttService")
@Transactional
public class ComCAttService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComCAttService.class);

	@Autowired
	private BeetlSQLHelper bsh;
	@Autowired
	private ComCAttDao comCAttDao;

	public ComCAtt save(ComCAtt entity) {
		entity = comCAttDao.save(entity);
		return entity;
	}

	public ComCAtt findById(Long fillId) {
		return comCAttDao.findOne(fillId);
	}

	/**
	 * 删除数据
	 * 
	 * @param fillId
	 *            主键值
	 */
	public void delete(Long attId) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("attId", attId);
		bsh.updateOrDelete("fw.system.BaseSql.delAtt", para);
	}

	public PageObject<Map<String, Object>> getComCAttList(String attId, String tblName, String tblCol, String tblKey,
			PageRequest pageRequest) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("attId", attId);
		para.put("tblName", tblName);
		para.put("tblCol", tblCol);
		para.put("tblKey", tblKey);
		return bsh.serachPage("fw.system.BaseSql.select", para, pageRequest);
	}

	/**
	 * 查找附件信息列表
	 * 
	 * @param tblName
	 * @param tblKey
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> getComCAttMapInfoList(String tblName, String tblKey) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("tblName", tblName);
		para.put("tblKey", tblKey);
		return this.bsh.getMapList("fw.system.BaseSql.findByTblKeyAndTblName", para);
	}

	public Map<String, Object> findByTblKeyAndTblName(Long tblKey, String tblName) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("tblKey", tblKey);
		para.put("tblName", tblName);
		return bsh.getMap("fw.system.BaseSql.findByTblKeyAndTblName", para);
	}

	/**
	 * 保存附件
	 * 
	 * @param request
	 * @param entity
	 * @param varity
	 * @return
	 * @throws IOException
	 */
	public ComCAtt saveComCAttData(HttpServletRequest request, ComCAtt entity, String varity) throws IOException {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断request是否有文件上传
		String annexUrl = "";

		if (multipartResolver.isMultipart(request)) {
			String targetPath = SystemConfig.UPLOAD_DIR_REAL + varity + "/";
			String returnPath = "/";
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> ite = multiRequest.getFileNames();
			while (ite.hasNext()) {
				MultipartFile file = multiRequest.getFile(ite.next());
				String fileName = file.getOriginalFilename();
				String newFileName = new Date().getTime() + fileName;
				File localFile = new File(targetPath + newFileName);
				if (!localFile.exists()) {
					localFile.mkdirs();
				}
				returnPath += varity + "/" + newFileName.replace("%", "%25").replace("#", "%23").replace("&", "%26");
				if (!StringUtil.isEmpty(returnPath)) {
					annexUrl = returnPath;
				}
				try {
					file.transferTo(localFile); // 将上传文件写到服务器上指定的文件
				} catch (IllegalStateException e) {
					LOGGER.error("上传失败！", e);
				}
			}
		}

		Principal principal = (Principal) SecurityUtils.getSubject().getPrincipal();
		if (entity.getAttId() == null) {
			entity.setCreateBy(principal.getUserCode());
			entity.setCreateDate(new Date());
			entity.setFilePath(annexUrl);
		} else {
			entity.setFilePath(annexUrl);
		}
		entity.setModifyBy(principal.getUserCode());
		entity.setModifyDate(new Date());
		save(entity);
		return entity;
	}
	
	
	/**
	 * 保存附件
	 * 
	 * @param request
	 * @param entity
	 * @param varity
	 * @return
	 * @throws IOException
	 */
	public List<ComCAtt> saveComCAttDataList(HttpServletRequest request, ComCAtt entity, String varity) throws IOException {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断request是否有文件上传
		List<ComCAtt> attList = new ArrayList<ComCAtt>();
		if (multipartResolver.isMultipart(request)) {
			String targetPath = SystemConfig.UPLOAD_DIR_REAL + varity + "/";
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> ite = multiRequest.getFileNames();
			while (ite.hasNext()) {
				ComCAtt newEntity = new ComCAtt() ;
				String returnPath = "/";
				String annexUrl = "";
				MultipartFile file = multiRequest.getFile(ite.next());
				String fileName = file.getOriginalFilename();
				newEntity.setFileName(fileName);
				String newFileName = new Date().getTime() + fileName;
				File localFile = new File(targetPath + newFileName);
				if (!localFile.exists()) {
					localFile.mkdirs();
				}
				returnPath += varity + "/" + newFileName.replace("%", "%25").replace("#", "%23").replace("&", "%26");
				if (!StringUtil.isEmpty(returnPath)) {
					annexUrl = returnPath;
				}
				try {
					file.transferTo(localFile); // 将上传文件写到服务器上指定的文件
				} catch (IllegalStateException e) {
					LOGGER.error("上传失败！", e);
				}
				Principal principal = (Principal) SecurityUtils.getSubject().getPrincipal();
				if (entity.getAttId() == null) {
					newEntity.setCreateBy(principal.getUserCode());
					newEntity.setCreateDate(new Date());
					newEntity.setFilePath(annexUrl);
				} else {
					newEntity.setFilePath(annexUrl);
				}
				newEntity.setTblKey(entity.getTblKey());
				newEntity.setTblName(entity.getTblName());
				newEntity.setTblCol(entity.getTblCol());
				newEntity.setModifyBy(principal.getUserCode());
				newEntity.setModifyDate(new Date());
				newEntity = save(newEntity);
				attList.add(newEntity);
			}
		}

		return attList;
	}
	
	/**
	 * 上传所有离线图片上传
	 * 
	 * @param request
	 * @param entity
	 * @param varity
	 * @return
	 * @throws IOException
	 */
	public List<ComCAtt> saveMySnapComCAttDataList(HttpServletRequest request, ComCAtt entity, String varity,String tabKey) throws IOException {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断request是否有文件上传
		List<ComCAtt> attList = new ArrayList<ComCAtt>();
		if (multipartResolver.isMultipart(request)) {
			String targetPath = SystemConfig.UPLOAD_DIR_REAL + varity + "/";
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> ite = multiRequest.getFileNames();

			while (ite.hasNext()) {								
				ComCAtt newEntity = new ComCAtt() ;
				String returnPath = "/";
				String annexUrl = "";
				MultipartFile file = multiRequest.getFile(ite.next());
				String fileName = file.getOriginalFilename();
				newEntity.setFileName(fileName);
				String newFileName = new Date().getTime() + fileName;
				File localFile = new File(targetPath + newFileName);
				if (!localFile.exists()) {
					localFile.mkdirs();
				}
				returnPath += varity + "/" + newFileName.replace("%", "%25").replace("#", "%23").replace("&", "%26");
				if (!StringUtil.isEmpty(returnPath)) {
					annexUrl = returnPath;
				}
				try {
					file.transferTo(localFile); // 将上传文件写到服务器上指定的文件
				} catch (IllegalStateException e) {
					LOGGER.error("上传失败！", e);
				}
				Principal principal = (Principal) SecurityUtils.getSubject().getPrincipal();
				if (entity.getAttId() == null) {
					newEntity.setCreateBy(principal.getUserCode());
					newEntity.setCreateDate(new Date());
					newEntity.setFilePath(annexUrl);
				} else {
					newEntity.setFilePath(annexUrl);
				}
				newEntity.setTblKey(Long.parseLong(tabKey));
				newEntity.setTblName("my_snap");
				newEntity.setTblCol("");
				newEntity.setModifyBy(principal.getUserCode());
				newEntity.setModifyDate(new Date());
				newEntity = save(newEntity);
				attList.add(newEntity);
			}
		}
		return attList;
	}
	
	/**
	 * 查询责任人照片
	 * @return
	 */
	public List<Map<String,Object>> appSearchUserPhoto() {
		Map<String,Object> para = new HashMap<String,Object>();
		return bsh.getMapList("business.wems.EquCOpAttention.appSearchUserPhoto",para);
	}
	
}
