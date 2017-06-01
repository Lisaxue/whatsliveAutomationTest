package com.ugc.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

public class YamlReadUtil {

	public static void main(String args[]) throws FileNotFoundException, YamlException {

		// String path1 = System.getProperty("user.dir");
		String path2 = "/Users/chenxuejiao/StudyDocs/HiMaven/whatslive-appium/src/main/java/com/ugc/pageObjectConfig/UILibrary.yaml";

		YamlReadUtil yamlReadUtil = new YamlReadUtil();
		yamlReadUtil.getLocatorMap(path2, "pageObject.HomePage");
		yamlReadUtil.getYamlPageUrl(path2, "pageObject.HomePage");
	}

	/**
	 * @param path
	 *            对象库文件地址
	 * @param pageName
	 *            页面名字
	 * @return 返回locator 哈希表 locatorName:locator
	 */
	public HashMap<String, Locator> getLocatorMap(String path, String pageName)
			throws FileNotFoundException, YamlException {
		HashMap<String, Locator> locatorHashMap = new HashMap<>();
		YamlReader yamlReader = new YamlReader(new FileReader(path));
		Object yamlObject = yamlReader.read();
		Map<?, ?> yamlMap = (Map<?, ?>) yamlObject;
		@SuppressWarnings("unchecked")
		ArrayList<HashMap<String, Object>> pages = (ArrayList<HashMap<String, Object>>) yamlMap.get("pages");
		for (int i = 0; i < pages.size(); i++)// 遍历Page节点
		{
			HashMap<String, Object> pageNode = pages.get(i);// 获取page节点
			@SuppressWarnings("unchecked")
			HashMap<String, Object> pageElement = (HashMap<String, Object>) pageNode.get("page");

			// System.out.println(pageNode);
			if (pageElement.get("pageName").toString().equalsIgnoreCase(pageName))// 判断是否需要获取的Page节点
			{
				// System.out.println(pageElement.get("desc"));
				@SuppressWarnings("unchecked")
				List<HashMap<String, Object>> locators = (List<HashMap<String, Object>>) pageElement.get("locators");// 获取locators列表
				for (int j = 0; j < locators.size(); j++)// 遍历locators列表
				{
					HashMap<String, Object> locatorNode = locators.get(j);
					Locator locator = new Locator();
					locator.setType(getByType(locatorNode.get("type").toString()));
					locator.setValue(locatorNode.get("value").toString());
					locator.setTimout(Integer.parseInt(locatorNode.get("timeout").toString()));
					locator.setLocatorName(locatorNode.get("name").toString());
					locatorHashMap.put(locatorNode.get("name").toString(), locator);
					// System.out.println();
				}

			} else {
				continue;
			}
			// System.out.println(pageObjet);
		}
		// System.out.println(locatorHashMap.get("登录").getLocalorName());
		return locatorHashMap;
	}

	public String getYamlPageUrl(String path, String pageName) throws FileNotFoundException, YamlException {
		Map<String, Locator> locatorHashMap = new HashMap<>();
		YamlReader yamlReader = new YamlReader(new FileReader(path));
		Object yamlObject = yamlReader.read();
		Map yamlMap = (Map) yamlObject;
		ArrayList<HashMap<String, Object>> pages = (ArrayList<HashMap<String, Object>>) yamlMap.get("pages");
		String url = "";
		for (int i = 0; i < pages.size(); i++)// 遍历Page节点
		{
			HashMap<String, Object> pageNode = pages.get(i);// 获取page节点
			HashMap<String, Object> pageElement = (HashMap<String, Object>) pageNode.get("page");
			if (pageElement.get("pageName").toString().equalsIgnoreCase(pageName))// 判断是否需要获取的Page节点
			{
				// System.out.println(pageElement.get("desc"));
				url = pageElement.get("value").toString();
				System.out.println(url);
			} else {
				continue;
			}
			// System.out.println(pageObjet);
		}

		System.out.println("url:" + url);
		return url;
	}

	/**
	 * @param type
	 */
	public static Locator.ByType getByType(String type) {
		Locator.ByType byType = Locator.ByType.xpath;
		if (type == null || type.equalsIgnoreCase("xpath")) {
			byType = Locator.ByType.xpath;
		} else if (type.equalsIgnoreCase("id")) {
			byType = Locator.ByType.id;
		} else if (type.equalsIgnoreCase("linkText")) {
			byType = Locator.ByType.linkText;
		} else if (type.equalsIgnoreCase("name")) {
			byType = Locator.ByType.name;
		} else if (type.equalsIgnoreCase("className")) {
			byType = Locator.ByType.className;
		} else if (type.equalsIgnoreCase("cssSelector")) {
			byType = Locator.ByType.cssSelector;
		} else if (type.equalsIgnoreCase("partialLinkText")) {
			byType = Locator.ByType.partialLinkText;
		} else if (type.equalsIgnoreCase("tagName")) {
			byType = Locator.ByType.tagName;
		}
		return byType;
	}
}
