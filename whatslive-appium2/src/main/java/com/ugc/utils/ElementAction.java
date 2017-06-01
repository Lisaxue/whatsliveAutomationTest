package com.ugc.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

/*
 * 
 * @2017.01.16   by cxj
 * sleep() 显式等待，程序休眠暂停
 * Waitformax() 隐式等待

 * */

public class ElementAction extends TestBaseCase {

	public static ArrayList<Exception> noSuchElementExceptions = new ArrayList<Exception>();
	private Log log = new Log(this.getClass());

	/**
	 * 显式等待，程序休眠暂停
	 * 
	 * @param time
	 *            以秒为单位
	 */
	public void sleep(long time) {
		try {
			log.info("等待" + time + "秒");
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 隐式等待
	 * 
	 * @param t
	 *            最大等待时间，秒为单位
	 **/
	public void Waitformax(int t) {
		driver.manage().timeouts().implicitlyWait(t, TimeUnit.SECONDS);
	}

	/**
	 * 判断元素是否显示
	 * 
	 * @param locator
	 *            元素定位信息
	 * @return 返回boolean true显示，false隐藏
	 */
	public boolean isElementDisplayed(Locator locator) {
		ElementAction action = new ElementAction();
		WebElement webElement = action.findElement(locator);
		webElement.isEnabled();
		log.info("元素显示状态为：" + webElement.isDisplayed());
		return webElement.isDisplayed();
	}

	/**
	 * 显式等待 判断页面是否完全加载完成
	 * 
	 * @param time
	 *            已秒为单位
	 */
	/*
	 * public void pagefoload(long time) { ExpectedCondition<Boolean> pageLoad =
	 * new ExpectedCondition<Boolean>() { public Boolean apply(WebDriver driver)
	 * { return ((JavascriptExecutor) driver).executeScript(
	 * "return document.readyState").equals("complete"); } }; WebDriverWait wait
	 * = new WebDriverWait(driver, time * 1000); wait.until(pageLoad); }
	 */

	/**
	 * 普通单击操作
	 * 
	 * @param locator
	 *            元素locator
	 */
	public void click(Locator locator) {
		try {
			WebElement webElement = findElement(locator);
			webElement.click();
			log.info("click元素：" + locator.getLocalorName() + "[" + "By." + locator.getType() + ":"
					+ locator.getElement() + "]成功！");
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			log.error("找不到元素，click失败:" + locator.getLocalorName() + "[" + "By." + locator.getType() + ":"
					+ locator.getElement() + "]");
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 清除文本框内容
	 * 
	 * @param locator
	 *            元素locator
	 */
	public void clear(Locator locator) {
		try {
			WebElement webElement = findElement(locator);
			webElement.clear();
			log.info("清除input值:" + locator.getLocalorName() + "[" + "By." + locator.getType() + ":"
					+ locator.getElement() + "]");
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			log.error("清除input值失败:" + locator.getLocalorName() + "[" + "By." + locator.getType() + ":"
					+ locator.getElement() + "]");
		}
	}

	/**
	 * 文本框输入操作
	 * 
	 * @param locator
	 *            元素locator
	 * @param value
	 *            输入值
	 */
	public void type(Locator locator, String value) {
		try {
			WebElement webElement = findElement(locator);
			webElement.sendKeys(value);
			// log.info("input输入："+locator.getLocalorName()+"["+"By."+locator.getType()+":"+locator.getElement()+"
			// value:"+value+"]");
			log.info("input输入：" + locator.getLocalorName() + "[" + "By." + locator.getType() + ":"
					+ locator.getElement() + "  value:" + "*******" + "]");
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			log.error("找不到元素，input输入失败:" + locator.getLocalorName() + "[" + "By." + locator.getType() + ":"
					+ locator.getElement() + "]");
			e.printStackTrace();
			// throw e;
			// Assertion.flag=false;
		}

	}

	/**
	 * 从一个地方滑动到另外一个地方，等待几秒松开
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param wait
	 *            等待几秒松开
	 */
	public void swipe(int x1, int y1, int x2, int y2, int wait) {
		driver.swipe(x1, y1, x2, y2, wait);
	}

	/**
	 * 向左滑动，等待几秒松开
	 * 
	 * @param AndroidDriver
	 * @param wait
	 *            等待几秒松开
	 */
	public void swipeToLeft(AndroidDriver<WebElement> driver, int wait) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width * 3 / 4, height / 2, width / 4, height / 2, wait);
	}

	/**
	 * 向右滑动，等待几秒松开
	 * 
	 * @param AndroidDriver
	 * @param wait
	 *            等待几秒松开
	 */
	public void swipeToRight(AndroidDriver<WebElement> driver, int wait) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;

		driver.swipe(width / 4, height / 2, width * 3 / 4, height / 2, wait);
	}

	/**
	 * 向上滑动，等待几秒松开
	 * 
	 * @param AndroidDriver
	 * @param wait
	 */
	public void swipeToUp(AndroidDriver<WebElement> driver, int wait) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;

		driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, wait);
	}

	/**
	 * 向下滑动，等待几秒松开
	 * 
	 * @param driver
	 * @param wait
	 */
	public void swipeToDown(AndroidDriver<WebElement> driver, int wait) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;

		driver.swipe(width / 2, height / 4, width / 2, height * 3 / 4, wait);
	}

	public WebElement findElement(final Locator locator) {
		/**
		 * 查找某个元素等待几秒
		 */
		// Waitformax(Integer.valueOf(locator.getWaitSec()));
		WebElement webElement = null;
		try {
			webElement = (new WebDriverWait(driver, 20)).until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver driver) {
					// TODO 自动生成的方法存根
					WebElement element = null;
					element = getElement(locator);
					// System.out.println("element:" + element);
					return element;
				}
			});

			return webElement;
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			log.info("无法定位页面元素");
			e.printStackTrace();
			Assertion.assertInfolList.add(
					"failed,找不到元素：[" + locator.getType() + ":" + locator.getElement() + "等待:" + locator.getWaitSec());
			noSuchElementExceptions.add(e);
			Assertion.messageList.add("找不到所需页面元素[" + locator.getElement() + "]:failed");
			ScreenShot screenShot = new ScreenShot(driver);
			// 设置截图名字
			Date nowDate = new Date();
			screenShot.setscreenName(this.formatDate(nowDate));
			screenShot.takeScreenshot();
			// 展示报表截图
			this.showscreenShot(nowDate);
			log.info(this.formatDate(nowDate));
			return webElement;
		} catch (TimeoutException e) {
			// TODO: handle exception
			log.info("超时无法定位页面元素");
			e.printStackTrace();
			Assertion.assertInfolList.add(
					"failed,超时找不到元素：[" + locator.getType() + ":" + locator.getElement() + "等待:" + locator.getWaitSec());
			noSuchElementExceptions.add(e);
			Assertion.messageList.add("超时找不到所需页面元素[" + locator.getElement() + "]:failed");
			ScreenShot screenShot = new ScreenShot(driver);
			// 设置截图名字
			Date nowDate = new Date();
			screenShot.setscreenName(this.formatDate(nowDate));
			screenShot.takeScreenshot(); // 展示报表截图
			this.showscreenShot(nowDate);
			log.info(this.formatDate(nowDate));
			return webElement;
		} catch (ElementNotVisibleException e) {
			// TODO: handle exception
			log.info("超时无法定位页面元素");
			e.printStackTrace();
			Assertion.assertInfolList.add(
					"failed,超时找不到元素：[" + locator.getType() + ":" + locator.getElement() + "等待:" + locator.getWaitSec());
			noSuchElementExceptions.add(e);
			Assertion.messageList.add("超时页面元素不可视[" + locator.getElement() + "]:failed");
			ScreenShot screenShot = new ScreenShot(driver);
			// 设置截图名字
			Date nowDate = new Date();
			screenShot.setscreenName(this.formatDate(nowDate));
			screenShot.takeScreenshot(); // 展示报表截图
			this.showscreenShot(nowDate);
			log.info(this.formatDate(nowDate));
			return webElement;
		}
	}

	/**
	 * 通过定位信息获取元素
	 * 
	 * @param locator
	 *            元素locator
	 * @return 返回WebElement
	 * @throws NoSuchElementException
	 *             找不到元素异常
	 */
	public WebElement getElement(Locator locator) {
		/**
		 * locator.getElement(),获取对象库对象定位信息
		 */
		// System.out.println("locator.getType():"+ locator.getType());
		// log.info("查找元素："+locator.getLocalorName()+"方式"+"["+"By."+locator.getType()+":"+locator.getElement()+"]");
		WebElement webElement;
		switch (locator.getType()) {
		case xpath:
			webElement = driver.findElement(By.xpath(locator.getElement()));
			break;
		case id:
			webElement = driver.findElement(By.id(locator.getElement()));
			break;
		case cssSelector:
			webElement = driver.findElement(By.cssSelector(locator.getElement()));
			break;
		case name:
			webElement = driver.findElement(By.name(locator.getElement()));
			break;
		case className:
			webElement = driver.findElement(By.className(locator.getElement()));
			break;
		case linkText:
			webElement = driver.findElement(By.linkText(locator.getElement()));
			break;
		case partialLinkText:
			webElement = driver.findElement(By.partialLinkText(locator.getElement()));
			break;
		case tagName:
			webElement = driver.findElement(By.tagName(locator.getElement()));
			break;
		default:
			webElement = driver.findElement(By.xpath(locator.getElement()));
			break;

		}
		return webElement;
	}

	private String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmssSSS");
		return formatter.format(date).toString();
	}

	// 报表展示截图
	private void showscreenShot(Date nowDate) {
		Assertion.messageList.add("&lt;a class=\"clickbox\" href=\"#url\"&gt;\n" + "&lt;img src=\"snapshot/"
				+ this.formatDate(nowDate) + ".jpg\" height=\"100\" width=\"100\" alt=\"\" /&gt;\n"
				+ "&lt;b class=\"lightbox\"&gt;\n" + "&lt;b class=\"light\"&gt;&lt;/b&gt;\n"
				+ "&lt;b class=\"box\"&gt;\n" + "&lt;img src=\"snapshot/" + this.formatDate(nowDate)
				+ ".jpg\" height=\"530\" width=\"1024\" onmousewheel=\"return bigimg(this)\" alt=\"\" /&gt;\n"
				+ "&lt;span&gt;滚动鼠标缩放大小,点击X关闭当前图片，返回报表界面.&lt;br /&gt;&lt;i&gt;&lt;/i&gt;&lt;/span&gt;\n"
				+ "&lt;/b&gt;\n" + "&lt;/b&gt;\n" + "&lt;/a&gt;\n" + "&lt;br class=\"clear\" /&gt;\n"
				+ "&lt;a class=\"clickbox\" href=\"#url\"&gt;" + "点击查看大图" + "&lt;b class=\"lightbox\"&gt;"
				+ "&lt;b class=\"light\"&gt;&lt;/b&gt;" + "&lt;b class=\"box\"&gt;&lt;img src=\"snapshot/"
				+ this.formatDate(nowDate)
				+ ".jpg\" height=\"530\" width=\"1024\" onmousewheel=\"return bigimg(this)\" alt=\"\" /&gt;"
				+ "&lt;span&gt;滚动鼠标缩放大小,点击X关闭当前图片，返回报表界面." + "&lt;br /&gt;&lt;i&gt;&lt;/i&gt;&lt;/span&gt;"
				+ "&lt;/b&gt;" + "&lt;/b&gt;" + " &lt;/a&gt;\n&lt;/br&gt;" + "&lt;div id=\"close\"&gt;&lt;/div&gt;\n");
	}

	/**
	 * 执行cmd命令
	 */
	public void executeCmd(String cmd) throws IOException {
		Runtime runtime = Runtime.getRuntime();
		runtime.exec(cmd);
		// System.out.println("cmd:"+ cmd);
	}
}
