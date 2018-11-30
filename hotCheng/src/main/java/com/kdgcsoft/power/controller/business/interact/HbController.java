package com.kdgcsoft.power.controller.business.interact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.CacheManager;

import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdgcsoft.power.common.util.StringUtil;
import com.kdgcsoft.power.controller.fw.base.BaseController;
import com.kdgcsoft.power.entity.business.interact.Bds;
import com.kdgcsoft.power.entity.business.interact.Bf;
import com.kdgcsoft.power.entity.business.interact.Cp;
import com.kdgcsoft.power.entity.business.interact.Dbtable;
import com.kdgcsoft.power.entity.business.interact.Hb;
import com.kdgcsoft.power.entity.business.interact.Hbtable;
import com.kdgcsoft.power.service.business.interact.HbService;

/**
 * pc端使用端口
 * 
 * @author lenovo
 * 
 */
@Controller
@RequestMapping("/hb")
public class HbController extends BaseController {
	private static CacheManager cacheManager = CacheManager.create();

	@Autowired
	private SQLManager sqlManager;
	
	@Autowired
	private HbService hbs;
	@RequestMapping(value = "/setHb", method = RequestMethod.GET)
	@ResponseBody
	public void setHb(){
		//分组查找hb表中的数据，列表，将相同不重复的数据添加到hb_copy中
		List<Hb> list = hbs.getList();
		Map<String, Object> en = new HashMap<String, Object>();
		//dArray是D码，tarray是trw码，farray是fl码，oarray是oe码，carray是产品
		List<String> dArray = new ArrayList<String>();
		List<String> tArray = new ArrayList<String>();
		List<String> fArray = new ArrayList<String>();
		List<String> oArray = new ArrayList<String>();
		List<String> cArray = new ArrayList<String>();
		for(int i=0; i<list.size();i++){
			Hb hb = list.get(i);
			//如果en中没有pp这个字段，说明都没有，将pp，cx等信息放入到en中去
			if (!en.containsKey("pp")) {   
				en.put("pp", hb.getPp());
				en.put("cj", hb.getCj());
				en.put("cx", hb.getCx());
				en.put("pl", hb.getPl());
				en.put("nk", hb.getNk());
				en.put("scplx", hb.getScplx());
			}
			//判断en中的六个字段是否和map中的一样，如果一样，把信息存到对应的list中去
			if (en.get("pp").equals(hb.getPp()) && en.get("cj").equals(hb.getCj()) && en.get("cx").equals(hb.getCx())
					 && en.get("pl").equals(hb.getPl())  && en.get("nk").equals(hb.getNk()) && en.get("scplx").equals(hb.getScplx())) {
				dArray.add(hb.getD1());
				dArray.add(hb.getD2());
				dArray.add(hb.getD3());
				dArray.add(hb.getD4());
				dArray.add(hb.getD5());
				dArray.add(hb.getD6());
				dArray.add(hb.getD7());
				
				tArray.add(hb.getT1());
				tArray.add(hb.getT2());
				
				fArray.add(hb.getFl1());
				fArray.add(hb.getFl2());
				
				oArray.add(hb.getO1());
				oArray.add(hb.getO2());
				oArray.add(hb.getO3());
				
				cArray.add(hb.getC1());cArray.add(hb.getC2());cArray.add(hb.getC3());cArray.add(hb.getC4());
				cArray.add(hb.getC5());cArray.add(hb.getC6());cArray.add(hb.getC7());cArray.add(hb.getC8());
				cArray.add(hb.getC9());cArray.add(hb.getC10());cArray.add(hb.getC11());cArray.add(hb.getC12());
				cArray.add(hb.getC13());cArray.add(hb.getC14());cArray.add(hb.getC15());cArray.add(hb.getC16());
				cArray.add(hb.getC17());cArray.add(hb.getC18());cArray.add(hb.getC19());cArray.add(hb.getC20());
				cArray.add(hb.getC21());
				
			} else {
				//对所有数组进行去重，并且将信息添加到en中去，保存到hbCopy表中
				dArray = arrayToOutRepet(dArray);
				tArray = arrayToOutRepet(tArray);
				fArray = arrayToOutRepet(fArray);
				oArray = arrayToOutRepet(oArray);
				cArray = arrayToOutRepet(cArray);
				//将信息添加到en中，如果为空则添空字符串
				for(int di=0;di<dArray.size();di++){
					int d = di+1;
					en.put("d"+d, dArray.get(di));
				}
				if(dArray.size() < 7){
					for(int dd=dArray.size()+1;dd<8;dd++){
						en.put("d"+dd, "");
					}
				}
				for(int ti=0;ti<tArray.size();ti++){
					int t = ti+1;
					en.put("t"+t, tArray.get(ti));
				}
				if(tArray.size() < 2){
					for(int tt=tArray.size()+1;tt<3;tt++){
						en.put("t"+tt, "");
					}
				}
				for(int fi=0;fi<fArray.size();fi++){
					int f = fi+1;
					en.put("fl"+f, fArray.get(fi));
				}
				if(fArray.size() < 2){
					for(int ff=fArray.size()+1;ff<3;ff++){
						en.put("fl"+ff, "");
					}
				}
				for(int oi=0;oi<oArray.size();oi++){
					int o = oi+1;
					en.put("o"+o, oArray.get(oi));
				}
				if(oArray.size() < 3){
					for(int oo=oArray.size()+1;oo<4;oo++){
						en.put("o"+oo, "");
					}
				}
				for(int ci=0;ci<cArray.size();ci++){
					int c = (ci+1);
					en.put("c"+c, cArray.get(ci));
				}
				if(cArray.size() < 5){
					for(int cc=cArray.size()+1;cc<6;cc++){
						en.put("c"+cc, "");
					}
				}
				//执行insert
				hbs.insert(en);
				
				en = new HashMap<String, Object>();
				dArray = new ArrayList<String>();
				tArray = new ArrayList<String>();
				fArray = new ArrayList<String>();
				oArray = new ArrayList<String>();
				cArray = new ArrayList<String>();
				
				en.put("pp", hb.getPp());
				en.put("cj", hb.getCj());
				en.put("cx", hb.getCx());
				en.put("pl", hb.getPl());
				en.put("nk", hb.getNk());
				en.put("scplx", hb.getScplx());
				
				if (en.get("pp").equals(hb.getPp()) && en.get("cj").equals(hb.getCj()) && en.get("cx").equals(hb.getCx())
						 && en.get("pl").equals(hb.getPl())  && en.get("nk").equals(hb.getNk()) && en.get("scplx").equals(hb.getScplx())) {
					dArray.add(hb.getD1());
					dArray.add(hb.getD2());
					dArray.add(hb.getD3());
					dArray.add(hb.getD4());
					dArray.add(hb.getD5());
					dArray.add(hb.getD6());
					dArray.add(hb.getD7());
					
					tArray.add(hb.getT1());
					tArray.add(hb.getT2());
					
					fArray.add(hb.getFl1());
					fArray.add(hb.getFl2());
					
					oArray.add(hb.getO1());
					oArray.add(hb.getO2());
					oArray.add(hb.getO3());
					
					cArray.add(hb.getC1());cArray.add(hb.getC2());cArray.add(hb.getC3());cArray.add(hb.getC4());
					cArray.add(hb.getC5());cArray.add(hb.getC6());cArray.add(hb.getC7());cArray.add(hb.getC8());
					cArray.add(hb.getC9());cArray.add(hb.getC10());cArray.add(hb.getC11());cArray.add(hb.getC12());
					cArray.add(hb.getC13());cArray.add(hb.getC14());cArray.add(hb.getC15());cArray.add(hb.getC16());
					cArray.add(hb.getC17());cArray.add(hb.getC18());cArray.add(hb.getC19());cArray.add(hb.getC20());
					cArray.add(hb.getC21());
					
				}
				
			} 
			if (i == list.size()-1) {
				dArray = arrayToOutRepet(dArray);
				tArray = arrayToOutRepet(tArray);
				fArray = arrayToOutRepet(fArray);
				oArray = arrayToOutRepet(oArray);
				cArray = arrayToOutRepet(cArray);
				//将信息添加到en中，如果为空则添空字符串
				for(int di=0;di<dArray.size();di++){
					int d = di+1;
					en.put("d"+d, dArray.get(di));
				}
				if(dArray.size() < 7){
					for(int dd=dArray.size()+1;dd<8;dd++){
						en.put("d"+dd, "");
					}
				}
				for(int ti=0;ti<tArray.size();ti++){
					int t = ti+1;
					en.put("t"+t, tArray.get(ti));
				}
				if(tArray.size() < 2){
					for(int tt=tArray.size()+1;tt<3;tt++){
						en.put("t"+tt, "");
					}
				}
				for(int fi=0;fi<fArray.size();fi++){
					int f = fi+1;
					en.put("fl"+f, fArray.get(fi));
				}
				if(fArray.size() < 2){
					for(int ff=fArray.size()+1;ff<3;ff++){
						en.put("fl"+ff, "");
					}
				}
				for(int oi=0;oi<oArray.size();oi++){
					int o = oi+1;
					en.put("o"+o, oArray.get(oi));
				}
				if(oArray.size() < 3){
					for(int oo=oArray.size()+1;oo<4;oo++){
						en.put("o"+oo, "");
					}
				}
				for(int ci=0;ci<cArray.size();ci++){
					int c = (ci+1);
					en.put("c"+c, cArray.get(ci));
				}
				if(cArray.size() < 5){
					for(int cc=cArray.size()+1;cc<6;cc++){
						en.put("c"+cc, "");
					}
				}
				hbs.insert(en);
			}  
		}
		
	}
	
