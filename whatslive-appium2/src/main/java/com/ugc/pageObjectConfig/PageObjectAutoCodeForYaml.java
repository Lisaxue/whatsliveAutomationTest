package com.ugc.pageObjectConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.esotericsoftware.yamlbeans.YamlReader;

public class PageObjectAutoCodeForYaml {

	static String path = "/Users/chenxuejiao/StudyDocs/HiMaven/whatslive-appium/src/main/java/com/ugc/pageObjectConfig/UILibrary.yaml";

	public static void autoCode() throws IOException {
		File file = new File(path);
		if (!file.exists())
			throw new IOException("Can't find " + path);

		YamlReader yamlReader = new YamlReader(new FileReader(file));
		Object yamlObject = yamlReader.read();
		Map yamlMap = (Map) yamlObject;
		ArrayList<HashMap<String, Object>> pages = (ArrayList<HashMap<String, Object>>) yamlMap.get("pages");// page列表

		for (int i = 0; i < pages.size(); i++) {
			HashMap<String, Object> pageNode = pages.get(i);// 获取page节点
			System.out.println("pages.get(i):" + pages.get(i));
			HashMap<String, Object> pageElement = (HashMap<String, Object>) pageNode.get("page");
			// System.out.println("pageElement:" + pageElement);
			// 获取page节点的name属性值
			String pageName = pageElement.get("pageName").toString();
			// .out.println("pageName:" + pageName);
			// 将pageName存储为数组
			String[] pageNameArray = pageName.split("\\.");
			// System.out.println("pageNameArray length:" +
			// pageNameArray.length);
			// System.out.println("pageNameArray[0]:" + pageNameArray[0]);
			// System.out.println("pageNameArray[1]:" + pageNameArray[1]);
			// 获取要写入的page所属的类名
			// String pageClassName = pageNameArray[1].toString();
			String pageClassName = pageNameArray[pageNameArray.length - 1].toString();
			// System.out.println("类名pageNameArray["+(pageNameArray.length -
			// 1)+"]:" + pageNameArray[1]);
			// 获取对象库包名
			String packageName = pageNameArray[0].toString();

			// --自动编写对象库代码（XXPage.java）开始--
			StringBuffer sb = new StringBuffer("package " + packageName + ";\n");
			sb.append("import java.io.IOException;\n");
			sb.append("import java.io.InputStream;\n");
			sb.append("import com.ugc.utils.BaseAction;\n");
			sb.append("import com.ugc.utils.Locator;\n");
			sb.append("import com.ugc.pageObjectConfig.PageObjectAutoCodeForYaml;");
			sb.append("//" + pageElement.get("desc") + "_对象库类\n");
			sb.append("public class " + pageClassName + " extends BaseAction {\n");
			sb.append("//用于eclipse工程内运行查找对象库文件路径\n");
			sb.append(
					"private String path=\"/Users/chenxuejiao/StudyDocs/HiMaven/whatslive-appium/src/main/java/com/ugc/pageObjectConfig/UILibrary.yaml\";\n");
			sb.append(" public  " + pageClassName + "() {\n");
			sb.append("//工程内读取对象库文件\n	");
			sb.append("setXmlObjectPath(path);\n ");
			sb.append("getLocatorMap();");
			sb.append("\n}");
			System.out.println(pageElement.get("desc"));
			List<HashMap<String, Object>> locators = (List<HashMap<String, Object>>) pageElement.get("locators");// 获取locators列表
			for (int j = 0; j < locators.size(); j++)// 遍历locators列表
			{
				// 获取locator节点
				HashMap<String, Object> locatorNode = locators.get(j);
				String locatorName = locatorNode.get("name").toString();
				if (locatorNode.size() > 3) {
					sb.append("\n/***\n" + "* " + locatorNode.get("value") + "\n" + "* @return\n"
							+ "* @throws IOException\n" + "*/\n");
				} else {
					sb.append("\n");
				}
				sb.append("public  Locator " + locatorName + "() throws IOException\n {\n");
				sb.append("   Locator locator=getLocator(\"" + locatorName + "\");\n");
				sb.append("   return locator;\n }\n");
			}
			sb.append("}");

			// 将自动生成的PageObject代码写入文件
			File pageObjectFile = new File("src/main/java/com/ugc/pageObject/" + pageClassName + ".java");
			if (pageObjectFile.exists()) {
				pageObjectFile.delete();
				;
			}
			try {
				FileWriter fileWriter = new FileWriter(pageObjectFile, false);
				BufferedWriter output = new BufferedWriter(fileWriter);
				output.write(sb.toString());
				output.flush();
				output.close();
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			System.out.println(sb);
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		PageObjectAutoCodeForYaml.autoCode();
	}

}
