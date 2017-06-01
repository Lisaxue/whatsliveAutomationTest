package com.ugc.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Assertion extends TestBaseCase {

	private static Log log = new Log(Assertion.class);
	// 收集断言信息文本，用于报表展示
	public static List<String> assertInfolList = new ArrayList<String>();
	// 收集信息文本用于报表展示
	public static List<String> messageList = new ArrayList<String>();
	// 收集断言异常用于报表日志展示
	public static List<Error> errors = new ArrayList<Error>();
	// 记录错误数量
	public static Integer errorIndex = 0;

	/**
	 * 验证页面是否出现某文本exceptStr
	 * 
	 * @param exceptStr
	 *            预期值
	 * @param Message
	 *            验证中文描述
	 * @author Administrator 郑树恒
	 */
	public static void VerityTextPresent(String exceptStr, String Message) {
		String verityStr = "【Assert验证】:" + "页面是否出现" + "【" + "预期值：" + exceptStr + "】" + "字符串";
		Boolean flag = false;
		log.info(Message + ":" + verityStr);
		try {
			// app查找是否出现某文本用@text属性，如果是web需是text()
			exceptStr = "//*[contains(@text,'" + exceptStr + "')]";
			System.out.println(exceptStr);
			List<WebElement> webElements = driver.findElements(By.xpath(exceptStr));
			if (webElements.size() > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			flag = false;
			ElementAction.noSuchElementExceptions.add(e);
			e.printStackTrace();
		}
		try {
			Assert.assertTrue(flag);
			AssertPassLog();
			assertInfolList.add(Message + verityStr + ":pass");
			messageList.add(Message + ":pass");
		} catch (Error f) {
			// TODO: handle exception
			AssertFailedLog();
			errors.add(f);
			errorIndex++;
			assertInfolList.add(Message + verityStr + ":failed");
			messageList.add(Message + ":failed");
			Assertion.snapshotInfo();
		}
	}

	/**
	 * 验证actual实际值是否包含预期值exceptStr
	 * 
	 * @param actual
	 *            实际值
	 * @param exceptStr
	 *            预期值
	 * @param Message
	 *            验证中文描述
	 * @author Administrator 郑树恒
	 */
	public static void VerityCationString(String actual, String exceptStr, String Message) {
		String verityStr = "Assert验证：{" + "实际值：" + actual + "," + "预期值：" + exceptStr + "} 实际值是否包含预期值";
		Boolean flagBoolean = actual.contains(exceptStr);
		log.info(Message + ":" + verityStr);
		try {
			Assert.assertTrue(flagBoolean);
			AssertPassLog();
			assertInfolList.add(Message + verityStr + ":pass");
			messageList.add(Message + ":pass");

		} catch (Error e) {
			// TODO: handle exception
			AssertFailedLog();
			errors.add(e);
			assertInfolList.add(Message + verityStr + ":failed");
			messageList.add(Message + ":failed");
			Assertion.snapshotInfo();
		}

	}

	private static void snapshotInfo() {
		WebDriver driver = TestBaseCase.driver;
		ScreenShot screenShot = new ScreenShot(driver);
		// 设置截图名字
		Date nowDate = new Date();
		screenShot.setscreenName(Assertion.formatDate(nowDate));
		screenShot.takeScreenshot();
		// Assertion.assertInfolList.add("&lt;a
		// href=\"snapshot/"+Assertion.formatDate(nowDate)+".jpg\" &gt;&lt;img
		// height=\"100\" width=\"100\"
		// src=\"snapshot\\"+Assertion.formatDate(nowDate)+".jpg\"&gt;&lt;/img&gt;&lt;/a&gt;&lt;br/&gt;"+"&lt;a
		// href=\"snapshot\\"+Assertion.formatDate(nowDate)+".jpg\"
		// &gt;点击查看大图&lt;/a&gt;\n");
		Assertion.messageList.add("&lt;a class=\"clickbox\" href=\"#url\"&gt;\n" + "&lt;img src=\"snapshot/"
				+ Assertion.formatDate(nowDate) + ".jpg\" height=\"100\" width=\"100\" alt=\"\" /&gt;\n"
				+ "&lt;b class=\"lightbox\"&gt;\n" + "&lt;b class=\"light\"&gt;&lt;/b&gt;\n"
				+ "&lt;b class=\"box\"&gt;\n" + "&lt;img src=\"snapshot/" + Assertion.formatDate(nowDate)
				+ ".jpg\" height=\"530\" width=\"1024\" onmousewheel=\"return bigimg(this)\" alt=\"\" /&gt;\n"
				+ "&lt;span&gt;滚动鼠标缩放大小,点击X关闭当前图片，返回报表界面.&lt;br /&gt;&lt;i&gt;&lt;/i&gt;&lt;/span&gt;\n"
				+ "&lt;/b&gt;\n" + "&lt;/b&gt;\n" + "&lt;/a&gt;\n" + "&lt;br class=\"clear\" /&gt;\n"
				+ "&lt;a class=\"clickbox\" href=\"#url\"&gt;" + "点击查看大图" + "&lt;b class=\"lightbox\"&gt;"
				+ "&lt;b class=\"light\"&gt;&lt;/b&gt;" + "&lt;b class=\"box\"&gt;&lt;img src=\"snapshot/"
				+ Assertion.formatDate(nowDate)
				+ ".jpg\" height=\"530\" width=\"1024\" onmousewheel=\"return bigimg(this)\" alt=\"\" /&gt;"
				+ "&lt;span&gt;滚动鼠标缩放大小,点击X关闭当前图片，返回报表界面." + "&lt;br /&gt;&lt;i&gt;&lt;/i&gt;&lt;/span&gt;"
				+ "&lt;/b&gt;" + "&lt;/b&gt;" + " &lt;/a&gt;\n&lt;/br&gt;" + "&lt;div id=\"close\"&gt;&lt;/div&gt;\n");
	}

	public static String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmssSSS");
		return formatter.format(date).toString();
	}

	// 断言成功日志内容
	private static void AssertPassLog() {
		log.info("【Assert验证  pass!】");
	}

	// 断言失败日志内容
	private static void AssertFailedLog() {
		log.error("【Assert验证  failed!】");
	}

	/*
	 * 判断用例是否含有验证失败的断言，如果有此方法会抛出异常，让testng监听器检测到， 如果没有不会抛出异常，testng监听器会认为用例成功
	 */
	public static void VerityError() {
		Assert.assertEquals(errors.size(), 0);
		// 有找不到元素的异常也认为用例失败
		Assert.assertEquals(ElementAction.noSuchElementExceptions.size(), 0);
	}
}