	@RequestMapping(value = "/deleteBds", method = RequestMethod.GET)
	@ResponseBody
	public void deleteBds(){
		//分组查找hb表中的数据，列表，将相同不重复的数据添加到hb_copy中
		List<Hb> list = hbs.getList();
		
		for(int i=0; i<list.size();i++){
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("pp", list.get(i).getPp());
			m.put("cj", list.get(i).getCj());
			m.put("cx", list.get(i).getCx());
			m.put("pl", list.get(i).getPl());
			m.put("nk", list.get(i).getNk());
			m.put("scplx", list.get(i).getScplx());
			hbs.deleteBds(m);
		}
	}
	
	
	@RequestMapping(value = "/setBf", method = RequestMethod.GET)
	@ResponseBody
	public void setBf(){
		//分组查找hb表中的数据，列表，将相同不重复的数据添加到hb_copy中
		List<Bf> list = hbs.getBf();
		Map<String, Object> en = new HashMap<String, Object>();
		List<String> dArray = new ArrayList<String>();
		
		for(int i=0; i<list.size();i++){
			Bf hb = list.get(i);
			//如果en中没有pp这个字段，说明都没有，将pp，cx等信息放入到en中去
			if (!en.containsKey("pp")) {   
				en.put("pp", hb.getPp());
				en.put("cj", hb.getCj());
				en.put("cx", hb.getCx());
				en.put("pl", hb.getPl());
				en.put("nk", hb.getNk());
				en.put("scplx", hb.getScplx());
			}
			//判断en中的六个字段是否和map中的一样，如果一样，把信息存到对应的list中去
			if (en.get("pp").equals(hb.getPp()) && en.get("cj").equals(hb.getCj()) && en.get("cx").equals(hb.getCx())
					 && en.get("pl").equals(hb.getPl())  && en.get("nk").equals(hb.getNk()) && en.get("scplx").equals(hb.getScplx())) {
				dArray.add(hb.getBm());
				
			} else {
				//对所有数组进行去重，并且将信息添加到en中去，保存到hbCopy表中
				dArray = arrayToOutRepet(dArray);
				
				//将信息添加到en中，如果为空则添空字符串
				for(int di=0;di<dArray.size();di++){
					int d = di+1;
					en.put("bm"+d, dArray.get(di));
				}
				if(dArray.size() < 5){
					for(int dd=dArray.size()+1;dd<6;dd++){
						en.put("bm"+dd, "");
					}
				}
				
				//执行insert
				hbs.insertBf(en);
				
				en = new HashMap<String, Object>();
				dArray = new ArrayList<String>();
				
				
				en.put("pp", hb.getPp());
				en.put("cj", hb.getCj());
				en.put("cx", hb.getCx());
				en.put("pl", hb.getPl());
				en.put("nk", hb.getNk());
				en.put("scplx", hb.getScplx());
				
				if (en.get("pp").equals(hb.getPp()) && en.get("cj").equals(hb.getCj()) && en.get("cx").equals(hb.getCx())
						 && en.get("pl").equals(hb.getPl())  && en.get("nk").equals(hb.getNk()) && en.get("scplx").equals(hb.getScplx())) {
					dArray.add(hb.getBm());
					
					
				}
				
			} 
			if (i == list.size()-1) {
				dArray = arrayToOutRepet(dArray);
				
				//将信息添加到en中，如果为空则添空字符串
				for(int di=0;di<dArray.size();di++){
					int d = di+1;
					en.put("bm"+d, dArray.get(di));
				}
				if(dArray.size() < 5){
					for(int dd=dArray.size()+1;dd<6;dd++){
						en.put("bm"+dd, "");
					}
				}
				
				hbs.insertBf(en);
			}  
		}
		
	}
	
