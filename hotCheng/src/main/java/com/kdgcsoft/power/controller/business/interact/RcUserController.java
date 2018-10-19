package com.kdgcsoft.power.controller.business.interact;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.CacheManager;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kdgcsoft.power.common.bean.ConflictException;
import com.kdgcsoft.power.common.bean.JsonMsg;
import com.kdgcsoft.power.common.bean.PageObject;
import com.kdgcsoft.power.common.bean.SystemConstants;
import com.kdgcsoft.power.common.util.StringUtil;
import com.kdgcsoft.power.controller.fw.base.BaseController;
import com.kdgcsoft.power.entity.business.interact.RpUser;
import com.kdgcsoft.power.service.business.interact.RpRegService;
import com.kdgcsoft.power.service.business.interact.RpStoreService;
import com.kdgcsoft.power.service.business.interact.RpUserService;
import com.xiaoleilu.hutool.util.CollectionUtil;

/**
 * PC端使用的接口
 * 
 * @author lenovo
 * 
 */
@Controller
@RequestMapping("/pcUser")
public class RcUserController extends BaseController {
	private static CacheManager cacheManager = CacheManager.create();

	@Autowired
	private RpUserService rpUserService;
	@Autowired
	private SQLManager sqlManager;

	@Autowired
	private RpStoreService rpStoreService;
	@Autowired
	private RpRegService rpRegService;

	/**
	 * 查询用户信息
	 * 
	 * @param request
	 * @param name模糊匹配用户名和微信号
	 *            idCode识别码
	 * @return
	 */
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	@ResponseBody
	public Object getList(HttpServletRequest request, Integer page, Integer rows) {
		String name = request.getParameter("name");
		String idCode = request.getParameter("idCode");
		PageRequest pageRequest = new PageRequest(page, rows);
		PageObject<Map<String, Object>> result = new PageObject<Map<String, Object>>();

		if (StringUtil.isEmpty(name)) {
			name = null;
		}
		if (StringUtil.isEmpty(idCode)) {
			idCode = null;
		}
		List<Map<String, Object>> ls = rpUserService.getList(name, idCode,
				pageRequest);
		List<Map<String, Object>> rs = CollectionUtil.sub(ls,
				(page - 1) * rows, page * rows);
		result.setTotalCount((long) ls.size());
		result.setList(rs);
		return getPageModel(result);
	};

	/**
	 * 根据id查找用户信息
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getInfo")
	@ResponseBody
	public Object getInfo(HttpServletRequest request) {
		String id = request.getParameter("id");
		RpUser user = rpUserService.getInfo(Long.parseLong(id));
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("res", user);
		return new JsonMsg(true, SystemConstants.MSG_SUCCESS, maps);
	};

	/**
	 * 删除信息
	 * 
	 * @param request
	 * @param id
	 *            主键
	 * @return
	 * @throws ConflictException
	 * @throws IOException
	 */
	@RequestMapping(value = "/del")
	@ResponseBody
	public JsonMsg saveSysCUser(HttpServletRequest request)
			throws ConflictException, IOException {
		String id = request.getParameter("id");
		rpUserService.delete(Long.parseLong(id));
		return new JsonMsg(true, SystemConstants.MSG_DELETE_SUCCESS, null);
	}

	/**
	 * 新增或修改
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public JsonMsg save(HttpServletRequest request, RpUser entity) {
		Long id = entity.getId();
		if (id != null) {
			rpUserService.update(entity);
		} else {
			rpUserService.insert(entity);
		}

		return new JsonMsg(true, SystemConstants.MSG_SAVE_SUCCESS, null);
	}

	/**
	 * 打开新增或修改页面
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/loadInfo")
	public ModelAndView loadInfo(String id, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		RpUser entity = new RpUser();
		String type = "add";
		if (!StringUtil.isEmpty(id)) {// 修改
			type = "edit";
			entity = rpUserService.getInfo(Long.parseLong(id));
		}
		model.addObject("entity", entity);
		model.addObject("type", type);
		model.setViewName("view/business/rpUserForm.jsp");
		return model;
	}
	
	/**
	 * 导出数据
	 * 
	 * @param request
	 */
	@RequestMapping("/export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		String param = request.getParameter("param");

		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map = gson.fromJson(param, map.getClass());
		String name = map.get("name").toString();
		String idCode = map.get("idCode").toString();
		if (StringUtil.isEmpty(name)) {
			name = null;
		}
		if (StringUtil.isEmpty(idCode)) {
			idCode = null;
		}

		List<Map<String, Object>> list =  rpUserService.getExportList(
				name, idCode);
		HSSFWorkbook hwb = new HSSFWorkbook();
		// 通过excle对象创建一个选项卡对象
		HSSFSheet sheet = hwb.createSheet("用户数据");
		Map<String, Object> entity = null;

		// 循环list创建行
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String fileName = "用户数据" + sdf.format(d) + ".xls";
		
		HSSFCellStyle cellStyle = hwb.createCellStyle(); // 样式对象
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
		HSSFFont font = hwb.createFont();// 设置字体
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 20);// 设置字体大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		cellStyle.setFont(font);
		
