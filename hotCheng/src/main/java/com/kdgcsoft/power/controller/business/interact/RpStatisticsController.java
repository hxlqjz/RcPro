package com.kdgcsoft.power.controller.business.interact;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.CacheManager;

import org.beetl.sql.core.SQLManager;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxxdemo.weixinsaomalogin.entity.MD5;
import com.kdgcsoft.power.common.bean.JsonMsg;
import com.kdgcsoft.power.common.bean.SystemConstants;
import com.kdgcsoft.power.common.util.StringUtil;
import com.kdgcsoft.power.controller.fw.base.BaseController;
import com.kdgcsoft.power.entity.business.interact.RpStatistics;
import com.kdgcsoft.power.entity.business.interact.RpUser;
import com.kdgcsoft.power.service.business.interact.RpStatisticsService;
import com.kdgcsoft.power.service.business.interact.RpUserService;

/**
 * 是微信扫码使用接口
 * 
 * @author lenovo
 * 
 */
@Controller
@RequestMapping("/statistics")
public class RpStatisticsController extends BaseController {
	private static CacheManager cacheManager = CacheManager.create();

	@Autowired
	private RpStatisticsService rpStatisticsService;
	@Autowired
	private SQLManager sqlManager;
	@Autowired
	private RpUserService rpUserService;

	/**
	 * 根据扫码的二维码编号查询，返回状态
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getActivate")
	@ResponseBody
	public Long getActivate(HttpServletRequest request) {
		String qrCode = request.getParameter("qrCode");
		if (StringUtil.isEmpty(qrCode)) {
			qrCode = null;
		}
		RpStatistics s = rpStatisticsService.checkQr(qrCode, null);
		if (s == null) {
			return Long.parseLong("0");
		} else {
			return s.getActiviateStatus();
		}

	}

	/**
	 * 扫码返现更新统计表
	 */

	@RequestMapping("/scanToBack")
	@ResponseBody
	public JsonMsg scanToBack(HttpServletRequest request) {
		// 车牌号，手机号，微信号，二维码，扫码时间和质保起始时间是当前时间，结束时间是18个月后
		String scanWechat = request.getParameter("scanWechat");
		String qrCode = request.getParameter("qrCode");
		String plateNumber = request.getParameter("plateNumber");
		String carTel = request.getParameter("carTel");
		
		//先获取这条数据，若这条数据的状态已经是2了，则不给更新了
		RpStatistics s = rpStatisticsService.checkQr(qrCode, null);
		if(s.getActiviateStatus() == 2){
			//什么也不做
			System.out.println("这条数据已经扫过了，不做任何操作");
		}else{
			//更新
			RpStatistics entity = new RpStatistics();
			entity.setScanWechat(scanWechat);
			entity.setQrCode(qrCode);
			entity.setPlateNumber(plateNumber);
			entity.setCarTel(carTel);

			rpStatisticsService.scanToBack(entity);
			
			
			System.out.println("开始返现----");
			String totalAmount = "0";
			String hbname = "泰科能佰尔";// 红包名称
			String zfy = "恭喜发财";
			if (s.getIsYs() == 1) {
				// 演示字段，返现金额为1分
				totalAmount = "100";
				hbname = "演示电池扫码返现";
				zfy = "演示恭喜发财";
			} else {
				// 返现5元
				totalAmount = "500";
			}
			//扫码返现----
			Map<String,String> map =new HashMap<String,String>();
			MD5 md = new MD5();
			String opendid = scanWechat;
			System.out.println("scantoBack扫码返现--->" + opendid);
			try {
				if(StringUtil.isEmpty(s.getCashTime())){
					map = md.sendRedPack(opendid, totalAmount, hbname, zfy);
					rpStatisticsService.updateCashTime(qrCode);
					System.out.println("扫码结果"+map);
				}else{
					System.out.println("--已经扫过了");
				}
				
			} catch (KeyManagementException e) {
				e.printStackTrace();
			} catch (UnrecoverableKeyException e) {
				e.printStackTrace();
			} catch (KeyStoreException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (CertificateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		
		
		RpUser u = rpUserService.getInfoByWechat(scanWechat);
		return new JsonMsg(true, SystemConstants.MSG_SAVE_SUCCESS, u);
	}

	/**
	 * 扫码时状态为2时查询此条信息
	 */
	@RequestMapping("/getInfoByQp")
	@ResponseBody
	public RpStatistics getInfoByQp(HttpServletRequest request) {
		String qrCode = request.getParameter("qrCode");
		String scanWechat = request.getParameter("scanWechat");
		return rpStatisticsService.getInfoByQp(qrCode, scanWechat);
	}

	/**
	 * 微信中查询记录根据rolePower 时间段+微信号查询，选查询他的role_power 只有1和3需要红包统计
	 */
	@RequestMapping("getListByRole")
	@ResponseBody
	public Object getListByRole(HttpServletRequest request) {
		String start = StringUtil.isEmpty(request.getParameter("start")) ? null
				: request.getParameter("start") + " 00:00:00";
		String end = StringUtil.isEmpty(request.getParameter("end")) ? null
				: request.getParameter("end") + " 23:59:59";
		String scanWechat = StringUtil.isEmpty(request
				.getParameter("scanWechat")) ? null : request
				.getParameter("scanWechat");
		String chooseWechat = StringUtil.isEmpty(request
				.getParameter("chooseWechat")) ? null : request
				.getParameter("chooseWechat");
		String province = StringUtil.isEmpty(request.getParameter("province")) ? null
				: request.getParameter("province");
		String storeName = StringUtil
				.isEmpty(request.getParameter("storeName")) ? null : request
				.getParameter("storeName");

		return rpStatisticsService.getListByRole(start, end, scanWechat,
				chooseWechat, province, storeName);
	}
	
	/**
	 * jilu2页面中，统计实时数据
	 * 查询当前人员的店面下，最近三天时间内，谁销售了哪些型号，返回时间月日时，型号，人员
	 * 需要门店信息，开始时间是三天前，结束时间是现在
	 * @param request
	 * @return
	 */
	@RequestMapping("getSsListByRole")
	@ResponseBody
	public Object getSsListByRole(HttpServletRequest request) {
		/*String start = StringUtil.isEmpty(request.getParameter("start")) ? null
				: request.getParameter("start") + " 00:00:00";
		String end = StringUtil.isEmpty(request.getParameter("end")) ? null
				: request.getParameter("end") + " 23:59:59";*/
		String scanWechat = StringUtil.isEmpty(request
				.getParameter("scanWechat")) ? null : request
				.getParameter("scanWechat");

		return rpStatisticsService.getSsListByRole(scanWechat);
	}

	/**
	 * 根据统计记录中的数据查找所有省份，再根据省份查找门店
	 */
	@RequestMapping("getProvince")
	@ResponseBody
	public Object getProvince(HttpServletRequest request) {
		List<Map<String, Object>> plist = rpStatisticsService.getProvince();
		return plist;
	}

	@RequestMapping("getStore")
	@ResponseBody
	public Object getStore(HttpServletRequest request) {
		String province = request.getParameter("province");
		List<Map<String, Object>> slist = rpStatisticsService
				.getStore(province);
		return slist;
	}

}
