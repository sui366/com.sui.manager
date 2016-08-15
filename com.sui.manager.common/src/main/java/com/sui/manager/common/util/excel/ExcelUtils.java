package com.sui.manager.common.util.excel;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**  
 *  
 */
public class ExcelUtils {

	// excel文件路径
	private String path = "";

	/**
	 * 无参构造函数 默认
	 */
	public ExcelUtils() {

	}

	/**
	 * 有参构造函数
	 * 
	 * @param path
	 *            excel路径
	 */
	public ExcelUtils(String path) {
		this.path = path;
	}

	/**
	 * 在磁盘生成一个含有内容的excel,路径为path属性
	 * 
	 * @param sheetName
	 *            导出的sheet名称
	 * @param fieldName
	 *            列名数组
	 * @param data
	 *            数据组
	 * @throws IOException
	 */
	public static void makeExcel(String path, String sheetName, String[] fieldName, List<String[]> data) throws IOException {
		// 在内存中生成工作薄
		HSSFWorkbook workbook = makeWorkBook(sheetName, fieldName, data);
		// 截取文件夹路径
		FileOutputStream fileOut = FileUtils.openOutputStream(new File(path));
		workbook.write(fileOut);
		fileOut.close();
	}

	/**
	 * 生成excel 支持单sheet和多sheet
	 * 
	 * @param list
	 * @param path
	 * @throws IOException
	 */
	public static void makeExcel(List<ExcelSheetParam> list, String path) throws IOException {
		HSSFWorkbook workbook = makeOfficeWorkBook(list);
		// 截取文件夹路径
		FileOutputStream fileOut = FileUtils.openOutputStream(new File(path));
		workbook.write(fileOut);
		fileOut.close();
	}

	public static HSSFCellStyle getStyle(HSSFWorkbook workbook) {
		// 设置字体;
		HSSFFont font = workbook.createFont();
		// 设置字体大小;
		font.setFontHeightInPoints((short) 10);
		// 设置字体名字;
		font.setFontName("Courier New");
		// font.setItalic(true);
		// font.setStrikeout(true);
		// 设置样式;
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置底边框;
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 设置底边框颜色;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		// 设置左边框;
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// 设置左边框颜色;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		// 设置右边框;
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// 设置右边框颜色;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		// 设置顶边框;
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置顶边框颜色;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		// 在样式用应用设置的字体;
		style.setFont(font);
		// 设置自动换行;
		style.setWrapText(false);
		// 设置水平对齐的样式为居中对齐;
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return style;
	}

	/**
	 * 在磁盘生成一个含有内容的excel,路径为path属性
	 * 
	 * @param sheetName
	 *            导出的sheet名称
	 * @param fieldName
	 *            列名数组
	 * @param data
	 *            数据组
	 * @throws IOException
	 */
	public static InputStream makeExcelInputStream(String sheetName, String[] fieldName, List<String[]> data) throws IOException {
		// 在内存中生成工作薄
		HSSFWorkbook workbook = makeWorkBook(sheetName, fieldName, data);
		ByteArrayInputStream in = new ByteArrayInputStream(workbook.getBytes());
		in.reset();
		return in;
	}

