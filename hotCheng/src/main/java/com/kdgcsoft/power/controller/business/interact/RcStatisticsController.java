package com.kdgcsoft.power.controller.business.interact;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kdgcsoft.power.common.bean.ConflictException;
import com.kdgcsoft.power.common.bean.JsonMsg;
import com.kdgcsoft.power.common.bean.PageObject;
import com.kdgcsoft.power.common.bean.SystemConstants;
import com.kdgcsoft.power.common.util.StringUtil;
import com.kdgcsoft.power.controller.fw.base.BaseController;
import com.kdgcsoft.power.entity.business.interact.RpStatistics;
import com.kdgcsoft.power.service.business.interact.RpStatisticsService;
import com.kdgcsoft.power.service.business.interact.RpUserService;
import com.xiaoleilu.hutool.util.CollectionUtil;

/**
 * pc端使用端口
 * 
 * @author lenovo
 * 
 */
@Controller
@RequestMapping("/pcStatistics")
public class RcStatisticsController extends BaseController {
	private static CacheManager cacheManager = CacheManager.create();

	@Autowired
	private RpStatisticsService rpStatisticsService;
	@Autowired
	private SQLManager sqlManager;
	@Autowired
	private RpUserService rpUserService;

	/**
	 * 查找统计信息
	 * 
	 * @param request
	 * @param activiateStatus状态
	 *            productModel产品型号
	 * @return
	 */
	@RequestMapping(value = "/getList")
	@ResponseBody
	public Object getList(HttpServletRequest request, Integer page, Integer rows) {
		/*
		 * String activiateStatus = request.getParameter("activiateStatus");
		 * String productModel = request.getParameter("productModel");
		 */
		String qrNo = request.getParameter("qrNo");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		PageRequest pageRequest = new PageRequest(page, rows);
		PageObject<Map<String, Object>> result = new PageObject<Map<String, Object>>();

		if (StringUtil.isEmpty(qrNo)) {
			qrNo = null;
		}

		if (StringUtil.isEmpty(startTime)) {
			startTime = null;
		} else {
			startTime = startTime + " 00:00:00";
		}
		if (StringUtil.isEmpty(endTime)) {
			endTime = null;
		} else {
			endTime = endTime + " 23:59:59";
		}
		List<Map<String, Object>> ls = rpStatisticsService.getList(qrNo,
				startTime, endTime, pageRequest);
		List<Map<String, Object>> rs = CollectionUtil.sub(ls,
				(page - 1) * rows, page * rows);
		result.setTotalCount((long) ls.size());
		result.setList(rs);
		return getPageModel(result);

	};

	/**
	 * 查找统计信息
	 * 
	 * @param request
	 * @param activiateStatus状态
	 *            productModel产品型号
	 * @return
	 */
	@RequestMapping(value = "/getTjList")
	@ResponseBody
	public Object getTjList(HttpServletRequest request, Integer page,
			Integer rows) {
		/*
		 * String activiateStatus = request.getParameter("activiateStatus");
		 * String productModel = request.getParameter("productModel");
		 */
		String wechat = request.getParameter("wechat");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		PageRequest pageRequest = new PageRequest(page, rows);
		PageObject<Map<String, Object>> result = new PageObject<Map<String, Object>>();

		if (StringUtil.isEmpty(wechat)) {
			wechat = null;
		}

		if (StringUtil.isEmpty(startTime)) {
			startTime = null;
		} else {
			startTime = startTime + " 00:00:00";
		}
		if (StringUtil.isEmpty(endTime)) {
			endTime = null;
		} else {
			endTime = endTime + " 23:59:59";
		}
		List<Map<String, Object>> ls = rpStatisticsService.getTjList(wechat,
				startTime, endTime, pageRequest);
		List<Map<String, Object>> rs = CollectionUtil.sub(ls,
				(page - 1) * rows, page * rows);
		result.setTotalCount((long) ls.size());
		result.setList(rs);
		return getPageModel(result);

	};

