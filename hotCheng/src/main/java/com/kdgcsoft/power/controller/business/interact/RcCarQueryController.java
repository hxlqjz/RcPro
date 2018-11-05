package com.kdgcsoft.power.controller.business.interact;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.xpath.DefaultXPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.InputSource;

import com.hp.hpl.sparta.ParseException;
import com.kdgcsoft.power.common.bean.JsonMsg;
import com.kdgcsoft.power.common.util.StringUtil;
import com.kdgcsoft.power.controller.fw.base.BaseController;
import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.service.business.interact.CarStyleService;
import com.kdgcsoft.power.service.business.interact.RcCarQueryService;

/**
 * 根据查询条件进行查询 品牌，厂家，车型，排量 两个查询接口放在一起，不再另加一个RcCarDisQuery了 微信端接口统一以wc(wechat)前缀命名
 * 
 * @author lenovo
 * 
 */
@Controller
@RequestMapping("/wcCarQuery")
public class RcCarQueryController extends BaseController {

	@Autowired
	private BeetlSQLHelper bsh;

	@Autowired
	private RcCarQueryService rcCarQueryService;
	@Autowired
	private CarStyleService carStyleService;

	/**
	 * 查找所有车型 view/wechat/carstyle/index.html页面中车型检索方法下拉框所用数据 所有查询方法请用get开头
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getAllCarStyle")
	@ResponseBody
	public Map<String, Object> getAllCarStyle(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> ls = rcCarQueryService.getAllCarStyle();
		map.put("ls", ls);
		return map;
	};

	/**
	 * 根据品牌，厂家车型，获取参数， 排量，年款，平台，发动机，变速箱，轮毂，销售名称，生产年份
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getOptByStyle", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getOptByStyle(HttpServletRequest request) {
		String carBrand = request.getParameter("carBrand");
		String carModel = request.getParameter("carModel");
		String carFactory = request.getParameter("carFactory");

		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> ls = rcCarQueryService.getOptByStyle(
				carBrand, carModel, carFactory);
		Map<String, Object> rs = ls.get(0);
		// 获取结果集，将结果以，分割，放入结果集中
		String[] plArry = convertStrToArray(rs.get("pl").toString());
		String[] nkArry = convertStrToArray(rs.get("nk").toString());
		String[] ptArry = convertStrToArray(rs.get("pt").toString());
		String[] fdjArry = convertStrToArray(rs.get("fdj").toString());
		String[] bsxArry = convertStrToArray(rs.get("bsx").toString());
		String[] lgArry = convertStrToArray(rs.get("lg").toString());
		String[] xsmcArry = convertStrToArray(rs.get("xsmc").toString());
		String[] scnfArry = convertStrToArray(rs.get("scnf").toString());

		map.put("pl", plArry);
		map.put("nk", nkArry);
		map.put("pt", ptArry);
		map.put("fdj", fdjArry);
		map.put("bsx", bsxArry);
		map.put("lg", lgArry);
		map.put("xsmc", xsmcArry);
		map.put("scnf", scnfArry);
		return map;
	};

	/**
	 * 根据条件查找车型数据 宝丰制动片 奔德士制动片 avia润滑油，变速箱油 泰科能波尔电池 火花塞 热骋电池
	 * 
	 * @param request
	 *            8+3
	 * @return
	 */
	@RequestMapping(value = "/getCarStyleByKey", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCarStyleByKey(HttpServletRequest request) {
		String carBrand = request.getParameter("carBrand");
		String carModel = request.getParameter("carModel");
		String carFactory = request.getParameter("carFactory");

		String pl = request.getParameter("plOpt");
		String nk = request.getParameter("nkOpt");
		String pt = request.getParameter("ptOpt");
		String fdj = request.getParameter("fdjOpt");
		String bsx = request.getParameter("bsxOpt");
		String lg = request.getParameter("lgOpt");
		String xsmc = request.getParameter("xsmcOpt");
		String scnf = request.getParameter("scnfOpt");

		Map<String, Object> map = new HashMap<String, Object>();

		List<Map<String, Object>> ls = rcCarQueryService.getCarStyleByKey(
				carBrand, carModel, carFactory, pl, nk, pt, fdj, bsx, lg, xsmc,
				scnf);
		// 各个模块数据放进去
		List<String> bfQzdArray = new ArrayList<String>();
		List<String> bfHzdArray = new ArrayList<String>();
		List<String> bdsQzdArray = new ArrayList<String>();
		List<String> bdsHzdArray = new ArrayList<String>();

		List<String> aviaRhyArray = new ArrayList<String>();
		List<String> aviaRhyByjzlArray = new ArrayList<String>();
		List<Map<String, Object>> aviaBsxyArray = new ArrayList<Map<String, Object>>();
		List<String> aviaBsxyByjzlArray = new ArrayList<String>();
		List<String> aviaBsxyDxjzlArray = new ArrayList<String>();

		List<Map<String, Object>> nbeArray = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> hhsArray = new ArrayList<Map<String, Object>>();

		Map<String, Object> rc = new HashMap<String, Object>();
		if (ls.size() != 0) {
			rc = ls.get(0);
			for (int i = 0; i < ls.size(); i++) {
				Map<String, Object> m = ls.get(i);
				bfQzdArray.add(m.get("bfQzd1").toString());
				bfQzdArray.add(m.get("bfQzd2").toString());
				bfQzdArray.add(m.get("bfQzd3").toString());

				bfHzdArray.add(m.get("bfHzd1").toString());
				bfHzdArray.add(m.get("bfHzd2").toString());
				bfHzdArray.add(m.get("bfHzd3").toString());

				bdsQzdArray.add(m.get("bdsQzd1").toString());
				bdsQzdArray.add(m.get("bdsQzd2").toString());
				bdsQzdArray.add(m.get("bdsQzd3").toString());

				bdsHzdArray.add(m.get("bdsHzd1").toString());
				bdsHzdArray.add(m.get("bdsHzd2").toString());
				bdsHzdArray.add(m.get("bdsHzd3").toString());

				aviaRhyArray.add(m.get("rhyAvia1").toString());
				aviaRhyArray.add(m.get("rhyAvia2").toString());
				aviaRhyArray.add(m.get("rhyAvia3").toString());
				aviaRhyArray.add(m.get("rhyAvia4").toString());
				aviaRhyByjzlArray.add(m.get("rhybyjzl").toString());

				Map<String, Object> bsxy = new HashMap<String, Object>();
				bsxy.put("info", m.get("bsxyAvia").toString());
				bsxy.put("bz", m.get("bsxybz").toString());
				aviaBsxyArray.add(bsxy);
				aviaBsxyByjzlArray.add(m.get("bsxbyjzl").toString());
				aviaBsxyDxjzlArray.add(m.get("bsxdxjzl").toString());

				Map<String, Object> nbe1 = new HashMap<String, Object>();
				nbe1.put("info", m.get("nbe1").toString());
				nbe1.put("bz", "");
				nbeArray.add(nbe1);
				Map<String, Object> nbe2 = new HashMap<String, Object>();
				nbe2.put("info", m.get("nbe2").toString());
				nbe2.put("bz", "");
				nbeArray.add(nbe2);

				Map<String, Object> lg1 = new HashMap<String, Object>();
				lg1.put("info", m.get("lg1").toString());
				lg1.put("bz", "");
				hhsArray.add(lg1);
				Map<String, Object> lg2 = new HashMap<String, Object>();
				lg2.put("info", m.get("lg2").toString());
				lg2.put("bz", "");
				hhsArray.add(lg2);
			}
		}

		map.put("bfQzd", arrayToOutRepet(bfQzdArray));
		map.put("bfHzd", arrayToOutRepet(bfHzdArray));
		map.put("bdsQzd", arrayToOutRepet(bdsQzdArray));
		map.put("bdsHzd", arrayToOutRepet(bdsHzdArray));
		map.put("aviaRhy", arrayToOutRepet(aviaRhyArray));
		map.put("aviaRhyJzl", arrayToOutRepet(aviaRhyByjzlArray));
		map.put("aviaBsxy", mapToOutRepet(aviaBsxyArray));
		map.put("aviaBsxyDxjzl", arrayToOutRepet(aviaBsxyDxjzlArray));
		map.put("aviaBsxyByjzl", arrayToOutRepet(aviaBsxyByjzlArray));
		map.put("hhs", mapToOutRepet(hhsArray));
		map.put("nbe", mapToOutRepet(nbeArray));
		map.put("rc", rc);
		return map;
	};

	@RequestMapping(value = "/getFactoryAndModelByBrand", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getFactoryAndModelByBrand(
			HttpServletRequest request) {
		String carBrand = request.getParameter("carBrand");
		List<Map<String, Object>> ls = rcCarQueryService
				.getFactoryAndModelByBrand(carBrand);
		List<Map<String, Object>> rec = new ArrayList<Map<String, Object>>();
		List<String> models = new ArrayList<String>();
		Map<String, Object> en = new HashMap<String, Object>();
		for (int i = 0; i < ls.size(); i++) {
			Map<String, Object> map = ls.get(i);
			if (!en.containsKey("carFactory")) {
				en.put("carFactory", map.get("carFactory"));
			}
			if (en.get("carFactory").equals(map.get("carFactory"))) {
				if (!models.contains(map.get("carModel").toString())) {
					models.add(map.get("carModel").toString());
				}
				en.put("carModels", models);
			} else {
				rec.add(en);
				en = new HashMap<String, Object>();
				models = new ArrayList<String>();
				if (!models.contains(map.get("carModel").toString())) {
					models.add(map.get("carModel").toString());
				}
				en.put("carModels", models);
			}
			if (i == ls.size() - 1) {
				rec.add(en);
			}
		}
		return rec;
	}

	/**
	 * 根据Vin码查找车型数据 车型，加油品等目录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getCarStyleByVin")
	@ResponseBody
	public Object getCarStyleByVin(HttpServletRequest request) {
		String vinCode = request.getParameter("vinCode");
		// 力扬接口
		JsonMsg msg = findVehicleInfoByVinCode(vinCode);
		Map<String, Object> data = (Map) msg.getData();
		JSONArray arr = (JSONArray) data.get("result");
		int size = arr.size();
		String[] ids = new String[size];
		Date cd = new Date();
		String date = DateUtils.formatDate(cd, "yyyy-MM-dd");
		// 记录扫码记录，不用管
		for (int i = 0; i < size; i++) {
			JSONObject ob = arr.getJSONObject(i);
			ids[i] = ob.get("LevelId").toString();
			String shiftMemo = ob.get("TransmissionModel").toString();
			if (!StringUtils.isEmpty(shiftMemo)) {
				List<Map<String, Object>> ls = carStyleService
						.findVinRecordList(ids[i]);
				if (ls.size() == 0) {
					carStyleService.insertVinRecordList(ids[i], shiftMemo,
							vinCode, date);
				}
				carStyleService.updateCarStyledata(ids[i], shiftMemo);
			}
		}
		Map<String, Object> map = getCarStyleByLyId(ids,request);
		return map;
	};

	@RequestMapping(value = "/getCarStyleByLyId")
	@ResponseBody
	public Map<String, Object> getCarStyleByLyId(String[] lyIds,HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("lyIds");
		String[] newIds = {ids};
		
		if(StringUtil.isEmpty(lyIds)){
			lyIds = newIds;
		}
		List<Map<String, Object>> ls = rcCarQueryService.getCarStyleByLyId(lyIds);
		// 获取结果集，将结果以，分割，放入结果集中
		// 各个模块数据放进去
		List<String> bfQzdArray = new ArrayList<String>();
		List<String> bfHzdArray = new ArrayList<String>();
		List<String> bdsQzdArray = new ArrayList<String>();
		List<String> bdsHzdArray = new ArrayList<String>();

		List<String> aviaRhyArray = new ArrayList<String>();
		List<String> aviaRhyByjzlArray = new ArrayList<String>();
		List<Map<String, Object>> aviaBsxyArray = new ArrayList<Map<String, Object>>();
		List<String> aviaBsxyByjzlArray = new ArrayList<String>();
		List<String> aviaBsxyDxjzlArray = new ArrayList<String>();

		List<Map<String, Object>> nbeArray = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> hhsArray = new ArrayList<Map<String, Object>>();

		Map<String, Object> rc = new HashMap<String, Object>();
		if (ls.size() != 0) {
			rc = ls.get(0);
			for (int i = 0; i < ls.size(); i++) {
				Map<String, Object> m = ls.get(i);
				bfQzdArray.add(m.get("bfQzd1").toString());
				bfQzdArray.add(m.get("bfQzd2").toString());
				bfQzdArray.add(m.get("bfQzd3").toString());

				bfHzdArray.add(m.get("bfHzd1").toString());
				bfHzdArray.add(m.get("bfHzd2").toString());
				bfHzdArray.add(m.get("bfHzd3").toString());

				bdsQzdArray.add(m.get("bdsQzd1").toString());
				bdsQzdArray.add(m.get("bdsQzd2").toString());
				bdsQzdArray.add(m.get("bdsQzd3").toString());

				bdsHzdArray.add(m.get("bdsHzd1").toString());
				bdsHzdArray.add(m.get("bdsHzd2").toString());
				bdsHzdArray.add(m.get("bdsHzd3").toString());

				aviaRhyArray.add(m.get("rhyAvia1").toString());
				aviaRhyArray.add(m.get("rhyAvia2").toString());
				aviaRhyArray.add(m.get("rhyAvia3").toString());
				aviaRhyArray.add(m.get("rhyAvia4").toString());
				aviaRhyByjzlArray.add(m.get("rhybyjzl").toString());

				Map<String, Object> bsxy = new HashMap<String, Object>();
				bsxy.put("info", m.get("bsxyAvia").toString());
				bsxy.put("bz", m.get("bsxybz").toString());
				aviaBsxyArray.add(bsxy);
				aviaBsxyByjzlArray.add(m.get("bsxbyjzl").toString());
				aviaBsxyDxjzlArray.add(m.get("bsxdxjzl").toString());

				Map<String, Object> nbe1 = new HashMap<String, Object>();
				nbe1.put("info", m.get("nbe1").toString());
				nbe1.put("bz", "");
				nbeArray.add(nbe1);
				Map<String, Object> nbe2 = new HashMap<String, Object>();
				nbe2.put("info", m.get("nbe2").toString());
				nbe2.put("bz", "");
				nbeArray.add(nbe2);

				Map<String, Object> lg1 = new HashMap<String, Object>();
				lg1.put("info", m.get("lg1").toString());
				lg1.put("bz", "");
				hhsArray.add(lg1);
				Map<String, Object> lg2 = new HashMap<String, Object>();
				lg2.put("info", m.get("lg2").toString());
				lg2.put("bz", "");
				hhsArray.add(lg2);
			}
		}

		map.put("bfQzd", arrayToOutRepet(bfQzdArray));
		map.put("bfHzd", arrayToOutRepet(bfHzdArray));
		map.put("bdsQzd", arrayToOutRepet(bdsQzdArray));
		map.put("bdsHzd", arrayToOutRepet(bdsHzdArray));
		map.put("aviaRhy", arrayToOutRepet(aviaRhyArray));
		map.put("aviaRhyJzl", arrayToOutRepet(aviaRhyByjzlArray));
		map.put("aviaBsxy", mapToOutRepet(aviaBsxyArray));
		map.put("aviaBsxyDxjzl", arrayToOutRepet(aviaBsxyDxjzlArray));
		map.put("aviaBsxyByjzl", arrayToOutRepet(aviaBsxyByjzlArray));
		map.put("hhs", mapToOutRepet(hhsArray));
		map.put("nbe", mapToOutRepet(nbeArray));
		map.put("rc", rc);
		map.put("ls", ls);
		return map;
	};
	/**
	 * @titlC: 根据vin码查询车型数据
	 * @param dictCatCode
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/findVehicleInfoByVinCode")
	@ResponseBody
	public JsonMsg findVehicleInfoByVinCode(String vinCode) {
		JsonMsg msgs = new JsonMsg();
		Map<String, Object> data = new HashMap<String, Object>();
		// String vinCode=request.getParameter("vinCode");
		String xmlInput = "<root><appkey>cac3a8c229c48a24</appkey><appsecret>c38446546e844c0ca77d9255a777c95e</appsecret><method>level.vehicle.vin.get</method><requestformat>json</requestformat><vin>"
				+ vinCode + "</vin></root>";// 传参是XML格式的字符串，xmlInput中的特殊字符需要转义，比如<>
		xmlInput = xmlInput.replace("<", "&lt;");
		xmlInput = xmlInput.replace(">", "&gt;");
		String soapRequestData = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"
				+ "<soap12:Body>"
				+ " <LevelData xmlns=\"http://www.dat881.com/\">"
				+ "<xmlInput>" + xmlInput + "</xmlInput>" + "</LevelData>" // 接口传入参数
				+ "  </soap12:Body>" + "</soap12:Envelope>";

		PostMethod postMethod = null;
		postMethod = new PostMethod("http://service.vin114.net/req"); // 接口地址

		byte[] b = null;
		try {
			b = soapRequestData.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		InputStream is = new ByteArrayInputStream(b, 0, b.length);
		RequestEntity re = new InputStreamRequestEntity(is, b.length,
				"application/soap+xml; charset=utf-8");
		postMethod.setRequestEntity(re);

		HttpClient httpClient = new HttpClient();
		try {
			httpClient.executeMethod(postMethod);
			soapRequestData = postMethod.getResponseBodyAsString(); // 获取接口的全部返回内容
			System.out.println(soapRequestData);
			InputSource source = new InputSource(new StringReader(
					soapRequestData));
			SAXReader reader = new SAXReader();
			Document doc = reader.read(source);
			DefaultXPath xpath = new DefaultXPath("/soap:Envelope/soap:Body");
			List list = xpath.selectNodes(doc);
			Iterator iterator2 = list.iterator();
			JSONObject ob = new JSONObject();
			while (iterator2.hasNext()) {
				Element node = (Element) iterator2.next();
				String content = node.getStringValue();
				ob = JSONObject.fromObject(content);
			}
			JSONArray rec = new JSONArray();
			JSONObject info = new JSONObject();
			JSONObject AddJson = new JSONObject();
			if (ob != null && ob.get("Result") != null) {
				rec = (JSONArray) ob.get("Result");
				data.put("result", rec);
			}
			if (ob != null && ob.get("Info") != null) {
				info = (JSONObject) ob.get("Info");
			}
			if (ob != null && ob.get("Additional") != null) {
				AddJson = (JSONObject) ob.get("Additional");
				data.put("Vin", AddJson.get("Vin"));
				data.put("VinYear", AddJson.get("VinYear"));
			}
			if (info.get("Success").toString().equals("true")) {
				Object o = rec.get(0);
			} else {

				System.out.println("err..");
			}

		} catch (Exception e) {
			System.out.println("errTack" + e);
		}
		msgs.setData(data);
		return msgs;
	}

	/**
	 * List<String> str 去重
	 * 
	 * @param str
	 * @return
	 */
	public static List<String> arrayToOutRepet(List<String> str) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < str.size(); i++) {
			if (!list.contains(str.get(i)) && StringUtil.isNotEmpty(str.get(i))) {

				list.add(str.get(i));
			}
		}
		return list;

	}

