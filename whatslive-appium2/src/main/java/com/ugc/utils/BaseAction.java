package com.ugc.utils;

import java.util.HashMap;

public class BaseAction {
	public String path = null;
	protected HashMap<String, Locator> locatorMap;
	Log log = new Log(this.getClass());

	public void setXmlObjectPath(String path) {
		this.path = path;
	}

	public void getLocatorMap() {
	//	System.out.println("path:" + path);
		YamlReadUtil yamlReadUtil = new YamlReadUtil();
		try {
			/*
			 * //if ((path == null || path.isEmpty())) { locatorMap =
			 * xmlReadUtil.readXMLDocument(path_inputStream_1,
			 * this.getClass().getCanonicalName()); } else { if
			 * (path.contains(".xml")) { locatorMap =
			 * xmlReadUtil.readXMLDocument(path,
			 * this.getClass().getCanonicalName()); } else
			 */ if (path.contains(".yaml")) {
				System.out.println("this.getClass().getCanonicalName():" + this.getClass().getCanonicalName());
				locatorMap = yamlReadUtil.getLocatorMap(path, this.getClass().getCanonicalName());		
				// }

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Locator getLocator(String locatorName) {
		Locator locator;
		
	//	System.out.println("loctorMap:" + locatorMap);
		/**
		 * 在对象库通过对象名字查找定位信息
		 */
		locator = locatorMap.get(locatorName);
		//System.out.println("loctor:" + locator);
		/**
		 * 加入对象库，找不到该定位信息，就创建一个定位信息
		 */
		if (locator == null) {
			log.error("没有找到" + locatorName + "页面元素");
		}
				
		return locator;
	}
	
	/*public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		BaseAction ba = new BaseAction();
	}*/
}
