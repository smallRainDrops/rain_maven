package com.example.crudproject.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;

public class CSVUtils {

	/**
	 * 生成为CVS文件
	 *
	 * @param exportData
	 *            源数据List
	 * @param map
	 *            csv文件的列表头map
	 * @param outPutPath
	 *            文件路径
	 * @param fileName
	 *            文件名称
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static File createCSVFile(List exportData, LinkedHashMap map, String outPutPath, String fileName) {
		File csvFile = null;
		BufferedWriter csvFileOutputStream = null;
		try {
			File file = new File(outPutPath);
			if (!file.exists()) {
				file.mkdir();
			}
			// 定义文件名格式并创建
			csvFile = new File(outPutPath + fileName + ".csv");
			// csvFile = File.createTempFile(fileName, ".csv", new
			// File(outPutPath));
			System.out.println("csvFile：" + csvFile);
			// UTF-8使正确读取分隔符","
			csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"),
					1024);
			csvFileOutputStream.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF }));
			System.out.println("csvFileOutputStream：" + csvFileOutputStream);
			// 写入文件头部
			for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
				Map.Entry propertyEntry = (Map.Entry) propertyIterator.next();
				csvFileOutputStream.write(
						"" + (String) propertyEntry.getValue() != null ? (String) propertyEntry.getValue() : "" + "");
				if (propertyIterator.hasNext()) {
					csvFileOutputStream.write(",");
				}
			}
			csvFileOutputStream.newLine();
			// 写入文件内容
			for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {
				Object row = (Object) iterator.next();
				for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
					Map.Entry propertyEntry = (Map.Entry) propertyIterator.next();
					csvFileOutputStream.write((String) BeanUtils.getProperty(row, (String) propertyEntry.getKey()));
					if (propertyIterator.hasNext()) {
						csvFileOutputStream.write(",");
					}
				}
				if (iterator.hasNext()) {
					csvFileOutputStream.newLine();
				}
			}
			csvFileOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				csvFileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return csvFile;
	}

	public static void exportFile(HttpServletResponse response, String csvFilePath, String fileName)
			throws IOException {
		response.setContentType("application/csv;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

		InputStream in = null;
		try {
			in = new FileInputStream(csvFilePath);
			int len = 0;
			byte[] buffer = new byte[1024];
			response.setCharacterEncoding("UTF-8");
			OutputStream out = response.getOutputStream();
			while ((len = in.read(buffer)) > 0) {
				out.write(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });
				out.write(buffer, 0, len);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public static void main(String[] args) {
		List exportData = new ArrayList<Map>();
		
		Date date = new Date();
		String dayDate1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		long time = date.getTime();
		Date javaDate = HSSFDateUtil.getJavaDate(time);
		String dayDate2 = new SimpleDateFormat("yyyy-MM-dd").format(javaDate);
		
		Map row1 = new LinkedHashMap<String, String>();
		
		row1.put("1", dayDate1);
		row1.put("2", dayDate2);
		row1.put("3", "13");
		row1.put("4", "14");
		row1.put("5", "15");
		exportData.add(row1);
		row1 = new LinkedHashMap<String, String>();
		row1.put("1", "21");
		row1.put("2", "22");
		row1.put("3", "23");
		row1.put("4", "24");
		row1.put("5", "25");
		exportData.add(row1);
		LinkedHashMap map = new LinkedHashMap();
		map.put("1", "policy_no");
		map.put("2", "step_date");
		map.put("3", "step_count");
		map.put("4", "free_day_ind");
		map.put("5", "za_batch_date");

		String path = "E:/export/";
		String fileName = "文件导出";
		File file = CSVUtils.createCSVFile(exportData, map, path, fileName);
		String fileName2 = file.getName();
		String format = new SimpleDateFormat("yyyyMMdd").format(new Date());
		System.out.println("文件名称：" + format + "/" + fileName2);
	}
}