	/**
	 * List<Map> str 去重
	 * 
	 * @param str
	 * @return
	 */
	public static List<Map<String, Object>> mapToOutRepet(
			List<Map<String, Object>> list) {
		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> oldMap : list) {
			String oldInfo = oldMap.get("info").toString();
			String oldBz = oldMap.get("bz").toString();
			int t = 0;
			for (Map<String, Object> newMap : outList) {
				String newInfo = newMap.get("info").toString();
				String newBz = newMap.get("bz").toString();
				// 如果两个值相等，或者为空
				if (newInfo.equals(oldInfo)) {
					// 如果info相等，判断bz是否相等，也相等break
					if (newBz.equals(oldBz)) {
						break;
					}
				}
				t++;
			}
			if (t == outList.size()) {
				if (StringUtil.isNotEmpty(oldMap.get("info").toString())
						|| StringUtil.isNotEmpty(oldMap.get("bz").toString())) {
					outList.add(oldMap);
				}

			}
		}

		return outList;

	}

	/**
	 * 将字符串分割成数组
	 * 
	 * @param str
	 * @return
	 */
	public static String[] convertStrToArray(String str) {
		String[] strArray = null;
		strArray = str.split(","); // 拆分字符为"," ,然后把结果交给数组strArray
		return strArray;
	}
}
