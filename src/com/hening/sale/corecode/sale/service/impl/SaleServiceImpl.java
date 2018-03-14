package com.hening.sale.corecode.sale.service.impl;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hening.sale.corecode.sale.entity.Sale;
import com.hening.sale.corecode.sale.mapper.SaleMapper;
import com.hening.sale.corecode.sale.service.SaleService;

@Service("SaleService")
public class SaleServiceImpl implements SaleService {

	@Autowired
	private SaleMapper saleMapper;
	
	@Override
	public List<Map<String, Object>> findProList(Sale sale) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = saleMapper.findSaleRecords(sale);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void addProductRecord(Sale sale) {
		try {
			saleMapper.addProductRecord(sale);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Integer getProductRecordCount(Sale sale) {
		Integer count = -1;  //返回-1则查询出错
		try {
			count = saleMapper.getProductRecordCount(sale);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public void deleteProductRecord(Sale sale) {
		try {
			saleMapper.deleteProductRecord(sale);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	@Override
	public void downloadSale(Sale sale,HttpServletRequest request,HttpServletResponse response) {
		try {
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			list = saleMapper.findSaleRecords(sale);
			//考虑了一下是否要判断长度是否为0，我想还是不判断了，把选择权交给用户，客户若想导出空表格就让客户导吧
			
			XSSFWorkbook wb = new XSSFWorkbook();
			Sheet sheet =  wb.createSheet();
			//sheet.setDisplayGridlines(false);   //是否要网格线
			for(short i=0;i<6;i++){
				sheet.setColumnWidth((short)i, (short)6000);
			}
			
			//标题
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 5));
			CellStyle headStyle = wb.createCellStyle();
			headStyle.setAlignment(CellStyle.ALIGN_CENTER);
			headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			headStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
			Font headFont = wb.createFont();
			headFont.setFontHeightInPoints((short)20);
			headFont.setFontName("宋体");
			headFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			headStyle.setFont(headFont);
			for(int m=0;m<2;m++){
				Row row = sheet.createRow(m);
				for(int n=0;n<6;n++){
					Cell cell = row.createCell(n);
					cell.setCellValue("");
					cell.setCellStyle(headStyle);
				}
			}
			Row headRow = sheet.getRow(0);
			Cell headCell =  headRow.getCell(0);
			
			headCell.setCellValue("销售业绩表");
			
			//表格内容
			//表头格式
			CellStyle contentHeadStyle = wb.createCellStyle();
			contentHeadStyle.setAlignment(CellStyle.ALIGN_CENTER);
			contentHeadStyle.setBorderLeft(XSSFCellStyle.BORDER_NONE);
			contentHeadStyle.setBorderRight(XSSFCellStyle.BORDER_NONE);
			Font contentHeadFont = wb.createFont();
			contentHeadFont.setFontHeightInPoints((short)18);
			contentHeadFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			contentHeadStyle.setFont(contentHeadFont);
			
			//内容格式
			CellStyle style = wb.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);
			style.setBorderLeft(XSSFCellStyle.BORDER_NONE);
			style.setBorderRight(XSSFCellStyle.BORDER_NONE);
			Font font = wb.createFont();
			font.setFontHeightInPoints((short)16);
			style.setFont(font);
			
			//业绩合计
			Row sumRow = sheet.createRow(2);
			Cell sumNamecell = sumRow.createCell(0);
			sumNamecell.setCellStyle(style);
			sumNamecell.setCellValue("销售业绩合计");
			double sum = 0;
			for(int i=0;i<list.size();i++){
				sum += Double.parseDouble((list.get(i).get("totalPrice")).toString());
			}
			Cell sumcell = sumRow.createCell(1);
			sumcell.setCellStyle(style);
			sumcell.setCellValue(sum);
			
			//表格内容-表头
			Map<String,Object> headMap = new HashMap<String,Object>();
			
			List<String> mapList = new ArrayList<String>();
			mapList.add("productName");
			mapList.add("count");
			mapList.add("totalPrice");
			mapList.add("operator");
			mapList.add("customer");
			mapList.add("recordDate");
			
			headMap.put("productName", "产品");
			headMap.put("count", "数量");
			headMap.put("totalPrice", "总价");
			headMap.put("operator", "操作员");
			headMap.put("customer", "客户");
			headMap.put("recordDate", "日期");
			list.add(0, headMap);
			
			for(int i=0;i<list.size();i++){
				Row contentRow = sheet.createRow(i+3);
				for(int j=0;j<6;j++){
					Cell cell = contentRow.createCell(j);
					if(i==0){
						cell.setCellStyle(contentHeadStyle);
					}else{
						cell.setCellStyle(style);
					}
					cell.setCellValue((list.get(i).get(mapList.get(j))).toString());
					
				}
			}
			
			//下载文件
			String fileName = "销售业绩表";
			// 解决中文文件名乱码问题
            if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1"); // firefox浏览器
            } else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
                fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
            } else if (request.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0) {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");// 谷歌
            }
            
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName+".xlsx");
            
			response.setContentType("application/octet-stream;");
			OutputStream out = response.getOutputStream();
			wb.write(out);
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