	/**
	 * 在输出流中导出excel
	 * 
	 * @param excelName
	 *            导出的excel名称 包括扩展名
	 * @param sheetName
	 *            导出的sheet名称
	 * @param fieldName
	 *            列名数组
	 * @param data
	 *            数据组
	 * @param response
	 *            response
	 */
	// public void makeStreamExcel(String excelName, String sheetName,String[]
	// fieldName
	// , List<String[]> data,HttpServletResponse response) {
	// OutputStream os = null;
	// try {
	// response.reset(); // 清空输出流
	// os = response.getOutputStream(); // 取得输出流
	// response.setHeader("Content-disposition", "attachment; filename="
	// + new String(excelName.getBytes(), "ISO-8859-1")); // 设定输出文件头
	// response.setContentType("application/msexcel"); // 定义输出类型
	// } catch (IOException ex) {// 捕捉异常
	// System.out.println("流操作错误:" + ex.getMessage());
	// }
	// //在内存中生成工作薄
	// HSSFWorkbook workbook = makeWorkBook(sheetName,fieldName,data);
	// try {
	// os.flush();
	// workbook.write(os);
	// } catch (IOException e) {
	// e.printStackTrace();
	// System.out.println("Output is closed");
	// }
	// }
	/**
	 * 根据条件，生成工作薄对象到内存
	 * 
	 * @param sheetName
	 *            工作表对象名称
	 * @param fieldName
	 *            首列列名称
	 * @param data
	 *            数据
	 * @return HSSFWorkbook
	 */
	@SuppressWarnings("deprecation")
	private static HSSFWorkbook makeOfficeWorkBook(List<ExcelSheetParam> sheetData) {
		// 产生工作薄对象
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle style = getStyle(workbook);
		int sheeId = 0;
		for (ExcelSheetParam item : sheetData) {
			// 产生工作表对象
			HSSFSheet sheet = workbook.createSheet();

			workbook.setSheetName(sheeId, item.getSheetName());
			// 产生一行
			HSSFRow row = sheet.createRow(0);
			// 产生单元格
			HSSFCell cell;
			// sheet.autoSizeColumn(2);
			// 写入各个字段的名称
			for (int i = 0; i < item.getFieldName().length; i++) {
				// 创建第一行各个字段名称的单元格
				cell = row.createCell((short) i);
				// 设置单元格内容为字符串型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// 为了能在单元格中输入中文,设置字符集为UTF_16
				// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				// 给单元格内容赋值
				cell.setCellStyle(style);
				cell.setCellValue(new HSSFRichTextString(item.getFieldName()[i]));
			}
			// 写入各条记录,每条记录对应excel表中的一行
			for (int i = 0; i < item.getData().size(); i++) {
				String[] tmp = item.getData().get(i);
				// 生成一行
				row = sheet.createRow(i + 1);
				for (int j = 0; j < tmp.length; j++) {
					cell = row.createCell((short) j);
					if (org.apache.commons.lang3.math.NumberUtils.isNumber(tmp[j])) {
						cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					} else {
						// 设置单元格字符类型为String
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					}
					cell.setCellStyle(style);
					cell.setCellValue(new HSSFRichTextString((tmp[j] == null) ? "" : tmp[j]));
				}
			}
			sheeId++;
		}

		return workbook;
	}

