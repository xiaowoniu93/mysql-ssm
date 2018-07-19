package com.xszheng.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xszheng.domain.export.ReportCard;

@RestController
public class ExportController {

	@RequestMapping(value="/export")
	public void export(HttpServletResponse response) throws IOException{
		ReportCard card1 = new ReportCard("张三", "初三", 80, 90, 85);
		ReportCard card2 = new ReportCard("李四", "初三", 70, 95, 80);
		ReportCard card3 = new ReportCard("wangwu", "初三", 87, 94, 82);
		List<ReportCard> data = new ArrayList<>();
		data.add(card1);
		data.add(card2);
		data.add(card3);
		String[] headStr = new String[]{"姓名", "年级", "语文分数", "数学分数", "英语分数"};
		
		// 创建Excel
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 创建sheet
		HSSFSheet sheet = workbook.createSheet("成绩单");
		// 创建表头行
		HSSFRow headRow = sheet.createRow(0);
		// 创建表头行样式
		HSSFCellStyle headRowStyle = workbook.createCellStyle();
		headRowStyle.setAlignment(HorizontalAlignment.CENTER);	// 居中
		// 字体
		HSSFFont headFont = workbook.createFont();
		headFont.setBold(true);	// 加粗
		headFont.setFontName("宋体");		// 字体名称
		headRowStyle.setFont(headFont);
		
		
		// 创建表头
		HSSFCell mergeCell = headRow.createCell(2);
		mergeCell.setCellValue("总分数");
		mergeCell.setCellStyle(headRowStyle);
		
		headRow = sheet.createRow(1);
		for (int i = 0; i < headStr.length; i++) {
			HSSFCell cell = headRow.createCell(i);
			cell.setCellValue(headStr[i]);
			cell.setCellStyle(headRowStyle);
		}
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 4));
		
		String fileName = "成绩单";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		workbook.write(baos);
		byte[] content = baos.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
		ServletOutputStream sos = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(sos);
			byte[] buff = new byte[2048];
			int bytesRead;
			while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {
				bos.write(buff, 0, bytesRead);
			}
			bos.flush();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(bis != null){
				bis.close();
			}
			if(bos != null){
				bos.close();
			}
		}
	}
}
