package com.kdgcsoft.power.controller.business.interact;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.CacheManager;

import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdgcsoft.power.common.bean.JsonMsg;
import com.kdgcsoft.power.common.util.StringUtil;
import com.kdgcsoft.power.controller.fw.base.BaseController;
import com.kdgcsoft.power.entity.business.interact.RpReg;
import com.kdgcsoft.power.entity.business.interact.RpStore;
import com.kdgcsoft.power.entity.business.interact.RpUser;
import com.kdgcsoft.power.service.business.interact.RpRegService;
import com.kdgcsoft.power.service.business.interact.RpStoreService;
import com.kdgcsoft.power.service.business.interact.RpUserService;

/**
 * 微信端使用的接口
 * 
 * @author lenovo
 * 
 */
@Controller
@RequestMapping("/user")
public class RpUserController extends BaseController {
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
	 * 根据微信号查询用户信息
	 * 
	 * @param wechatNo
	 * @return
	 */
	@RequestMapping("/getInfoByWechat")
	@ResponseBody
	public RpUser getInfoByWechat(String wechatNo) {
		if (wechatNo != null) {
			RpUser en = rpUserService.getInfoByWechat(wechatNo);
			return en;
		} else {
			return null;
		}

	}

	/**
	 * 微信注册用户 用户输入识别码，判断识别码是否存在 不存在则提示没有该数据
	 * 存在，则往用户表中添加数据，微信号，识别号，queryPower和rolePower从门店表中带过来
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping("/reg")
	@ResponseBody
	public JsonMsg reg(HttpServletRequest request) {
		String wechatNo = request.getParameter("wechatNo");
		String nickName = request.getParameter("nickName");
		String idCode = request.getParameter("idCode");
		String userName = request.getParameter("userName");
		String tel = request.getParameter("tel");
		JsonMsg j = new JsonMsg();
		RpUser u = rpUserService.getInfoByWechat(wechatNo);
		if (u != null) {
			j.setSuccess(false);
			j.setMsg("用户已存在");
		} else {
			RpStore s = rpStoreService.getInfoByIdCode(idCode);
			if (s != null) {
				String info = s.getProvince() + "-" + s.getStoreName();
				rpUserService.reg(wechatNo, nickName, idCode, userName, tel,
						info);
				j.setSuccess(true);
				j.setMsg("注册成功");
				// return new JsonMsg(true,"注册成功",null);
			} else {
				RpReg entity = rpRegService.getInfo(wechatNo);
				if (entity == null) {
					RpReg r = new RpReg();
					r.setWechatNo(wechatNo);
					rpRegService.insert(r);
				} else {
					rpRegService.update(entity);
				}
				entity = rpRegService.getInfo(wechatNo);
				j.setSuccess(false);
				j.setData(entity);
				j.setMsg("请查询后输入识别码");
			}
		}

		return j;
	}

	/**
	 * 用户登录注册，输入注册码时获取他输错的次数
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/checkNum")
	@ResponseBody
	public Object checkNum(HttpServletRequest request) {
		String wechatNo = request.getParameter("wechatNo");
		RpReg entity = rpRegService.getInfo(wechatNo);
		return entity;

	}

	/**
	 * 根据openid/wechatNO 获取门店下的用户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUsersByStore")
	@ResponseBody
	public Object getUsersByStore(HttpServletRequest request) {
		String wechatNo = request.getParameter("wechatNo");
		if (StringUtil.isEmpty(wechatNo)) {
			wechatNo = null;
		}
		List<RpUser> list = rpUserService.getUsersByStore(wechatNo);
		return list;
	}

}