	/**
	 * 根据条件，生成工作薄对象到内存
	 * 
	 * @param sheetName
	 *            工作表对象名称
	 * @param fieldName
	 *            首列列名称
	 * @param data
	 *            数据
	 * @return HSSFWorkbook
	 */
	@SuppressWarnings("deprecation")
	private static HSSFWorkbook makeWorkBook(String sheetName, String[] fieldName, List<String[]> data) {
		// 产生工作薄对象
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle style = getStyle(workbook);
		// 产生工作表对象
		HSSFSheet sheet = workbook.createSheet();
		// 为了工作表能支持中文,设置字符集为UTF_16
		workbook.setSheetName(0, sheetName);
		// 产生一行
		HSSFRow row = sheet.createRow(0);
		// 产生单元格
		HSSFCell cell;
		// sheet.autoSizeColumn(2);
		// 写入各个字段的名称
		for (int i = 0; i < fieldName.length; i++) {
			// 创建第一行各个字段名称的单元格
			cell = row.createCell((short) i);
			// 设置单元格内容为字符串型
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// 为了能在单元格中输入中文,设置字符集为UTF_16
			// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			// 给单元格内容赋值
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(fieldName[i]));
		}
		// 写入各条记录,每条记录对应excel表中的一行
		for (int i = 0; i < data.size(); i++) {
			String[] tmp = data.get(i);
			// 生成一行
			row = sheet.createRow(i + 1);
			for (int j = 0; j < tmp.length; j++) {
				cell = row.createCell((short) j);
				if (org.apache.commons.lang3.math.NumberUtils.isNumber(tmp[j])) {
					cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				} else {
					// 设置单元格字符类型为String
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				}
				cell.setCellStyle(style);
				cell.setCellValue(new HSSFRichTextString((tmp[j] == null) ? "" : tmp[j]));
			}
		}
		return workbook;
	}

	public void write(int sheetOrder, int colum, int row, String content) throws Exception {
		Workbook workbook = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(path)));
		Sheet sheet = workbook.getSheetAt(sheetOrder);
		Row rows = sheet.createRow(row);
		Cell cell = rows.createCell(colum);
		cell.setCellValue(content);
		FileOutputStream fileOut = new FileOutputStream(path);
		workbook.write(fileOut);
		fileOut.close();

	}

	/**
	 * 得到一个工作区最后一条记录的序号
	 * 
	 * @param sheetOrder
	 *            工作区序号
	 * @return int
	 * @throws IOException
	 */
	public int getSheetLastRowNum(int sheetOrder) throws IOException {
		Workbook workbook = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(path)));
		Sheet sheet = workbook.getSheetAt(sheetOrder);
		return sheet.getLastRowNum();
	}

	public String read(int sheetOrder, int colum, int row) throws Exception {
		Workbook workbook = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(path)));
		Sheet sheet = workbook.getSheetAt(sheetOrder);
		Row rows = sheet.getRow(row);
		Cell cell = rows.getCell(colum);
		String content = cell.getStringCellValue();
		return content;
	}

	/**
	 * 根据path属性，在磁盘生成一个新的excel
	 * 
	 * @throws IOException
	 */
	public void makeEmptyExcel() throws IOException {
		Workbook wb = new HSSFWorkbook();
		@SuppressWarnings("unused")
		Sheet sheet = wb.createSheet("new sheet");
		// 截取文件夹路径
		String filePath = path.substring(0, path.lastIndexOf("/"));
		// 如果路径不存在，创建路径
		File file = new File(filePath);
		if (!file.exists())
			file.mkdirs();
		FileOutputStream fileOut = new FileOutputStream(filePath + "/" + path.substring(path.lastIndexOf("/") + 1));
		wb.write(fileOut);
		fileOut.close();
	}

	/**
	 * 根据工作区序号，读取该工作去下的所有记录，每一条记录是一个String[]<br/> 注意如果单元格中的数据为数字将会被自动转换为字符串<br/>
	 * 如果单元格中存在除数字，字符串以外的其他类型数据，将会产生错误 @param sheetOrder 工作区序号 @return @throws
	 * IOException @throws
	 */
	public List<String[]> getDataFromSheet(int sheetOrder) throws IOException {
		Workbook workbook = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(path)));
		Sheet sheet = workbook.getSheetAt(sheetOrder);
		List<String[]> strs = new ArrayList<String[]>();
		// 注意得到的行数是基于0的索引 遍历所有的行
		// System.out.println(sheet.getLastRowNum());
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			Row rows = sheet.getRow(i);
			String[] str = new String[rows.getLastCellNum()];
			// 遍历每一列
			for (int k = 0; k < rows.getLastCellNum(); k++) {
				Cell cell = rows.getCell(k);
				// 数字类型时
				if (0 == cell.getCellType()) {
					// 用于格式化数字，只保留数字的整数部分
					DecimalFormat df = new DecimalFormat("#0.000");
					str[k] = df.format(cell.getNumericCellValue());
				} else
					str[k] = cell.getStringCellValue();
				// System.out.println(cell.getCellType()+"-------------"+str[k]);
			}
			strs.add(str);
		}
		return strs;
	}

	/**
	 * 根据工作区序号，读取该工作去下的所有记录，每一条记录是一个String[]<br/> 注意如果单元格中的数据为数字将会被自动转换为字符串<br/>
	 * 如果单元格中存在除数字，字符串以外的其他类型数据，将会产生错误 @param sheetOrder 工作区序号 @return @throws
	 * IOException @throws
	 */
	public static List<String[]> getDataFromSheet(File file, int sheetOrder, int startRowNum) throws IOException {
		List<String[]> strs = new ArrayList<String[]>();

		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));

		// Workbook workbook = new HSSFWorkbook(new POIFSFileSystem(
		// new FileInputStream(file)));
		Sheet sheet = workbook.getSheetAt(sheetOrder);
		for (int i = startRowNum; i <= sheet.getLastRowNum(); i++) {
			Row rows = sheet.getRow(i);
			if (rows == null) {
				break;
			}
			String[] str = new String[rows.getLastCellNum()];
			// 遍历每一列
			for (int k = 0; k < rows.getLastCellNum(); k++) {
				Cell cell = rows.getCell(k);
				if (cell == null) {
					continue;
				}
				// 数字类型时
				if (0 == cell.getCellType()) {
					// 用于格式化数字，只保留数字的整数部分
					DecimalFormat df = new DecimalFormat("#0.000");
					str[k] = df.format(cell.getNumericCellValue()).replace(",", "");
					// 如果是整数则直接剔除小数部分
					if (NumberUtils.isLong(str[k])) {
						str[k] = NumberUtils.clearFractionZero(str[k]);
					}
					// System.out.println(str[k]);
				} else {
					str[k] = cell.getStringCellValue();
					// System.out.println(cell.getCellType()+"-------------"+str[k]);
				}
			}
			strs.add(str);
		}
		return strs;
	}

	/**
	 * 读取第一个sheet的值,如果是数字只读取整数
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static List<String[]> getDataFromSheetByOneSheet(File file) throws IOException {
		List<String[]> strs = new ArrayList<String[]>();
		Workbook workbook = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(file)));
		Sheet sheet = workbook.getSheetAt(0);
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row rows = sheet.getRow(i);
			String[] str = new String[rows.getLastCellNum()];
			// 遍历每一列
			for (int k = 0; k < rows.getLastCellNum(); k++) {
				Cell cell = rows.getCell(k);
				// 数字类型时
				if (0 == cell.getCellType()) {
					// 用于格式化数字，只保留数字的整数部分
					str[k] = NumberUtils.format(cell.getNumericCellValue(), 0);
				} else
					str[k] = cell.getStringCellValue();
				// System.out.println(cell.getCellType()+"-------------"+str[k]);
			}
			strs.add(str);
		}
		return strs;
	}

	/**
	 * 根据工作区序号，读取该工作去下的所有记录，每一条记录是一个String[]<br/> 注意如果单元格中的数据为数字将会被自动转换为字符串<br/>
	 * 如果单元格中存在除数字，字符串以外的其他类型数据，将会产生错误 @param sheetOrder 工作区序号 @return @throws
	 * IOException @throws
	 */
	public static List<String[]> getDataFromSheet(File file, int sheetOrder) throws IOException {
		return getDataFromSheet(file, sheetOrder, 0);
	}

	// public String extractTextFromXLS2007(String fileName) throws Exception{
	// StringBuffer content = new StringBuffer();
	// //构造 XSSFWorkbook 对象，strPath 传入文件路径
	// XSSFWorkbook xwb = new XSSFWorkbook(fileName);
	// //循环工作表Sheet
	// for(int numSheet = 0; numSheet < xwb.getNumberOfSheets(); numSheet++){
	// XSSFSheet xSheet = xwb.getSheetAt(numSheet);
	// if(xSheet == null){
	// continue;
	// }
	//
	// //循环行Row
	// for(int rowNum = 0; rowNum <= xSheet.getLastRowNum(); rowNum++){
	// XSSFRow xRow = xSheet.getRow(rowNum);
	// if(xRow == null){
	// continue;
	// }
	//
	// //循环列Cell
	// for(int cellNum = 0; cellNum <= xRow.getLastCellNum(); cellNum++){
	// XSSFCell xCell = xRow.getCell(cellNum);
	// if(xCell == null){
	// continue;
	// }
	//
	// if(xCell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN){
	// content.append(xCell.getBooleanCellValue());
	// }else if(xCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
	// content.append(xCell.getNumericCellValue());
	// }else{
	// content.append(xCell.getStringCellValue());
	// }
	// }
	// }
	// }
	// }
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
		// ExcelUtils eu=new ExcelUtils("d:/数据导出-2010-08-30.xls");
		// String[] fieldNames = new
		// String[]{"国家","厂商","机型","下载用户数","应用名称","结算比例","原始收入","合同比例","当前汇率","预估分成","统计日期"};
		// List<String[]> ss=new ArrayList<String[]>();
		// ss.add(new String[]{"印尼","aux","0.00000023323"});
		//// eu.makeExcel("d:/数据导出-2010-08-30.xls","record", fieldNames, ss);
		// List<ExcelSheetParam> list= new ArrayList<ExcelSheetParam>();
		// ExcelSheetParam p =null;
		// p = new ExcelSheetParam();
		// p.setData(ss);
		// p.setFieldName(fieldNames);
		// p.setSheetName("aa");
		// list.add(p);
		// p = new ExcelSheetParam();
		// p.setData(new ArrayList<String[]>());
		// p.setFieldName(fieldNames);
		// p.setSheetName("bb");
		// list.add(p);
		// HSSFWorkbook workbook = makeOfficeWorkBook(list);
		// //截取文件夹路径
		// FileOutputStream fileOut =
		// FileUtils.openOutputStream(new File("d:/数据导出-2011-08-17.xls"));
		// workbook.write(fileOut);
		// fileOut.close();

		// List list = eu.getDataFromSheet(0);
		// System.out.println(list);
		// List<String[]> ss=new ArrayList<String[]>();
		// ss.add(new String[]{"你撒地方","sdfds","sss"});
		// ss.add(new String[]{"瓦尔","撒地方"});
		// eu.makeExcel("smsLog", new String[]{"色粉","的是否"}, ss);

		// List<String[]> strs=ExcelUtils.getDataFromSheet(0);
		// for (String[] str : strs) {
		// for (String s : str) {
		// System.out.println(s);
		// }
		// }
		// String content = "Hello Worlds";
		// excelUtils.write(2, 3, content);
		// String newContent = excelUtils.read(0,1, 1);
		// System.out.println(newContent);
		// excelUtils.makeEmptyExcel("d:/a/ab", "a.xls");

		ExcelUtils eu = new ExcelUtils();
		List<String[]> data = eu.getDataFromSheet(new File("D:/temp/test.xlsx"), 0, 1);
		for (String[] strs : data) {
			for (String str : strs) {
				System.out.println(str);
			}
		}
	}

	/*
	 * 校验内容本身是否重复
	 * 
	 * @param contentList:从execl采集到的数据
	 * 
	 * @param indexArray:需要判断重复的唯一性索引,例如 new Integer[]{0,1} 代表第一列和第二列不能重复
	 * 
	 * @param minColumnNum:每行的最小列数
	 * 
	 * @return 返回有内容代表重复
	 */
	public static StringBuffer verifyUnique(List<String[]> contentList, Integer[] indexArray, int minColumnNum) {
		StringBuffer out = new StringBuffer();
		if (contentList == null) {
			return out;
		}
		int i = 1;
		List<String> contentStrList = new ArrayList<String>();
		for (String[] content : contentList) {
			i += 1;
			if (content.length < minColumnNum) {
				out.append("第【").append(i).append("】行:纪录不完整！<br>");
				continue;
			}
			String contentStr = "";
			for (int j = 0; j < indexArray.length; j++) {
				contentStr = contentStr + "\0" + content[indexArray[j]];
			}
			if (contentStrList.contains(contentStr)) {
				out.append("第【").append(i).append("】行:纪录重复！<br>");
			} else {
				contentStrList.add(contentStr);
			}
		}
		return out;
	}
}