	@RequestMapping(value = "/setBds", method = RequestMethod.GET)
	@ResponseBody
	public void setBds(){
		//分组查找hb表中的数据，列表，将相同不重复的数据添加到hb_copy中
		List<Bds> list = hbs.getBds();
		Map<String, Object> en = new HashMap<String, Object>();
		List<String> dArray = new ArrayList<String>();
		
		for(int i=0; i<list.size();i++){
			Bds hb = list.get(i);
			//如果en中没有pp这个字段，说明都没有，将pp，cx等信息放入到en中去
			if (!en.containsKey("pp")) {   
				en.put("pp", hb.getPp());
				en.put("cj", hb.getCj());
				en.put("cx", hb.getCx());
				en.put("pl", hb.getPl());
				en.put("nk", hb.getNk());
				en.put("scplx", hb.getScplx());
			}
			//判断en中的六个字段是否和map中的一样，如果一样，把信息存到对应的list中去
			if (en.get("pp").equals(hb.getPp()) && en.get("cj").equals(hb.getCj()) && en.get("cx").equals(hb.getCx())
					 && en.get("pl").equals(hb.getPl())  && en.get("nk").equals(hb.getNk()) && en.get("scplx").equals(hb.getScplx())) {
				dArray.add(hb.getBm());
				
			} else {
				//对所有数组进行去重，并且将信息添加到en中去，保存到hbCopy表中
				dArray = arrayToOutRepet(dArray);
				
				//将信息添加到en中，如果为空则添空字符串
				for(int di=0;di<dArray.size();di++){
					int d = di+1;
					en.put("bm"+d, dArray.get(di));
				}
				if(dArray.size() < 5){
					for(int dd=dArray.size()+1;dd<6;dd++){
						en.put("bm"+dd, "");
					}
				}
				
				//执行insert
				hbs.insertBds(en);
				
				en = new HashMap<String, Object>();
				dArray = new ArrayList<String>();
				
				
				en.put("pp", hb.getPp());
				en.put("cj", hb.getCj());
				en.put("cx", hb.getCx());
				en.put("pl", hb.getPl());
				en.put("nk", hb.getNk());
				en.put("scplx", hb.getScplx());
				
				if (en.get("pp").equals(hb.getPp()) && en.get("cj").equals(hb.getCj()) && en.get("cx").equals(hb.getCx())
						 && en.get("pl").equals(hb.getPl())  && en.get("nk").equals(hb.getNk()) && en.get("scplx").equals(hb.getScplx())) {
					dArray.add(hb.getBm());
					
					
				}
				
			} 
			if (i == list.size()-1) {
				dArray = arrayToOutRepet(dArray);
				
				//将信息添加到en中，如果为空则添空字符串
				for(int di=0;di<dArray.size();di++){
					int d = di+1;
					en.put("bm"+d, dArray.get(di));
				}
				if(dArray.size() < 5){
					for(int dd=dArray.size()+1;dd<6;dd++){
						en.put("bm"+dd, "");
					}
				}
				
				hbs.insertBds(en);
			}  
		}
		
	}
	public Map<String, Object> setDbMap(Hbtable h){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("pp", h.getPp());
		para.put("cj", h.getCj());
		para.put("cx", h.getCx());
		para.put("pl", h.getPl());
		para.put("nk", h.getNk());
		para.put("scplx", h.getScplx());
		
		para.put("d1",h.getD1());
		para.put("d2",h.getD2());
		para.put("d3",h.getD3());
		para.put("d4",h.getD4());
		para.put("d5",h.getD5());
		para.put("d6",h.getD6());
		para.put("d7",h.getD7());
		para.put("t1",h.getT1());
		para.put("t2",h.getT2());
		para.put("fl1",h.getFl1());
		para.put("fl2",h.getFl2());
		para.put("o1",h.getO1());
		para.put("o2",h.getO2());
		para.put("o3",h.getO3());
		para.put("c11",h.getC11());
		para.put("c12",h.getC12());
		para.put("c13",h.getC13());
		para.put("c14",h.getC14());
		
		para.put("c21",h.getC21());
		para.put("c22",h.getC22());
		para.put("c23",h.getC23());
		para.put("c24",h.getC24());
		
		para.put("c31",h.getC31());
		para.put("c32",h.getC32());
		para.put("c33",h.getC33());
		para.put("c34",h.getC34());
		
		para.put("c41",h.getC41());
		para.put("c42",h.getC42());
		para.put("c43",h.getC43());
		para.put("c44",h.getC44());
		
		para.put("bf1",h.getBf1());
		para.put("bf2",h.getBf2());
		para.put("bf3",h.getBf3());
		para.put("bf4",h.getBf4());
		para.put("bf5",h.getBf5());
		
		para.put("bds1",h.getBds1());
		para.put("bds2",h.getBds2());
		para.put("bds3",h.getBds3());
		para.put("bds4",h.getBds4());
		return para;
	}
	public Map<String, Object> setParamByArray(Map<String, Object> para,List<String> bArray,String param){
		for(int di=0;di<bArray.size();di++){
			int d = di+1;
			para.put(param+d, bArray.get(di));
		}
		if(bArray.size() < 4){
			for(int dd=bArray.size()+1;dd<5;dd++){
				para.put(param+dd, "");
			}
		}
		return para;
	}
	@RequestMapping(value = "/setDb", method = RequestMethod.GET)
	@ResponseBody
	public void setDb(){
		
		List<Hbtable> list = hbs.getHbList();
		
		for(int i=0; i<list.size();i++){
			Hbtable h = list.get(i);
			
			Map<String, Object> para = setDbMap(h);
			//bdsArray奔德士编码组，用来查找转换宝丰编码的
			List<String> bdsArray = new ArrayList<String>();
			bdsArray.add(h.getBds1());
			bdsArray.add(h.getBds2());
			bdsArray.add(h.getBds3());
			bdsArray.add(h.getBds4());
			Map<String, Object> bdsPara = new HashMap<String, Object>();
			System.out.println(bdsArray+"bds=="+removeNull(bdsArray));
			bdsArray = removeNull(bdsArray);
			List<Cp> cp = new ArrayList<Cp>();
			List<String> bdsToBfArray = new ArrayList<String>();
			if(bdsArray.size() != 0){
				bdsPara.put("bds", removeNull(bdsArray));
				//根据奔德士查询宝丰编码
				cp = hbs.getBfByBds(bdsPara);
				if(cp.size() != 0){
					for(int cpl=0;  cpl<cp.size(); cpl++){
						bdsToBfArray.add(cp.get(cpl).getBf());
					}
				}
			}
			
			//chang1-4不管先放进去
			bdsToBfArray = removeNull(bdsToBfArray);
			para = setParamByArray(para,bdsToBfArray,"chang");
			
			//原宝丰编码
			List<String> bfArray = new ArrayList<String>();
			bfArray.add(h.getBf1());
			bfArray.add(h.getBf2());
			bfArray.add(h.getBf3());
			bfArray.add(h.getBf4());
			bfArray = removeNull(bfArray);
			//对比原宝丰编码和转换的编码
			//如果长度都不相等，那肯定就是不一样的
			System.out.println("ybf="+bfArray);
			System.out.println("bf="+bdsToBfArray);
			int k = 0;
			if(bfArray.size() == bdsToBfArray.size()){
				//判断元素是否相等
				for (String str : bfArray) {
		            if (!bdsToBfArray.contains(str)) {
		                k++;
		                break;
		            }
		        }
				if(k != 0){
					
					para = setParamByArray(para,new ArrayList<String>(),"zh");
					para.put("cy", "");
					System.out.println("不相等"+k);
				}else{
					para = setParamByArray(para,bdsToBfArray,"zh");
					para.put("cy", "");
					System.out.println("相等"+k);
				}
			}else{
				//不相等，判断是否有个数组是0
				if(bfArray.size() == 0){
					System.out.println("宝丰号为空");
					para = setParamByArray(para,bdsToBfArray,"zh");
					para.put("cy", "");
				}else if(bdsToBfArray.size() == 0){
					System.out.println("翻译为空");
					para = setParamByArray(para,bfArray,"zh");
					para.put("cy", "");
				}else{
					para = setParamByArray(para,new ArrayList<String>(),"zh");
					para.put("cy", "1");
				}
			}
			
			hbs.insertDb(para); 
		}
		
	}
	
	
	/**
	 * 去除空值
	 * @param str
	 * @return
	 */
	public static List<String> removeNull(List<String> str) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < str.size(); i++) {
			if(StringUtil.isNotEmpty(str.get(i))){
				list.add(str.get(i));
			}
		}
		return list;

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
	
	  

}
