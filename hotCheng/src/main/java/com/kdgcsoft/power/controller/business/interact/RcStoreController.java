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
import com.kdgcsoft.power.entity.business.interact.RpStore;
import com.kdgcsoft.power.service.business.interact.RpStoreService;
import com.xiaoleilu.hutool.util.CollectionUtil;

/**
 * PC端使用的接口
 * 
 * @author lenovo
 * 
 */
@Controller
@RequestMapping("/pcStore")
public class RcStoreController extends BaseController {
	private static CacheManager cacheManager = CacheManager.create();

	@Autowired
	private RpStoreService rpStoreService;
	@Autowired
	private SQLManager sqlManager;

	/**
	 * 查找门店表信息
	 * 
	 * @param request
	 * @param storename门店名
	 *            idCode识别码
	 * @return
	 */
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	@ResponseBody
	public Object getStoreList(HttpServletRequest request, Integer page,
			Integer rows) {
		if (page == null) {
			page = 1;
		}
		if (rows == null) {
			rows = 20;
		}
		PageRequest pageRequest = new PageRequest(page, rows);
		PageObject<Map<String, Object>> result = new PageObject<Map<String, Object>>();
		String storeName = request.getParameter("storeName");
		String idCode = request.getParameter("idCode");

		if (StringUtil.isEmpty(storeName)) {
			storeName = null;
		}
		if (StringUtil.isEmpty(idCode)) {
			idCode = null;
		}
		List<Map<String, Object>> ls = rpStoreService.getList(storeName,
				idCode, pageRequest);
		List<Map<String, Object>> rs = CollectionUtil.sub(ls,
				(page - 1) * rows, page * rows);
		result.setTotalCount((long) ls.size());
		result.setList(rs);
		return getPageModel(result);
	};

	/**
	 * 根据id查找门店表信息
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getInfo")
	@ResponseBody
	public Object getInfo(HttpServletRequest request) {
		String id = request.getParameter("id");
		RpStore store = rpStoreService.getInfo(Long.parseLong(id));
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("res", store);
		return new JsonMsg(true, SystemConstants.MSG_SUCCESS, maps);
	};

	/**
	 * 根据idCode查找门店表信息
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getInfoByIdCode")
	@ResponseBody
	public Object getInfoByIdCode(HttpServletRequest request) {
		String idCode = request.getParameter("idCode");
		RpStore store = rpStoreService.getInfoByIdCode(idCode);
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("res", store);
		return new JsonMsg(true, SystemConstants.MSG_SUCCESS, maps);
	};

	/**
	 * 删除门店信息
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
		rpStoreService.delete(Long.parseLong(id));
		return new JsonMsg(true, SystemConstants.MSG_DELETE_SUCCESS, null);
	}

	/**
	 * 新增或修改门店信息
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public JsonMsg save(HttpServletRequest request, RpStore entity) {
		Long id = entity.getId();
		if (id != null) {
			rpStoreService.update(entity);
		} else {
			rpStoreService.insert(entity);
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
		RpStore entity = new RpStore();
		if (!StringUtil.isEmpty(id)) {// 修改
			entity = rpStoreService.getInfo(Long.parseLong(id));
		}
		model.addObject("entity", entity);
		model.setViewName("view/business/rpStoreForm.jsp");
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
		String storeName = map.get("storeName").toString();
		String idCode = map.get("idCode").toString();
		if (StringUtil.isEmpty(storeName)) {
			storeName = null;
		}
		if (StringUtil.isEmpty(idCode)) {
			idCode = null;
		}

		
		List<Map<String, Object>> list = rpStoreService.getExportList(
				storeName, idCode);

		HSSFWorkbook hwb = new HSSFWorkbook();
		// 通过excle对象创建一个选项卡对象
		HSSFSheet sheet = hwb.createSheet("门店数据");
		Map<String, Object> entity = null;

		// 循环list创建行
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String fileName = "门店数据" + sdf.format(d) + ".xls";
		
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

		HSSFRow title = sheet.createRow(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
		
		title.createCell(0).setCellValue("门店数据");
		title.getCell(0).setCellStyle(cellStyle);

		String[] titles = { "省份", "门店名", "识别码", "信息查询权限", "角色权限"};
		HSSFRow trow = sheet.createRow(1);
		for (int t = 0; t < titles.length; t++) {
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
					entity.get("province") != null ? entity.get(
							"province").toString() : "");
			row.createCell(1).setCellValue(
					entity.get("storeName") != null ? entity.get("storeName").toString()
							: "");
			row.createCell(2).setCellValue(
					entity.get("idCode") != null ? entity.get(
							"idCode").toString() : "");
			
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
			row.createCell(3).setCellValue(qp);
			row.createCell(4).setCellValue(rp);
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
