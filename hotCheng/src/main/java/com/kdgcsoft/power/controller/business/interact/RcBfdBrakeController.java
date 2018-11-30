package com.kdgcsoft.power.controller.business.interact;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import net.sf.ehcache.CacheManager;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kdgcsoft.power.common.bean.ConflictException;
import com.kdgcsoft.power.common.bean.JsonMsg;
import com.kdgcsoft.power.common.bean.PageObject;
import com.kdgcsoft.power.common.bean.SystemConstants;
import com.kdgcsoft.power.common.util.StringUtil;
import com.kdgcsoft.power.controller.fw.base.BaseController;
import com.kdgcsoft.power.entity.business.interact.RcBfdBrake;
import com.kdgcsoft.power.entity.business.interact.RpStatistics;
import com.kdgcsoft.power.service.business.interact.RcBfdBrakeService;
import com.xiaoleilu.hutool.util.CollectionUtil;

/**
 * pc端使用端口
 * 制动查询接口
 * 
 * @author lenovo
 * 
 */
@Controller
@RequestMapping("/pcBfd")
public class RcBfdBrakeController extends BaseController {
	private static CacheManager cacheManager = CacheManager.create();

	@Autowired
	private RcBfdBrakeService rcBfdBrakeService;
	@Autowired
	private SQLManager sqlManager;

	/**
	 * 查找制动信息
	 * 
	 * @param request
	 * @param bfd 技术BFD
	 * 		  bCode 宝丰号或奔德士号
	 * @return
	 */
	@RequestMapping(value = "/getList")
	@ResponseBody
	public Object getList(HttpServletRequest request, Integer page, Integer rows) {
		String bfd = request.getParameter("bfd");
		String bCode = request.getParameter("bCode");
		PageRequest pageRequest = new PageRequest(page, rows);
		PageObject<Map<String, Object>> result = new PageObject<Map<String, Object>>();

		if (StringUtil.isEmpty(bfd)) {
			bfd = null;
		}

		if (StringUtil.isEmpty(bCode)) {
			bCode = null;
		} 
		
		List<Map<String, Object>> ls = rcBfdBrakeService.getList(bfd,bCode, pageRequest);
		List<Map<String, Object>> rs = CollectionUtil.sub(ls,(page - 1) * rows, page * rows);
		result.setTotalCount((long) ls.size());
		result.setList(rs);
		return getPageModel(result);

	};