		HSSFCellStyle titleStyle = hwb.createCellStyle();  
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont tfont = hwb.createFont();// 设置字体
		tfont.setFontName("Arial");
		tfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		titleStyle.setFont(tfont);
		
		HSSFCellStyle vStyle = hwb.createCellStyle();  
		vStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
		vStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));

		HSSFRow title = sheet.createRow(0);
		title.createCell(0).setCellValue("用户数据");
		title.getCell(0).setCellStyle(cellStyle);
		
		String[] titles = { "微信openID", "昵称", "识别码", "姓名", "电话", "备注信息", 
				"信息查询权限", "角色权限","黑名单" };
		HSSFRow trow = sheet.createRow(1);
		for (int t = 0; t < titles.length; t++) {
			System.out.println(titles[t]);
			trow.createCell(t).setCellValue(titles[t]);
			sheet.setColumnWidth(t,
					(int) ((titles[t].length() * 3 + 0.72) * 256));
			sheet.setColumnWidth(14, (int) ((20 + 0.72) * 256));
			trow.getCell(t).setCellStyle(titleStyle);
		}
		for (int i = 0; i < list.size(); i++) {
			// 新建一行
			HSSFRow row = sheet.createRow(i + 2);
			row.setRowStyle(cellStyle);
			entity = list.get(i);
			row.createCell(0).setCellValue(
					entity.get("wechatNo") != null ? entity.get(
							"wechatNo").toString() : "");
			row.createCell(1).setCellValue(
					entity.get("nickName") != null ? entity.get("nickName").toString()
							: "");
			row.createCell(2).setCellValue(
					entity.get("idCode") != null ? entity.get(
							"idCode").toString() : "");
			row.createCell(3).setCellValue(
					entity.get("userName") != null ? entity.get(
							"userName").toString() : "");
			row.createCell(4).setCellValue(
					entity.get("tel") != null ? entity.get(
							"tel").toString() : "");
			row.createCell(5).setCellValue(
					entity.get("info") != null ? entity.get(
							"info").toString() : "");
			
			String qp = entity.get("queryPower") != null ? entity.get(
					"queryPower").toString() : "";
			String rp = entity.get("rolePower") != null ? entity.get(
					"rolePower").toString() : "";
			if ("1".equals(qp)) {
				qp = "高";
			} else if ("2".equals(qp)) {
				qp = "底";
			} else {
				qp = "";
			}
			if ("1".equals(rp)) {
				rp = "总权限";
			} else if ("2".equals(rp)) {
				rp = "门店权限";
			} else if ("3".equals(rp)) {
				rp = "个人权限";
			} else {
				rp = "";
			}
			row.createCell(6).setCellValue(qp);
			row.createCell(7).setCellValue(rp);
			String isBlack = entity.get("isBlack") != null ? entity.get(
					"isBlack").toString() : "0";
			if ("0".equals(isBlack)) {
				isBlack = "正常";
			} else if ("1".equals(isBlack)) {
				isBlack = "黑名单";
			}
			row.createCell(8).setCellValue(isBlack);
			
		}
		response.reset();
		try { 
			fileName =  java.net.URLEncoder.encode(fileName, "UTF-8");
		} catch (UnsupportedEncodingException e1) { 
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.setHeader("Content-Disposition", "attachment;filename="
				+ fileName);
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		OutputStream output;
		/* OutputStream out = null; */
		try {
			output = response.getOutputStream();
			BufferedOutputStream bufferedOutPut = new BufferedOutputStream(
					output);
			bufferedOutPut.flush();
			hwb.write(bufferedOutPut);
			bufferedOutPut.close();
			/*
			 * out = new FileOutputStream("E:/rpExcel/"+fileName);
			 * hwb.write(out);
			 */
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} /*
		 * finally { try { // out.close(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } }
		 */
	}
}
