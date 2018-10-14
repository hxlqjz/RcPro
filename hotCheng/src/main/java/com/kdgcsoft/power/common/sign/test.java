package com.kdgcsoft.power.common.sign;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.kdgcsoft.power.common.sign.itext.PdfSignBox;
import com.kdgcsoft.power.common.sign.itext.PdfSignItext;





public class test {
	public static void main(String[] args) throws  Exception {
		     String KEYSTORE="d://sign//test.p12";
		       char[] PASSWORD = "123".toCharArray();//keystory密码
		       String SRC="d://sign//demo.pdf" ;//原始pdf
		       String DEST="d://sign//demo_signed_box.pdf" ;//签名完成的pdf
		       String DEST2="d://sign//demo_signed_itext.pdf" ;//签名完成的pdf
		      String chapterPath="d://sign//chapter.png";//签章图片
		      String signername="測試";
		      String reason="数据不可更改";
		      String location="桃源乡";
		     
	PdfSignBox.sign(PASSWORD, new FileInputStream(KEYSTORE), 
			new FileInputStream(chapterPath), 
			new File(SRC),new File(DEST),signername, reason, location);	
	
	
	PdfSignItext.sign(new FileInputStream(SRC), new FileOutputStream(DEST2), 
			new FileInputStream(KEYSTORE), PASSWORD, 
		 reason, location, chapterPath);
	}
}
