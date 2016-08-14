package com.sui.manager.common.util;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class FileUtil {
	public static String PATH_SEPARATE = "/";

	/**
	 * 创建目录
	 * @param dirPath
	 */
	public static void mkdirs(String dirPath) {
		File dirFile = new File(dirPath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
	}

	/**
	 * 删除文件
	 * @param dirPath
	 */
	public static boolean deleteFile(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			return true;
		}
		return file.delete();
	}

	public static boolean deleteDirectory(String dirPath) {
		return deleteDirectory(new File(dirPath));
	}

	/**
	 * 删除文件和目录
	 * @param file
	 * @return
	 */
	public static boolean deleteDirectory(File file) {
		if (!file.exists()) {
			return true;
		}
		if (file.isDirectory()) {
			File[] listFiles = file.listFiles();
			for (File childrenFile : listFiles) {
				FileUtil.deleteDirectory(childrenFile);
			}
		}
		return file.delete();
	}

	/**
	 * 获取一个随机的文件名
	 * @param realFilename
	 * @return
	 */
	public static String getRandomFileName(String realFilename) {
		String newFileName = Calendar.getInstance().getTimeInMillis() + RandomUtil.getNumberCode(4);
		int index = realFilename.lastIndexOf(".");
		if (index >= 0) {
			newFileName += realFilename.substring(index);
		}
		return newFileName;
	}

	/**
	 * 获取一个新文件对象，存在先删除
	 * @param filePath
	 * @return
	 */
	public static File getNewFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
		return file;
	}

	/**
	 * 创建一个文件，如果文件存在就先删除再创建一个新的
	 * @param filePath	文件完整路径
	 * @return	返回新创建的文件
	 */
	public static File createNewFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return file;
	}

	/**
	 * 生成带版本号的文件名
	 * @param fileName
	 * @param processId
	 * @return
	 */
	public static String getFileVersionName(String fileName, int version) {
		int index = fileName.lastIndexOf(".");
		return fileName.substring(0, index) + version + fileName.substring(index);
	}

	/**
	 * 检查文件路径是否存在
	 * @param filePath
	 * @return
	 */
	public static boolean checkFileExist(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}
}