	 @RequestMapping("/tofindPic")
	 @ResponseBody
	 public void tofindPic(String imgName, HttpServletRequest request,HttpServletResponse response){
		
		 FileInputStream in;
		 response.setContentType("application/octet-stream;charset=UTF-8");
		 try {
			 //图片读取路径
			in=new FileInputStream("E:\\zdqImg\\"+imgName);
			int i=in.available();
			byte[]data=new byte[i];
			in.read(data);
			in.close();
			
			//写图片
			OutputStream outputStream=new BufferedOutputStream(response.getOutputStream());
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }



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
		RcBfdBrake s = rcBfdBrakeService.getInfo(Long.parseLong(id));
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
		rcBfdBrakeService.delete(Long.parseLong(id));
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
	public JsonMsg save(HttpServletRequest request, RcBfdBrake entity) {
		Long id = entity.getId();
		if (id != null) {
			rcBfdBrakeService.update(entity);
		} else {
			rcBfdBrakeService.insert(entity);
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
		RcBfdBrake entity = new RcBfdBrake();
		if (!StringUtil.isEmpty(id)) {// 修改
			entity = rcBfdBrakeService.getInfo(Long.parseLong(id));
		}
		model.addObject("entity", entity);
		model.setViewName("view/business/rcBfdBrakeForm.jsp");
		return model;
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
				RcBfdBrake s = new RcBfdBrake();
				s.setWheel(sheet.getCell(0, i+1).getContents());
				s.setBfd(sheet.getCell(1, i+1).getContents());
				s.setBfCode(sheet.getCell(2, i+1).getContents());
				s.setBfMemo(sheet.getCell(3, i+1).getContents());
				s.setBdsCode(sheet.getCell(4, i+1).getContents());
				s.setBdsMemo(sheet.getCell(5, i+1).getContents());
				s.setdCode(sheet.getCell(6, i+1).getContents());
				s.setFmsiCode(sheet.getCell(7, i+1).getContents());
				s.setFldm1(sheet.getCell(8, i+1).getContents());
				s.setFldm2(sheet.getCell(9, i+1).getContents());
				s.setTrw1(sheet.getCell(10, i+1).getContents());
				s.setTrw2(sheet.getCell(11, i+1).getContents());
				s.setOe1(sheet.getCell(12, i+1).getContents());
				s.setOe2(sheet.getCell(13, i+1).getContents());
				s.setOe3(sheet.getCell(14, i+1).getContents());
				s.setOe4(sheet.getCell(15, i+1).getContents());
				s.setOe5(sheet.getCell(16, i+1).getContents());
				
				rcBfdBrakeService.insert(s);
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
		RcBfdBrake entity = new RcBfdBrake();
		if (!StringUtil.isEmpty(id)) {
			entity = rcBfdBrakeService.getInfo(Long.parseLong(id));
		}
		model.addObject("entity", entity);
		model.setViewName("view/business/rcBfdBrakeDetail.jsp");
		return model;
	}
	
	@RequestMapping(value = "/loadImg")
	public ModelAndView loadImg(String id, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		RcBfdBrake entity = new RcBfdBrake();
		entity = rcBfdBrakeService.getInfo(Long.parseLong(id));
		model.addObject("entity", entity);
		model.setViewName("view/business/upload.jsp");
		return model;
	}
	
	@RequestMapping("uploadImg")
	@ResponseBody
    public Object uploadImg(String id, HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> result = new HashMap<String, Object>();
        boolean executeResult = false;
        try {
          
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            String realPath = "";
            for (Iterator it = multipartRequest.getFileNames(); it.hasNext();) {
                String key = (String) it.next();
                MultipartFile mulfile = multipartRequest.getFile(key);
                //封装处理文件工具类Tools
                String pathTmp = saveFile2(multipartRequest, mulfile);
                if(!"".equals(pathTmp)){
                    realPath += pathTmp; 
                }
            }
            System.out.println("RcBfdBrakeController.uploadImg()"+realPath);
            //修改信息的imgSrc
            Map<String, Object> para = new HashMap<String, Object>();
            para.put("id", id);
            para.put("imgSrc", realPath);
            rcBfdBrakeService.updateImg(para);
          
            result.put("success", "true");
            result.put("id", id);
            result.put("imgSrc", realPath);
           // response.getWriter().print(result);
            executeResult = true;
        } catch (Exception e) {
        	 result.put("success", "false");
            try {
                response.getWriter().print("false");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            executeResult = false;
            e.printStackTrace();
        } finally {   
            //插入操作日志
        }
        return result;
    }
	 /**
     * 工具类：上传文件：不改名字
     */
    public static String saveFile2(HttpServletRequest request,
            MultipartFile file) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                String separator = File.separator;
                String picPath2 = System.getProperty("user.dir").replace("bin","webapps")+ File.separator;
                String directory = "E:\\zdqImg/";//picPath2 + "wyInFile" + separator; 
                String newPicName = "";
                if (file.getSize() != 0) { 
                    String originalFileNameLeft = file.getOriginalFilename();
                    // 新的图片名称
                    int index = originalFileNameLeft.lastIndexOf(".");
                    newPicName = originalFileNameLeft.substring(0, index)
                            + originalFileNameLeft.substring(index);
                    // 新图片，写入磁盘
                    File f = new File(directory, newPicName);
                    if (!f.exists()) {
                        // 先创建文件所在的目录
                        f.getParentFile().mkdirs();
                    } else {
                        f.delete();
                    }

                    file.transferTo(f);
                }
                return newPicName;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }
	
	/*@RequestMapping(value="uploadImg")
	@ResponseBody
	public void uploadImg(@RequestParam(value="file",required=false)MultipartFile file, String id, HttpServletRequest request,HttpServletResponse response)throws IOException {
		String fileName = file.getOriginalFilename();
		System.out.println("fileName=="+fileName);
		if(!file.isEmpty()){
			String path = "E:/zdqImg/";
			File targetFile = new File(path, fileName); 
			OutputStream out = null;
			InputStream in = null;
			try{
				byte[] buf = file.getBytes();
				int length= in.read(buf);
				while(length != -1){
					out.write(buf, 0, length);
				}
				out.flush();
				in.close();
				out.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	 
	
	
	}*/
}