	/**
	 * 根据id查找信息
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getInfo")
	@ResponseBody
	public Object getInfo(HttpServletRequest request) {
		String id = request.getParameter("id");
		RpStatistics s = rpStatisticsService.getInfo(Long.parseLong(id));
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("res", s);
		return new JsonMsg(true, SystemConstants.MSG_SUCCESS, maps);
	};

	/**
	 * 删除
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
	public JsonMsg del(HttpServletRequest request) throws ConflictException,
			IOException {
		String id = request.getParameter("id");
		rpStatisticsService.delete(Long.parseLong(id));
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
	public JsonMsg save(HttpServletRequest request, RpStatistics entity) {
		Long id = entity.getId();
		if (id != null) {
			rpStatisticsService.update(entity);
		} else {
			rpStatisticsService.insert(entity);
		}

		return new JsonMsg(true, SystemConstants.MSG_SAVE_SUCCESS, null);
	}

	/**
	 * 激活区间内的二维码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/activate")
	@ResponseBody
	public JsonMsg activate(HttpServletRequest request) {
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String presaleArea = request.getParameter("presaleArea");
		Long count = rpStatisticsService.getActivateCount(
				Long.parseLong(start), Long.parseLong(end));
		if (count != 0) {
			return new JsonMsg(false, "激活区间内有已销售数据", null);
		} else {
			rpStatisticsService.activate(Long.parseLong(start),
					Long.parseLong(end), presaleArea);
			return new JsonMsg(true, "激活成功", null);
		}

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
		RpStatistics entity = new RpStatistics();
		if (!StringUtil.isEmpty(id)) {// 修改
			entity = rpStatisticsService.getInfo(Long.parseLong(id));
		}
		model.addObject("entity", entity);
		model.setViewName("view/business/rpStatisticsForm.jsp");
		return model;
	}

	/**
	 * 
	 * 根据二维码或者序号判断是否存在
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkQr")
	@ResponseBody
	public Object checkQr(HttpServletRequest request) {
		String qrCode = request.getParameter("qrCode");
		String qrNo = request.getParameter("qrNo");
		if (StringUtil.isEmpty(qrCode)) {
			qrCode = null;
		}
		if (StringUtil.isEmpty(qrNo)) {
			qrNo = null;
		}
		RpStatistics s = rpStatisticsService.checkQr(qrCode, qrNo);
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("res", s);
		return new JsonMsg(true, SystemConstants.MSG_SUCCESS, maps);
	};

	/**
	 * 导出数据
	 * 
	 * @param request
	 */
	@RequestMapping("/export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		String param = request.getParameter("param");

		String activiateStatus = request.getParameter("activiateStatus");
		String productModel = request.getParameter("productModel");
		// String startTime = request.getParameter("startTime");
		// String endTime = request.getParameter("endTime");

		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map = gson.fromJson(param, map.getClass());
		String startTime = map.get("startTime").toString();
		String endTime = map.get("endTime").toString();
		if (StringUtil.isEmpty(productModel)) {
			productModel = null;
		}
		if (StringUtil.isEmpty(activiateStatus)) {
			activiateStatus = null;
		}

		if (StringUtil.isEmpty(startTime)) {
			startTime = null;
		} else {
			startTime = startTime + " 00:00:00";
		}
		if (StringUtil.isEmpty(endTime)) {
			endTime = null;
		} else {
			endTime = endTime + " 23:59:59";
		}
		List<Map<String, Object>> list = rpStatisticsService.getExportList(
				activiateStatus, productModel, startTime, endTime);

		HSSFWorkbook hwb = new HSSFWorkbook();
		// 通过excle对象创建一个选项卡对象
		HSSFSheet sheet = hwb.createSheet("统计报表");
		Map<String, Object> entity = null;

		// 循环list创建行
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String fileName = "统计报表" + sdf.format(d) + ".xls";
		HSSFCellStyle cellStyle = hwb.createCellStyle(); // 样式对象
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		HSSFFont font = hwb.createFont();// 设置字体
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 30);// 设置字体大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		cellStyle.setFont(font);

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 19));

		HSSFRow title = sheet.createRow(0);
		title.setRowStyle(cellStyle);
		title.createCell(0).setCellValue("统计报表");

		String[] titles = { "预售地区", "二维码序号", "产品型号", "激活状态", "扫码时间", "起始质保日期",
				"终止质保日期", "返现时间", "扫码微信号", "省份", "门店名", "姓名", "电话", "备注",
				"信息查询权限", "角色权限", "车主车牌号", "车主手机号", "昵称" };
		HSSFRow trow = sheet.createRow(1);
		for (int t = 0; t < titles.length; t++) {
			System.out.println(titles[t]);
			trow.createCell(t).setCellValue(titles[t]);
			sheet.setColumnWidth(t,
					(int) ((titles[t].length() * 3 + 0.72) * 256));
			sheet.setColumnWidth(14, (int) ((20 + 0.72) * 256));
		}
		for (int i = 0; i < list.size(); i++) {
			// 新建一行
			HSSFRow row = sheet.createRow(i + 2);
			row.setRowStyle(cellStyle);
			entity = list.get(i);
			// row.createCell(0).setCellValue(entity.get("id")!=null?entity.get("id").toString():"");
			row.createCell(0).setCellValue(
					entity.get("presaleArea") != null ? entity.get(
							"presaleArea").toString() : "");
			row.createCell(1).setCellValue(
					entity.get("qrNo") != null ? entity.get("qrNo").toString()
							: "");
			row.createCell(2).setCellValue(
					entity.get("productModel") != null ? entity.get(
							"productModel").toString() : "");
			String as = entity.get("activiateStatus") != null ? entity.get(
					"activiateStatus").toString() : "0";
			if ("0".equals(as)) {
				as = "未激活";
			} else if ("1".equals(as)) {
				as = "待销售";
			} else {
				as = "已销售";
			}
			row.createCell(3).setCellValue(as);
			row.createCell(4).setCellValue(
					entity.get("scanTime") != null ? entity.get("scanTime")
							.toString() : "");
			row.createCell(5).setCellValue(
					entity.get("qualityStart") != null ? entity.get(
							"qualityStart").toString() : "");
			row.createCell(6).setCellValue(
					entity.get("qualityEnd") != null ? entity.get("qualityEnd")
							.toString() : "");
			row.createCell(7).setCellValue(
					entity.get("cashTime") != null ? entity.get("cashTime")
							.toString() : "");
			row.createCell(8).setCellValue(
					entity.get("scanWechat") != null ? entity.get("scanWechat")
							.toString() : "");
			row.createCell(9).setCellValue(
					entity.get("province") != null ? entity.get("province")
							.toString() : "");
			row.createCell(10).setCellValue(
					entity.get("storeName") != null ? entity.get("storeName")
							.toString() : "");
			row.createCell(11).setCellValue(
					entity.get("wechatName") != null ? entity.get("wechatName")
							.toString() : "");
			row.createCell(12).setCellValue(
					entity.get("wechatTel") != null ? entity.get("wechatTel")
							.toString() : "");
			row.createCell(13).setCellValue(
					entity.get("wechatInfo") != null ? entity.get("wechatInfo")
							.toString() : "");
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
			row.createCell(14).setCellValue(qp);
			row.createCell(15).setCellValue(rp);
			row.createCell(16).setCellValue(
					entity.get("plateNumber") != null ? entity.get(
							"plateNumber").toString() : "");
			row.createCell(17).setCellValue(
					entity.get("carTel") != null ? entity.get("carTel")
							.toString() : "");
			row.createCell(18).setCellValue(
					entity.get("nickName") != null ? entity.get("nickName")
							.toString() : "");
		}
		response.reset();
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

	/**
	 * 导入数据
	 * 
	 * @param request
	 */
	@RequestMapping("/importExcel")
	@ResponseBody
	public void importExcel(HttpServletRequest request) {
		Workbook workbook = null;
		String excelPath = request.getParameter("excelPath");
		try {
			// 获取Ecle对象
			workbook = Workbook.getWorkbook(new File(excelPath));
			// 获取选项卡对象 第0个选项卡
			Sheet sheet = workbook.getSheet(0);
			// 循环选项卡中的值
			for (int i = 0; i < sheet.getRows() - 1; i++) {
				RpStatistics s = new RpStatistics();
				s.setQrCode(sheet.getCell(0, i + 1).getContents());
				s.setQrNo(Long.parseLong(sheet.getCell(1, i + 1).getContents()));
				s.setProductModel(sheet.getCell(2, i + 1).getContents());
				s.setActiviateStatus(Long.parseLong("0"));
				rpStatisticsService.insert(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook.close();
		}
	}

	/**
	 * 双击打开详情页面
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getDetail")
	public ModelAndView getDetail(String id, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		RpStatistics entity = new RpStatistics();
		if (!StringUtil.isEmpty(id)) {
			entity = rpStatisticsService.getInfo(Long.parseLong(id));
		}
		model.addObject("entity", entity);
		model.setViewName("view/business/rpStatisticsDetail.jsp");
		return model;
	}
	
	/**
	 * 导出统计数据
	 * 
	 * @param request
	 */
	@RequestMapping("/exportTj")
	@ResponseBody
	public void exportTj(HttpServletRequest request, HttpServletResponse response) {
		String param = request.getParameter("param");


		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map = gson.fromJson(param, map.getClass());
		String wechat = map.get("wechat").toString();
		String startTime = map.get("startTime").toString();
		String endTime = map.get("endTime").toString();
		if (StringUtil.isEmpty(wechat)) {
			wechat = null;
		}

		if (StringUtil.isEmpty(startTime)) {
			startTime = null;
		} else {
			startTime = startTime + " 00:00:00";
		}
		if (StringUtil.isEmpty(endTime)) {
			endTime = null;
		} else {
			endTime = endTime + " 23:59:59";
		}
		List<Map<String, Object>> list = rpStatisticsService.getExportTjList(
				wechat, startTime, endTime);

		HSSFWorkbook hwb = new HSSFWorkbook();
		// 通过excle对象创建一个选项卡对象
		HSSFSheet sheet = hwb.createSheet("销售统计报表");
		Map<String, Object> entity = null;

		// 循环list创建行
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String fileName = "销售统计报表" + sdf.format(d) + ".xls";
		HSSFCellStyle cellStyle = hwb.createCellStyle(); // 样式对象
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		HSSFFont font = hwb.createFont();// 设置字体
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 30);// 设置字体大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		cellStyle.setFont(font);

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

		HSSFRow title = sheet.createRow(0);
		title.setRowStyle(cellStyle);
		title.createCell(0).setCellValue("销售统计报表");

		String[] titles = { "扫码时间", "微信openID", "姓名", "昵称", "电话", "扫码数量"};
		HSSFRow trow = sheet.createRow(1);
		for (int t = 0; t < titles.length; t++) {
			System.out.println(titles[t]);
			trow.createCell(t).setCellValue(titles[t]);
			sheet.setColumnWidth(t,
					(int) ((titles[t].length() * 3 + 0.72) * 256));
			sheet.setColumnWidth(14, (int) ((20 + 0.72) * 256));
		}
		for (int i = 0; i < list.size(); i++) {
			// 新建一行
			HSSFRow row = sheet.createRow(i + 2);
			row.setRowStyle(cellStyle);
			entity = list.get(i);
			row.createCell(0).setCellValue(
					entity.get("scantime") != null ? entity.get(
							"scantime").toString() : "");
			row.createCell(1).setCellValue(
					entity.get("scanWechat") != null ? entity.get("scanWechat").toString()
							: "");
			row.createCell(2).setCellValue(
					entity.get("userName") != null ? entity.get(
							"userName").toString() : "");
			
			row.createCell(3).setCellValue(
					entity.get("nickName") != null ? entity.get("nickName")
							.toString() : "");
			row.createCell(4).setCellValue(
					entity.get("wechatTel") != null ? entity.get(
							"wechatTel").toString() : "");
			row.createCell(5).setCellValue(
					entity.get("scannum") != null ? entity.get("scannum")
							.toString() : "");
		}
		response.reset();
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
