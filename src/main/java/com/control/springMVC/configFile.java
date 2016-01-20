package com.control.springMVC;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
// 类似Struts的Action
@RequestMapping("/config")
public class configFile {
	@RequestMapping("/showPath")
	public String showPath() {

		return "";
	}

	public static void loadFile() {
		Properties p = loadPropertyFile("config/common.properties");
	}

	private static Properties loadPropertyFile(String fullFile) {
		String webRootPath = null;
		if (null == fullFile || fullFile.equals(""))
			throw new IllegalArgumentException(
					"Properties file path                                                                                                       can not be null : "
							+ fullFile);
		webRootPath = configFile.class.getClassLoader().getResource(fullFile)
				.getPath();
		InputStream inputStream = null;
		Properties p = null;
		try {
			inputStream = new FileInputStream(webRootPath);
			p = new Properties();
			p.load(inputStream);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Properties file not found: "
					+ fullFile);
		} catch (IOException e) {
			throw new IllegalArgumentException(
					"Properties file can not be loading: " + fullFile);
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return p;
	}
	
	
	@RequestMapping("/loadfindFile")
	public String loadfindFile() {
		return "findpath";
	}

	@RequestMapping("/findFile")
	@ResponseBody
	public String findFile() {
		String path1=findFile1("/");
		String path2=findFile2("");
		String path3=findFile3("config/file2.properties");
		String path4=findFile4("config/file4.properties");
		String path5=findFile5("");
		JSONObject jsondata = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("path1value", path1);
		map.put("path2value", path2);
		map.put("path3value", path3);
		map.put("path4value", path4);
		map.put("path5value", path5);
		jsondata=JSONObject.fromObject(map);
		return jsondata.toString();
	}
	
	
	/**
	 *
	 * @param fullFile
	 * @return
	 */
	private static String findFile1(String fullFile) {
		String webRootPath = "";
		try {
			webRootPath = configFile.class.getClassLoader()
					.getResource(fullFile).getPath();
			System.out.println("findFile1方法1找到文件！路径为："+webRootPath);
		} catch (Exception e) {
			System.out.println("findFile1方法1未找到文件！");
		}
		return webRootPath;
	}
	
	
	private static String findFile2(String fullFile) {
		String webRootPath = "";
		try {
			webRootPath = configFile.class.getClassLoader()
					.getResource(fullFile).getPath();
			System.out.println("findFile2方法2找到文件！路径为："+webRootPath);
		} catch (Exception e) {
			System.out.println("findFile2方法2未找到文件！");
		}
		return webRootPath;
	}
	
	private static String findFile3(String fullFile) {
		String webRootPath = "";
		try {
			webRootPath=System.getProperty("/"); 
			System.out.println("findFile3方法3找到文件！路径为："+webRootPath);
		} catch (Exception e) {
			System.out.println("findFile3方法3未找到文件！");
		}
		return webRootPath;
	}
	
	/*
	 * 获取classpath目录（WEB-INF），然后获取父一级文件夹路径，然后获取
	 */
	private static String findFile4(String fullFile) {
		String webRootPath = "";
		try {
			webRootPath = configFile.class.getClassLoader()
					.getResource("/").getPath();
			webRootPath=getparent(webRootPath);
			webRootPath=webRootPath+fullFile;
			
			System.out.println("findFile4方法4找到文件！路径为："+webRootPath);
		} catch (Exception e) {
			System.out.println("findFile4方法4未找到文件！");
		}
		return webRootPath;
	}
	
	private static String findFile5(String fullFile) {
		String webRootPath = "";
		try {
			webRootPath = configFile.class.getClassLoader()
					.getResource("").getPath();
			System.out.println("findFile5方法5找到文件！路径为："+webRootPath);
		} catch (Exception e) {
			System.out.println("findFile5方法5未找到文件！");
		}
		return webRootPath;
	}
	
	private static String findFile6(String fullFile) {
		String webRootPath = "";
		try {
			webRootPath = configFile.class.getClassLoader()
					.getResource(fullFile).getPath();
			System.out.println("findFile6方法6找到文件！路径为："+webRootPath);
		} catch (Exception e) {
			System.out.println("findFile6方法6未找到文件！");
		}
		return webRootPath;
	}
	
	private static String findFile7(String fullFile) {
		String webRootPath = "";
		try {
			webRootPath = configFile.class.getClassLoader()
					.getResource(fullFile).getPath();
			System.out.println("findFile7方法7找到文件！路径为："+webRootPath);
		} catch (Exception e) {
			System.out.println("findFile7方法7未找到文件！");
		}
		return webRootPath;
	}
	private static String getparent(String classPathAbsolutePath){
		classPathAbsolutePath = cutLastString(
				classPathAbsolutePath, "/", 1);
		return classPathAbsolutePath;
	}
	
	private static String cutLastString(String source, String dest, int num) {
		// String cutSource=null;
		for (int i = 0; i < num; i++) {
			source = source.substring(0,
					source.lastIndexOf(dest, source.length() - 2) + 1);

		}

		return source;
	}
	
}
